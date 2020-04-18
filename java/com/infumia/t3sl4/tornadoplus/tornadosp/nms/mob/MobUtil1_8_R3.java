package com.infumia.t3sl4.tornadoplus.tornadopsp.nms.mob;

import java.util.Iterator;
import java.util.List;

import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.IEntity;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.IItem;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.mob.IMob;
import com.infumia.t3sl4.tornadoplus.tornadoputil.misc.MobUtil;
import com.infumia.t3sl4.tornadoplus.tornadoputil.misc.ReflectionUtil;
import com.infumia.t3sl4.tornadoplus.tornadoputil.misc.StringUtil;
import com.infumia.t3sl4.tornadoplus.tornadopsp.TornadoSpawnersPlus;
import com.infumia.t3sl4.tornadoplus.tornadopsp.nms.IMobUtil;
import net.minecraft.server.v1_8_R3.EntityCreature;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.EntityTypes;
import net.minecraft.server.v1_8_R3.GenericAttributes;
import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import net.minecraft.server.v1_8_R3.NBTTagString;
import net.minecraft.server.v1_8_R3.PathfinderGoalHurtByTarget;
import net.minecraft.server.v1_8_R3.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_8_R3.WorldServer;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftCreature;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class MobUtil1_8_R3 implements IMobUtil {
   public Entity createEntity(EntityType entityType, World world) {
      WorldServer worldServer = ((CraftWorld)world).getHandle();
      EntityLiving entity = (EntityLiving)EntityTypes.createEntityByName(entityType.getName(), worldServer);
      return entity.getBukkitEntity();
   }

   public Entity spawnEntity(Entity entity, Location location) {
      net.minecraft.server.v1_8_R3.Entity craftEntity = ((CraftEntity)entity).getHandle();
      craftEntity.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
      ((CraftLivingEntity)craftEntity.getBukkitEntity()).setRemoveWhenFarAway(false);
      craftEntity.getWorld().addEntity(craftEntity, SpawnReason.CUSTOM);
      return craftEntity.getBukkitEntity();
   }

   public IMob toIMob(LivingEntity entity) {
      NBTTagCompound nbtTagCompound = new NBTTagCompound();
      ((CraftLivingEntity)entity).getHandle().e(nbtTagCompound);
      NBTTagList asIDTag = (NBTTagList)nbtTagCompound.get("Tags");
      if (asIDTag == null) {
         return null;
      } else {
         for(int i = 0; i < asIDTag.size(); ++i) {
            if (asIDTag.getString(i).startsWith("asID:")) {
               return (IMob)TornadoSpawnersPlus.MOBS.get(asIDTag.getString(i).replaceAll("asID:", ""));
            }
         }

         return null;
      }
   }

   public Object convertNBT(IEntity iEntity) {
      NBTTagCompound nbtTagCompound = new NBTTagCompound();
      IMob iMob = (IMob)iEntity;
      NBTTagList asID = new NBTTagList();
      asID.add(new NBTTagString("asID:" + iMob.convertID()));
      nbtTagCompound.set("Tags", asID);
      NBTTagList attrList = new NBTTagList();
      NBTTagCompound healthTag = new NBTTagCompound();
      healthTag.setString("Name", GenericAttributes.maxHealth.getName());
      healthTag.setDouble("Base", (double)(Integer)iMob.pullArgument("health"));
      attrList.add(healthTag);
      NBTTagCompound movementSpeedTag;
      if (!MobUtil.getInstance().passive(iMob.toEntityType())) {
         movementSpeedTag = new NBTTagCompound();
         movementSpeedTag.setString("Name", GenericAttributes.ATTACK_DAMAGE.getName());
         movementSpeedTag.setInt("Base", (Integer)iMob.pullArgument("damage"));
         attrList.add(movementSpeedTag);
      }

      movementSpeedTag = new NBTTagCompound();
      movementSpeedTag.setString("Name", GenericAttributes.MOVEMENT_SPEED.getName());
      movementSpeedTag.setFloat("Base", (Float)iMob.pullArgument("movementSpeed"));
      attrList.add(movementSpeedTag);
      NBTTagCompound followRangeTag = new NBTTagCompound();
      followRangeTag.setString("Name", GenericAttributes.FOLLOW_RANGE.getName());
      followRangeTag.setDouble("Base", (Double)iMob.pullArgument("followRange"));
      attrList.add(followRangeTag);
      NBTTagCompound knockbackResistanceTag = new NBTTagCompound();
      knockbackResistanceTag.setString("Name", GenericAttributes.c.getName());
      knockbackResistanceTag.setDouble("Base", (Double)iMob.pullArgument("knockbackResistance"));
      attrList.add(knockbackResistanceTag);
      String customName = ((String)iMob.pullArgument("customName")).split(":")[0];
      String customNameVisible = ((String)iMob.pullArgument("customName")).split(":")[1];
      customNameVisible = Boolean.parseBoolean(customNameVisible) ? "1" : "0";
      nbtTagCompound.setString("CustomName", StringUtil.getInstance().chatColor(customName));
      nbtTagCompound.setInt("CustomNameVisible", Integer.parseInt(customNameVisible));
      IItem[] equipment = (IItem[])iMob.pullArgument("equipment");
      NBTTagList equipList = new NBTTagList();
      IItem[] var17 = equipment;
      int var16 = equipment.length;

      for(int var15 = 0; var15 < var16; ++var15) {
         IItem items = var17[var15];
         if (items != null) {
            NBTTagCompound itemStackTag = new NBTTagCompound();
            ItemStack itemStack = CraftItemStack.asNMSCopy(items.create());
            itemStack.save(itemStackTag);
            equipList.add(itemStackTag);
         } else {
            equipList.add(new NBTTagCompound());
         }
      }

      nbtTagCompound.set("ArmorItems", equipList);
      nbtTagCompound.set("Attributes", attrList);
      return nbtTagCompound;
   }

   public Creature addTarget(List<EntityType> targets, Creature creature) {
      EntityCreature entityCreature = ((CraftCreature)creature).getHandle();
      List listB = (List) ReflectionUtil.getInstance().getField((Object)entityCreature.targetSelector, "b");
      listB.clear();
      List listC = (List)ReflectionUtil.getInstance().getField((Object)entityCreature.targetSelector, "c");
      listC.clear();
      entityCreature.targetSelector.a(1, new PathfinderGoalHurtByTarget(entityCreature, true, new Class[0]));
      Iterator var7 = targets.iterator();

      while(var7.hasNext()) {
         EntityType entityType = (EntityType)var7.next();
         Class<?> entityCreatureClass = EntityTypes.createEntityByName(entityType.getName(), ((CraftWorld)creature.getWorld()).getHandle()).getClass();
         entityCreature.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(entityCreature, entityCreatureClass, true));
      }

      return (Creature)entityCreature.getBukkitEntity();
   }
}

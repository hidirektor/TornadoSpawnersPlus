package me.t3sl4.tornadoplus.entity.mob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import me.t3sl4.tornadoplus.entity.item.IItem;
import me.t3sl4.tornadoplus.util.configuration.ConfigurationFile;
import me.t3sl4.tornadoplus.TornadoSpawnersPlus;
import me.t3sl4.tornadoplus.entity.IEntityArgument;
import me.t3sl4.tornadoplus.entity.mob.argument.ArmorArgument;
import me.t3sl4.tornadoplus.entity.mob.argument.CustomNameArgument;
import me.t3sl4.tornadoplus.entity.mob.argument.DamageArgument;
import me.t3sl4.tornadoplus.entity.mob.argument.DropArgument;
import me.t3sl4.tornadoplus.entity.mob.argument.EquipmentArgument;
import me.t3sl4.tornadoplus.entity.mob.argument.FollowRangeArgument;
import me.t3sl4.tornadoplus.entity.mob.argument.HealthArgument;
import me.t3sl4.tornadoplus.entity.mob.argument.KnockbackResistanceArgument;
import me.t3sl4.tornadoplus.entity.mob.argument.MobNameArgument;
import me.t3sl4.tornadoplus.entity.mob.argument.MovementSpeedArgument;
import me.t3sl4.tornadoplus.entity.mob.argument.TargetArgument;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

public class BasicMob implements IMob {
   private final ConfigurationFile file;
   private final Map<String, IMobArgument> arguments;

   public BasicMob(ConfigurationFile file) {
      this.arguments = new HashMap();
      this.file = file;
      TornadoSpawnersPlus.MOBS.put(this.convertID(), this);
   }

   public BasicMob(String id) {
      this(TornadoSpawnersPlus.getInstance().getUtil().addFile(id, "data/mobs"));
      this.file.set("mob-name", "ZOMBIE");
      this.file.set("health", 20);
      this.file.set("damage", 2);
      this.file.set("armor", 2);
      this.file.set("movement-speed", 0.23000000417232513D);
      this.file.set("follow-range", 10);
      this.file.set("knockback-resistance", 0);
      this.file.set("custom-name", id + " Zombie");
      this.file.set("custom-name-visible", false);
      this.file.set("target-list", new ArrayList());
      this.file.set("equipments.head", "");
      this.file.set("equipments.chestplate", "");
      this.file.set("equipments.legging", "");
      this.file.set("equipments.boots", "");
      this.file.set("drops", new ArrayList());
      this.register();
   }

   public Object pullArgument(String key) {
      return this.arguments.get(key) != null ? ((IMobArgument)this.arguments.get(key)).convert() : null;
   }

   public void pushArgument(String key, IEntityArgument argument) {
      this.arguments.put(key, (IMobArgument)argument);
      this.save();
   }

   public LivingEntity create(World world) {
      return (LivingEntity)TornadoSpawnersPlus.getInstance().getIMobUtil().createEntity(this.toEntityType(), world);
   }

   public Entity spawn(Location location) {
      return TornadoSpawnersPlus.getInstance().getIMobUtil().spawnEntity(this.create(location.getWorld()), location);
   }

   public void register() {
      this.arguments.clear();
      this.arguments.put("mobName", new MobNameArgument(this.file.getString("mob-name")));
      this.arguments.put("health", new HealthArgument(this.file.getInt("health")));
      this.arguments.put("damage", new DamageArgument(this.file.getInt("damage")));
      this.arguments.put("armor", new ArmorArgument(this.file.getDouble("armor")));
      this.arguments.put("movementSpeed", new MovementSpeedArgument(this.file.getFloat("movement-speed")));
      this.arguments.put("followRange", new FollowRangeArgument((double)this.file.getFloat("follow-range")));
      this.arguments.put("knockbackResistance", new KnockbackResistanceArgument(this.file.getDouble("knockback-resistance")));
      this.arguments.put("customName", new CustomNameArgument(this.file.getString("custom-name"), (Boolean)this.file.get("custom-name-visible")));
      this.arguments.put("target", new TargetArgument(this.file.getStringList("target-list")));
      this.arguments.put("equipment", new EquipmentArgument(this.file.getString("equipments.head"), this.file.getString("equipments.chestplate"), this.file.getString("equipments.legging"), this.file.getString("equipments.boots")));
      this.arguments.put("drops", new DropArgument(this.file.getStringList("drops")));
   }

   public void save() {
      this.file.set("mob-name", ((EntityType)this.pullArgument("mobName")).name());
      this.file.set("health", this.pullArgument("health"));
      this.file.set("damage", this.pullArgument("damage"));
      this.file.set("armor", this.pullArgument("armor"));
      this.file.set("movement-speed", this.pullArgument("movementSpeed"));
      this.file.set("follow-range", this.pullArgument("followRange"));
      this.file.set("knockback-resistance", this.pullArgument("knockbackResistance"));
      String customName = ((String)this.pullArgument("customName")).split(":")[0];
      Boolean customNameVisible = Boolean.parseBoolean(((String)this.pullArgument("customName")).split(":")[1]);
      this.file.set("custom-name", customName);
      this.file.set("custom-name-visible", customNameVisible);
      List<EntityType> typeList = (List)this.pullArgument("target");
      List<String> typS = new ArrayList();
      Iterator var5 = typeList.iterator();

      while(var5.hasNext()) {
         EntityType typeListS = (EntityType)var5.next();
         typS.add(typeListS.name());
      }

      this.file.set("target-list", typS);
      IItem[] equipments = (IItem[])this.pullArgument("equipment");
      if (equipments[0] != null) {
         this.file.set("equipment.head", equipments[0].convertID());
      }

      if (equipments[1] != null) {
         this.file.set("equipment.chestplate", equipments[1].convertID());
      }

      if (equipments[2] != null) {
         this.file.set("equipment.legging", equipments[2].convertID());
      }

      if (equipments[3] != null) {
         this.file.set("equipment.boots", equipments[3].convertID());
      }

      Map<IItem, Integer> drops = (Map)this.pullArgument("drops");
      List<String> dropList = new ArrayList();
      Iterator var8 = drops.keySet().iterator();

      while(var8.hasNext()) {
         IItem item = (IItem)var8.next();
         dropList.add(item.convertID() + ":" + drops.get(item));
      }

      this.file.set("drops", dropList);
   }

   public void remove() {
      TornadoSpawnersPlus.MOBS.remove(this.convertID());
      this.file.delete();
   }

   public EntityType toEntityType() {
      return EntityType.valueOf(this.file.getString("mob-name"));
   }

   public ItemStack create() {
      return null;
   }

   public Object convertNBT() {
      return TornadoSpawnersPlus.getInstance().getIMobUtil().convertNBT(this);
   }

   public String convertID() {
      return this.file.name();
   }
}

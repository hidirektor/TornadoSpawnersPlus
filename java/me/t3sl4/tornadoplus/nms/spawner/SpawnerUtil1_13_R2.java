package me.t3sl4.tornadoplus.nms.spawner;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import me.t3sl4.tornadoplus.entity.IEntity;
import me.t3sl4.tornadoplus.entity.item.IItem;
import me.t3sl4.tornadoplus.entity.mob.IMob;
import me.t3sl4.tornadoplus.entity.spawner.ISpawner;
import me.t3sl4.tornadoplus.util.misc.XMaterial;
import me.t3sl4.tornadoplus.TornadoSpawnersPlus;
import me.t3sl4.tornadoplus.nms.ISpawnerUtil;
import net.minecraft.server.v1_13_R2.BlockPosition;
import net.minecraft.server.v1_13_R2.NBTBase;
import net.minecraft.server.v1_13_R2.NBTTagCompound;
import net.minecraft.server.v1_13_R2.NBTTagList;
import net.minecraft.server.v1_13_R2.TileEntityMobSpawner;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_13_R2.CraftWorld;

public class SpawnerUtil1_13_R2 implements ISpawnerUtil {
   public void placeSpawner(ISpawner iSpawner, Location location) {
      TileEntityMobSpawner spawner;
      NBTTagCompound nbtTileEntity;
      label32: {
         location.getBlock().setType(XMaterial.SPAWNER.parseMaterial());
         BlockPosition blockPos = new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ());
         spawner = (TileEntityMobSpawner)((CraftWorld)location.getWorld()).getHandle().getTileEntity(blockPos);
         nbtTileEntity = new NBTTagCompound();
         spawner.save(nbtTileEntity);
         String iEntityID = (String)iSpawner.pullArgument("spawn");
         String s = (String)iSpawner.pullArgument("spawnType");
         switch(s.hashCode()) {
         case 108288:
            if (!s.equals("mob")) {
               break label32;
            }
            break;
         case 3242771:
            if (s.equals("item")) {
               IItem IItem = (IItem)TornadoSpawnersPlus.ITEMS.get(iEntityID);
               if (IItem != null) {
                  nbtTileEntity.set("SpawnData", (NBTBase)IItem.convertNBT());
               }
               break;
            }
         default:
            break label32;
         }

         IMob iMob = (IMob)TornadoSpawnersPlus.MOBS.get(iEntityID);
         if (iMob != null) {
            nbtTileEntity.set("SpawnData", (NBTBase)iMob.convertNBT());
         }
      }

      Map<String, Integer> tagMap = (Map)iSpawner.convertNBT();
      Iterator var11 = tagMap.keySet().iterator();

      while(var11.hasNext()) {
         String keys = (String)var11.next();
         nbtTileEntity.setInt(keys, (Integer)tagMap.get(keys));
      }

      NBTTagCompound spawnData = nbtTileEntity.getCompound("SpawnData");
      spawnData.setString("asID", iSpawner.convertID());
      nbtTileEntity.set("SpawnData", spawnData);
      nbtTileEntity.remove("SpawnPotentials");
      spawner.load(nbtTileEntity);
   }

   public ISpawner toISpawner(Location location) {
      NBTTagCompound spawDataTag = this.spawnData(location);
      if (spawDataTag == null) {
         return null;
      } else {
         String asID = spawDataTag.getString("asID");
         return asID == null ? null : (ISpawner)TornadoSpawnersPlus.SPAWNERS.get(asID);
      }
   }

   public IEntity toIEntity(Location location) {
      NBTTagCompound asIDTag = this.spawnData(location);
      if (asIDTag == null) {
         return null;
      } else if (asIDTag.get("Item") != null) {
         NBTTagCompound asIDItemTag = (NBTTagCompound)asIDTag.get("Item");
         NBTTagCompound asIDItemIdTag = (NBTTagCompound)asIDItemTag.get("tag");
         if (asIDItemIdTag == null) {
            return null;
         } else {
            String asID = asIDItemIdTag.getString("asID");
            return asID == null ? null : (IEntity)TornadoSpawnersPlus.ITEMS.get(asID);
         }
      } else if (asIDTag.get("Tags") == null) {
         return null;
      } else {
         NBTTagList asIDItemTag2 = (NBTTagList)asIDTag.get("Tags");

         for(int i = 0; i < asIDItemTag2.size(); ++i) {
            if (asIDItemTag2.getString(i).startsWith("asID:")) {
               return (IEntity)TornadoSpawnersPlus.MOBS.get(asIDItemTag2.getString(i).replaceAll("asID:", ""));
            }
         }

         return null;
      }
   }

   private NBTTagCompound spawnData(Location location) {
      BlockPosition blockPos = new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ());
      TileEntityMobSpawner spawner = (TileEntityMobSpawner)((CraftWorld)location.getWorld()).getHandle().getTileEntity(blockPos);
      return spawner == null ? null : (NBTTagCompound)spawner.aa_().get("SpawnData");
   }

   public Object convertNBT(IEntity iEntity) {
      Map<String, Integer> tagList = new HashMap();
      tagList.put("SpawnCount", (Integer)iEntity.pullArgument("spawnCount"));
      tagList.put("Delay", (Integer)iEntity.pullArgument("delay"));
      tagList.put("MaxSpawnDelay", (Integer)iEntity.pullArgument("delay") * 20);
      tagList.put("MinSpawnDelay", (Integer)iEntity.pullArgument("delay") * 20);
      tagList.put("SpawnRange", (Integer)iEntity.pullArgument("range"));
      tagList.put("MaxNearbyEntities", (Integer)iEntity.pullArgument("maxSpawnEntity"));
      tagList.put("RequiredPlayerRange", (Integer)iEntity.pullArgument("requiredPlayerRange"));
      return tagList;
   }
}
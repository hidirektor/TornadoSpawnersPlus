package com.infumia.t3sl4.tornadoplus.tornadopsp.nms.spawner;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.IEntity;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.IItem;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.mob.IMob;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner.ISpawner;
import com.infumia.t3sl4.tornadoplus.tornadoputil.misc.XMaterial;
import com.infumia.t3sl4.tornadoplus.tornadopsp.TornadoSpawnersPlus;
import com.infumia.t3sl4.tornadoplus.tornadopsp.nms.ISpawnerUtil;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.NBTBase;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import net.minecraft.server.v1_8_R3.TileEntityMobSpawner;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;

public class SpawnerUtil1_8_R3 implements ISpawnerUtil {
   public void placeSpawner(ISpawner iSpawner, Location location) {
      TileEntityMobSpawner spawner;
      NBTTagCompound nbtTileEntity;
      label32: {
         location.getBlock().setType(XMaterial.SPAWNER.parseMaterial());
         BlockPosition blockPos = new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ());
         spawner = (TileEntityMobSpawner)((CraftWorld)location.getWorld()).getHandle().getTileEntity(blockPos);
         nbtTileEntity = new NBTTagCompound();
         spawner.b(nbtTileEntity);
         String s = (String)iSpawner.pullArgument("spawnType");
         String iItemID;
         switch(s.hashCode()) {
         case 108288:
            if (!s.equals("mob")) {
               break label32;
            }
            break;
         case 3242771:
            if (s.equals("item")) {
               iItemID = (String)iSpawner.pullArgument("spawn");
               IItem IItem = (IItem)TornadoSpawnersPlus.ITEMS.get(iItemID);
               if (IItem != null) {
                  nbtTileEntity.set("SpawnData", (NBTBase)IItem.convertNBT());
                  nbtTileEntity.setString("EntityId", "Item");
               }
               break;
            }
         default:
            break label32;
         }

         iItemID = (String)iSpawner.pullArgument("spawn");
         IMob iMob = (IMob)TornadoSpawnersPlus.MOBS.get(iItemID);
         if (iMob != null) {
            nbtTileEntity.set("SpawnData", (NBTBase)iMob.convertNBT());
            nbtTileEntity.setString("EntityId", iMob.toEntityType().getName());
         }
      }

      Map<String, Integer> tagMap = (Map)iSpawner.convertNBT();
      Iterator var10 = tagMap.keySet().iterator();

      while(var10.hasNext()) {
         String keys = (String)var10.next();
         nbtTileEntity.setInt(keys, (Integer)tagMap.get(keys));
      }

      NBTTagCompound spawnData = nbtTileEntity.getCompound("SpawnData");
      spawnData.setString("asID", iSpawner.convertID());
      nbtTileEntity.set("SpawnData", spawnData);
      nbtTileEntity.remove("SpawnPotentials");
      spawner.a(nbtTileEntity);
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
               return (IEntity)TornadoSpawnersPlus.ITEMS.get(asIDItemTag2.getString(i).replaceAll("asID:", ""));
            }
         }

         return null;
      }
   }

   private NBTTagCompound spawnData(Location location) {
      BlockPosition blockPos = new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ());
      TileEntityMobSpawner spawner = (TileEntityMobSpawner)((CraftWorld)location.getWorld()).getHandle().getTileEntity(blockPos);
      if (spawner == null) {
         return null;
      } else {
         NBTTagCompound nbtTagCompound = new NBTTagCompound();
         spawner.b(nbtTagCompound);
         nbtTagCompound.remove("SpawnPotentials");
         return nbtTagCompound.getCompound("SpawnData");
      }
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

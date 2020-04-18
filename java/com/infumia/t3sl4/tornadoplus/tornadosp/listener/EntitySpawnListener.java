package com.infumia.t3sl4.tornadoplus.tornadopsp.listener;

import java.util.List;
import com.infumia.t3sl4.tornadoplus.tornadopsp.TornadoSpawnersPlus;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.IItem;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.mob.IMob;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner.ISpawner;
import com.infumia.t3sl4.tornadoplus.tornadopsp.events.TSPEntitySpawnEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.SpawnerSpawnEvent;

public class EntitySpawnListener implements Listener {
   @EventHandler
   public void entitySpawnEvent(SpawnerSpawnEvent event) {
      if (!TornadoSpawnersPlus.MOBS.isEmpty() && !TornadoSpawnersPlus.ITEMS.isEmpty()) {
         ISpawner iSpawner = TornadoSpawnersPlus.getInstance().getISpawnerUtil().toISpawner(event.getSpawner().getLocation());
         if (iSpawner != null) {
            Entity entity = event.getEntity();
            TSPEntitySpawnEvent asEntitySpawnEvent;
            if (entity instanceof Item) {
               IItem iItem = (IItem)TornadoSpawnersPlus.getInstance().getISpawnerUtil().toIEntity(event.getSpawner().getLocation());
               if (iItem == null) {
                  return;
               }

               asEntitySpawnEvent = new TSPEntitySpawnEvent(entity, iItem);
               Bukkit.getPluginManager().callEvent(asEntitySpawnEvent);
            } else if (entity instanceof Creature) {
               IMob iMob = (IMob)TornadoSpawnersPlus.getInstance().getISpawnerUtil().toIEntity(event.getSpawner().getLocation());
               if (iMob == null) {
                  return;
               }

               asEntitySpawnEvent = new TSPEntitySpawnEvent(entity, iMob);
               Bukkit.getPluginManager().callEvent(asEntitySpawnEvent);
               if (asEntitySpawnEvent.isCancelled()) {
                  return;
               }

               TornadoSpawnersPlus.getInstance().getIMobUtil().addTarget((List)asEntitySpawnEvent.getiEntity().pullArgument("target"), (Creature)asEntitySpawnEvent.getEntity());
            }
         }
      }

   }
}

package com.infumia.t3sl4.tornadoplus.tornadopsp.listener;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import com.infumia.t3sl4.tornadoplus.tornadopsp.TornadoSpawnersPlus;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.IItem;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.mob.IMob;
import com.infumia.t3sl4.tornadoplus.tornadopsp.events.TSPMobDeathEvent;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeathListener implements Listener {
   private final Random random = new Random();

   @EventHandler
   public void entityDeathListener(EntityDeathEvent event) {
      if (!TornadoSpawnersPlus.MOBS.isEmpty()) {
         LivingEntity livingEntity = event.getEntity();
         IMob iMob = TornadoSpawnersPlus.getInstance().getIMobUtil().toIMob(livingEntity);
         if (iMob != null) {
            TSPMobDeathEvent asMobDeathEvent = new TSPMobDeathEvent(livingEntity, iMob);
            Bukkit.getPluginManager().callEvent(asMobDeathEvent);
            if (!asMobDeathEvent.isCancelled()) {
               event.getDrops().clear();
               Map<IItem, Integer> dropMap = (Map)asMobDeathEvent.getIMob().pullArgument("drops");
               if (dropMap != null) {
                  World world = asMobDeathEvent.getEntity().getWorld();
                  Iterator var7 = dropMap.keySet().iterator();

                  while(var7.hasNext()) {
                     IItem iItem = (IItem)var7.next();
                     if ((Integer)dropMap.get(iItem) == 100) {
                        world.dropItemNaturally(asMobDeathEvent.getEntity().getLocation(), iItem.create());
                     } else {
                        int chance = this.random.nextInt(100);
                        if (chance < (Integer)dropMap.get(iItem)) {
                           world.dropItemNaturally(asMobDeathEvent.getEntity().getLocation(), iItem.create());
                        }
                     }
                  }
               }
            }
         }
      }

   }
}

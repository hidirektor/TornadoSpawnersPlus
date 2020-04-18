package com.infumia.t3sl4.tornadoplus.tornadopsp.listener;

import com.infumia.t3sl4.tornadoplus.tornadoputil.misc.PluginUtil;
import com.infumia.t3sl4.tornadoplus.tornadoputil.misc.XMaterial;
import com.infumia.t3sl4.tornadoplus.tornadopsp.TornadoSpawnersPlus;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner.ISpawner;
import com.infumia.t3sl4.tornadoplus.tornadopsp.events.TSPSpawnerPlaceEvent;
import com.infumia.t3sl4.tornadoplus.tornadopsp.misc.VariableName;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class BlockPlaceListener implements Listener {
   @EventHandler
   public void blockPlaceListener(BlockPlaceEvent event) {
      Player player = event.getPlayer();
      Block block = event.getBlock();
      ItemStack itemStack = event.getItemInHand();
      if (event.canBuild() && !event.isCancelled() && block.getType() == XMaterial.SPAWNER.parseMaterial()) {
         ISpawner iSpawner = TornadoSpawnersPlus.getInstance().getIItemUtil().toISpawner(itemStack);
         if (iSpawner != null) {
            TSPSpawnerPlaceEvent asSpawnerPlaceEvent = new TSPSpawnerPlaceEvent(player, iSpawner, block);
            Bukkit.getPluginManager().callEvent(asSpawnerPlaceEvent);
            if (!asSpawnerPlaceEvent.isCancelled()) {
               if (!player.hasPermission("aspawner.placespawner")) {
                  PluginUtil.getInstance().sendMessage(VariableName.pullMessage("error.permission"), player);
                  asSpawnerPlaceEvent.setCancelled(true);
               } else {
                  asSpawnerPlaceEvent.getISpawner().placeSpawner(block.getLocation());
               }
            }
         }
      }

   }
}

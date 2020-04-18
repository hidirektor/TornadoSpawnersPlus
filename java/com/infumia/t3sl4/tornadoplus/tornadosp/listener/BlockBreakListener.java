package com.infumia.t3sl4.tornadoplus.tornadopsp.listener;

import com.infumia.t3sl4.tornadoplus.tornadoputil.misc.PluginUtil;
import com.infumia.t3sl4.tornadoplus.tornadoputil.misc.XMaterial;
import com.infumia.t3sl4.tornadoplus.tornadopsp.TornadoSpawnersPlus;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner.ISpawner;
import com.infumia.t3sl4.tornadoplus.tornadopsp.events.TSPSpawnerBreakEvent;
import com.infumia.t3sl4.tornadoplus.tornadopsp.misc.VariableName;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {
   @EventHandler
   public void blockBreakListener(BlockBreakEvent event) {
      Player player = event.getPlayer();
      Block block = event.getBlock();
      Location blockLocation = block.getLocation();
      if (!event.isCancelled() && block.getType() == XMaterial.SPAWNER.parseMaterial()) {
         ISpawner asSpawner = TornadoSpawnersPlus.getInstance().getISpawnerUtil().toISpawner(blockLocation);
         if (asSpawner != null) {
            TSPSpawnerBreakEvent asSpawnerBreakEvent = new TSPSpawnerBreakEvent(player, asSpawner, block, event.getExpToDrop());
            Bukkit.getPluginManager().callEvent(asSpawnerBreakEvent);
            event.setExpToDrop(asSpawnerBreakEvent.getExpDrop());
            if (asSpawnerBreakEvent.isCancelled()) {
               event.setCancelled(true);
            } else if (!player.hasPermission("aspawner.breakspawner")) {
               PluginUtil.getInstance().sendMessage(VariableName.pullMessage("error.permission"), player);
               asSpawnerBreakEvent.setCancelled(true);
            } else if (!asSpawnerBreakEvent.getISpawner().breakSpawner(block.getLocation(), player)) {
               asSpawnerBreakEvent.setCancelled(true);
            }
         }
      }

   }
}

package me.t3sl4.tornadoplus.listener;

import me.t3sl4.tornadoplus.util.misc.PluginUtil;
import me.t3sl4.tornadoplus.util.misc.XMaterial;
import me.t3sl4.tornadoplus.TornadoSpawnersPlus;
import me.t3sl4.tornadoplus.entity.spawner.ISpawner;
import me.t3sl4.tornadoplus.events.TSPSpawnerPlaceEvent;
import me.t3sl4.tornadoplus.misc.VariableName;
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

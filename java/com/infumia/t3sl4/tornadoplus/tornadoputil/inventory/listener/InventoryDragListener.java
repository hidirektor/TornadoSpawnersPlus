package com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.listener;

import com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.Page;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.PlayerInventory;

public final class InventoryDragListener implements Listener {
   @EventHandler
   public void listener(InventoryDragEvent event) {
      if (event.getInventory().getHolder() instanceof Page && !(event.getInventory() instanceof PlayerInventory)) {
         ((Page)event.getInventory().getHolder()).accept(event);
      }

   }
}

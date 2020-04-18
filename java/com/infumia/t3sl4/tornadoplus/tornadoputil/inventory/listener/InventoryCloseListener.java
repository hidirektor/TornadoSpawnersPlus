package com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.listener;

import com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.Page;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public final class InventoryCloseListener implements Listener {
   @EventHandler
   public void closeListener(InventoryCloseEvent event) {
      if (event.getInventory().getHolder() instanceof Page) {
         ((Page)event.getInventory().getHolder()).handleClose(event);
      }

   }
}

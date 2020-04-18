package com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.requirement;

import com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.Requirement;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;

public class DragReq implements Requirement {
   public boolean control(InventoryInteractEvent event) {
      return event instanceof InventoryDragEvent;
   }
}

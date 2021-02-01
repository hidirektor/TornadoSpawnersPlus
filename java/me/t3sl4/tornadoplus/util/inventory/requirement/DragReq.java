package me.t3sl4.tornadoplus.util.inventory.requirement;

import me.t3sl4.tornadoplus.util.inventory.Requirement;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;

public class DragReq implements Requirement {
   public boolean control(InventoryInteractEvent event) {
      return event instanceof InventoryDragEvent;
   }
}

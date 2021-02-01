package me.t3sl4.tornadoplus.util.inventory;

import org.bukkit.event.inventory.InventoryInteractEvent;

public interface Target<o> {
   void handle(InventoryInteractEvent var1);
}

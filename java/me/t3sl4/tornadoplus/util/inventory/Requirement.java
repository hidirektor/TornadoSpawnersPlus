package me.t3sl4.tornadoplus.util.inventory;

import org.bukkit.event.inventory.InventoryInteractEvent;

public interface Requirement {
   boolean control(InventoryInteractEvent var1);
}

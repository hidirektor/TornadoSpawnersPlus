package me.t3sl4.tornadoplus.util.inventory;

import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public interface Element {
   void displayOn(Inventory var1, int var2, int var3);

   void accept(InventoryInteractEvent var1);

   boolean is(Element var1);

   boolean is(ItemStack var1);
}

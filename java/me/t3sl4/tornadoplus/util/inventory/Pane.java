package me.t3sl4.tornadoplus.util.inventory;

import me.t3sl4.tornadoplus.util.observer.Target;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public interface Pane {
   void fill(Element var1);

   void fill(Element... var1);

   void clear();

   boolean add(Element var1);

   Element[] add(Element... var1);

   void insert(Element var1, int var2, int var3, boolean var4) throws IllegalArgumentException;

   void replaceAll(Element... var1);

   void remove(int var1, int var2) throws IllegalArgumentException;

   void subscribe(Target<Object> var1);

   boolean contains(ItemStack var1);

   void accept(InventoryInteractEvent var1);

   void displayOn(Inventory var1);
}

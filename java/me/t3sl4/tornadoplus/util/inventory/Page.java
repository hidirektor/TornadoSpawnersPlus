package me.t3sl4.tornadoplus.util.inventory;

import me.t3sl4.tornadoplus.util.observer.Target;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.InventoryHolder;

public interface Page extends InventoryHolder, Target<Object> {
   void add(Pane var1, int var2);

   void remove(int var1);

   void rearrange(int var1, int var2);

   void defineHolder(Page var1);

   void showTo(Player var1);

   void handleClose(InventoryCloseEvent var1);

   void accept(InventoryInteractEvent var1);
}

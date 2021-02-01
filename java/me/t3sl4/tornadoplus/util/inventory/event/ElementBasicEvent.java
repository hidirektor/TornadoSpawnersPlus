package me.t3sl4.tornadoplus.util.inventory.event;

import java.util.Objects;
import me.t3sl4.tornadoplus.util.inventory.ElementEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryInteractEvent;

public class ElementBasicEvent implements ElementEvent {
   private final InventoryInteractEvent event;

   public ElementBasicEvent(InventoryInteractEvent event) {
      this.event = (InventoryInteractEvent)Objects.requireNonNull(event);
   }

   public Player player() {
      return (Player)this.event.getWhoClicked();
   }

   public void cancel() {
      this.event.setCancelled(true);
   }

   public void closeView() {
      Bukkit.getScheduler().runTask(this.event.getHandlers().getRegisteredListeners()[0].getPlugin(), () -> {
         this.event.getWhoClicked().closeInventory();
      });
   }
}

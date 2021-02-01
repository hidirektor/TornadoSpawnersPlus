package me.t3sl4.tornadoplus.util.inventory.event;

import java.util.Objects;

import me.t3sl4.tornadoplus.util.inventory.ElementEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ElementClickEvent implements ElementEvent {
   private final InventoryClickEvent event;
   private final ElementBasicEvent elementBasicEvent;

   public ElementClickEvent(InventoryClickEvent event) {
      this.event = (InventoryClickEvent)Objects.requireNonNull(event);
      this.elementBasicEvent = new ElementBasicEvent(event);
   }

   public ItemStack clicked() {
      return this.event.getCurrentItem();
   }

   public int clickedX() {
      return this.event.getSlot() % 9;
   }

   public int clickedY() {
      return this.event.getSlot() / 9;
   }

   public Player player() {
      return this.elementBasicEvent.player();
   }

   public void cancel() {
      this.elementBasicEvent.cancel();
   }

   public void closeView() {
      this.elementBasicEvent.closeView();
   }
}

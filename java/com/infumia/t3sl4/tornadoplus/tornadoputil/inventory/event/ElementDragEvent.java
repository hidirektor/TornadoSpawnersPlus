package com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.event;

import java.util.Map;
import java.util.Objects;

import com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.ElementEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;

public class ElementDragEvent implements ElementEvent {
   private final InventoryDragEvent event;
   private final ElementBasicEvent elementBasicEvent;

   public ElementDragEvent(InventoryDragEvent event) {
      this.event = (InventoryDragEvent)Objects.requireNonNull(event);
      this.elementBasicEvent = new ElementBasicEvent(event);
   }

   public ItemStack oldCursor() {
      return this.event.getOldCursor();
   }

   public Map<Integer, ItemStack> items() {
      return this.event.getNewItems();
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

package com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.requirement;

import java.util.Objects;

import com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.Requirement;
import com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.Element;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;

public class AddedElementReq implements Requirement {
   private final Element element;

   public AddedElementReq(Element element) {
      this.element = (Element)Objects.requireNonNull(element);
   }

   public boolean control(InventoryInteractEvent event) {
      if (event instanceof InventoryDragEvent)
         return ((InventoryDragEvent)event).getNewItems().values().stream().anyMatch(this.element::is);
      return false;
   }
}

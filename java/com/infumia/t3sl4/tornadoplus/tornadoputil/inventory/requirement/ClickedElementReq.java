package com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.requirement;

import java.util.Objects;
import com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.Element;
import com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.Requirement;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;

public class ClickedElementReq implements Requirement {
   private final Element element;

   public ClickedElementReq(Element element) {
      this.element = (Element)Objects.requireNonNull(element);
   }

   public boolean control(InventoryInteractEvent event) {
      return event instanceof InventoryClickEvent ? this.element.is(((InventoryClickEvent)event).getCurrentItem()) : false;
   }
}

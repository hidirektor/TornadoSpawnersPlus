package com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.requirement;

import java.util.Objects;
import com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.Requirement;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;

public class ClickTypeReq implements Requirement {
   private final ClickType clickType;

   public ClickTypeReq(ClickType clickType) {
      this.clickType = (ClickType)Objects.requireNonNull(clickType);
   }

   public boolean control(InventoryInteractEvent event) {
      if (event instanceof InventoryClickEvent) {
         return ((InventoryClickEvent)event).getClick() == this.clickType;
      } else {
         return false;
      }
   }
}

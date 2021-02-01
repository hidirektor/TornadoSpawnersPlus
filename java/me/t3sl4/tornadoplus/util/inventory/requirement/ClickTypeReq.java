package me.t3sl4.tornadoplus.util.inventory.requirement;

import java.util.Objects;
import me.t3sl4.tornadoplus.util.inventory.Requirement;
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

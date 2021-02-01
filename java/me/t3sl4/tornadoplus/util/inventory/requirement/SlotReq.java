package me.t3sl4.tornadoplus.util.inventory.requirement;

import java.util.Objects;
import me.t3sl4.tornadoplus.util.inventory.Requirement;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;

public class SlotReq implements Requirement {
   private final int slot;

   public SlotReq(int slot) {
      this.slot = (Integer)Objects.requireNonNull(slot);
   }

   public boolean control(InventoryInteractEvent event) {
      if (event instanceof InventoryClickEvent) {
         return ((InventoryClickEvent)event).getSlot() == this.slot;
      } else {
         return ((InventoryDragEvent)event).getInventorySlots().contains(this.slot);
      }
   }
}

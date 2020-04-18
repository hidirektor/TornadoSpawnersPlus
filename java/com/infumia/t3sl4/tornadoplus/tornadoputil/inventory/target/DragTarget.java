package com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.target;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;
import com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.Requirement;
import com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.Target;
import com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.event.ElementDragEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;

public class DragTarget implements Target {
   private final Consumer<ElementDragEvent> handler;
   private final Requirement[] requirements;

   public DragTarget(Consumer<ElementDragEvent> handler, Requirement... requirements) {
      this.handler = (Consumer)Objects.requireNonNull(handler);
      this.requirements = (Requirement[])Objects.requireNonNull(requirements);
   }

   public void handle(InventoryInteractEvent event) {
      if (event instanceof InventoryDragEvent && Arrays.stream(this.requirements).allMatch((requirement) -> {
         return requirement.control(event);
      })) {
         this.handler.accept(new ElementDragEvent((InventoryDragEvent)event));
      }

   }
}

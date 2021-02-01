package me.t3sl4.tornadoplus.util.inventory.target;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;
import me.t3sl4.tornadoplus.util.inventory.Requirement;
import me.t3sl4.tornadoplus.util.inventory.Target;
import me.t3sl4.tornadoplus.util.inventory.event.ElementClickEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;

public class ClickTarget implements Target {
   private final Consumer<ElementClickEvent> handler;
   private final Requirement[] requirements;

   public ClickTarget(Consumer<ElementClickEvent> handler, Requirement... requirements) {
      this.handler = (Consumer)Objects.requireNonNull(handler);
      this.requirements = (Requirement[])Objects.requireNonNull(requirements);
   }

   public void handle(InventoryInteractEvent event) {
      if (event instanceof InventoryClickEvent && Arrays.stream(this.requirements).allMatch((requirement) -> {
         return requirement.control(event);
      })) {
         this.handler.accept(new ElementClickEvent((InventoryClickEvent)event));
      }

   }
}

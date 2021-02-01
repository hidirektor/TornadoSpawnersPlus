package me.t3sl4.tornadoplus.util.inventory.target;

import java.util.Objects;
import java.util.function.Consumer;
import me.t3sl4.tornadoplus.util.inventory.Requirement;
import me.t3sl4.tornadoplus.util.inventory.Target;
import me.t3sl4.tornadoplus.util.inventory.event.ElementBasicEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;

public class BasicTarget implements Target {
   private final Consumer<ElementBasicEvent> handler;
   private final Requirement[] requirements;

   public BasicTarget(Consumer<ElementBasicEvent> handler, Requirement... requirements) {
      this.handler = (Consumer)Objects.requireNonNull(handler);
      this.requirements = (Requirement[])Objects.requireNonNull(requirements);
   }

   public void handle(InventoryInteractEvent event) {
      Requirement[] var5;
      int var4 = (var5 = this.requirements).length;

      for(int var3 = 0; var3 < var4; ++var3) {
         Requirement requirement = var5[var3];
         if (!requirement.control(event)) {
            return;
         }
      }

      this.handler.accept(new ElementBasicEvent(event));
   }
}
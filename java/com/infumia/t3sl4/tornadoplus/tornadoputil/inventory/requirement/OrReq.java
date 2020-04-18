package com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.requirement;

import java.util.Objects;
import com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.Requirement;
import org.bukkit.event.inventory.InventoryInteractEvent;

public class OrReq implements Requirement {
   private final Requirement[] requirements;

   public OrReq(Requirement... requirements) {
      this.requirements = (Requirement[])Objects.requireNonNull(requirements);
   }

   public boolean control(InventoryInteractEvent event) {
      Requirement[] var5;
      int var4 = (var5 = this.requirements).length;

      for(int var3 = 0; var3 < var4; ++var3) {
         Requirement requirement = var5[var3];
         if (requirement.control(event)) {
            return true;
         }
      }

      return false;
   }
}

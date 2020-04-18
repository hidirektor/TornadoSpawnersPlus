package com.infumia.t3sl4.tornadoplus.tornadopsp.entity.mob.argument;

import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.mob.IMobArgument;

public class ArmorArgument implements IMobArgument {
   private final double armor;

   public ArmorArgument(double armor) {
      this.armor = armor;
   }

   public Double convert() {
      return this.armor;
   }
}

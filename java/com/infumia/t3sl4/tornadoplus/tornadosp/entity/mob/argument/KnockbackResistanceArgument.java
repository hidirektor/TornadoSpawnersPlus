package com.infumia.t3sl4.tornadoplus.tornadopsp.entity.mob.argument;

import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.mob.IMobArgument;

public class KnockbackResistanceArgument implements IMobArgument {
   private final double knockbackResistance;

   public KnockbackResistanceArgument(double knockbackResistance) {
      this.knockbackResistance = knockbackResistance;
   }

   public Double convert() {
      return this.knockbackResistance;
   }
}

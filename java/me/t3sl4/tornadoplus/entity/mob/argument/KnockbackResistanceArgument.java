package me.t3sl4.tornadoplus.entity.mob.argument;

import me.t3sl4.tornadoplus.entity.mob.IMobArgument;

public class KnockbackResistanceArgument implements IMobArgument {
   private final double knockbackResistance;

   public KnockbackResistanceArgument(double knockbackResistance) {
      this.knockbackResistance = knockbackResistance;
   }

   public Double convert() {
      return this.knockbackResistance;
   }
}

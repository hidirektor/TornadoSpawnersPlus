package me.t3sl4.tornadoplus.entity.mob.argument;

import me.t3sl4.tornadoplus.entity.mob.IMobArgument;

public class ArmorArgument implements IMobArgument {
   private final double armor;

   public ArmorArgument(double armor) {
      this.armor = armor;
   }

   public Double convert() {
      return this.armor;
   }
}

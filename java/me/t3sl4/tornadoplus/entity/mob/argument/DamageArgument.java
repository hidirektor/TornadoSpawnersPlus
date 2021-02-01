package me.t3sl4.tornadoplus.entity.mob.argument;

import me.t3sl4.tornadoplus.entity.mob.IMobArgument;

public class DamageArgument implements IMobArgument {
   private final int damage;

   public DamageArgument(int damage) {
      this.damage = damage;
   }

   public Integer convert() {
      return this.damage;
   }
}

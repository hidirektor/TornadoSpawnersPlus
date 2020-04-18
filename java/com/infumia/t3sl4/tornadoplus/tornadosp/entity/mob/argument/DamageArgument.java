package com.infumia.t3sl4.tornadoplus.tornadopsp.entity.mob.argument;

import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.mob.IMobArgument;

public class DamageArgument implements IMobArgument {
   private final int damage;

   public DamageArgument(int damage) {
      this.damage = damage;
   }

   public Integer convert() {
      return this.damage;
   }
}

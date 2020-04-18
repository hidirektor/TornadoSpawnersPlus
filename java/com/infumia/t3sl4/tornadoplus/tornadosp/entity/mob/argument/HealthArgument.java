package com.infumia.t3sl4.tornadoplus.tornadopsp.entity.mob.argument;

import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.mob.IMobArgument;

public class HealthArgument implements IMobArgument {
   private final int health;

   public HealthArgument(int health) {
      this.health = health;
   }

   public Integer convert() {
      return this.health;
   }
}

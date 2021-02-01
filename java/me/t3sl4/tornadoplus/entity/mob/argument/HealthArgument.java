package me.t3sl4.tornadoplus.entity.mob.argument;

import me.t3sl4.tornadoplus.entity.mob.IMobArgument;

public class HealthArgument implements IMobArgument {
   private final int health;

   public HealthArgument(int health) {
      this.health = health;
   }

   public Integer convert() {
      return this.health;
   }
}

package me.t3sl4.tornadoplus.entity.mob.argument;

import me.t3sl4.tornadoplus.entity.mob.IMobArgument;

public class MovementSpeedArgument implements IMobArgument {
   private final float speed;

   public MovementSpeedArgument(float speed) {
      this.speed = speed;
   }

   public Float convert() {
      return this.speed;
   }
}

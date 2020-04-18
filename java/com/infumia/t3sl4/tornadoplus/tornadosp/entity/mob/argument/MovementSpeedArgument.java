package com.infumia.t3sl4.tornadoplus.tornadopsp.entity.mob.argument;

import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.mob.IMobArgument;

public class MovementSpeedArgument implements IMobArgument {
   private final float speed;

   public MovementSpeedArgument(float speed) {
      this.speed = speed;
   }

   public Float convert() {
      return this.speed;
   }
}

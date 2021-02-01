package me.t3sl4.tornadoplus.entity.mob.argument;

import me.t3sl4.tornadoplus.entity.mob.IMobArgument;

public class FollowRangeArgument implements IMobArgument {
   private final double followRange;

   public FollowRangeArgument(double followRange) {
      this.followRange = followRange;
   }

   public Double convert() {
      return this.followRange;
   }
}

package com.infumia.t3sl4.tornadoplus.tornadopsp.entity.mob.argument;

import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.mob.IMobArgument;

public class FollowRangeArgument implements IMobArgument {
   private final double followRange;

   public FollowRangeArgument(double followRange) {
      this.followRange = followRange;
   }

   public Double convert() {
      return this.followRange;
   }
}

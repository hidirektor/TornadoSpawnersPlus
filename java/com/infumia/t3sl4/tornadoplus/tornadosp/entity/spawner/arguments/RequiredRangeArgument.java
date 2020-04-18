package com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner.arguments;

import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner.ISpawnerArgument;

public class RequiredRangeArgument implements ISpawnerArgument {
   private final int requiredPlayerRange;

   public RequiredRangeArgument(int requiredPlayerRange) {
      this.requiredPlayerRange = requiredPlayerRange;
   }

   public Integer convert() {
      return this.requiredPlayerRange;
   }
}

package com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner.arguments;

import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner.ISpawnerArgument;

public class RangeArgument implements ISpawnerArgument {
   private final int range;

   public RangeArgument(int range) {
      this.range = range;
   }

   public Integer convert() {
      return this.range;
   }
}

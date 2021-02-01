package me.t3sl4.tornadoplus.entity.spawner.arguments;

import me.t3sl4.tornadoplus.entity.spawner.ISpawnerArgument;

public class RangeArgument implements ISpawnerArgument {
   private final int range;

   public RangeArgument(int range) {
      this.range = range;
   }

   public Integer convert() {
      return this.range;
   }
}

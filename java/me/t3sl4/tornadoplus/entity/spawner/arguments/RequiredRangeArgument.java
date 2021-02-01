package me.t3sl4.tornadoplus.entity.spawner.arguments;

import me.t3sl4.tornadoplus.entity.spawner.ISpawnerArgument;

public class RequiredRangeArgument implements ISpawnerArgument {
   private final int requiredPlayerRange;

   public RequiredRangeArgument(int requiredPlayerRange) {
      this.requiredPlayerRange = requiredPlayerRange;
   }

   public Integer convert() {
      return this.requiredPlayerRange;
   }
}

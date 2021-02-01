package me.t3sl4.tornadoplus.entity.spawner.arguments;

import me.t3sl4.tornadoplus.entity.spawner.ISpawnerArgument;

public class DelayArgument implements ISpawnerArgument {
   private final int delay;

   public DelayArgument(int delay) {
      this.delay = delay;
   }

   public Integer convert() {
      return this.delay;
   }
}

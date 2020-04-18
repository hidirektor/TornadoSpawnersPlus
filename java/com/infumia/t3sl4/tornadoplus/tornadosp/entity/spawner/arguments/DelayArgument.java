package com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner.arguments;

import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner.ISpawnerArgument;

public class DelayArgument implements ISpawnerArgument {
   private final int delay;

   public DelayArgument(int delay) {
      this.delay = delay;
   }

   public Integer convert() {
      return this.delay;
   }
}

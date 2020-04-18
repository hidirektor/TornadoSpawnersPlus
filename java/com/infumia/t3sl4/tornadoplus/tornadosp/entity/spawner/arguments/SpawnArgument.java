package com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner.arguments;

import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner.ISpawnerArgument;

public class SpawnArgument implements ISpawnerArgument {
   private final String spawn;

   public SpawnArgument(String spawn) {
      this.spawn = spawn;
   }

   public String convert() {
      return this.spawn;
   }
}

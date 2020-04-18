package com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner.arguments;

import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner.ISpawnerArgument;

public class SpawnCountArgument implements ISpawnerArgument {
   private final int spawnCount;

   public SpawnCountArgument(int spawnCount) {
      this.spawnCount = spawnCount;
   }

   public Integer convert() {
      return this.spawnCount;
   }
}

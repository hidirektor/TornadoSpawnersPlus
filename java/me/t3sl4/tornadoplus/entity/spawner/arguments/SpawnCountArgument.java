package me.t3sl4.tornadoplus.entity.spawner.arguments;

import me.t3sl4.tornadoplus.entity.spawner.ISpawnerArgument;

public class SpawnCountArgument implements ISpawnerArgument {
   private final int spawnCount;

   public SpawnCountArgument(int spawnCount) {
      this.spawnCount = spawnCount;
   }

   public Integer convert() {
      return this.spawnCount;
   }
}

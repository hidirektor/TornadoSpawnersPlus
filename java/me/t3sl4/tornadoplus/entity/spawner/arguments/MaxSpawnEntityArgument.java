package me.t3sl4.tornadoplus.entity.spawner.arguments;

import me.t3sl4.tornadoplus.entity.spawner.ISpawnerArgument;

public class MaxSpawnEntityArgument implements ISpawnerArgument {
   private final int maxSpawnEntity;

   public MaxSpawnEntityArgument(int maxSpawnEntity) {
      this.maxSpawnEntity = maxSpawnEntity;
   }

   public Integer convert() {
      return this.maxSpawnEntity;
   }
}

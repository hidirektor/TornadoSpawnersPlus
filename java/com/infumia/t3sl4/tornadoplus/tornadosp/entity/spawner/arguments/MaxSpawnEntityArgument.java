package com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner.arguments;

import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner.ISpawnerArgument;

public class MaxSpawnEntityArgument implements ISpawnerArgument {
   private final int maxSpawnEntity;

   public MaxSpawnEntityArgument(int maxSpawnEntity) {
      this.maxSpawnEntity = maxSpawnEntity;
   }

   public Integer convert() {
      return this.maxSpawnEntity;
   }
}

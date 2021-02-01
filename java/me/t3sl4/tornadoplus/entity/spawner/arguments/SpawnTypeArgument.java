package me.t3sl4.tornadoplus.entity.spawner.arguments;

import me.t3sl4.tornadoplus.entity.spawner.ISpawnerArgument;

public class SpawnTypeArgument implements ISpawnerArgument {
   private final String spawnType;

   public SpawnTypeArgument(String spawnType) {
      this.spawnType = spawnType;
   }

   public String convert() {
      return this.spawnType;
   }
}

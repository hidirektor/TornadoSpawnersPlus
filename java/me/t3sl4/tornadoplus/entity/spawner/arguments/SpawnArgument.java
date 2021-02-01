package me.t3sl4.tornadoplus.entity.spawner.arguments;

import me.t3sl4.tornadoplus.entity.spawner.ISpawnerArgument;

public class SpawnArgument implements ISpawnerArgument {
   private final String spawn;

   public SpawnArgument(String spawn) {
      this.spawn = spawn;
   }

   public String convert() {
      return this.spawn;
   }
}

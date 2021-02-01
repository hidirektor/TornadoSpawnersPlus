package me.t3sl4.tornadoplus.entity.spawner.arguments;

import me.t3sl4.tornadoplus.entity.spawner.ISpawnerArgument;
import org.bukkit.Material;

public class BlockNameArgument implements ISpawnerArgument {
   private final String blockName;

   public BlockNameArgument(String blockName) {
      this.blockName = blockName;
   }

   public Object convert() {
      return Material.getMaterial(this.blockName);
   }
}

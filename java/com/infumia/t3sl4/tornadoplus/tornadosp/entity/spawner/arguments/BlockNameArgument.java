package com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner.arguments;

import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner.ISpawnerArgument;
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

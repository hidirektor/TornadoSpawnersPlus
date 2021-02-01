package me.t3sl4.tornadoplus.entity.mob.argument;

import me.t3sl4.tornadoplus.entity.mob.IMobArgument;
import org.bukkit.entity.EntityType;

public class MobNameArgument implements IMobArgument {
   private final String mobName;

   public MobNameArgument(String mobName) {
      this.mobName = mobName;
   }

   public EntityType convert() {
      return EntityType.valueOf(this.mobName);
   }
}

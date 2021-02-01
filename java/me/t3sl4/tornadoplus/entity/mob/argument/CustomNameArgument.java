package me.t3sl4.tornadoplus.entity.mob.argument;

import me.t3sl4.tornadoplus.entity.mob.IMobArgument;

public class CustomNameArgument implements IMobArgument {
   private final String customName;
   private final boolean customNameVisible;

   public CustomNameArgument(String customName, boolean customNameVisible) {
      this.customName = customName;
      this.customNameVisible = customNameVisible;
   }

   public String convert() {
      return this.customName + ":" + this.customNameVisible;
   }
}

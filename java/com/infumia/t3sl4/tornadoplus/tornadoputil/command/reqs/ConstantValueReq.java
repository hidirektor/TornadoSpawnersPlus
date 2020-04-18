package com.infumia.t3sl4.tornadoplus.tornadoputil.command.reqs;

import com.infumia.t3sl4.tornadoplus.tornadoputil.command.ErrorType;
import com.infumia.t3sl4.tornadoplus.tornadoputil.command.IArgumentReq;
import org.bukkit.command.CommandSender;

public class ConstantValueReq implements IArgumentReq {
   private final int location;
   private final String[] args;

   public ConstantValueReq(int location, String[] args) {
      this.location = location;
      this.args = args;
   }

   public ErrorType control(CommandSender sender, String[] args) {
      String[] var6;
      int var5 = (var6 = this.args).length;

      for(int var4 = 0; var4 < var5; ++var4) {
         String argElements = var6[var4];
         if (args[this.location].equalsIgnoreCase(argElements)) {
            return ErrorType.NONE;
         }
      }

      return ErrorType.WRONG_USAGE;
   }
}

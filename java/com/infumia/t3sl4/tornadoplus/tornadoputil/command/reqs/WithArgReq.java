package com.infumia.t3sl4.tornadoplus.tornadoputil.command.reqs;

import com.infumia.t3sl4.tornadoplus.tornadoputil.command.ErrorType;
import com.infumia.t3sl4.tornadoplus.tornadoputil.command.IArgumentReq;
import org.bukkit.command.CommandSender;

public class WithArgReq implements IArgumentReq {
   private final int minArg;
   private final int maxArg;

   public WithArgReq(int minArg, int maxArg) {
      this.minArg = minArg;
      this.maxArg = maxArg;
   }

   public ErrorType control(CommandSender sender, String[] args) {
      if (this.maxArg == 0) {
         return this.minArg <= args.length ? ErrorType.NONE : ErrorType.WRONG_USAGE;
      } else {
         return this.minArg <= args.length && args.length <= this.maxArg ? ErrorType.NONE : ErrorType.WRONG_USAGE;
      }
   }
}

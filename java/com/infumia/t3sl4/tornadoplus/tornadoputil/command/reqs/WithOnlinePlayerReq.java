package com.infumia.t3sl4.tornadoplus.tornadoputil.command.reqs;

import com.infumia.t3sl4.tornadoplus.tornadoputil.command.ErrorType;
import com.infumia.t3sl4.tornadoplus.tornadoputil.command.IArgumentReq;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class WithOnlinePlayerReq implements IArgumentReq {
   private final int[] playerArgNum;

   public WithOnlinePlayerReq(int[] playerArgNum) {
      this.playerArgNum = playerArgNum;
   }

   public ErrorType control(CommandSender sender, String[] args) {
      int[] var6;
      int var5 = (var6 = this.playerArgNum).length;

      for(int var4 = 0; var4 < var5; ++var4) {
         int i = var6[var4];
         if (Bukkit.getPlayer(args[i]) == null) {
            return ErrorType.PLAYER_NOT_FOUND;
         }
      }

      return args.length >= this.playerArgNum.length ? ErrorType.NONE : ErrorType.WRONG_USAGE;
   }
}

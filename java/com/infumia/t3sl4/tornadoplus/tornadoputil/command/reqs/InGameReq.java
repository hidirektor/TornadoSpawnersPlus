package com.infumia.t3sl4.tornadoplus.tornadoputil.command.reqs;

import com.infumia.t3sl4.tornadoplus.tornadoputil.command.ErrorType;
import com.infumia.t3sl4.tornadoplus.tornadoputil.command.IArgumentReq;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InGameReq implements IArgumentReq {
   public ErrorType control(CommandSender sender, String[] args) {
      return sender instanceof Player ? ErrorType.NONE : ErrorType.IN_GAME_COMMAND;
   }
}

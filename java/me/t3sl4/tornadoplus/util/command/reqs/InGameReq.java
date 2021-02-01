package me.t3sl4.tornadoplus.util.command.reqs;

import me.t3sl4.tornadoplus.util.command.ErrorType;
import me.t3sl4.tornadoplus.util.command.IArgumentReq;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InGameReq implements IArgumentReq {
   public ErrorType control(CommandSender sender, String[] args) {
      return sender instanceof Player ? ErrorType.NONE : ErrorType.IN_GAME_COMMAND;
   }
}

package me.t3sl4.tornadoplus.util.command;

import org.bukkit.command.CommandSender;

public interface IArgumentReq {
   ErrorType control(CommandSender var1, String[] var2);
}

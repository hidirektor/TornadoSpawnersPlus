package me.t3sl4.tornadoplus.util.command;

import org.bukkit.command.CommandSender;

public interface IArgument {
   void process(CommandSender var1, String[] var2);

   IArgument isFinalArgument(String var1);

   ErrorType control(CommandSender var1, String[] var2);
}
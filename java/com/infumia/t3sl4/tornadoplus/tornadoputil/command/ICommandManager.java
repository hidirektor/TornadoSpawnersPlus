package com.infumia.t3sl4.tornadoplus.tornadoputil.command;

import org.bukkit.command.CommandExecutor;

public interface ICommandManager extends CommandExecutor {
   IArgument argument(String var1);

   void register();

   void unregister();
}

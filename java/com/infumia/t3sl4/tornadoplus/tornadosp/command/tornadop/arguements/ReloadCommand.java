package com.infumia.t3sl4.tornadoplus.tornadopsp.command.tornadop.arguements;

import com.infumia.t3sl4.tornadoplus.tornadoputil.command.ErrorType;
import com.infumia.t3sl4.tornadoplus.tornadoputil.command.IArgument;
import com.infumia.t3sl4.tornadoplus.tornadoputil.misc.PluginUtil;
import com.infumia.t3sl4.tornadoplus.tornadopsp.TornadoSpawnersPlus;
import com.infumia.t3sl4.tornadoplus.tornadopsp.misc.VariableName;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements IArgument {
   private final IArgument argument;

   public ReloadCommand(IArgument argument) {
      this.argument = argument;
   }

   public void process(CommandSender commandSender, String[] args) {
      TornadoSpawnersPlus.getInstance().reloadPlugin();
      PluginUtil.getInstance().sendMessage(VariableName.pullMessage("general.reload-completed"), commandSender);
   }

   public IArgument isFinalArgument(String s) {
      return this.argument.isFinalArgument(s);
   }

   public ErrorType control(CommandSender commandSender, String[] strings) {
      return this.argument.control(commandSender, strings);
   }
}

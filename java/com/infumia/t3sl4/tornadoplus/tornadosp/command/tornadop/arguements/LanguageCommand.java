package com.infumia.t3sl4.tornadoplus.tornadopsp.command.tornadop.arguements;

import com.infumia.t3sl4.tornadoplus.tornadoputil.command.ErrorType;
import com.infumia.t3sl4.tornadoplus.tornadoputil.command.IArgument;
import com.infumia.t3sl4.tornadoplus.tornadoputil.misc.PluginUtil;
import com.infumia.t3sl4.tornadoplus.tornadopsp.TornadoSpawnersPlus;
import com.infumia.t3sl4.tornadoplus.tornadopsp.misc.VariableName;
import org.bukkit.command.CommandSender;

public class LanguageCommand implements IArgument {
   private final IArgument argument;

   public LanguageCommand(IArgument argument) {
      this.argument = argument;
   }

   public void process(CommandSender commandSender, String[] args) {
      if (!args[1].equalsIgnoreCase("tr") && !args[1].equalsIgnoreCase("en")) {
         PluginUtil.getInstance().sendMessage(VariableName.pullMessage("error.unknown-language"), commandSender);
      } else {
         TornadoSpawnersPlus.getInstance().getConfigs().set("plugin-language", args[1]);
         TornadoSpawnersPlus.getInstance().reloadPlugin();
         String message = VariableName.pullMessage("general.language-has-been-changed").replaceAll("%changed-language%", args[1]);
         PluginUtil.getInstance().sendMessage(message, commandSender);
      }

   }

   public IArgument isFinalArgument(String s) {
      return this.argument.isFinalArgument(s);
   }

   public ErrorType control(CommandSender commandSender, String[] strings) {
      return this.argument.control(commandSender, strings);
   }
}

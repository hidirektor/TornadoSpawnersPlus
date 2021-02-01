package me.t3sl4.tornadoplus.command.arguements;

import me.t3sl4.tornadoplus.util.command.ErrorType;
import me.t3sl4.tornadoplus.util.command.IArgument;
import me.t3sl4.tornadoplus.util.misc.PluginUtil;
import me.t3sl4.tornadoplus.TornadoSpawnersPlus;
import me.t3sl4.tornadoplus.misc.VariableName;
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

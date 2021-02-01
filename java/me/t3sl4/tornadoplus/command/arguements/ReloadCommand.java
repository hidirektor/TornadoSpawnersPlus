package me.t3sl4.tornadoplus.command.arguements;

import me.t3sl4.tornadoplus.util.command.ErrorType;
import me.t3sl4.tornadoplus.util.command.IArgument;
import me.t3sl4.tornadoplus.util.misc.PluginUtil;
import me.t3sl4.tornadoplus.TornadoSpawnersPlus;
import me.t3sl4.tornadoplus.misc.VariableName;
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

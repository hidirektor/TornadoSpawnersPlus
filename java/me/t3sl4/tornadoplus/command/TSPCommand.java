package me.t3sl4.tornadoplus.command;

import me.t3sl4.tornadoplus.util.command.ErrorType;
import me.t3sl4.tornadoplus.util.command.IArgument;
import me.t3sl4.tornadoplus.util.command.ICommand;
import me.t3sl4.tornadoplus.util.misc.PluginUtil;
import me.t3sl4.tornadoplus.misc.VariableName;
import org.bukkit.command.CommandSender;

public class TSPCommand implements ICommand {
   private final ICommand command;

   public TSPCommand(ICommand command) {
      this.command = command;
   }

   public void process(CommandSender commandSender, String[] args) {
      PluginUtil.getInstance().sendMessage(VariableName.pullMessage("commands"), commandSender);
   }

   public IArgument isFinalArgument(String s) {
      return this.command.isFinalArgument(s);
   }

   public ErrorType control(CommandSender commandSender, String[] strings) {
      return this.command.control(commandSender, strings);
   }
}

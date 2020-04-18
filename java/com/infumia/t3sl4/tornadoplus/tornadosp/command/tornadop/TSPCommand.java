package com.infumia.t3sl4.tornadoplus.tornadopsp.command.tornadop;

import com.infumia.t3sl4.tornadoplus.tornadoputil.command.ErrorType;
import com.infumia.t3sl4.tornadoplus.tornadoputil.command.IArgument;
import com.infumia.t3sl4.tornadoplus.tornadoputil.command.ICommand;
import com.infumia.t3sl4.tornadoplus.tornadoputil.misc.PluginUtil;
import com.infumia.t3sl4.tornadoplus.tornadopsp.misc.VariableName;
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

package com.infumia.t3sl4.tornadoplus.tornadopsp.command.tornadop.arguements;

import java.util.ArrayList;
import java.util.List;

import com.infumia.t3sl4.tornadoplus.tornadoputil.command.ErrorType;
import com.infumia.t3sl4.tornadoplus.tornadoputil.command.IArgument;
import com.infumia.t3sl4.tornadoplus.tornadoputil.misc.PluginUtil;
import com.infumia.t3sl4.tornadoplus.tornadopsp.TornadoSpawnersPlus;
import com.infumia.t3sl4.tornadoplus.tornadopsp.misc.VariableName;
import org.bukkit.command.CommandSender;

public class ListCommand implements IArgument {
   private final IArgument argument;

   public ListCommand(IArgument argument) {
      this.argument = argument;
   }

   public void process(CommandSender commandSender, String[] args) {
      List<String> list = new ArrayList();
      String type = args[1];
      if (type.equalsIgnoreCase("spawner")) {
         list.addAll(TornadoSpawnersPlus.SPAWNERS.keySet());
      } else if (type.equalsIgnoreCase("mob")) {
         list.addAll(TornadoSpawnersPlus.MOBS.keySet());
      } else {
         if (!type.equalsIgnoreCase("item")) {
            PluginUtil.getInstance().sendMessage(VariableName.pullMessage("error.unknown-type"), commandSender);
            return;
         }

         list.addAll(TornadoSpawnersPlus.ITEMS.keySet());
      }

      PluginUtil.getInstance().sendMessage(list.toString(), commandSender);
   }

   public IArgument isFinalArgument(String s) {
      return this.argument.isFinalArgument(s);
   }

   public ErrorType control(CommandSender commandSender, String[] strings) {
      return this.argument.control(commandSender, strings);
   }
}

package me.t3sl4.tornadoplus.command.arguements;

import java.util.ArrayList;
import java.util.List;

import me.t3sl4.tornadoplus.util.command.ErrorType;
import me.t3sl4.tornadoplus.util.command.IArgument;
import me.t3sl4.tornadoplus.util.misc.PluginUtil;
import me.t3sl4.tornadoplus.TornadoSpawnersPlus;
import me.t3sl4.tornadoplus.misc.VariableName;
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

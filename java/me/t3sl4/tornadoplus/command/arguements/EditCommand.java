package me.t3sl4.tornadoplus.command.arguements;

import me.t3sl4.tornadoplus.listener.GeneralInventoryListener;
import me.t3sl4.tornadoplus.util.command.ErrorType;
import me.t3sl4.tornadoplus.util.command.IArgument;
import me.t3sl4.tornadoplus.util.misc.PluginUtil;
import me.t3sl4.tornadoplus.TornadoSpawnersPlus;
import me.t3sl4.tornadoplus.entity.IEntity;
import me.t3sl4.tornadoplus.misc.VariableName;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EditCommand implements IArgument {
   private final IArgument argument;

   public EditCommand(IArgument argument) {
      this.argument = argument;
   }

   public void process(CommandSender commandSender, String[] strings) {
      IEntity entity = null;
      if (strings[1].equalsIgnoreCase("item")) {
         entity = (IEntity)TornadoSpawnersPlus.ITEMS.get(strings[2]);
      } else if (strings[1].equalsIgnoreCase("mob")) {
         entity = (IEntity)TornadoSpawnersPlus.MOBS.get(strings[2]);
      } else if (strings[1].equalsIgnoreCase("spawner")) {
         entity = (IEntity)TornadoSpawnersPlus.SPAWNERS.get(strings[2]);
      }

      if (entity != null) {
         GeneralInventoryListener.build((Player)commandSender).openEditMenu(entity);
      } else {
         PluginUtil.getInstance().sendMessage(VariableName.pullMessage("error.unknown-id"), commandSender);
      }

   }

   public IArgument isFinalArgument(String s) {
      return this.argument.isFinalArgument(s);
   }

   public ErrorType control(CommandSender commandSender, String[] strings) {
      return this.argument.control(commandSender, strings);
   }
}

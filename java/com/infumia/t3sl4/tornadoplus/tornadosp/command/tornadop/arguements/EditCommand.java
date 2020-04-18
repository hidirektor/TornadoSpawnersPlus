package com.infumia.t3sl4.tornadoplus.tornadopsp.command.tornadop.arguements;

import com.infumia.t3sl4.tornadoplus.tornadopsp.listener.GeneralInventoryListener;
import com.infumia.t3sl4.tornadoplus.tornadoputil.command.ErrorType;
import com.infumia.t3sl4.tornadoplus.tornadoputil.command.IArgument;
import com.infumia.t3sl4.tornadoplus.tornadoputil.misc.PluginUtil;
import com.infumia.t3sl4.tornadoplus.tornadopsp.TornadoSpawnersPlus;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.IEntity;
import com.infumia.t3sl4.tornadoplus.tornadopsp.misc.VariableName;
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

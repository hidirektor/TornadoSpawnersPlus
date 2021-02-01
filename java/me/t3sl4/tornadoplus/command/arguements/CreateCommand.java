package me.t3sl4.tornadoplus.command.arguements;

import me.t3sl4.tornadoplus.listener.GeneralInventoryListener;
import me.t3sl4.tornadoplus.util.command.ErrorType;
import me.t3sl4.tornadoplus.util.command.IArgument;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateCommand implements IArgument {
   private final IArgument argument;

   public CreateCommand(IArgument argument) {
      this.argument = argument;
   }

   public void process(CommandSender commandSender, String[] args) {
      GeneralInventoryListener.build((Player)commandSender).openCreateMenu();
   }

   public IArgument isFinalArgument(String s) {
      return this.argument.isFinalArgument(s);
   }

   public ErrorType control(CommandSender commandSender, String[] strings) {
      return this.argument.control(commandSender, strings);
   }
}

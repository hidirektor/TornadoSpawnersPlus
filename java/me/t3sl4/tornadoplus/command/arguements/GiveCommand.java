package me.t3sl4.tornadoplus.command.arguements;

import me.t3sl4.tornadoplus.util.command.ErrorType;
import me.t3sl4.tornadoplus.util.command.IArgument;
import me.t3sl4.tornadoplus.util.misc.PluginUtil;
import me.t3sl4.tornadoplus.TornadoSpawnersPlus;
import me.t3sl4.tornadoplus.entity.spawner.ISpawner;
import me.t3sl4.tornadoplus.misc.VariableName;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveCommand implements IArgument {
   private final IArgument argument;

   public GiveCommand(IArgument argument) {
      this.argument = argument;
   }

   public void process(CommandSender commandSender, String[] args) {
      Player player = Bukkit.getPlayer(args[1]);
      ISpawner iSpawner = (ISpawner)TornadoSpawnersPlus.SPAWNERS.get(args[2]);
      if (args.length == 3) {
         iSpawner.give(player);
      } else {
         int count = 0;

         try {
            count = Integer.parseInt(args[3]);
         } catch (Exception var7) {
            PluginUtil.getInstance().sendMessage(VariableName.pullMessage("error.wrong-usage"), player);
         }

         for(int i = 0; i < count; ++i) {
            if (!iSpawner.give(player)) {
               return;
            }
         }
      }

   }

   public IArgument isFinalArgument(String s) {
      return this.argument.isFinalArgument(s);
   }

   public ErrorType control(CommandSender commandSender, String[] strings) {
      return this.argument.control(commandSender, strings);
   }
}

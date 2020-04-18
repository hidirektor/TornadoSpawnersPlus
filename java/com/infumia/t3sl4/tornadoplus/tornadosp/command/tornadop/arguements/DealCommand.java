package com.infumia.t3sl4.tornadoplus.tornadopsp.command.tornadop.arguements;

import java.util.Iterator;

import com.infumia.t3sl4.tornadoplus.tornadoputil.command.ErrorType;
import com.infumia.t3sl4.tornadoplus.tornadoputil.command.IArgument;
import com.infumia.t3sl4.tornadoplus.tornadoputil.misc.PluginUtil;
import com.infumia.t3sl4.tornadoplus.tornadopsp.TornadoSpawnersPlus;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner.ISpawner;
import com.infumia.t3sl4.tornadoplus.tornadopsp.misc.VariableName;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DealCommand implements IArgument {
   private final IArgument argument;

   public DealCommand(IArgument argument) {
      this.argument = argument;
   }

   public void process(CommandSender commandSender, String[] args) {
      ISpawner iSpawner = (ISpawner)TornadoSpawnersPlus.SPAWNERS.get(args[1]);
      Iterator var4 = Bukkit.getOnlinePlayers().iterator();

      while(true) {
         while(var4.hasNext()) {
            Player player = (Player)var4.next();
            if (args.length == 2) {
               iSpawner.give(player);
            } else {
               int count = 0;

               try {
                  count = Integer.parseInt(args[2]);
               } catch (Exception var8) {
                  PluginUtil.getInstance().sendMessage(VariableName.pullMessage("error.wrong-usage"), player);
               }

               for(int i = 0; i < count; ++i) {
                  if (!iSpawner.give(player)) {
                     return;
                  }
               }
            }
         }

         return;
      }
   }

   public IArgument isFinalArgument(String s) {
      return this.argument.isFinalArgument(s);
   }

   public ErrorType control(CommandSender commandSender, String[] strings) {
      return this.argument.control(commandSender, strings);
   }
}

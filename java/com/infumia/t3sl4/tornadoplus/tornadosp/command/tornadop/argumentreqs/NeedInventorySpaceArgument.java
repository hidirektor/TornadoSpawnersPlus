package com.infumia.t3sl4.tornadoplus.tornadopsp.command.tornadop.argumentreqs;

import java.util.Iterator;

import com.infumia.t3sl4.tornadoplus.tornadoputil.command.ErrorType;
import com.infumia.t3sl4.tornadoplus.tornadoputil.command.IArgumentReq;
import com.infumia.t3sl4.tornadoplus.tornadoputil.misc.InventoryUtil;
import com.infumia.t3sl4.tornadoplus.tornadoputil.misc.PluginUtil;
import com.infumia.t3sl4.tornadoplus.tornadopsp.TornadoSpawnersPlus;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner.ISpawner;
import com.infumia.t3sl4.tornadoplus.tornadopsp.misc.VariableName;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NeedInventorySpaceArgument implements IArgumentReq {
   private final int[] whoIs;
   private final int itemArg;
   private final boolean self;

   public NeedInventorySpaceArgument(int[] whoIs, int itemArg, boolean self) {
      this.whoIs = whoIs;
      this.itemArg = itemArg;
      this.self = self;
   }

   public ErrorType control(CommandSender commandSender, String[] args) {
      ISpawner iSpawner = (ISpawner)TornadoSpawnersPlus.SPAWNERS.get(args[this.itemArg]);
      if (iSpawner == null) {
         PluginUtil.getInstance().sendMessage(VariableName.pullMessage("error.spawner-not-found"), commandSender);
         return ErrorType.DENY;
      } else {
         if (this.self) {
            if (!InventoryUtil.getInstance().checkInventory((Player)commandSender, iSpawner.create())) {
               PluginUtil.getInstance().sendMessage(VariableName.pullMessage("error.your-inventory-is-full"), commandSender);
               return ErrorType.NONE;
            }
         } else if (this.whoIs.length == 0) {
            Iterator var4 = Bukkit.getOnlinePlayers().iterator();

            while(var4.hasNext()) {
               Player player = (Player)var4.next();
               if (!InventoryUtil.getInstance().checkInventory(player, iSpawner.create())) {
                  PluginUtil.getInstance().sendMessage(VariableName.pullMessage("error.player-inventory-is-full").replaceAll("%player%", player.getName()), commandSender);
                  return ErrorType.DENY;
               }
            }
         } else {
            int[] var8 = this.whoIs;
            int var9 = var8.length;

            for(int var6 = 0; var6 < var9; ++var6) {
               int i = var8[var6];
               if (!InventoryUtil.getInstance().checkInventory(Bukkit.getPlayer(args[i]), iSpawner.create())) {
                  PluginUtil.getInstance().sendMessage(VariableName.pullMessage("error.player-inventory-is-full").replaceAll("%player%", Bukkit.getPlayer(args[i]).getName()), commandSender);
                  return ErrorType.DENY;
               }
            }
         }

         return ErrorType.NONE;
      }
   }
}

package me.t3sl4.tornadoplus.util.command;

import java.util.Map;
import java.util.Objects;
import me.t3sl4.tornadoplus.util.T3SL4Util;
import me.t3sl4.tornadoplus.util.misc.LogUtil;
import me.t3sl4.tornadoplus.util.misc.PluginUtil;
import me.t3sl4.tornadoplus.util.misc.ReflectionUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class BaseCommandManager implements ICommandManager {
   private final JavaPlugin plugin;
   private final String commandLabel;
   private final ICommand mainCommand;

   public BaseCommandManager(JavaPlugin plugin, String commandLabel, ICommand mainCommand) {
      Objects.requireNonNull(plugin);
      Objects.requireNonNull(commandLabel);
      Objects.requireNonNull(mainCommand);
      this.plugin = plugin;
      this.commandLabel = commandLabel;
      this.mainCommand = mainCommand;
   }

   public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
      if (args.length == 0) {
         ErrorType type = this.mainCommand.control(commandSender, args);
         if (type == ErrorType.NONE) {
            this.mainCommand.process(commandSender, args);
         } else if (type != ErrorType.DENY) {
            PluginUtil.getInstance().sendMessage(((T3SL4Util)T3SL4Util.UTIL.get(this.plugin)).pullMessage(type), commandSender);
         }
      } else {
         IArgument nextArgument = this.argument(args[0]);
         ErrorType type = nextArgument.control(commandSender, args);
         if (type == ErrorType.NONE) {
            nextArgument.process(commandSender, args);
         } else if (type != ErrorType.DENY) {
            PluginUtil.getInstance().sendMessage(((T3SL4Util)T3SL4Util.UTIL.get(this.plugin)).pullMessage(type), commandSender);
         }
      }

      return true;
   }

   public IArgument argument(String arg) {
      return this.mainCommand.isFinalArgument(arg);
   }

   public void register() {
      Bukkit.getServer().getPluginCommand(this.commandLabel).setExecutor(this);
   }

   public void unregister() {
      try {
         Object commandMap = ReflectionUtil.getInstance().getField((Object)Bukkit.getServer(), "commandMap");
         Map<String, Command> knownCommands = (Map)ReflectionUtil.getInstance().getField(commandMap, "knownCommands");
         knownCommands.remove(this.commandLabel);
      } catch (Exception var3) {
         LogUtil.getInstance().warn(var3.getMessage());
      }

   }
}

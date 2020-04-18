package com.infumia.t3sl4.tornadoplus.tornadoputil.misc;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

import com.infumia.t3sl4.tornadoplus.tornadoputil.command.ICommandManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;

public class PluginUtil {
   private static PluginUtil instance;

   private PluginUtil() {
   }

   public void sendMessage(String message, CommandSender... commandSenders) {
      Objects.requireNonNull(commandSenders);
      if (commandSenders.length == 0) {
         commandSenders = new CommandSender[]{Bukkit.getConsoleSender()};
      }

      Arrays.stream(commandSenders).forEach((commandSender) -> {
         commandSender.sendMessage(commandSender instanceof Player ? StringUtil.getInstance().chatColor(message) : StringUtil.getInstance().chatColor(StringUtil.getInstance().convertTrToEn(message)));
      });
   }

   public void unregisterListener(Plugin plugin) {
      HandlerList.unregisterAll(plugin);
   }

   public void test(ICommandManager[] commandManagers) {
      Arrays.stream(commandManagers).forEach(ICommandManager::unregister);
   }

   public void unregisterCommand(ICommandManager... commandManagers) {
      Arrays.stream(commandManagers).forEach(ICommandManager::unregister);
   }

   public Object[] addItemToArray(Object[] array, Object element) {
      array = Arrays.copyOf(array, array.length + 1);
      array[array.length - 1] = element;
      return array;
   }

   public void deleteWorld(World world) {
      Bukkit.getServer().unloadWorld(world, true);
      this.deleteWorld0(world.getWorldFolder());
   }

   private void deleteWorld0(File worldFile) {
      if (worldFile.exists()) {
         File[] files = worldFile.listFiles();

         for(int i = 0; i < files.length; ++i) {
            if (files[i].isDirectory()) {
               this.deleteWorld0(files[i]);
            } else {
               files[i].delete();
            }
         }
      }

      worldFile.delete();
   }

   public boolean isPluginEnabled(String name) {
      Plugin plugin = this.getPlugin(name);
      return plugin != null && plugin.isEnabled();
   }

   public Plugin getPlugin(String name) {
      Plugin[] var5;
      int var4 = (var5 = Bukkit.getPluginManager().getPlugins()).length;

      for(int var3 = 0; var3 < var4; ++var3) {
         Plugin plugin = var5[var3];
         if (plugin.getName().equalsIgnoreCase(name)) {
            return plugin;
         }
      }

      return null;
   }

   private String getMCVersion() {
      String ver = Bukkit.getVersion();
      ver = ver.substring(ver.lastIndexOf("MC:")).replaceAll("[)]", "").replaceAll("MC: ", "");
      return ver;
   }

   private String getPackageVersion() {
      return Bukkit.getServer().getClass().getPackage().getName().replaceAll("org.bukkit.craftbukkit.", "").replaceAll("v", "");
   }

   public String getCustomVersion() {
      return this.getMCVersion() + this.getPackageVersion().substring(this.getPackageVersion().lastIndexOf("_")).replaceAll("_", "");
   }

   public static synchronized PluginUtil getInstance() {
      if (instance == null) {
         instance = new PluginUtil();
      }

      return instance;
   }
}

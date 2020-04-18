package com.infumia.t3sl4.tornadoplus.tornadoputil;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.infumia.t3sl4.tornadoplus.tornadoputil.command.BaseCommandManager;
import com.infumia.t3sl4.tornadoplus.tornadoputil.command.ErrorType;
import com.infumia.t3sl4.tornadoplus.tornadoputil.command.ICommand;
import com.infumia.t3sl4.tornadoplus.tornadoputil.configuration.ConfigurationFile;
import com.infumia.t3sl4.tornadoplus.tornadoputil.configuration.files.BaseYamlFile;
import com.infumia.t3sl4.tornadoplus.tornadoputil.configuration.files.LanguageFile;
import com.infumia.t3sl4.tornadoplus.tornadoputil.exceptions.UnsupportedVersionException;
import com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.listener.InventoryClickListener;
import com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.listener.InventoryCloseListener;
import com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.listener.InventoryDragListener;
import com.infumia.t3sl4.tornadoplus.tornadoputil.misc.PluginUtil;
import com.infumia.t3sl4.tornadoplus.tornadoputil.misc.VersionUtil;
import com.infumia.t3sl4.tornadoplus.tornadoputil.configuration.files.BaseConfigurationFile;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class T3SL4Util {
   public static final Map<JavaPlugin, T3SL4Util> UTIL = new HashMap();
   private final PluginUtil pluginUtil = PluginUtil.getInstance();
   private final JavaPlugin plugin;
   private Map<ErrorType, String> messages;
   private Listener[] listeners;
   private String pluginLanguage;

   public T3SL4Util(JavaPlugin plugin) {
      Objects.requireNonNull(plugin);
      if (VersionUtil.MINOR < 8) {
         throw new UnsupportedVersionException();
      } else {
         this.plugin = plugin;
         UTIL.put(plugin, this);
      }
   }

   public void addCommandMessages(String wrongUsage, String playerNotFound, String permission, String inGameCommand) {
      Objects.requireNonNull(wrongUsage);
      Objects.requireNonNull(playerNotFound);
      Objects.requireNonNull(permission);
      Objects.requireNonNull(inGameCommand);
      (this.messages = new EnumMap(ErrorType.class)).put(ErrorType.WRONG_USAGE, wrongUsage);
      this.messages.put(ErrorType.PLAYER_NOT_FOUND, playerNotFound);
      this.messages.put(ErrorType.PERMISSION, permission);
      this.messages.put(ErrorType.IN_GAME_COMMAND, inGameCommand);
   }

   public void addListener(Listener... listeners) {
      Objects.requireNonNull(listeners);
      if (this.listeners != null) {
         this.pluginUtil.unregisterListener(this.plugin);
      }

      this.listeners = new Listener[]{new InventoryClickListener(), new InventoryDragListener(), new InventoryCloseListener()};
      Arrays.stream(listeners).forEach((listener) -> {
         this.listeners = (Listener[])this.pluginUtil.addItemToArray(this.listeners, listener);
      });
      Arrays.stream(this.listeners).forEach((listener) -> {
         Bukkit.getPluginManager().registerEvents(listener, this.plugin);
      });
   }

   public ConfigurationFile addFile(String fileName, String filePath, String... language) {
      Objects.requireNonNull(fileName);
      Objects.requireNonNull(filePath);
      if (this.pluginLanguage == null && language.length != 0) {
         throw new IllegalStateException("You need to setup the language of the Plugin!");
      } else {
         return (ConfigurationFile)(language.length == 0 ? new BaseConfigurationFile(new BaseYamlFile(this.plugin, fileName, filePath)) : new LanguageFile(this.plugin, fileName, filePath, this.pluginLanguage, language));
      }
   }

   public void createCommand(String commandLabel, ICommand command) {
      Objects.requireNonNull(commandLabel);
      Objects.requireNonNull(command);
      (new BaseCommandManager(this.plugin, commandLabel, command)).register();
   }

   public String pullMessage(ErrorType type) {
      return (String)this.messages.get(type);
   }

   public String pluginLanguage(String pluginLanguage) {
      Objects.requireNonNull(pluginLanguage);
      return this.pluginLanguage = pluginLanguage;
   }

   public String pluginLanguage() {
      return this.pluginLanguage;
   }
}
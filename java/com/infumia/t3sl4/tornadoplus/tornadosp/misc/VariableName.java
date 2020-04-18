package com.infumia.t3sl4.tornadoplus.tornadopsp.misc;

import java.util.List;

import com.infumia.t3sl4.tornadoplus.tornadopsp.TornadoSpawnersPlus;
import com.infumia.t3sl4.tornadoplus.tornadoputil.misc.StringUtil;

public class VariableName {
   public static final String COMMAND_CREATE = "create";
   public static final String COMMAND_EDIT = "edit";
   public static final String COMMAND_DEAL = "deal";
   public static final String COMMAND_GIVE = "give";
   public static final String COMMAND_HELP = "help";
   public static final String COMMAND_LANGUAGE = "language";
   public static final String COMMAND_LIST = "list";
   public static final String COMMAND_RELOAD = "reload";
   public static final String COMMAND_TAKE = "take";
   public static final String SETTING_PREFIX = "prefix";
   public static final String ERROR_PERMISSION = "error.permission";
   public static final String ERROR_IN_GAME_COMMAND = "error.in-game-command";
   public static final String ERROR_SPAWNER_NOT_FOUND = "error.spawner-not-found";
   public static final String ERROR_PLAYER_NOT_FOUND = "error.player-not-found";
   public static final String ERROR_UNKNOWN_LANGUAGE = "error.unknown-language";
   public static final String ERROR_UNKNOWN_TYPE = "error.unknown-type";
   public static final String ERROR_UNKNOWN_ID = "error.unknown-id";
   public static final String ERROR_YOUR_INVENTORY_IS_FULL = "error.your-inventory-is-full";
   public static final String ERROR_PLAYER_INVENTORY_IS_FULL = "error.player-inventory-is-full";
   public static final String ERROR_WRONG_USAGE = "error.wrong-usage";
   public static final String ERROR_MATERIAL_NOT_FOUND = "error.material-not-found";
   public static final String ERROR_ENCH_NOT_FOUND = "error.ench-not-found";
   public static final String ERROR_FLAG_NOT_FOUND = "error.flag-not-found";
   public static final String ERROR_MOB_NOT_FOUND = "error.mob-not-found";
   public static final String ERROR_ITEM_NOT_FOUND = "error.item-not-found";
   public static final String ERROR_THERE_IS_SPAWNER = "error.there-is-spawner";
   public static final String ERROR_THERE_IS_ITEM = "error.there-is-item";
   public static final String ERROR_THERE_IS_MOB = "error.there-is-mob";
   public static final String GENERAL_RELOAD_COMPLETED = "general.reload-completed";
   public static final String GENERAL_LANGUAGE_HAS_BEEN_CHANGED = "general.language-has-been-changed";
   public static final String GENERAL_SPAWNER_HAS_BEEN_BROKEN = "general.spawner-has-been-broken";
   public static final String LIST_COMMANDS = "commands";
   public static final String CONFIG_PLUGIN_LANGUAGE = "plugin-language";
   public static final String CONFIG_GIVE_BROKEN_SPAWNER_INSTANT = "give-the-broken-spawner-instant";
   public static final String CONFIG_SPAWNER_SETTINGS_SPAWNER_NAME = "spawner-settings.spawner-name";
   public static final String CONFIG_SPAWNER_SETTINGS_SPAWNER_LORE = "spawner-settings.spawner-lore";

   public static String pullMessage(String path) {
      Object message = TornadoSpawnersPlus.getInstance().getMessages().get(path);
      if (message instanceof List)
         message = StringUtil.getInstance().toStringBuilder((List)message);
      message = ((String)message).replaceAll("%prefix%", TornadoSpawnersPlus.getInstance().getSettings().getString("prefix"));
      return (String)message;
   }
}

package com.infumia.t3sl4.tornadoplus.tornadopsp;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.infumia.t3sl4.tornadoplus.tornadopsp.command.tornadop.TSPCommand;
import com.infumia.t3sl4.tornadoplus.tornadopsp.command.tornadop.argumentreqs.NeedInventorySpaceArgument;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.BasicItem;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.IItem;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.mob.BasicMob;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.mob.IMob;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner.BasicSpawner;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner.ISpawner;
import com.infumia.t3sl4.tornadoplus.tornadopsp.listener.BlockBreakListener;
import com.infumia.t3sl4.tornadoplus.tornadopsp.listener.BlockPlaceListener;
import com.infumia.t3sl4.tornadoplus.tornadopsp.listener.EntityDeathListener;
import com.infumia.t3sl4.tornadoplus.tornadopsp.listener.EntitySpawnListener;
import com.infumia.t3sl4.tornadoplus.tornadopsp.nms.IItemUtil;
import com.infumia.t3sl4.tornadoplus.tornadopsp.nms.IMobUtil;
import com.infumia.t3sl4.tornadoplus.tornadopsp.nms.ISpawnerUtil;
import com.infumia.t3sl4.tornadoplus.tornadopsp.nms.item.*;
import com.infumia.t3sl4.tornadoplus.tornadopsp.nms.mob.*;
import com.infumia.t3sl4.tornadoplus.tornadopsp.nms.spawner.*;
import com.infumia.t3sl4.tornadoplus.tornadoputil.T3SL4Util;
import com.infumia.t3sl4.tornadoplus.tornadoputil.command.reqs.ConstantValueReq;
import com.infumia.t3sl4.tornadoplus.tornadoputil.command.reqs.InGameReq;
import com.infumia.t3sl4.tornadoplus.tornadoputil.command.reqs.WithOnlinePlayerReq;
import com.infumia.t3sl4.tornadoplus.tornadoputil.configuration.ConfigurationFile;
import com.infumia.t3sl4.tornadoplus.tornadoputil.misc.PluginUtil;
import com.infumia.t3sl4.tornadoplus.tornadoputil.misc.XMaterial;
import com.infumia.t3sl4.tornadoplus.tornadoputil.version.VersionMatcher;
import com.infumia.t3sl4.tornadoplus.tornadopsp.command.tornadop.arguements.CreateCommand;
import com.infumia.t3sl4.tornadoplus.tornadopsp.command.tornadop.arguements.DealCommand;
import com.infumia.t3sl4.tornadoplus.tornadopsp.command.tornadop.arguements.EditCommand;
import com.infumia.t3sl4.tornadoplus.tornadopsp.command.tornadop.arguements.GiveCommand;
import com.infumia.t3sl4.tornadoplus.tornadopsp.command.tornadop.arguements.HelpCommand;
import com.infumia.t3sl4.tornadoplus.tornadopsp.command.tornadop.arguements.LanguageCommand;
import com.infumia.t3sl4.tornadoplus.tornadopsp.command.tornadop.arguements.ListCommand;
import com.infumia.t3sl4.tornadoplus.tornadopsp.command.tornadop.arguements.ReloadCommand;
import com.infumia.t3sl4.tornadoplus.tornadopsp.command.tornadop.arguements.TakeCommand;
import com.infumia.t3sl4.tornadoplus.tornadopsp.misc.VariableName;
import com.infumia.t3sl4.tornadoplus.tornadoputil.command.BaseArgument;
import com.infumia.t3sl4.tornadoplus.tornadoputil.command.BaseCommand;
import com.infumia.t3sl4.tornadoplus.tornadoputil.command.IArgument;
import com.infumia.t3sl4.tornadoplus.tornadoputil.command.IArgumentReq;
import com.infumia.t3sl4.tornadoplus.tornadoputil.command.ICommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;

public final class TornadoSpawnersPlus extends JavaPlugin {
   private static TornadoSpawnersPlus instance;
   private T3SL4Util util;
   private ConfigurationFile configs;
   private ConfigurationFile settings;
   private ConfigurationFile messages;
   private ConfigurationFile commands;
   private IMobUtil iMobUtil;
   private IItemUtil iItemUtil;
   private ISpawnerUtil iSpawnerUtil;
   public static final Map<String, ISpawner> SPAWNERS = new HashMap();
   public static final Map<String, IItem> ITEMS = new HashMap();
   public static final Map<String, IMob> MOBS = new HashMap();
   private static final String DATA_ITEMS = "data/items";
   private static final String DATA_MOBS = "data/mobs";
   private static final String DATA_SPAWNERS = "data/spawners";

   public synchronized void onEnable() {
      if (instance != null) {
         throw new IllegalStateException("TornadoSpawners+ iki kez aktif edilemez!");
      } else {
         instance = this;
         this.util = new T3SL4Util(instance);
         //this.iMobUtil = (IMobUtil)(new VersionMatcher(7, new Class[]{MobUtil1_8_R1.class, MobUtil1_8_R2.class, MobUtil1_8_R3.class, MobUtil1_9_R1.class, MobUtil1_9_R2.class, MobUtil1_10_R1.class, MobUtil1_11_R1.class, MobUtil1_12_R1.class, MobUtil1_13_R1.class, MobUtil1_13_R2.class, MobUtil1_14_R1.class, MobUtil1_15_R1.class})).getNmsClass();
         this.iItemUtil = (IItemUtil)(new VersionMatcher(8, new Class[]{ItemUtil1_8_R1.class, ItemUtil1_8_R2.class, ItemUtil1_8_R3.class, ItemUtil1_9_R1.class, ItemUtil1_9_R2.class, ItemUtil1_10_R1.class, ItemUtil1_11_R1.class, ItemUtil1_12_R1.class, ItemUtil1_13_R1.class, ItemUtil1_13_R2.class, ItemUtil1_14_R1.class, ItemUtil1_15_R1.class})).getNmsClass();
         this.iSpawnerUtil = (ISpawnerUtil)(new VersionMatcher(11, new Class[]{SpawnerUtil1_8_R1.class, SpawnerUtil1_8_R2.class, SpawnerUtil1_8_R3.class, SpawnerUtil1_9_R1.class, SpawnerUtil1_9_R2.class, SpawnerUtil1_10_R1.class, SpawnerUtil1_11_R1.class, SpawnerUtil1_12_R1.class, SpawnerUtil1_13_R1.class, SpawnerUtil1_13_R2.class, SpawnerUtil1_14_R1.class, SpawnerUtil1_15_R1.class})).getNmsClass();
         this.reloadPlugin();
         Bukkit.getConsoleSender().sendMessage("§5§l-------------------------");
         Bukkit.getConsoleSender().sendMessage("         §4SYN_T3SL4");
         Bukkit.getConsoleSender().sendMessage("                      ");
         Bukkit.getConsoleSender().sendMessage("         §4Halil#4439");
         Bukkit.getConsoleSender().sendMessage("                      ");
         Bukkit.getConsoleSender().sendMessage("§aTornadoSpawners§4§l+§a: §c1.8.x§7-§c1.13.x");
         Bukkit.getConsoleSender().sendMessage("§5§l-------------------------");
      }
   }

   public void reloadPlugin() {
      this.loadFiles();
      this.loadCommands();
      this.loadData();
      this.registerListeners();
   }

   private void loadFiles() {
      if (this.configs == null) {
         this.configs = this.util.addFile("config", "");
      } else {
         this.configs.load();
      }

      this.util.pluginLanguage((String)this.configs.get("plugin-language"));
      if (this.settings == null) {
         this.settings = this.util.addFile("settings", "", "en", "tr");
      } else {
         this.settings.load();
      }

      if (this.messages == null) {
         this.messages = this.util.addFile("messages", "", "en", "tr");
      } else {
         this.messages.load();
      }

      if (this.commands == null) {
         this.commands = this.util.addFile("commands", "", "en", "tr");
      } else {
         this.commands.load();
      }

      this.configs.load();
      this.settings.load();
      this.messages.load();
      this.commands.load();
      this.util.addCommandMessages(VariableName.pullMessage("error.wrong-usage"), VariableName.pullMessage("error.player-not-found"), VariableName.pullMessage("error.permission"), VariableName.pullMessage("error.in-game-command"));
   }

   private void loadCommands() {
      IArgument create = new CreateCommand(new BaseArgument((String)this.commands.get("create"), "tsp+.create", 1, 1, new IArgumentReq[]{new InGameReq()}));
      IArgument edit = new EditCommand(new BaseArgument((String)this.commands.get("edit"), "tsp+.edit", 3, 3, new IArgumentReq[]{new InGameReq(), new ConstantValueReq(1, new String[]{"mob", "item", "spawner"})}));
      IArgument deal = new DealCommand(new BaseArgument((String)this.commands.get("deal"), "tsp+.deal", 2, 3, new IArgumentReq[]{new NeedInventorySpaceArgument(new int[0], 1, false)}));
      IArgument give = new GiveCommand(new BaseArgument((String)this.commands.get("give"), "tsp+.give", 3, 4, new IArgumentReq[]{new WithOnlinePlayerReq(new int[]{1}), new NeedInventorySpaceArgument(new int[]{1}, 2, false)}));
      IArgument help = new HelpCommand(new BaseArgument((String)this.commands.get("help"), "tsp+.help", 1, 1, new IArgumentReq[0]));
      IArgument language = new LanguageCommand(new BaseArgument((String)this.commands.get("language"), "tsp+.language", 2, 2, new IArgumentReq[0]));
      IArgument list = new ListCommand(new BaseArgument((String)this.commands.get("list"), "tsp+.list", 2, 2, new IArgumentReq[]{new ConstantValueReq(1, new String[]{"spawner", "mob", "item"})}));
      IArgument reload = new ReloadCommand(new BaseArgument((String)this.commands.get("reload"), "tsp+.reload", 1, 1, new IArgumentReq[0]));
      IArgument take = new TakeCommand(new BaseArgument((String)this.commands.get("take"), "tsp+.take", 2, 3, new IArgumentReq[]{new InGameReq(), new NeedInventorySpaceArgument(new int[0], 1, true)}));
      ICommand command = new TSPCommand(new BaseCommand("tsp+.help", new IArgument[]{create, edit, deal, give, help, language, list, reload, take}));
      this.util.createCommand("tsp+", command);
   }

   private void loadData() {
      SPAWNERS.clear();
      ITEMS.clear();
      MOBS.clear();
      File itemsFile = new File(this.getDataFolder().getAbsoluteFile() + "/data/items");
      File mobsFile = new File(this.getDataFolder().getAbsoluteFile() + "/data/mobs");
      File spawnersFile = new File(this.getDataFolder().getAbsoluteFile() + "/data/spawners");
      if (!itemsFile.exists()) {
         itemsFile.mkdirs();
      }

      if (!mobsFile.exists()) {
         mobsFile.mkdirs();
      }

      if (!spawnersFile.exists()) {
         spawnersFile.mkdirs();
      }

      this.loadDefaultDataFiles();
      File[] var4 = itemsFile.listFiles();
      int var5 = var4.length;

      int var6;
      File file;
      ConfigurationFile fileFactory;
      for(var6 = 0; var6 < var5; ++var6) {
         file = var4[var6];
         fileFactory = this.util.addFile(file.getName(), "data/items");
         if (XMaterial.fromString((String)fileFactory.get("material")) == null) {
            String message = VariableName.pullMessage("error.material-not-found").replaceAll("%file-name%", file.getName());
            PluginUtil.getInstance().sendMessage(message);
         } else {
            (new BasicItem(fileFactory)).register();
         }
      }

      var4 = mobsFile.listFiles();
      var5 = var4.length;

      for(var6 = 0; var6 < var5; ++var6) {
         file = var4[var6];
         fileFactory = this.util.addFile(file.getName(), "data/mobs");

         try {
            EntityType.valueOf((String)fileFactory.get("mob-name"));
         } catch (IllegalArgumentException var10) {
            PluginUtil.getInstance().sendMessage(VariableName.pullMessage("error.mob-not-found").replaceAll("%file-name%", file.getName().replaceAll(".yml", "")));
            continue;
         }

         (new BasicMob(fileFactory)).register();
      }

      var4 = spawnersFile.listFiles();
      var5 = var4.length;

      for(var6 = 0; var6 < var5; ++var6) {
         file = var4[var6];
         fileFactory = this.util.addFile(file.getName(), "data/spawners");
         (new BasicSpawner(fileFactory)).register();
      }

   }

   private void loadDefaultDataFiles() {
      this.util.addFile("altin.yml", "data/items");
      this.util.addFile("altinblok.yml", "data/items");
      this.util.addFile("demir.yml", "data/items");
      this.util.addFile("demirblok.yml", "data/items");
      this.util.addFile("elmas.yml", "data/items");
      this.util.addFile("elmasblok.yml", "data/items");
      this.util.addFile("endtasi.yml", "data/items");
      this.util.addFile("kiziltas.yml", "data/items");
      this.util.addFile("kiziltasblok.yml", "data/items");
      this.util.addFile("komur.yml", "data/items");
      this.util.addFile("komurblok.yml", "data/items");
      this.util.addFile("kuvars.yml", "data/items");
      this.util.addFile("kuvarsblok.yml", "data/items");
      this.util.addFile("prizmarin.yml", "data/items");
      this.util.addFile("sampleitem.yml", "data/items");
      this.util.addFile("sunger.yml", "data/items");
      this.util.addFile("zumrut.yml", "data/items");
      this.util.addFile("zumrutblok.yml", "data/items");
      this.util.addFile("ahtapot.yml", "data/mobs");
      this.util.addFile("at.yml", "data/mobs");
      this.util.addFile("balcik.yml", "data/mobs");
      this.util.addFile("blaze.yml", "data/mobs");
      this.util.addFile("cadi.yml", "data/mobs");
      this.util.addFile("creeper.yml", "data/mobs");
      this.util.addFile("domuz.yml", "data/mobs");
      this.util.addFile("enderman.yml", "data/mobs");
      this.util.addFile("ghast.yml", "data/mobs");
      this.util.addFile("gumusbaligi.yml", "data/mobs");
      this.util.addFile("inek.yml", "data/mobs");
      this.util.addFile("iskelet.yml", "data/mobs");
      this.util.addFile("koylu.yml", "data/mobs");
      this.util.addFile("koyun.yml", "data/mobs");
      this.util.addFile("leopar.yml", "data/mobs");
      this.util.addFile("kurt.yml", "data/mobs");
      this.util.addFile("magaraorumcegi.yml", "data/mobs");
      this.util.addFile("magmakubu.yml", "data/mobs");
      this.util.addFile("moontar.yml", "data/mobs");
      this.util.addFile("orumcek.yml", "data/mobs");
      this.util.addFile("samplemob.yml", "data/mobs");
      this.util.addFile("tavsan.yml", "data/mobs");
      this.util.addFile("tavuk.yml", "data/mobs");
      this.util.addFile("yarasa.yml", "data/mobs");
      this.util.addFile("zombi.yml", "data/mobs");
      this.util.addFile("zombidomuzadam.yml", "data/mobs");
      this.util.addFile("altin.yml", "data/spawners");
      this.util.addFile("altinblok.yml", "data/spawners");
      this.util.addFile("demir.yml", "data/spawners");
      this.util.addFile("demirblok.yml", "data/spawners");
      this.util.addFile("elmas.yml", "data/spawners");
      this.util.addFile("elmasblok.yml", "data/spawners");
      this.util.addFile("endtasi.yml", "data/spawners");
      this.util.addFile("kiziltas.yml", "data/spawners");
      this.util.addFile("kiziltasblok.yml", "data/spawners");
      this.util.addFile("komur.yml", "data/spawners");
      this.util.addFile("komurblok.yml", "data/spawners");
      this.util.addFile("kuvars.yml", "data/spawners");
      this.util.addFile("kuvarsblok.yml", "data/spawners");
      this.util.addFile("prizmarin.yml", "data/spawners");
      this.util.addFile("samplespawner.yml", "data/spawners");
      this.util.addFile("sunger.yml", "data/spawners");
      this.util.addFile("zumrut.yml", "data/spawners");
      this.util.addFile("zumrutblok.yml", "data/spawners");
      this.util.addFile("ahtapot.yml", "data/spawners");
      this.util.addFile("at.yml", "data/spawners");
      this.util.addFile("balcik.yml", "data/spawners");
      this.util.addFile("blaze.yml", "data/spawners");
      this.util.addFile("cadi.yml", "data/spawners");
      this.util.addFile("creeper.yml", "data/spawners");
      this.util.addFile("domuz.yml", "data/spawners");
      this.util.addFile("enderman.yml", "data/spawners");
      this.util.addFile("ghast.yml", "data/spawners");
      this.util.addFile("gumusbaligi.yml", "data/spawners");
      this.util.addFile("inek.yml", "data/spawners");
      this.util.addFile("iskelet.yml", "data/spawners");
      this.util.addFile("koylu.yml", "data/spawners");
      this.util.addFile("koyun.yml", "data/spawners");
      this.util.addFile("leopar.yml", "data/spawners");
      this.util.addFile("kurt.yml", "data/spawners");
      this.util.addFile("magaraorumcegi.yml", "data/spawners");
      this.util.addFile("magmakubu.yml", "data/spawners");
      this.util.addFile("moontar.yml", "data/spawners");
      this.util.addFile("orumcek.yml", "data/spawners");
      this.util.addFile("tavsan.yml", "data/spawners");
      this.util.addFile("tavuk.yml", "data/spawners");
      this.util.addFile("yarasa.yml", "data/spawners");
      this.util.addFile("zombi.yml", "data/spawners");
      this.util.addFile("zombidomuzadam.yml", "data/spawners");
   }

   private void registerListeners() {
      this.util.addListener(new BlockBreakListener(), new BlockPlaceListener(), new EntityDeathListener(), new EntitySpawnListener());
   }

   public IMobUtil getIMobUtil() {
      return this.iMobUtil;
   }

   public ISpawnerUtil getISpawnerUtil() {
      return this.iSpawnerUtil;
   }

   public IItemUtil getIItemUtil() {
      return this.iItemUtil;
   }

   public ConfigurationFile getConfigs() {
      return this.configs;
   }

   public ConfigurationFile getSettings() {
      return this.settings;
   }

   public ConfigurationFile getMessages() {
      return this.messages;
   }

   public ConfigurationFile getCommands() {
      return this.commands;
   }

   public T3SL4Util getUtil() {
      return this.util;
   }

   public static TornadoSpawnersPlus getInstance() {
      if (instance == null) {
         throw new IllegalStateException("TornadoSpawners+ isn't loaded!");
      } else {
         return instance;
      }
   }
}

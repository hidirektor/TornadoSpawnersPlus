package com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner.arguments.RequiredRangeArgument;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner.arguments.SpawnArgument;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner.arguments.SpawnTypeArgument;
import com.infumia.t3sl4.tornadoplus.tornadoputil.builders.ItemFactory;
import com.infumia.t3sl4.tornadoplus.tornadoputil.configuration.ConfigurationFile;
import com.infumia.t3sl4.tornadoplus.tornadoputil.misc.InventoryUtil;
import com.infumia.t3sl4.tornadoplus.tornadoputil.misc.PluginUtil;
import com.infumia.t3sl4.tornadoplus.tornadoputil.misc.StringUtil;
import com.infumia.t3sl4.tornadoplus.tornadoputil.misc.XMaterial;
import com.infumia.t3sl4.tornadoplus.tornadopsp.TornadoSpawnersPlus;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.IEntityArgument;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner.arguments.DelayArgument;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner.arguments.MaxSpawnEntityArgument;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner.arguments.RangeArgument;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner.arguments.SpawnCountArgument;
import com.infumia.t3sl4.tornadoplus.tornadopsp.misc.VariableName;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public final class BasicSpawner implements ISpawner {
   private final ConfigurationFile file;
   private final Map<String, ISpawnerArgument> arguments;

   public BasicSpawner(ConfigurationFile file) {
      this.arguments = new HashMap();
      this.file = file;
      TornadoSpawnersPlus.SPAWNERS.put(this.convertID(), this);
   }

   public BasicSpawner(String id) {
      this(TornadoSpawnersPlus.getInstance().getUtil().addFile(id, "data/spawners"));
      this.file.set("spawn-type", "");
      this.file.set("spawn", "");
      this.file.set("options.delay", 5);
      this.file.set("options.range", 2);
      this.file.set("options.required-player-range", 10);
      this.file.set("options.max-spawn-entity", 3);
      this.file.set("options.spawn-count", 1);
      this.register();
   }

   public Object pullArgument(String key) {
      return this.arguments.get(key) != null ? ((ISpawnerArgument)this.arguments.get(key)).convert() : null;
   }

   public void pushArgument(String key, IEntityArgument argument) {
      this.arguments.put(key, (ISpawnerArgument)argument);
      this.save();
   }

   public void register() {
      this.arguments.put("spawnType", new SpawnTypeArgument(this.file.getString("spawn-type")));
      this.arguments.put("spawn", new SpawnArgument((String)this.file.get("spawn")));
      this.arguments.put("delay", new DelayArgument((Integer)this.file.get("options.delay")));
      this.arguments.put("range", new RangeArgument((Integer)this.file.get("options.range")));
      this.arguments.put("requiredPlayerRange", new RequiredRangeArgument((Integer)this.file.get("options.required-player-range")));
      this.arguments.put("maxSpawnEntity", new MaxSpawnEntityArgument((Integer)this.file.get("options.max-spawn-entity")));
      this.arguments.put("spawnCount", new SpawnCountArgument((Integer)this.file.get("options.spawn-count")));
   }

   public void save() {
      this.file.set("spawn-type", this.pullArgument("spawnType"));
      this.file.set("spawn", this.pullArgument("spawn"));
      this.file.set("options.delay", this.pullArgument("delay"));
      this.file.set("options.range", this.pullArgument("range"));
      this.file.set("options.required-player-range", this.pullArgument("requiredPlayerRange"));
      this.file.set("options.max-spawn-entity", this.pullArgument("maxSpawnEntity"));
      this.file.set("options.spawn-count", this.pullArgument("spawnCount"));
   }

   public void remove() {
      TornadoSpawnersPlus.SPAWNERS.remove(this.convertID());
      this.file.delete();
   }

   public ItemStack create() {
      String displayName = StringUtil.getInstance().chatColor(TornadoSpawnersPlus.getInstance().getConfigs().getString("spawner-settings.spawner-name").replaceAll("%spawner-id%", this.convertID()).replaceAll("%prefix%", (String)TornadoSpawnersPlus.getInstance().getSettings().get("prefix")));
      List<String> lore = StringUtil.getInstance().chatColorL(StringUtil.getInstance().replaceAllInList((List)TornadoSpawnersPlus.getInstance().getConfigs().get("spawner-settings.spawner-lore"), "%spawn-id%", (String)this.file.get("spawn")));
      ItemStack itemStack = ItemFactory.ItemFactoryBuilder.builder().material(XMaterial.SPAWNER.parseMaterial()).displayName(displayName).lore(lore).build().createItemStack();
      itemStack = TornadoSpawnersPlus.getInstance().getIItemUtil().addIItemId(itemStack, this.convertID());
      return itemStack;
   }

   public boolean give(Player player) {
      if (InventoryUtil.getInstance().checkInventory(player, this.create())) {
         player.getInventory().addItem(new ItemStack[]{this.create()});
         return true;
      } else {
         PluginUtil.getInstance().sendMessage(VariableName.pullMessage("error.your-inventory-is-full"), player);
         return false;
      }
   }

   public void placeSpawner(Location location) {
      TornadoSpawnersPlus.getInstance().getISpawnerUtil().placeSpawner(this, location);
   }

   public boolean breakSpawner(Location location, Player player) {
      if ((Boolean)TornadoSpawnersPlus.getInstance().getConfigs().get("give-the-broken-spawner-instant")) {
         if (!InventoryUtil.getInstance().checkInventory(player, this.create())) {
            PluginUtil.getInstance().sendMessage(VariableName.pullMessage("error.your-inventory-is-full"), player);
            return false;
         }

         Bukkit.getScheduler().runTaskLater(TornadoSpawnersPlus.getInstance(), () -> {
            location.getBlock().setType(Material.AIR);
         }, 1L);
         player.getInventory().addItem(new ItemStack[]{this.create()});
      } else {
         location.getBlock().breakNaturally();
      }

      PluginUtil.getInstance().sendMessage(VariableName.pullMessage("general.spawner-has-been-broken"), player);
      return true;
   }

   public Object convertNBT() {
      return TornadoSpawnersPlus.getInstance().getISpawnerUtil().convertNBT(this);
   }

   public String convertID() {
      return this.file.name();
   }
}

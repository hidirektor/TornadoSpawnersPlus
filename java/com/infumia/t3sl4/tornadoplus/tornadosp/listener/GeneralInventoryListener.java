package com.infumia.t3sl4.tornadoplus.tornadopsp.listener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.infumia.t3sl4.tornadoplus.tornadoputil.builders.ItemFactory;
import com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.Page;
import com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.Pane;
import com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.Requirement;
import com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.Target;
import com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.event.ElementBasicEvent;
import com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.event.ElementClickEvent;
import com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.event.ElementDragEvent;
import com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.pane.BasicPane;
import com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.requirement.ClickTypeReq;
import com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.target.BasicTarget;
import com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.target.ClickTarget;
import com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.target.DragTarget;
import com.infumia.t3sl4.tornadoplus.tornadoputil.misc.PluginUtil;
import com.infumia.t3sl4.tornadoplus.tornadoputil.misc.StringUtil;
import com.infumia.t3sl4.tornadoplus.tornadoputil.misc.XMaterial;
import com.infumia.t3sl4.tornadoplus.tornadoputil.misc.api.AnvilUtil;
import com.infumia.t3sl4.tornadoplus.tornadopsp.TornadoSpawnersPlus;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.IEntity;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.BasicItem;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.IItem;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.argument.AmountArgument;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.argument.DisplayNameArgument;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.argument.EnchantmentArgument;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.argument.FlagArgument;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.argument.LoreArgument;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.argument.MaterialArgument;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.argument.UnbreakableArgument;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.mob.BasicMob;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.mob.IMob;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.mob.argument.ArmorArgument;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.mob.argument.CustomNameArgument;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.mob.argument.DamageArgument;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.mob.argument.DropArgument;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.mob.argument.EquipmentArgument;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.mob.argument.FollowRangeArgument;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.mob.argument.HealthArgument;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.mob.argument.KnockbackResistanceArgument;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.mob.argument.MobNameArgument;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.mob.argument.MovementSpeedArgument;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.mob.argument.TargetArgument;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner.BasicSpawner;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner.ISpawner;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner.arguments.DelayArgument;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner.arguments.MaxSpawnEntityArgument;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner.arguments.RangeArgument;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner.arguments.RequiredRangeArgument;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner.arguments.SpawnArgument;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner.arguments.SpawnCountArgument;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner.arguments.SpawnTypeArgument;
import com.infumia.t3sl4.tornadoplus.tornadopsp.misc.VariableName;
import com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.Element;
import com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.element.BasicElement;
import com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.page.ChestPage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class GeneralInventoryListener implements Listener {
   private Element GENERAL_MENU_ITEM_ICON;
   private Element GENERAL_MENU_MOB_ICON;
   private Element GENERAL_MENU_SPAWNER_ICON;
   private Pane GENERAL_MENU_PANE;
   private Page GENERAL_MENU_PAGE;
   private Element MOB_MENU_HEAD_ICON;
   private Element MOB_MENU_ENTITYTYPE_ICON;
   private Element MOB_MENU_HEALTH_ICON;
   private Element MOB_MENU_DAMAGE_ICON;
   private Element MOB_MENU_ARMOR_ICON;
   private Element MOB_MENU_MOVEMENT_ICON;
   private Element MOB_MENU_FOLLOW_ICON;
   private Element MOB_MENU_KNOCKBACK_ICON;
   private Element MOB_MENU_DISPLAY_ICON;
   private Element MOB_MENU_TARGETS_ICON;
   private Element MOB_MENU_EQUIPMENTS_ICON;
   private Element MOB_MENU_DROPS_ICON;
   private Element MOB_MENU_BARRIER_ICON;
   private Pane MOB_MENU_PANE;
   private Page MOB_MENU_PAGE;
   private Element ITEM_MENU_ITEM_ICON;
   private Element ITEM_MENU_MATERIAL_ICON;
   private Element ITEM_MENU_COUNT_ICON;
   private Element ITEM_MENU_DISPLAY_ICON;
   private Element ITEM_MENU_LORE_ICON;
   private Element ITEM_MENU_ENCH_ICON;
   private Element ITEM_MENU_FLAG_ICON;
   private Element ITEM_MENU_UNBREAKABLE_ICON;
   private Element ITEM_MENU_BARRIER_ICON;
   private Pane ITEM_MENU_PANE;
   private Page ITEM_MENU_PAGE;
   private Element SPAWNER_MENU_SPAWNER_ICON;
   private Element SPAWNER_MENU_SPAWN_TYPE_ICON;
   private Element SPAWNER_MENU_SPAWN_ICON;
   private Element SPAWNER_MENU_DELAY_ICON;
   private Element SPAWNER_MENU_RANGE_ICON;
   private Element SPAWNER_MENU_REQUIRED_PLAYER_RANGE_ICON;
   private Element SPAWNER_MENU_MAX_SPAWN_ENTITY_ICON;
   private Element SPAWNER_MENU_SPAWN_COUNT_ICON;
   private Element SPAWNER_MENU_BARRIER_ICON;
   private Pane SPAWNER_MENU_PANE;
   private Page SPAWNER_MENU_PAGE;
   private final Player PLAYER;
   private final AnvilUtil anvil;
   private final TornadoSpawnersPlus instance = TornadoSpawnersPlus.getInstance();

   private GeneralInventoryListener(Player player) {
      this.PLAYER = player;
      this.anvil = AnvilUtil.init(TornadoSpawnersPlus.getInstance(), player);
   }

   private void initCreatePage() {
      this.GENERAL_MENU_ITEM_ICON = new BasicElement(ItemFactory.ItemFactoryBuilder.builder().material(XMaterial.DIAMOND_SWORD.parseMaterial()).displayName(StringUtil.getInstance().chatColor("&aItem creat menu")).build().createItemStack(), new Target[]{new ClickTarget((event) -> {
         event.cancel();
         event.closeView();
         Bukkit.getScheduler().runTaskLater(this.instance, () -> {
            this.anvil.open("Type...", (anvilClicker, reply) -> {
               anvilClicker.closeInventory();
               if (TornadoSpawnersPlus.ITEMS.get(reply) != null) {
                  PluginUtil.getInstance().sendMessage(VariableName.pullMessage("error.there-is-item"), anvilClicker);
               } else {
                  this.openEditMenu(new BasicItem(reply));
               }

               return reply;
            });
         }, 3L);
      }, new Requirement[0]), new DragTarget(ElementDragEvent::cancel, new Requirement[0])});
      this.GENERAL_MENU_MOB_ICON = new BasicElement(ItemFactory.ItemFactoryBuilder.builder().material(XMaterial.ZOMBIE_SPAWN_EGG.parseMaterial()).displayName(StringUtil.getInstance().chatColor("&aMob create menu")).build().createItemStack(), new Target[]{new ClickTarget((event) -> {
         event.cancel();
         event.closeView();
         Bukkit.getScheduler().runTaskLater(this.instance, () -> {
            this.anvil.open("Type...", (anvilClicker, reply) -> {
               anvilClicker.closeInventory();
               if (TornadoSpawnersPlus.MOBS.get(reply) != null) {
                  PluginUtil.getInstance().sendMessage(VariableName.pullMessage("error.there-is-mob"), anvilClicker);
               } else {
                  this.openEditMenu(new BasicMob(reply));
               }

               return reply;
            });
         }, 3L);
      }, new Requirement[0]), new DragTarget(ElementDragEvent::cancel, new Requirement[0])});
      this.GENERAL_MENU_SPAWNER_ICON = new BasicElement(ItemFactory.ItemFactoryBuilder.builder().material(XMaterial.SPAWNER.parseMaterial()).displayName(StringUtil.getInstance().chatColor("&aSpawner create menu")).build().createItemStack(), new Target[]{new ClickTarget((event) -> {
         event.cancel();
         event.closeView();
         Bukkit.getScheduler().runTaskLater(this.instance, () -> {
            this.anvil.open("Type...", (anvilClicker, reply) -> {
               anvilClicker.closeInventory();
               if (TornadoSpawnersPlus.SPAWNERS.get(reply) != null) {
                  PluginUtil.getInstance().sendMessage(VariableName.pullMessage("error.there-is-spawner"), anvilClicker);
               } else {
                  this.openEditMenu(new BasicSpawner(reply));
               }

               return reply;
            });
         }, 3L);
      }, new Requirement[0]), new DragTarget(ElementDragEvent::cancel, new Requirement[0])});
      this.GENERAL_MENU_PANE = new BasicPane(0, 0, 3, 9);
      this.GENERAL_MENU_PANE.insert(this.GENERAL_MENU_ITEM_ICON, 2, 1, true);
      this.GENERAL_MENU_PANE.insert(this.GENERAL_MENU_MOB_ICON, 4, 1, true);
      this.GENERAL_MENU_PANE.insert(this.GENERAL_MENU_SPAWNER_ICON, 6, 1, true);
      this.GENERAL_MENU_PAGE = new ChestPage(StringUtil.getInstance().chatColor("&aCreate an Entity"), 27, new Pane[]{this.GENERAL_MENU_PANE});
   }

   private void initSpawnerEditPage(ISpawner entity) {
      String spawnType = (String)entity.pullArgument("spawnType");
      String spawn = (String)entity.pullArgument("spawn");
      int delay = (Integer)entity.pullArgument("delay");
      int range = (Integer)entity.pullArgument("range");
      int requiredPlayerRange = (Integer)entity.pullArgument("requiredPlayerRange");
      int maxSpawnEntity = (Integer)entity.pullArgument("maxSpawnEntity");
      int spawnCount = (Integer)entity.pullArgument("spawnCount");
      List<String> properties = new ArrayList();
      properties.add("&eSpawn Type: &r&l" + spawnType);
      properties.add("&eSpawn: &r&l" + spawn);
      properties.add("&eDelay: &r&l" + delay);
      properties.add("&eRange: &r&l" + range);
      properties.add("&eRequired Player Range: &r&l" + requiredPlayerRange);
      properties.add("&eMax Spawn Entity: &r&l" + maxSpawnEntity);
      properties.add("&eSpawn Count: &r&l" + spawnCount);
      this.SPAWNER_MENU_SPAWNER_ICON = new BasicElement(ItemFactory.ItemFactoryBuilder.builder().material(XMaterial.SPAWNER.parseMaterial()).displayName(StringUtil.getInstance().chatColor("&a&l" + entity.convertID())).lore(StringUtil.getInstance().chatColorL(properties)).build().createItemStack(), "spawnerProperties", new Target[]{new BasicTarget(ElementBasicEvent::cancel, new Requirement[0])});
      ItemStack spawnTypeItem = ItemFactory.ItemFactoryBuilder.builder().material(XMaterial.ZOMBIE_SPAWN_EGG.parseMaterial()).displayName(StringUtil.getInstance().chatColor("&a&lSpawn Type")).lore(StringUtil.getInstance().chatColorL(Collections.singletonList("&e" + spawnType))).build().createItemStack();
      if (spawnType.equalsIgnoreCase("item")) {
         spawnTypeItem.setType(Material.DIAMOND_SWORD);
      }

      this.SPAWNER_MENU_SPAWN_TYPE_ICON = new BasicElement(spawnTypeItem, "spawnType", new Target[]{new ClickTarget((elementClickEvent) -> {
         if (spawnType.equalsIgnoreCase("item")) {
            entity.pushArgument("spawnType", new SpawnTypeArgument("mob"));
         } else {
            entity.pushArgument("spawnType", new SpawnTypeArgument("item"));
         }

         this.openEditMenu(entity);
      }, new Requirement[0]), new DragTarget(ElementDragEvent::cancel, new Requirement[0]), new BasicTarget(ElementBasicEvent::cancel, new Requirement[0])});
      this.SPAWNER_MENU_SPAWN_ICON = new BasicElement(ItemFactory.ItemFactoryBuilder.builder().material(Material.BOWL).displayName(StringUtil.getInstance().chatColor("&a&lSpawn")).lore(StringUtil.getInstance().chatColorL(Collections.singletonList("&e" + spawn))).build().createItemStack(), "spawn", new Target[]{new ClickTarget((elementClickEvent) -> {
         elementClickEvent.cancel();
         elementClickEvent.closeView();
         Bukkit.getScheduler().runTaskLater(this.instance, () -> {
            this.anvil.open("Type...", (anvilClicker, reply) -> {
               anvilClicker.closeInventory();
               if (spawnType.equalsIgnoreCase("item")) {
                  if (TornadoSpawnersPlus.ITEMS.get(reply) == null) {
                     PluginUtil.getInstance().sendMessage(VariableName.pullMessage("error.unknown-id"), anvilClicker);
                  } else {
                     entity.pushArgument("spawn", new SpawnArgument(reply));
                     this.openEditMenu(entity);
                  }
               } else if (TornadoSpawnersPlus.MOBS.get(reply) == null) {
                  PluginUtil.getInstance().sendMessage(VariableName.pullMessage("error.unknown-id"), anvilClicker);
               } else {
                  entity.pushArgument("spawn", new SpawnArgument(reply));
                  this.openEditMenu(entity);
               }

               return reply;
            });
         }, 3L);
      }, new Requirement[0]), new DragTarget(ElementDragEvent::cancel, new Requirement[0]), new BasicTarget(ElementBasicEvent::cancel, new Requirement[0])});
      this.SPAWNER_MENU_DELAY_ICON = new BasicElement(ItemFactory.ItemFactoryBuilder.builder().material(XMaterial.CLOCK.parseMaterial()).displayName(StringUtil.getInstance().chatColor("&a&lDelay")).lore(StringUtil.getInstance().chatColorL(Collections.singletonList("&e" + delay))).build().createItemStack(), "delay", new Target[]{new ClickTarget((elementClickEvent) -> {
         entity.pushArgument("delay", new DelayArgument(delay + 1));
         this.openEditMenu(entity);
      }, new Requirement[]{new ClickTypeReq(ClickType.LEFT)}), new ClickTarget((elementClickEvent) -> {
         if (delay - 1 > 0) {
            entity.pushArgument("delay", new DelayArgument(delay - 1));
            this.openEditMenu(entity);
         }

      }, new Requirement[]{new ClickTypeReq(ClickType.RIGHT)}), new DragTarget(ElementDragEvent::cancel, new Requirement[0]), new BasicTarget(ElementBasicEvent::cancel, new Requirement[0])});
      this.SPAWNER_MENU_RANGE_ICON = new BasicElement(ItemFactory.ItemFactoryBuilder.builder().material(Material.ARROW).displayName(StringUtil.getInstance().chatColor("&a&lRange")).lore(StringUtil.getInstance().chatColorL(Collections.singletonList("&e" + range))).build().createItemStack(), "range", new Target[]{new ClickTarget((elementClickEvent) -> {
         entity.pushArgument("range", new RangeArgument(range + 1));
         this.openEditMenu(entity);
      }, new Requirement[]{new ClickTypeReq(ClickType.LEFT)}), new ClickTarget((elementClickEvent) -> {
         if (range - 1 > 0) {
            entity.pushArgument("range", new RangeArgument(range - 1));
            this.openEditMenu(entity);
         }

      }, new Requirement[]{new ClickTypeReq(ClickType.RIGHT)}), new DragTarget(ElementDragEvent::cancel, new Requirement[0]), new BasicTarget(ElementBasicEvent::cancel, new Requirement[0])});
      this.SPAWNER_MENU_REQUIRED_PLAYER_RANGE_ICON = new BasicElement(ItemFactory.ItemFactoryBuilder.builder().material(XMaterial.PLAYER_HEAD.parseMaterial()).displayName(StringUtil.getInstance().chatColor("&a&lRequired Player Range")).lore(StringUtil.getInstance().chatColorL(Collections.singletonList("&e" + requiredPlayerRange))).build().createItemStack(), "requiredPlayerRange", new Target[]{new ClickTarget((elementClickEvent) -> {
         if (requiredPlayerRange - 1 > 0) {
            entity.pushArgument("requiredPlayerRange", new RequiredRangeArgument(requiredPlayerRange - 1));
            this.openEditMenu(entity);
         }

      }, new Requirement[]{new ClickTypeReq(ClickType.RIGHT)}), new ClickTarget((elementClickEvent) -> {
         entity.pushArgument("requiredPlayerRange", new RequiredRangeArgument(requiredPlayerRange + 1));
         this.openEditMenu(entity);
      }, new Requirement[]{new ClickTypeReq(ClickType.LEFT)}), new DragTarget(ElementDragEvent::cancel, new Requirement[0]), new BasicTarget(ElementBasicEvent::cancel, new Requirement[0])});
      this.SPAWNER_MENU_SPAWN_COUNT_ICON = new BasicElement(ItemFactory.ItemFactoryBuilder.builder().material(Material.APPLE).displayName(StringUtil.getInstance().chatColor("&a&lSpawn Count")).lore(StringUtil.getInstance().chatColorL(Collections.singletonList("&e" + spawnCount))).build().createItemStack(), "spawnCount", new Target[]{new ClickTarget((elementClickEvent) -> {
         if (spawnCount - 1 > 0) {
            entity.pushArgument("spawnCount", new SpawnCountArgument(spawnCount - 1));
            this.openEditMenu(entity);
         }

      }, new Requirement[]{new ClickTypeReq(ClickType.RIGHT)}), new ClickTarget((elementClickEvent) -> {
         entity.pushArgument("spawnCount", new SpawnCountArgument(spawnCount + 1));
         this.openEditMenu(entity);
      }, new Requirement[]{new ClickTypeReq(ClickType.LEFT)}), new DragTarget(ElementDragEvent::cancel, new Requirement[0]), new BasicTarget(ElementBasicEvent::cancel, new Requirement[0])});
      this.SPAWNER_MENU_MAX_SPAWN_ENTITY_ICON = new BasicElement(ItemFactory.ItemFactoryBuilder.builder().material(XMaterial.WHEAT_SEEDS.parseMaterial()).displayName(StringUtil.getInstance().chatColor("&a&lMax Spawn Entity")).lore(StringUtil.getInstance().chatColorL(Collections.singletonList("&e" + maxSpawnEntity))).build().createItemStack(), "maxSpawnEntity", new Target[]{new ClickTarget((elementClickEvent) -> {
         if (maxSpawnEntity - 1 > 0) {
            entity.pushArgument("maxSpawnEntity", new MaxSpawnEntityArgument(maxSpawnEntity - 1));
            this.openEditMenu(entity);
         }

      }, new Requirement[]{new ClickTypeReq(ClickType.RIGHT)}), new ClickTarget((elementClickEvent) -> {
         entity.pushArgument("maxSpawnEntity", new MaxSpawnEntityArgument(maxSpawnEntity + 1));
         this.openEditMenu(entity);
      }, new Requirement[]{new ClickTypeReq(ClickType.LEFT)}), new DragTarget(ElementDragEvent::cancel, new Requirement[0]), new BasicTarget(ElementBasicEvent::cancel, new Requirement[0])});
      this.SPAWNER_MENU_BARRIER_ICON = new BasicElement(ItemFactory.ItemFactoryBuilder.builder().material(Material.BARRIER).displayName(StringUtil.getInstance().chatColor("&c&lDelete Spawner")).build().createItemStack(), new Target[]{new ClickTarget((elementClickEvent) -> {
         elementClickEvent.cancel();
         elementClickEvent.closeView();
         entity.remove();
      }, new Requirement[0]), new DragTarget(ElementDragEvent::cancel, new Requirement[0]), new BasicTarget(ElementBasicEvent::cancel, new Requirement[0])});
      Element window = new BasicElement(ItemFactory.ItemFactoryBuilder.builder().material(XMaterial.GRAY_STAINED_GLASS_PANE.parseMaterial()).build().createItemStack(), new Target[]{new ClickTarget(ElementClickEvent::cancel, new Requirement[0]), new DragTarget(ElementDragEvent::cancel, new Requirement[0]), new BasicTarget(ElementBasicEvent::cancel, new Requirement[0])});
      this.SPAWNER_MENU_PANE = new BasicPane(0, 0, 6, 9);
      this.SPAWNER_MENU_PANE.insert(this.SPAWNER_MENU_SPAWNER_ICON, 4, 1, true);
      this.SPAWNER_MENU_PANE.insert(this.SPAWNER_MENU_SPAWN_TYPE_ICON, 1, 3, true);
      this.SPAWNER_MENU_PANE.insert(this.SPAWNER_MENU_SPAWN_ICON, 2, 3, true);
      this.SPAWNER_MENU_PANE.insert(this.SPAWNER_MENU_DELAY_ICON, 3, 3, true);
      this.SPAWNER_MENU_PANE.insert(this.SPAWNER_MENU_RANGE_ICON, 4, 3, true);
      this.SPAWNER_MENU_PANE.insert(this.SPAWNER_MENU_REQUIRED_PLAYER_RANGE_ICON, 5, 3, true);
      this.SPAWNER_MENU_PANE.insert(this.SPAWNER_MENU_SPAWN_COUNT_ICON, 6, 3, true);
      this.SPAWNER_MENU_PANE.insert(this.SPAWNER_MENU_MAX_SPAWN_ENTITY_ICON, 7, 3, true);
      this.SPAWNER_MENU_PANE.insert(this.SPAWNER_MENU_BARRIER_ICON, 4, 4, true);

      for(int i = 0; i < 9; ++i) {
         this.SPAWNER_MENU_PANE.insert(window, i, 0, true);
         this.SPAWNER_MENU_PANE.insert(window, i, 5, true);
         if (i > 0 && i < 5) {
            this.SPAWNER_MENU_PANE.insert(window, 0, i, true);
            this.SPAWNER_MENU_PANE.insert(window, 8, i, true);
         }
      }

      this.SPAWNER_MENU_PAGE = new ChestPage(StringUtil.getInstance().chatColor("&a&lSpawner Edit"), 54, new Pane[]{this.SPAWNER_MENU_PANE});
   }

   private void initItemEditPage(IItem entity) {
      Material material = (Material)entity.pullArgument("material");
      int amount = (Integer)entity.pullArgument("amount");
      String display = (String)entity.pullArgument("displayName");
      List<String> lore = (List)entity.pullArgument("lore");
      List<String> enchantments = new ArrayList();
      Map<Enchantment, Integer> enchantmentIntegerMap = (Map)entity.pullArgument("enchantments");
      Iterator var8 = enchantmentIntegerMap.entrySet().iterator();

      while(var8.hasNext()) {
         Entry<Enchantment, Integer> enchantment = (Entry)var8.next();
         enchantments.add(((Enchantment)enchantment.getKey()).getName() + ": " + enchantmentIntegerMap.get(enchantment.getKey()));
      }

      List<String> flag = new ArrayList();
      List<ItemFlag> flags = (List)entity.pullArgument("flags");
      Iterator var10 = flags.iterator();

      while(var10.hasNext()) {
         ItemFlag itemFlag = (ItemFlag)var10.next();
         flag.add(itemFlag.name());
      }

      boolean unbreakable = (Boolean)entity.pullArgument("unbreakable");
      List<String> properties = new ArrayList();
      properties.add("&eMaterial: " + material);
      properties.add("&eAmount: " + amount);
      properties.add("&eDisplay Name: &r" + display);
      properties.add("&eLore:");
      properties.addAll(lore);
      properties.add("&eEnchantments:");
      properties.addAll(enchantments);
      properties.add("&eFlags:");
      properties.addAll(flag);
      properties.add("&eUnbreakable: " + unbreakable);
      this.ITEM_MENU_ITEM_ICON = new BasicElement(ItemFactory.ItemFactoryBuilder.builder().material(Material.STICK).displayName(StringUtil.getInstance().chatColor("&a&l" + entity.convertID())).lore(StringUtil.getInstance().chatColorL(properties)).build().createItemStack(), "item", new Target[]{new ClickTarget(ElementClickEvent::cancel, new Requirement[0]), new DragTarget(ElementDragEvent::cancel, new Requirement[0]), new BasicTarget(ElementBasicEvent::cancel, new Requirement[0])});
      this.ITEM_MENU_MATERIAL_ICON = new BasicElement(ItemFactory.ItemFactoryBuilder.builder().material(material).displayName(StringUtil.getInstance().chatColor("&a&lMaterial")).build().createItemStack(), "material", new Target[]{new ClickTarget((elementClickEvent) -> {
         elementClickEvent.cancel();
         elementClickEvent.closeView();
         Bukkit.getScheduler().runTaskLater(this.instance, () -> {
            this.anvil.open("Type...", (anvilClicker, reply) -> {
               anvilClicker.closeInventory();
               if (Material.getMaterial(reply) == null) {
                  PluginUtil.getInstance().sendMessage(VariableName.pullMessage("error.material-not-found").replaceAll("%file-name%", entity.convertID()), anvilClicker);
               } else {
                  entity.pushArgument("material", new MaterialArgument(reply));
                  this.openEditMenu(entity);
               }

               return reply;
            });
         }, 3L);
      }, new Requirement[0]), new DragTarget(ElementDragEvent::cancel, new Requirement[0]), new BasicTarget(ElementBasicEvent::cancel, new Requirement[0])});
      this.ITEM_MENU_COUNT_ICON = new BasicElement(ItemFactory.ItemFactoryBuilder.builder().material(Material.APPLE).displayName(StringUtil.getInstance().chatColor("&a&lAmount")).lore(StringUtil.getInstance().chatColorL(Collections.singletonList("&e" + amount))).build().createItemStack(), "amount", new Target[]{new ClickTarget((elementClickEvent) -> {
         entity.pushArgument("amount", new AmountArgument(amount + 1));
         this.openEditMenu(entity);
      }, new Requirement[]{new ClickTypeReq(ClickType.LEFT)}), new ClickTarget((elementClickEvent) -> {
         if (amount - 1 > 0) {
            entity.pushArgument("amount", new AmountArgument(amount - 1));
            this.openEditMenu(entity);
         }

      }, new Requirement[]{new ClickTypeReq(ClickType.RIGHT)}), new DragTarget(ElementDragEvent::cancel, new Requirement[0]), new BasicTarget(ElementBasicEvent::cancel, new Requirement[0])});
      this.ITEM_MENU_DISPLAY_ICON = new BasicElement(ItemFactory.ItemFactoryBuilder.builder().material(Material.NAME_TAG).displayName(StringUtil.getInstance().chatColor("&a&lDisplay Name")).lore(StringUtil.getInstance().chatColorL(Collections.singletonList("&r" + display))).build().createItemStack(), "displayName", new Target[]{new ClickTarget((elementClickEvent) -> {
         elementClickEvent.cancel();
         elementClickEvent.closeView();
         Bukkit.getScheduler().runTaskLater(this.instance, () -> {
            this.anvil.open("Type...", (anvilClicker, reply) -> {
               anvilClicker.closeInventory();
               entity.pushArgument("displayName", new DisplayNameArgument(reply));
               this.openEditMenu(entity);
               return reply;
            });
         }, 3L);
      }, new Requirement[]{new ClickTypeReq(ClickType.LEFT)}), new ClickTarget((elementClickEvent) -> {
         elementClickEvent.cancel();
         elementClickEvent.closeView();
         entity.pushArgument("displayName", new DisplayNameArgument(""));
         Bukkit.getScheduler().runTaskLater(this.instance, () -> {
            this.openEditMenu(entity);
         }, 3L);
      }, new Requirement[]{new ClickTypeReq(ClickType.RIGHT)}), new DragTarget(ElementDragEvent::cancel, new Requirement[0]), new BasicTarget(ElementBasicEvent::cancel, new Requirement[0])});
      this.ITEM_MENU_LORE_ICON = new BasicElement(ItemFactory.ItemFactoryBuilder.builder().material(Material.PAPER).displayName(StringUtil.getInstance().chatColor("&a&lLore Name")).lore(StringUtil.getInstance().chatColorL(lore)).build().createItemStack(), "lore", new Target[]{new ClickTarget((elementClickEvent) -> {
         elementClickEvent.cancel();
         elementClickEvent.closeView();
         Bukkit.getScheduler().runTaskLater(this.instance, () -> {
            this.anvil.open("Type...", (anvilClicker, reply) -> {
               anvilClicker.closeInventory();
               lore.add(reply);
               entity.pushArgument("lore", new LoreArgument(lore));
               this.openEditMenu(entity);
               return reply;
            });
         }, 3L);
      }, new Requirement[]{new ClickTypeReq(ClickType.LEFT)}), new ClickTarget((elementClickEvent) -> {
         elementClickEvent.cancel();
         elementClickEvent.closeView();
         lore.remove(lore.size() - 1);
         entity.pushArgument("lore", new LoreArgument(lore));
         Bukkit.getScheduler().runTaskLater(this.instance, () -> {
            this.openEditMenu(entity);
         }, 3L);
      }, new Requirement[]{new ClickTypeReq(ClickType.RIGHT)}), new DragTarget(ElementDragEvent::cancel, new Requirement[0]), new BasicTarget(ElementBasicEvent::cancel, new Requirement[0])});
      this.ITEM_MENU_ENCH_ICON = new BasicElement(ItemFactory.ItemFactoryBuilder.builder().material(Material.BOOK).displayName(StringUtil.getInstance().chatColor("&a&lEnchantments")).lore(StringUtil.getInstance().chatColorL(enchantments)).build().createItemStack(), "enchantment", new Target[]{new ClickTarget((elementClickEvent) -> {
         elementClickEvent.cancel();
         elementClickEvent.closeView();
         Bukkit.getScheduler().runTaskLater(this.instance, () -> {
            this.anvil.open("Type...", (anvilClicker, reply) -> {
               anvilClicker.closeInventory();
               String[] enchStrings = reply.split(":");
               if (enchStrings.length == 2) {
                  if (Enchantment.getByName(enchStrings[0]) != null) {
                     enchantments.add(reply);
                     entity.pushArgument("enchantments", new EnchantmentArgument(enchantments));
                     this.openEditMenu(entity);
                  } else {
                     PluginUtil.getInstance().sendMessage(VariableName.pullMessage("error.ench-not-found").replaceAll("%file-name%", entity.convertID()), anvilClicker);
                  }
               } else {
                  PluginUtil.getInstance().sendMessage(VariableName.pullMessage("error.wrong-usage"), anvilClicker);
               }

               return reply;
            });
         }, 3L);
      }, new Requirement[]{new ClickTypeReq(ClickType.LEFT)}), new ClickTarget((elementClickEvent) -> {
         elementClickEvent.cancel();
         elementClickEvent.closeView();
         enchantments.remove(enchantments.size() - 1);
         entity.pushArgument("enchantments", new EnchantmentArgument(enchantments));
         this.openEditMenu(entity);
      }, new Requirement[]{new ClickTypeReq(ClickType.RIGHT)}), new DragTarget(ElementDragEvent::cancel, new Requirement[0]), new BasicTarget(ElementBasicEvent::cancel, new Requirement[0])});
      this.ITEM_MENU_FLAG_ICON = new BasicElement(ItemFactory.ItemFactoryBuilder.builder().material(XMaterial.BLACK_BANNER.parseMaterial()).displayName(StringUtil.getInstance().chatColor("&a&lItem Flags")).lore(StringUtil.getInstance().chatColorL(flag)).build().createItemStack(), "flag", new Target[]{new ClickTarget((elementClickEvent) -> {
         elementClickEvent.cancel();
         elementClickEvent.closeView();
         Bukkit.getScheduler().runTaskLater(this.instance, () -> {
            this.anvil.open("Type...", (anvilClicker, reply) -> {
               anvilClicker.closeInventory();
               if (ItemFlag.valueOf(reply) != null) {
                  flag.add(reply);
                  entity.pushArgument("flag", new FlagArgument(flag));
                  this.openEditMenu(entity);
               } else {
                  PluginUtil.getInstance().sendMessage(VariableName.pullMessage("error.flag-not-found").replaceAll("%file-name%", entity.convertID()), anvilClicker);
               }

               return reply;
            });
         }, 3L);
      }, new Requirement[]{new ClickTypeReq(ClickType.LEFT)}), new ClickTarget((elementClickEvent) -> {
         elementClickEvent.cancel();
         elementClickEvent.closeView();
         flag.remove(flag.size() - 1);
         entity.pushArgument("flag", new FlagArgument(flag));
         this.openEditMenu(entity);
      }, new Requirement[]{new ClickTypeReq(ClickType.RIGHT)}), new DragTarget(ElementDragEvent::cancel, new Requirement[0]), new BasicTarget(ElementBasicEvent::cancel, new Requirement[0])});
      this.ITEM_MENU_UNBREAKABLE_ICON = new BasicElement(ItemFactory.ItemFactoryBuilder.builder().material(Material.DIAMOND_CHESTPLATE).displayName(StringUtil.getInstance().chatColor("&a&lUnbreakable")).lore(StringUtil.getInstance().chatColorL(Collections.singletonList(unbreakable ? "&b" + unbreakable : "&c" + unbreakable))).build().createItemStack(), "unbreakable", new Target[]{new ClickTarget((elementClickEvent) -> {
         if (unbreakable) {
            entity.pushArgument("unbreakable", new UnbreakableArgument(false));
         } else {
            entity.pushArgument("unbreakable", new UnbreakableArgument(true));
         }

         this.openEditMenu(entity);
      }, new Requirement[0]), new DragTarget(ElementDragEvent::cancel, new Requirement[0]), new BasicTarget(ElementBasicEvent::cancel, new Requirement[0])});
      this.ITEM_MENU_BARRIER_ICON = new BasicElement(ItemFactory.ItemFactoryBuilder.builder().material(Material.BARRIER).displayName(StringUtil.getInstance().chatColor("&c&lDelete Item")).build().createItemStack(), new Target[]{new ClickTarget((elementClickEvent) -> {
         elementClickEvent.cancel();
         elementClickEvent.closeView();
         entity.remove();
      }, new Requirement[0]), new DragTarget(ElementDragEvent::cancel, new Requirement[0]), new BasicTarget(ElementBasicEvent::cancel, new Requirement[0])});
      Element window = new BasicElement(ItemFactory.ItemFactoryBuilder.builder().material(XMaterial.GRAY_STAINED_GLASS_PANE.parseMaterial()).build().createItemStack(), new Target[]{new ClickTarget(ElementClickEvent::cancel, new Requirement[0]), new DragTarget(ElementDragEvent::cancel, new Requirement[0]), new BasicTarget(ElementBasicEvent::cancel, new Requirement[0])});
      this.ITEM_MENU_PANE = new BasicPane(0, 0, 6, 9);
      this.ITEM_MENU_PANE.insert(this.ITEM_MENU_ITEM_ICON, 4, 1, true);
      this.ITEM_MENU_PANE.insert(this.ITEM_MENU_MATERIAL_ICON, 1, 3, true);
      this.ITEM_MENU_PANE.insert(this.ITEM_MENU_COUNT_ICON, 2, 3, true);
      this.ITEM_MENU_PANE.insert(this.ITEM_MENU_DISPLAY_ICON, 3, 3, true);
      this.ITEM_MENU_PANE.insert(this.ITEM_MENU_LORE_ICON, 4, 3, true);
      this.ITEM_MENU_PANE.insert(this.ITEM_MENU_ENCH_ICON, 5, 3, true);
      this.ITEM_MENU_PANE.insert(this.ITEM_MENU_FLAG_ICON, 6, 3, true);
      this.ITEM_MENU_PANE.insert(this.ITEM_MENU_UNBREAKABLE_ICON, 7, 3, true);
      this.ITEM_MENU_PANE.insert(this.ITEM_MENU_BARRIER_ICON, 4, 4, true);

      for(int i = 0; i < 9; ++i) {
         this.ITEM_MENU_PANE.insert(window, i, 0, true);
         this.ITEM_MENU_PANE.insert(window, i, 5, true);
         if (i > 0 && i < 5) {
            this.ITEM_MENU_PANE.insert(window, 0, i, true);
            this.ITEM_MENU_PANE.insert(window, 8, i, true);
         }
      }

      this.ITEM_MENU_PAGE = new ChestPage(StringUtil.getInstance().chatColor("&a&lItem Edit"), 54, new Pane[]{this.ITEM_MENU_PANE});
   }

   private void initMobEditPage(IMob entity) {
      EntityType mobName = (EntityType)entity.pullArgument("mobName");
      int health = (Integer)entity.pullArgument("health");
      int damage = (Integer)entity.pullArgument("damage");
      double armor = (Double)entity.pullArgument("armor");
      float movementSpeed = (Float)entity.pullArgument("movementSpeed");
      double followRange = (Double)entity.pullArgument("followRange");
      double knockbackResistance = (Double)entity.pullArgument("knockbackResistance");
      String customName = (String)entity.pullArgument("customName");
      List<EntityType> target = (List)entity.pullArgument("target");
      IItem[] equipment = (IItem[])entity.pullArgument("equipment");
      Map<IItem, Integer> drop = (Map)entity.pullArgument("drops");
      String customNames = customName.split(":")[0];
      boolean customNamesVisible = Boolean.parseBoolean(customName.split(":")[1]);
      List<String> targets = new ArrayList();
      target.forEach((target1) -> {
         targets.add(target1.name());
      });
      List<String> equipments = new ArrayList();
      equipments.add(equipment[0] != null ? equipment[0].convertID() : "");
      equipments.add(equipment[1] != null ? equipment[1].convertID() : "");
      equipments.add(equipment[2] != null ? equipment[2].convertID() : "");
      equipments.add(equipment[3] != null ? equipment[3].convertID() : "");
      List<String> drops = new ArrayList();
      Iterator var21 = drop.keySet().iterator();

      while(var21.hasNext()) {
         IItem item = (IItem)var21.next();
         drops.add(item.convertID() + ":" + drop.get(item));
      }

      List<String> properties = new ArrayList();
      properties.add("&eEntity Type: " + mobName.name());
      properties.add("&eHealth: " + health);
      properties.add("&eDamage: " + damage);
      properties.add("&eArmor: " + armor);
      properties.add("&eMovement Speed: " + movementSpeed);
      properties.add("&eFollow Range: " + followRange);
      properties.add("&eKnockback Resistance: " + knockbackResistance);
      properties.add("&eCustom Name: " + customNames);
      properties.add("&eCustom Name Visible: " + customNamesVisible);
      properties.add("&eTargets:");
      targets.forEach((targetS) -> {
         properties.add("&r" + targetS);
      });
      properties.add("&eEquipments:");
      properties.addAll(equipments);
      properties.add("&eDrops:");
      properties.addAll(drops);
      this.MOB_MENU_HEAD_ICON = new BasicElement(ItemFactory.ItemFactoryBuilder.builder().material(XMaterial.ZOMBIE_HEAD.parseMaterial()).displayName(StringUtil.getInstance().chatColor("&a&l" + entity.convertID())).lore(StringUtil.getInstance().chatColorL(properties)).build().createItemStack(), "head", new Target[]{new ClickTarget(ElementClickEvent::cancel, new Requirement[0]), new BasicTarget(ElementBasicEvent::cancel, new Requirement[0]), new DragTarget(ElementDragEvent::cancel, new Requirement[0])});
      this.MOB_MENU_ENTITYTYPE_ICON = new BasicElement(ItemFactory.ItemFactoryBuilder.builder().material(XMaterial.ZOMBIE_SPAWN_EGG.parseMaterial()).displayName(StringUtil.getInstance().chatColor("&a&lEntity Type")).lore(StringUtil.getInstance().chatColorL(Collections.singletonList("&e" + mobName))).build().createItemStack(), "entityType", new Target[]{new ClickTarget((elementClickEvent) -> {
         elementClickEvent.cancel();
         elementClickEvent.closeView();
         Bukkit.getScheduler().runTaskLater(this.instance, () -> {
            this.anvil.open("Type...", (anvilClicker, reply) -> {
               anvilClicker.closeInventory();

               try {
                  EntityType.valueOf(reply);
                  entity.pushArgument("mobName", new MobNameArgument(reply));
                  this.openEditMenu(entity);
               } catch (Exception var5) {
                  PluginUtil.getInstance().sendMessage(VariableName.pullMessage("error.mob-not-found").replaceAll("%file-name%", entity.convertID()), anvilClicker);
               }

               return reply;
            });
         }, 3L);
      }, new Requirement[0]), new DragTarget(ElementDragEvent::cancel, new Requirement[0]), new BasicTarget(ElementBasicEvent::cancel, new Requirement[0])});
      this.MOB_MENU_HEALTH_ICON = new BasicElement(ItemFactory.ItemFactoryBuilder.builder().material(XMaterial.ROSE_RED.parseMaterial()).displayName(StringUtil.getInstance().chatColor("&a&lHealth")).lore(StringUtil.getInstance().chatColorL(Collections.singletonList("&e" + health))).build().createItemStack(), "health", new Target[]{new ClickTarget((elementClickEvent) -> {
         elementClickEvent.cancel();
         elementClickEvent.closeView();
         Bukkit.getScheduler().runTaskLater(TornadoSpawnersPlus.getInstance(), () -> {
            this.anvil.open("Type...", (anvilClicker, reply) -> {
               anvilClicker.closeInventory();

               try {
                  entity.pushArgument("health", new HealthArgument(Integer.parseInt(reply)));
                  this.openEditMenu(entity);
               } catch (Exception var5) {
                  PluginUtil.getInstance().sendMessage(VariableName.pullMessage("error.wrong-usage"), anvilClicker);
               }

               return reply;
            });
         }, 3L);
      }, new Requirement[0]), new DragTarget(ElementDragEvent::cancel, new Requirement[0]), new BasicTarget(ElementBasicEvent::cancel, new Requirement[0])});
      this.MOB_MENU_DAMAGE_ICON = new BasicElement(ItemFactory.ItemFactoryBuilder.builder().material(Material.DIAMOND_SWORD).displayName(StringUtil.getInstance().chatColor("&a&lDamage")).lore(StringUtil.getInstance().chatColorL(Collections.singletonList("&e" + damage))).itemFlags(Collections.singletonList(ItemFlag.HIDE_ATTRIBUTES)).build().createItemStack(), "damage", new Target[]{new ClickTarget((elementClickEvent) -> {
         elementClickEvent.cancel();
         elementClickEvent.closeView();
         Bukkit.getScheduler().runTaskLater(TornadoSpawnersPlus.getInstance(), () -> {
            this.anvil.open("Type...", (anvilClicker, reply) -> {
               anvilClicker.closeInventory();

               try {
                  entity.pushArgument("damage", new DamageArgument(Integer.parseInt(reply)));
                  this.openEditMenu(entity);
               } catch (Exception var5) {
                  PluginUtil.getInstance().sendMessage(VariableName.pullMessage("error.wrong-usage"), anvilClicker);
               }

               return reply;
            });
         }, 3L);
      }, new Requirement[0]), new DragTarget(ElementDragEvent::cancel, new Requirement[0]), new BasicTarget(ElementBasicEvent::cancel, new Requirement[0])});
      this.MOB_MENU_ARMOR_ICON = new BasicElement(ItemFactory.ItemFactoryBuilder.builder().material(Material.IRON_CHESTPLATE).displayName(StringUtil.getInstance().chatColor("&a&lArmor")).lore(StringUtil.getInstance().chatColorL(Collections.singletonList("&e" + armor))).itemFlags(Collections.singletonList(ItemFlag.HIDE_ATTRIBUTES)).build().createItemStack(), "armor", new Target[]{new ClickTarget((elementClickEvent) -> {
         elementClickEvent.cancel();
         elementClickEvent.closeView();
         Bukkit.getScheduler().runTaskLater(TornadoSpawnersPlus.getInstance(), () -> {
            this.anvil.open("Type...", (anvilClicker, reply) -> {
               anvilClicker.closeInventory();

               try {
                  entity.pushArgument("armor", new ArmorArgument(Double.parseDouble(reply)));
                  this.openEditMenu(entity);
               } catch (Exception var5) {
                  PluginUtil.getInstance().sendMessage(VariableName.pullMessage("error.wrong-usage"), anvilClicker);
               }

               return reply;
            });
         }, 3L);
      }, new Requirement[0]), new DragTarget(ElementDragEvent::cancel, new Requirement[0]), new BasicTarget(ElementBasicEvent::cancel, new Requirement[0])});
      this.MOB_MENU_MOVEMENT_ICON = new BasicElement(ItemFactory.ItemFactoryBuilder.builder().material(XMaterial.GOLDEN_BOOTS.parseMaterial()).displayName(StringUtil.getInstance().chatColor("&a&lMovement Speed")).lore(StringUtil.getInstance().chatColorL(Collections.singletonList("&e" + movementSpeed))).itemFlags(Collections.singletonList(ItemFlag.HIDE_ATTRIBUTES)).build().createItemStack(), "movementSpeed", new Target[]{new ClickTarget((elementClickEvent) -> {
         elementClickEvent.cancel();
         elementClickEvent.closeView();
         Bukkit.getScheduler().runTaskLater(TornadoSpawnersPlus.getInstance(), () -> {
            this.anvil.open("Type...", (anvilClicker, reply) -> {
               anvilClicker.closeInventory();

               try {
                  entity.pushArgument("movementSpeed", new MovementSpeedArgument(Float.parseFloat(reply)));
                  this.openEditMenu(entity);
               } catch (Exception var5) {
                  PluginUtil.getInstance().sendMessage(VariableName.pullMessage("error.wrong-usage"), anvilClicker);
               }

               return reply;
            });
         }, 3L);
      }, new Requirement[0]), new DragTarget(ElementDragEvent::cancel, new Requirement[0]), new BasicTarget(ElementBasicEvent::cancel, new Requirement[0])});
      this.MOB_MENU_FOLLOW_ICON = new BasicElement(ItemFactory.ItemFactoryBuilder.builder().material(Material.FISHING_ROD).displayName(StringUtil.getInstance().chatColor("&a&lFollow Range")).lore(StringUtil.getInstance().chatColorL(Collections.singletonList("&e" + followRange))).build().createItemStack(), "followRange", new Target[]{new ClickTarget((elementClickEvent) -> {
         elementClickEvent.cancel();
         elementClickEvent.closeView();
         Bukkit.getScheduler().runTaskLater(TornadoSpawnersPlus.getInstance(), () -> {
            this.anvil.open("Type...", (anvilClicker, reply) -> {
               anvilClicker.closeInventory();

               try {
                  entity.pushArgument("followRange", new FollowRangeArgument(Double.parseDouble(reply)));
                  this.openEditMenu(entity);
               } catch (Exception var5) {
                  PluginUtil.getInstance().sendMessage(VariableName.pullMessage("error.wrong-usage"), anvilClicker);
               }

               return reply;
            });
         }, 3L);
      }, new Requirement[0]), new DragTarget(ElementDragEvent::cancel, new Requirement[0]), new BasicTarget(ElementBasicEvent::cancel, new Requirement[0])});
      this.MOB_MENU_KNOCKBACK_ICON = new BasicElement(ItemFactory.ItemFactoryBuilder.builder().material(Material.CHAINMAIL_LEGGINGS).displayName(StringUtil.getInstance().chatColor("&a&lKnockback Resistance")).lore(StringUtil.getInstance().chatColorL(Collections.singletonList("&e" + knockbackResistance))).itemFlags(Collections.singletonList(ItemFlag.HIDE_ATTRIBUTES)).build().createItemStack(), "knockbackResistance", new Target[]{new ClickTarget((elementClickEvent) -> {
         elementClickEvent.cancel();
         elementClickEvent.closeView();
         Bukkit.getScheduler().runTaskLater(TornadoSpawnersPlus.getInstance(), () -> {
            this.anvil.open("Type...", (anvilClicker, reply) -> {
               anvilClicker.closeInventory();

               try {
                  entity.pushArgument("knockbackResistance", new KnockbackResistanceArgument(Double.parseDouble(reply)));
                  this.openEditMenu(entity);
               } catch (Exception var5) {
                  PluginUtil.getInstance().sendMessage(VariableName.pullMessage("error.wrong-usage"), anvilClicker);
               }

               return reply;
            });
         }, 3L);
      }, new Requirement[0]), new DragTarget(ElementDragEvent::cancel, new Requirement[0]), new BasicTarget(ElementBasicEvent::cancel, new Requirement[0])});
      this.MOB_MENU_DISPLAY_ICON = new BasicElement(ItemFactory.ItemFactoryBuilder.builder().material(Material.NAME_TAG).displayName(StringUtil.getInstance().chatColor("&a&lCustom Name")).lore(StringUtil.getInstance().chatColorL(Arrays.asList("&e" + customNames, customNamesVisible ? "&b" + customNamesVisible : "&c" + customNamesVisible))).build().createItemStack(), "customName", new Target[]{new ClickTarget((elementClickEvent) -> {
         elementClickEvent.cancel();
         elementClickEvent.closeView();
         Bukkit.getScheduler().runTaskLater(TornadoSpawnersPlus.getInstance(), () -> {
            this.anvil.open("Type...", (anvilClicker, reply) -> {
               anvilClicker.closeInventory();
               if (reply.split(":").length == 2) {
                  String name = reply.split(":")[0];
                  boolean bool = Boolean.parseBoolean(reply.split(":")[1]);
                  entity.pushArgument("customName", new CustomNameArgument(name, bool));
                  this.openEditMenu(entity);
               } else {
                  PluginUtil.getInstance().sendMessage(VariableName.pullMessage("error.wrong-usage"), anvilClicker);
               }

               return reply;
            });
         }, 3L);
      }, new Requirement[]{new ClickTypeReq(ClickType.LEFT)}), new ClickTarget((elementClickEvent) -> {
         elementClickEvent.cancel();
         elementClickEvent.closeView();
         entity.pushArgument("customName", new CustomNameArgument("", false));
         Bukkit.getScheduler().runTaskLater(TornadoSpawnersPlus.getInstance(), () -> {
            this.openEditMenu(entity);
         }, 3L);
      }, new Requirement[]{new ClickTypeReq(ClickType.RIGHT)}), new DragTarget(ElementDragEvent::cancel, new Requirement[0]), new BasicTarget(ElementBasicEvent::cancel, new Requirement[0])});
      this.MOB_MENU_TARGETS_ICON = new BasicElement(ItemFactory.ItemFactoryBuilder.builder().material(Material.BOOK).displayName(StringUtil.getInstance().chatColor("&a&lTarget List")).lore(StringUtil.getInstance().chatColorL(targets)).build().createItemStack(), "target", new Target[]{new ClickTarget((elementClickEvent) -> {
         elementClickEvent.cancel();
         elementClickEvent.closeView();
         Bukkit.getScheduler().runTaskLater(TornadoSpawnersPlus.getInstance(), () -> {
            this.anvil.open("Type...", (anvilClicker, reply) -> {
               anvilClicker.closeInventory();

               try {
                  EntityType.valueOf(reply);
                  targets.add(reply);
                  entity.pushArgument("target", new TargetArgument(targets));
                  this.openEditMenu(entity);
               } catch (Exception var6) {
                  PluginUtil.getInstance().sendMessage(VariableName.pullMessage("error.mob-not-found").replaceAll("%file-name%", entity.convertID()), anvilClicker);
               }

               return reply;
            });
         }, 3L);
      }, new Requirement[]{new ClickTypeReq(ClickType.LEFT)}), new ClickTarget((elementClickEvent) -> {
         elementClickEvent.cancel();
         elementClickEvent.closeView();
         if (!targets.isEmpty()) {
            targets.remove(targets.size() - 1);
            entity.pushArgument("target", new TargetArgument(targets));
         }

         Bukkit.getScheduler().runTaskLater(TornadoSpawnersPlus.getInstance(), () -> {
            this.openEditMenu(entity);
         }, 3L);
      }, new Requirement[]{new ClickTypeReq(ClickType.RIGHT)}), new DragTarget(ElementDragEvent::cancel, new Requirement[0]), new BasicTarget(ElementBasicEvent::cancel, new Requirement[0])});
      this.MOB_MENU_BARRIER_ICON = new BasicElement(ItemFactory.ItemFactoryBuilder.builder().material(Material.BARRIER).displayName(StringUtil.getInstance().chatColor("&c&lDelete Mob")).build().createItemStack(), new Target[]{new ClickTarget((elementClickEvent) -> {
         elementClickEvent.cancel();
         elementClickEvent.closeView();
         entity.remove();
      }, new Requirement[0]), new DragTarget(ElementDragEvent::cancel, new Requirement[0]), new BasicTarget(ElementBasicEvent::cancel, new Requirement[0])});
      this.MOB_MENU_EQUIPMENTS_ICON = new BasicElement(ItemFactory.ItemFactoryBuilder.builder().material(Material.ARMOR_STAND).displayName(StringUtil.getInstance().chatColor("&a&lEquipment List")).lore(StringUtil.getInstance().chatColorL(equipments)).build().createItemStack(), "equipment", new Target[]{new ClickTarget((elementClickEvent) -> {
         elementClickEvent.cancel();
         elementClickEvent.closeView();
         Bukkit.getScheduler().runTaskLater(TornadoSpawnersPlus.getInstance(), () -> {
            this.anvil.open("Type...", (anvilClicker, reply) -> {
               anvilClicker.closeInventory();
               if (TornadoSpawnersPlus.ITEMS.get(reply) != null) {
                  int i;
                  for(i = 0; i < 4 && !((String)equipments.get(i)).equalsIgnoreCase(""); ++i) {
                  }

                  equipments.set(i, reply);
                  entity.pushArgument("equipment", new EquipmentArgument((String)equipments.get(0), (String)equipments.get(1), (String)equipments.get(2), (String)equipments.get(3)));
                  this.openEditMenu(entity);
               } else {
                  PluginUtil.getInstance().sendMessage(VariableName.pullMessage("error.item-not-found").replaceAll("%file-name%", entity.convertID()), anvilClicker);
               }

               return reply;
            });
         }, 3L);
      }, new Requirement[]{new ClickTypeReq(ClickType.LEFT)}), new ClickTarget((elementClickEvent) -> {
         elementClickEvent.cancel();
         elementClickEvent.closeView();

         int i;
         for(i = equipments.size() - 1; i > 0 && ((String)equipments.get(i)).equalsIgnoreCase(""); --i) {
         }

         equipments.set(i, "");
         entity.pushArgument("equipment", new EquipmentArgument((String)equipments.get(0), (String)equipments.get(1), (String)equipments.get(2), (String)equipments.get(3)));
         Bukkit.getScheduler().runTaskLater(TornadoSpawnersPlus.getInstance(), () -> {
            this.openEditMenu(entity);
         }, 3L);
      }, new Requirement[]{new ClickTypeReq(ClickType.RIGHT)}), new DragTarget(ElementDragEvent::cancel, new Requirement[0]), new BasicTarget(ElementBasicEvent::cancel, new Requirement[0])});
      this.MOB_MENU_DROPS_ICON = new BasicElement(ItemFactory.ItemFactoryBuilder.builder().material(Material.CHEST).displayName(StringUtil.getInstance().chatColor("&a&lDrop List")).lore(StringUtil.getInstance().chatColorL(drops)).build().createItemStack(), "drops", new Target[]{new ClickTarget((elementClickEvent) -> {
         elementClickEvent.cancel();
         elementClickEvent.closeView();
         Bukkit.getScheduler().runTaskLater(TornadoSpawnersPlus.getInstance(), () -> {
            this.anvil.open("Type...", (anvilClicker, reply) -> {
               anvilClicker.closeInventory();
               if (TornadoSpawnersPlus.ITEMS.get(reply.split(":")[0]) != null) {
                  drops.add(reply);
                  entity.pushArgument("drops", new DropArgument(drops));
                  this.openEditMenu(entity);
               } else {
                  PluginUtil.getInstance().sendMessage(VariableName.pullMessage("error.item-not-found").replaceAll("%file-name%", entity.convertID()), anvilClicker);
               }

               return reply;
            });
         }, 3L);
      }, new Requirement[]{new ClickTypeReq(ClickType.LEFT)}), new ClickTarget((elementClickEvent) -> {
         elementClickEvent.cancel();
         elementClickEvent.closeView();
         if (drop.size() > 0) {
            drops.remove(drops.size() - 1);
            entity.pushArgument("drops", new DropArgument(drops));
         }

         Bukkit.getScheduler().runTaskLater(TornadoSpawnersPlus.getInstance(), () -> {
            this.openEditMenu(entity);
         }, 3L);
      }, new Requirement[]{new ClickTypeReq(ClickType.RIGHT)}), new DragTarget(ElementDragEvent::cancel, new Requirement[0]), new BasicTarget(ElementBasicEvent::cancel, new Requirement[0])});
      Element window = new BasicElement(ItemFactory.ItemFactoryBuilder.builder().material(XMaterial.GRAY_STAINED_GLASS_PANE.parseMaterial()).build().createItemStack(), new Target[]{new ClickTarget(ElementClickEvent::cancel, new Requirement[0]), new DragTarget(ElementDragEvent::cancel, new Requirement[0]), new BasicTarget(ElementBasicEvent::cancel, new Requirement[0])});
      this.MOB_MENU_PANE = new BasicPane(0, 0, 6, 9);
      this.MOB_MENU_PANE.insert(this.MOB_MENU_HEAD_ICON, 4, 1, true);
      this.MOB_MENU_PANE.insert(this.MOB_MENU_ENTITYTYPE_ICON, 1, 3, true);
      this.MOB_MENU_PANE.insert(this.MOB_MENU_HEALTH_ICON, 2, 3, true);
      this.MOB_MENU_PANE.insert(this.MOB_MENU_DAMAGE_ICON, 3, 3, true);
      this.MOB_MENU_PANE.insert(this.MOB_MENU_ARMOR_ICON, 4, 3, true);
      this.MOB_MENU_PANE.insert(this.MOB_MENU_MOVEMENT_ICON, 5, 3, true);
      this.MOB_MENU_PANE.insert(this.MOB_MENU_FOLLOW_ICON, 6, 3, true);
      this.MOB_MENU_PANE.insert(this.MOB_MENU_KNOCKBACK_ICON, 7, 3, true);
      this.MOB_MENU_PANE.insert(this.MOB_MENU_DISPLAY_ICON, 1, 4, true);
      this.MOB_MENU_PANE.insert(this.MOB_MENU_TARGETS_ICON, 2, 4, true);
      this.MOB_MENU_PANE.insert(this.MOB_MENU_BARRIER_ICON, 4, 4, true);
      this.MOB_MENU_PANE.insert(this.MOB_MENU_EQUIPMENTS_ICON, 6, 4, true);
      this.MOB_MENU_PANE.insert(this.MOB_MENU_DROPS_ICON, 7, 4, true);

      for(int i = 0; i < 9; ++i) {
         this.MOB_MENU_PANE.insert(window, i, 0, true);
         this.MOB_MENU_PANE.insert(window, i, 5, true);
         if (i > 0 && i < 5) {
            this.MOB_MENU_PANE.insert(window, 0, i, true);
            this.MOB_MENU_PANE.insert(window, 8, i, true);
         }
      }

      this.MOB_MENU_PAGE = new ChestPage(StringUtil.getInstance().chatColor("&a&lMob Edit"), 54, new Pane[]{this.MOB_MENU_PANE});
   }

   public void openCreateMenu() {
      this.initCreatePage();
      this.GENERAL_MENU_PAGE.showTo(this.PLAYER);
   }

   public void openEditMenu(IEntity entity) {
      if (entity instanceof ISpawner) {
         this.initSpawnerEditPage((ISpawner)entity);
         this.SPAWNER_MENU_PAGE.showTo(this.PLAYER);
      } else if (entity instanceof IItem) {
         this.initItemEditPage((IItem)entity);
         this.ITEM_MENU_PAGE.showTo(this.PLAYER);
      } else if (entity instanceof IMob) {
         this.initMobEditPage((IMob)entity);
         this.MOB_MENU_PAGE.showTo(this.PLAYER);
      }

   }

   public static synchronized GeneralInventoryListener build(Player player) {
      return new GeneralInventoryListener(player);
   }
}

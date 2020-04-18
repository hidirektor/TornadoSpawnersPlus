package com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.infumia.t3sl4.tornadoplus.tornadoputil.configuration.ConfigurationFile;
import com.infumia.t3sl4.tornadoplus.tornadoputil.misc.PluginUtil;
import com.infumia.t3sl4.tornadoplus.tornadopsp.TornadoSpawnersPlus;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.IEntityArgument;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.argument.AmountArgument;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.argument.DisplayNameArgument;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.argument.EnchantmentArgument;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.argument.FlagArgument;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.argument.LoreArgument;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.argument.MaterialArgument;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.argument.UnbreakableArgument;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.argument.meta.BannerMeta;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.argument.meta.BookMeta;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.argument.meta.EnchantedStoredMeta;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.argument.meta.FireworkMeta;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.argument.meta.LeatherArmorMeta;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.argument.meta.MapMeta;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.argument.meta.PotionMeta;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.argument.meta.SkullMeta;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.banner.Pattern;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class BasicItem implements IItem {
   private final ConfigurationFile file;
   private final Map<String, IItemArgument> arguments;

   public BasicItem(ConfigurationFile file) {
      this.arguments = new HashMap();
      this.file = file;
      TornadoSpawnersPlus.ITEMS.put(this.convertID(), this);
   }

   public BasicItem(String id) {
      this(TornadoSpawnersPlus.getInstance().getUtil().addFile(id, "data/items"));
      this.file.set("material", "DIAMOND");
      this.file.set("options.amount", 1);
      this.file.set("options.display-name", "");
      this.file.set("options.lore", new ArrayList());
      this.file.set("options.enchantments", new ArrayList());
      this.file.set("options.flags", new ArrayList());
      this.file.set("options.unbreakable", false);
      this.file.set("metas", new ArrayList());
      this.register();
   }

   public ItemStack create() {
      ItemStack itemStack = new ItemStack(Material.DIAMOND);
      this.arguments.values().forEach((argument) -> {
         argument.add(itemStack);
      });
      return itemStack;
   }

   public Object pullArgument(String key) {
      return this.arguments.get(key) != null ? ((IItemArgument)this.arguments.get(key)).convert() : null;
   }

   public void pushArgument(String key, IEntityArgument argument) {
      this.arguments.put(key, (IItemArgument)argument);
      this.save();
   }

   public void give(Player player) {
      player.getInventory().addItem(new ItemStack[]{this.create()});
   }

   public void drop(Location location) {
      location.getWorld().dropItemNaturally(location, this.create());
   }

   public void register() {
      this.arguments.clear();
      this.arguments.put("material", new MaterialArgument(this.file.getString("material")));
      this.arguments.put("amount", new AmountArgument((Integer)this.file.get("options.amount")));
      this.arguments.put("displayName", new DisplayNameArgument(this.file.getString("options.display-name")));
      this.arguments.put("lore", new LoreArgument(this.file.getStringList("options.lore")));
      this.arguments.put("unbreakable", new UnbreakableArgument((Boolean)this.file.get("options.unbreakable")));
      this.arguments.put("flags", new FlagArgument(this.file.getStringList("options.flags")));
      Iterator var2 = this.file.getStringList("options.enchantments").iterator();

      while(var2.hasNext()) {
         String enchs = (String)var2.next();
         if (Enchantment.getByName(enchs.split(":")[0]) == null) {
            PluginUtil.getInstance().sendMessage("&cDetected unknown item enchantment name in " + this.file.name());
            return;
         }
      }

      this.arguments.put("enchantments", new EnchantmentArgument(this.file.getStringList("options.enchantments")));
      if (this.file.get("metas.banner") != null) {
         this.arguments.put("banner", new BannerMeta(this.file.getStringList("metas.banner")));
      }

      if (this.file.get("metas.book") != null) {
         this.arguments.put("book", new BookMeta(this.file.getString("metas.book.author"), this.file.getString("metas.book.title"), this.file.getStringList("metas.book.pages")));
      }

      if (this.file.get("metas.enchanted-stored") != null) {
         this.arguments.put("enchantedStored", new EnchantedStoredMeta(this.file.getStringList("metas.enchanted-stored")));
      }

      if (this.file.get("metas.firework") != null) {
         this.arguments.put("firework", new FireworkMeta(this.file.getStringList("metas.firework")));
      }

      if (this.file.get("metas.leather-armor") != null) {
         this.arguments.put("leather", new LeatherArmorMeta((Integer)this.file.get("metas.leather-armor.color")));
      }

      if (this.file.get("metas.map") != null) {
         this.arguments.put("map", new MapMeta((Boolean)this.file.get("metas.map.scaling"), (Integer)this.file.get("metas.map.color")));
      }

      if (this.file.get("metas.potion") != null) {
         this.arguments.put("poiton", new PotionMeta(this.file.getStringList("metas.potion")));
      }

      if (this.file.get("metas.skull") != null) {
         this.arguments.put("skull", new SkullMeta(this.file.getString("metas.skull.owner")));
      }

   }

   public void save() {
      this.file.set("material", ((Material)this.pullArgument("material")).name());
      this.file.set("options.amount", this.pullArgument("amount"));
      this.file.set("options.display-name", this.pullArgument("displayName"));
      this.file.set("options.lore", this.pullArgument("lore"));
      Map<Enchantment, Integer> enchantmentIntegerMap = (Map)this.pullArgument("enchantments");
      List<String> enchantmentList = new ArrayList();
      Iterator var4 = enchantmentIntegerMap.keySet().iterator();

      while(var4.hasNext()) {
         Enchantment enchantment = (Enchantment)var4.next();
         enchantmentList.add(enchantment.getName() + ":" + enchantmentIntegerMap.get(enchantment));
      }

      this.file.set("options.enchantments", enchantmentList);
      this.file.set("options.flags", this.pullArgument("flags"));
      this.file.set("options.unbreakable", this.pullArgument("unbreakable"));
      this.file.set("metas", new ArrayList());
      Iterator var6;
      List effects;
      ArrayList list;
      if (this.pullArgument("banner") != null) {
         effects = (List)this.pullArgument("banner");
         list = new ArrayList();
         var6 = effects.iterator();

         while(var6.hasNext()) {
            Pattern pattern = (Pattern)var6.next();
            list.add(pattern.getColor().getDyeData() + ":" + pattern.getPattern().getIdentifier());
         }

         this.file.set("metas.banner", list);
      }

      if (this.pullArgument("book") != null) {
         String author = (String)((List)this.pullArgument("book")).get(0);
         String title = (String)((List)this.pullArgument("title")).get(1);
         List<String> pages = (List)((List)this.pullArgument("title")).get(2);
         this.file.set("book.author", author);
         this.file.set("book.title", title);
         this.file.set("book.pages", pages);
      }

      if (this.pullArgument("enchantedStored") != null) {
         Map<Enchantment, Integer> enchantedStoredMap = (Map)this.pullArgument("enchantedStored");
         list = new ArrayList();
         var6 = enchantedStoredMap.keySet().iterator();

         while(var6.hasNext()) {
            Enchantment enchantment2 = (Enchantment)var6.next();
            list.add(enchantment2.getName() + ":" + enchantedStoredMap.get(enchantment2));
         }

         this.file.set("enchantedStored", list);
      }

      if (this.pullArgument("firework") != null) {
         effects = (List)this.pullArgument("firework");
         list = new ArrayList();
         var6 = effects.iterator();

         while(var6.hasNext()) {
            FireworkEffect effect = (FireworkEffect)var6.next();
            List<Integer> colors = new ArrayList();
            List<Integer> fadeColors = new ArrayList();
            effect.getColors().forEach((color) -> {
               colors.add(color.asRGB());
            });
            effect.getFadeColors().forEach((fadeColor) -> {
               fadeColors.add(fadeColor.asRGB());
            });
            list.add("type:" + effect.getType() + ";flicker:" + effect.hasFlicker() + ";trail:" + effect.hasTrail() + ";colors:" + colors + ";fade-colors:" + fadeColors);
         }

         this.file.set("metas.firework", list);
      }

      if (this.pullArgument("leather") != null) {
         this.file.set("metas.leather-armor", ((Color)this.pullArgument("leather")).asRGB());
      }

      if (this.pullArgument("map") != null) {
         boolean bool = Boolean.parseBoolean(((String)this.pullArgument("map")).split(":")[0]);
         int in = Integer.parseInt(((String)this.pullArgument("map")).split(":")[1]);
         this.file.set("metas.map.scaling", bool);
         this.file.set("metas.map.color", Color.fromRGB(in));
      }

      if (this.pullArgument("potion") != null) {
         List<String> effectList = new ArrayList();
         List<PotionEffect> effects2 = (List)this.pullArgument("potion");
         var6 = effects2.iterator();

         while(var6.hasNext()) {
            PotionEffect effect2 = (PotionEffect)var6.next();
            effectList.add(effect2.getType().getName() + ":" + effect2.getDuration() + ":" + effect2.getAmplifier());
         }

         this.file.set("metas.potion", effectList);
      }

      if (this.pullArgument("skull") != null) {
         this.file.set("metas.skull.owner", ((OfflinePlayer)this.pullArgument("skull")).getName());
      }

   }

   public void remove() {
      TornadoSpawnersPlus.ITEMS.remove(this.convertID());
      this.file.delete();
   }

   public Object convertNBT() {
      return TornadoSpawnersPlus.getInstance().getIItemUtil().convertNBT(this);
   }

   public String convertID() {
      return this.file.name();
   }
}

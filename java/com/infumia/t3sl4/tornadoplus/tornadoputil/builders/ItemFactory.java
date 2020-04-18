package com.infumia.t3sl4.tornadoplus.tornadoputil.builders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.infumia.t3sl4.tornadoplus.tornadoputil.misc.StringUtil;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public final class ItemFactory {
   private final Material material;
   private final String displayName;
   private final int amount;
   private final short durability;
   private final List<String> lore;
   private final List<ItemFlag> itemFlags;
   private final Map<Enchantment, Integer> enchantments;

   private ItemFactory(ItemFactory.ItemFactoryBuilder builder) {
      this.material = builder.material;
      this.displayName = builder.displayName;
      this.amount = builder.amount;
      this.durability = builder.durability;
      this.lore = builder.lore;
      this.itemFlags = builder.itemFlags;
      this.enchantments = builder.enchantments;
   }

   public ItemStack createItemStack() {
      ItemStack itemStack = new ItemStack(this.material);
      itemStack.setDurability(this.durability);
      itemStack.setAmount(this.amount);
      itemStack.addUnsafeEnchantments(this.enchantments);
      ItemMeta itemMeta = itemStack.getItemMeta();
      itemMeta.setDisplayName(StringUtil.getInstance().chatColor(this.displayName));
      itemMeta.setLore(StringUtil.getInstance().chatColorL(this.lore));
      this.itemFlags.forEach((xva$0) -> {
         itemMeta.addItemFlags(new ItemFlag[]{xva$0});
      });
      itemStack.setItemMeta(itemMeta);
      return itemStack;
   }

   // $FF: synthetic method
   ItemFactory(ItemFactory.ItemFactoryBuilder var1, ItemFactory var2) {
      this(var1);
   }

   public static final class ItemFactoryBuilder {
      private Material material;
      private String displayName;
      private int amount;
      private short durability;
      private List<String> lore;
      private List<ItemFlag> itemFlags;
      private Map<Enchantment, Integer> enchantments;

      private ItemFactoryBuilder() {
         this.material = Material.AIR;
         this.displayName = "";
         this.amount = 1;
         this.lore = new ArrayList();
         this.itemFlags = new ArrayList();
         this.enchantments = new HashMap();
      }

      public static ItemFactory.ItemFactoryBuilder builder() {
         return new ItemFactory.ItemFactoryBuilder();
      }

      public ItemFactory.ItemFactoryBuilder material(Material material) {
         this.material = material;
         return this;
      }

      public ItemFactory.ItemFactoryBuilder displayName(String displayName) {
         this.displayName = displayName;
         return this;
      }

      public ItemFactory.ItemFactoryBuilder amount(int amount) {
         this.amount = amount;
         return this;
      }

      public ItemFactory.ItemFactoryBuilder lore(List<String> lore) {
         this.lore = lore;
         return this;
      }

      public ItemFactory.ItemFactoryBuilder itemFlags(List<ItemFlag> itemFlags) {
         this.itemFlags = itemFlags;
         return this;
      }

      public ItemFactory.ItemFactoryBuilder enchantments(Map<Enchantment, Integer> enchantments) {
         this.enchantments = enchantments;
         return this;
      }

      public ItemFactory.ItemFactoryBuilder durability(short durability) {
         this.durability = durability;
         return this;
      }

      public ItemFactory build() {
         return new ItemFactory(this, (ItemFactory)null);
      }
   }
}

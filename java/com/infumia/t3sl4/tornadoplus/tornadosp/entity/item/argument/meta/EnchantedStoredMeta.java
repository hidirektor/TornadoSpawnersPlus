package com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.argument.meta;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.IItemArgument;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

public class EnchantedStoredMeta implements IItemArgument {
   private final List<String> enchantmentStoredStringList;

   public EnchantedStoredMeta(List<String> enchantmentStoredStringList) {
      this.enchantmentStoredStringList = enchantmentStoredStringList;
   }

   public Object convert() {
      Map<Enchantment, Integer> enchantmentArgument = new HashMap();
      Iterator var2 = this.enchantmentStoredStringList.iterator();

      while(var2.hasNext()) {
         String enchantArgument = (String)var2.next();
         String[] strings = enchantArgument.split(":");
         String stringFirstPart = null;
         int enchantmentLevel;
         if (strings.length == 2) {
            stringFirstPart = strings[0];
            int var7 = Integer.valueOf(strings[1]);
         } else if (strings.length == 1) {
            stringFirstPart = strings[0];
         }
      }

      return enchantmentArgument;
   }

   public void add(ItemStack itemStack) {
      EnchantmentStorageMeta enchantmentStorageMeta = (EnchantmentStorageMeta)itemStack.getItemMeta();
      Iterator var3 = ((Map)this.convert()).keySet().iterator();

      while(var3.hasNext()) {
         Enchantment enchantment = (Enchantment)var3.next();
         enchantmentStorageMeta.addStoredEnchant(enchantment, (Integer)((Map)this.convert()).get(enchantment), true);
      }

      itemStack.setItemMeta(enchantmentStorageMeta);
   }
}

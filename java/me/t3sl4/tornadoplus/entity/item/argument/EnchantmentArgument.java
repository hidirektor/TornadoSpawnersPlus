package me.t3sl4.tornadoplus.entity.item.argument;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import me.t3sl4.tornadoplus.entity.item.IItemArgument;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class EnchantmentArgument implements IItemArgument {
   private final List<String> enchantmentsStringList;

   public EnchantmentArgument(List<String> enchantmentsStringList) {
      this.enchantmentsStringList = enchantmentsStringList;
   }

   public Map<Enchantment, Integer> convert() {
      Map<Enchantment, Integer> enchantmentArguments = new HashMap();
      Iterator var2 = this.enchantmentsStringList.iterator();

      while(var2.hasNext()) {
         String enchantArgument = (String)var2.next();
         String[] strings = enchantArgument.split(":");
         String stringFirstPart = null;
         int enchantmentLevel = 1;
         if (strings.length == 2) {
            stringFirstPart = strings[0];
            enchantmentLevel = Integer.valueOf(strings[1]);
         } else if (strings.length == 1) {
            stringFirstPart = strings[0];
         }

         if (stringFirstPart != null) {
            enchantmentArguments.put(Enchantment.getByName(stringFirstPart), enchantmentLevel);
         }
      }

      return enchantmentArguments;
   }

   public void add(ItemStack itemStack) {
      if (!this.enchantmentsStringList.isEmpty()) {
         itemStack.addUnsafeEnchantments(this.convert());
      }

   }
}

package com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.argument;

import com.infumia.t3sl4.tornadoplus.tornadoputil.misc.StringUtil;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.IItemArgument;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DisplayNameArgument implements IItemArgument {
   private final String displayName;

   public DisplayNameArgument(String displayName) {
      this.displayName = displayName;
   }

   public String convert() {
      return this.displayName;
   }

   public void add(ItemStack itemStack) {
      ItemMeta itemMeta = itemStack.getItemMeta();
      if (!this.displayName.equals(".")) {
         itemMeta.setDisplayName(StringUtil.getInstance().chatColor(this.displayName));
         itemStack.setItemMeta(itemMeta);
      }

   }
}

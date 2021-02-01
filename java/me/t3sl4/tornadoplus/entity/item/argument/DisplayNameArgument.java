package me.t3sl4.tornadoplus.entity.item.argument;

import me.t3sl4.tornadoplus.util.misc.StringUtil;
import me.t3sl4.tornadoplus.entity.item.IItemArgument;
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

package me.t3sl4.tornadoplus.entity.item.argument;

import java.util.List;

import me.t3sl4.tornadoplus.util.misc.StringUtil;
import me.t3sl4.tornadoplus.entity.item.IItemArgument;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LoreArgument implements IItemArgument {
   private final List<String> lore;

   public LoreArgument(List<String> lore) {
      this.lore = lore;
   }

   public List<String> convert() {
      return this.lore;
   }

   public void add(ItemStack itemStack) {
      ItemMeta itemMeta = itemStack.getItemMeta();
      if (!this.lore.isEmpty()) {
         itemMeta.setLore(StringUtil.getInstance().chatColorL(this.lore));
         itemStack.setItemMeta(itemMeta);
      }

   }
}

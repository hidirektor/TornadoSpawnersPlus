package me.t3sl4.tornadoplus.entity.item.argument;

import java.util.ArrayList;
import java.util.List;
import me.t3sl4.tornadoplus.entity.item.IItemArgument;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class FlagArgument implements IItemArgument {
   private final List<String> itemFlagStringList;

   public FlagArgument(List<String> itemFlagStringList) {
      this.itemFlagStringList = itemFlagStringList;
   }

   public List<ItemFlag> convert() {
      List<ItemFlag> flagArgument = new ArrayList();
      this.itemFlagStringList.forEach((x) -> {
         flagArgument.add(ItemFlag.valueOf(x));
      });
      return flagArgument;
   }

   public void add(ItemStack itemStack) {
      ItemMeta itemMeta = itemStack.getItemMeta();
      if (!this.itemFlagStringList.isEmpty()) {
         this.convert().forEach((xva$0) -> {
            itemMeta.addItemFlags(new ItemFlag[]{xva$0});
         });
         itemStack.setItemMeta(itemMeta);
      }

   }
}

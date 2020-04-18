package com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.argument;

import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.IItemArgument;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.ItemMeta.Spigot;

public class UnbreakableArgument implements IItemArgument {
   private final boolean unbreakable;

   public UnbreakableArgument(boolean unbreakable) {
      this.unbreakable = unbreakable;
   }

   public Boolean convert() {
      return this.unbreakable;
   }

   public void add(ItemStack itemStack) {
      ItemMeta itemMeta = itemStack.getItemMeta();

      try {
         itemMeta.spigot().setUnbreakable(this.unbreakable);
      } catch (UnsupportedOperationException var4) {
         ((Spigot)itemMeta).setUnbreakable(this.unbreakable);
      }

      itemStack.setItemMeta(itemMeta);
   }
}

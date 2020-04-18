package com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.argument.meta;

import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.IItemArgument;
import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;

public class LeatherArmorMeta implements IItemArgument {
   private final int colorInteger;

   public LeatherArmorMeta(int colorInteger) {
      this.colorInteger = colorInteger;
   }

   public Color convert() {
      return Color.fromRGB(this.colorInteger);
   }

   public void add(ItemStack itemStack) {
      org.bukkit.inventory.meta.LeatherArmorMeta leatherArmorMeta = (org.bukkit.inventory.meta.LeatherArmorMeta)itemStack.getItemMeta();
      leatherArmorMeta.setColor(this.convert());
   }
}

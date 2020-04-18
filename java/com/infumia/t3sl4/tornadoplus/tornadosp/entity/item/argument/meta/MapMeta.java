package com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.argument.meta;

import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.IItemArgument;
import org.bukkit.inventory.ItemStack;

public class MapMeta implements IItemArgument {
   private final boolean scaling;
   private final int color;

   public MapMeta(boolean scaling, int color) {
      this.scaling = scaling;
      this.color = color;
   }

   public String convert() {
      return this.scaling + ":" + this.color;
   }

   public void add(ItemStack itemStack) {
      org.bukkit.inventory.meta.MapMeta mapMeta = (org.bukkit.inventory.meta.MapMeta)itemStack.getItemMeta();
      mapMeta.setScaling(this.scaling);
      int color = Integer.parseInt(this.convert().split(":")[1]);
      itemStack.setItemMeta(mapMeta);
   }
}

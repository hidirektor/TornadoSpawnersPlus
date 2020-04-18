package com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.argument;

import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.IItemArgument;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class MaterialArgument implements IItemArgument {
   private final String material;

   public MaterialArgument(String material) {
      this.material = material;
   }

   public void add(ItemStack itemStack) {
      itemStack.setType(this.convert());
   }

   public Material convert() {
      return Material.getMaterial(this.material);
   }
}

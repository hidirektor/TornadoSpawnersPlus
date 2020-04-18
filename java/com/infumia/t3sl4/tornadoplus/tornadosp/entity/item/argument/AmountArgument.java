package com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.argument;

import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.IItemArgument;
import org.bukkit.inventory.ItemStack;

public class AmountArgument implements IItemArgument {
   private final int amount;

   public AmountArgument(int amount) {
      this.amount = amount;
   }

   public Integer convert() {
      return this.amount;
   }

   public void add(ItemStack itemStack) {
      itemStack.setAmount(this.amount);
   }
}

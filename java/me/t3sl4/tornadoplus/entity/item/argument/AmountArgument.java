package me.t3sl4.tornadoplus.entity.item.argument;

import me.t3sl4.tornadoplus.entity.item.IItemArgument;
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

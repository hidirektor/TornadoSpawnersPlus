package com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.argument.meta;

import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.IItemArgument;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;

public class SkullMeta implements IItemArgument {
   private final String ownerString;

   public SkullMeta(String ownerString) {
      this.ownerString = ownerString;
   }

   public OfflinePlayer convert() {
      return Bukkit.getOfflinePlayer(this.ownerString);
   }

   public void add(ItemStack itemStack) {
      org.bukkit.inventory.meta.SkullMeta skullMeta = (org.bukkit.inventory.meta.SkullMeta)itemStack.getItemMeta();
   }
}

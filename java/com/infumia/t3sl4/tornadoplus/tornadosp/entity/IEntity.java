package com.infumia.t3sl4.tornadoplus.tornadopsp.entity;

import org.bukkit.inventory.ItemStack;

public interface IEntity {
   Object pullArgument(String var1);

   void pushArgument(String var1, IEntityArgument var2);

   ItemStack create();

   void register();

   void save();

   void remove();

   Object convertNBT();

   String convertID();
}

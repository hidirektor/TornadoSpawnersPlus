package com.infumia.t3sl4.tornadoplus.tornadopsp.nms;

import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner.ISpawner;
import org.bukkit.inventory.ItemStack;

public interface IItemUtil extends INMSUtil {
   ItemStack addIItemId(ItemStack var1, String var2);

   ISpawner toISpawner(ItemStack var1);
}

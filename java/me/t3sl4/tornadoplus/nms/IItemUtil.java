package me.t3sl4.tornadoplus.nms;

import me.t3sl4.tornadoplus.entity.spawner.ISpawner;
import org.bukkit.inventory.ItemStack;

public interface IItemUtil extends INMSUtil {
   ItemStack addIItemId(ItemStack var1, String var2);

   ISpawner toISpawner(ItemStack var1);
}

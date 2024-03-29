package me.t3sl4.tornadoplus.nms.item;

import me.t3sl4.tornadoplus.entity.IEntity;
import me.t3sl4.tornadoplus.entity.spawner.ISpawner;
import me.t3sl4.tornadoplus.TornadoSpawnersPlus;
import me.t3sl4.tornadoplus.nms.IItemUtil;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class ItemUtil1_12_R1 implements IItemUtil {
   public ItemStack addIItemId(ItemStack itemStack, String id) {
      net.minecraft.server.v1_12_R1.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
      NBTTagCompound nbtTagCompound = nmsItemStack.hasTag() ? nmsItemStack.getTag() : new NBTTagCompound();
      nbtTagCompound.setString("asID", id);
      nmsItemStack.setTag(nbtTagCompound);
      return CraftItemStack.asBukkitCopy(nmsItemStack);
   }

   public ISpawner toISpawner(ItemStack itemStack) {
      return (ISpawner)TornadoSpawnersPlus.SPAWNERS.getOrDefault(this.toASID(itemStack), (ISpawner)null);
   }

   private String toASID(ItemStack itemStack) {
      net.minecraft.server.v1_12_R1.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
      if (nmsItemStack.hasTag()) {
         NBTTagCompound nbtTagCompound = nmsItemStack.getTag();
         if (nbtTagCompound.getString("asID") != null) {
            return nbtTagCompound.getString("asID");
         }
      }

      return "";
   }

   public Object convertNBT(IEntity iEntity) {
      ItemStack itemStack = iEntity.create();
      net.minecraft.server.v1_12_R1.ItemStack craftItemStack = CraftItemStack.asNMSCopy(itemStack);
      NBTTagCompound itemStackTag1 = new NBTTagCompound();
      NBTTagCompound itemStackTag2 = new NBTTagCompound();
      itemStackTag1.setString("id", "Item");
      craftItemStack.save(itemStackTag2);
      NBTTagCompound asIDTag = (NBTTagCompound)itemStackTag2.get("tag");
      if (asIDTag == null) {
         itemStackTag2.set("tag", new NBTTagCompound());
         asIDTag = (NBTTagCompound)itemStackTag2.get("tag");
      }

      asIDTag.setString("asID", iEntity.convertID());
      itemStackTag2.set("tag", asIDTag);
      itemStackTag1.set("Item", itemStackTag2);
      return itemStackTag1;
   }
}

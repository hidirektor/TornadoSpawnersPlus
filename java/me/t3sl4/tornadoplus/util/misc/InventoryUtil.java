package me.t3sl4.tornadoplus.util.misc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

public class InventoryUtil {
   private static InventoryUtil instance;

   private InventoryUtil() {
   }

   public boolean checkInventory(Player p, ItemStack item) {
      if (p.getInventory().firstEmpty() >= 0 && item.getAmount() <= item.getMaxStackSize()) {
         return true;
      } else {
         HashMap<Integer, ? extends ItemStack> items = p.getInventory().all(item.getType());
         int amount = item.getAmount();

         ItemStack i;
         for(Iterator var6 = items.values().iterator(); var6.hasNext(); amount -= i.getMaxStackSize() - i.getAmount()) {
            i = (ItemStack)var6.next();
         }

         return amount <= 0;
      }
   }

   public String[] playerInventoryToBase64(PlayerInventory playerInventory) throws IllegalStateException {
      String content = this.toBase64(playerInventory);
      String armor = this.itemStackArrayToBase64(playerInventory.getArmorContents());
      return new String[]{content, armor};
   }

   public String toBase64(Inventory inventory) throws IllegalStateException {
      try {
         ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
         BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
         dataOutput.writeInt(inventory.getSize());

         for(int i = 0; i < inventory.getSize(); ++i) {
            dataOutput.writeObject(inventory.getItem(i));
         }

         dataOutput.close();
         return Base64Coder.encodeLines(outputStream.toByteArray());
      } catch (Exception var5) {
         throw new IllegalStateException("Unable to save item stacks.", var5);
      }
   }

   public Inventory fromBase64(String data) throws IOException {
      try {
         ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
         BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
         Inventory inventory = Bukkit.getServer().createInventory((InventoryHolder)null, dataInput.readInt());

         for(int i = 0; i < inventory.getSize(); ++i) {
            inventory.setItem(i, (ItemStack)dataInput.readObject());
         }

         dataInput.close();
         return inventory;
      } catch (ClassNotFoundException var6) {
         throw new IOException("Unable to decode class type.", var6);
      }
   }

   public String itemStackArrayToBase64(ItemStack[] items) throws IllegalStateException {
      try {
         ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
         BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
         dataOutput.writeInt(items.length);

         for(int i = 0; i < items.length; ++i) {
            dataOutput.writeObject(items[i]);
         }

         dataOutput.close();
         return Base64Coder.encodeLines(outputStream.toByteArray());
      } catch (Exception var5) {
         throw new IllegalStateException("Unable to save item stacks.", var5);
      }
   }

   public ItemStack[] itemStackArrayFromBase64(String data) throws IOException {
      try {
         ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
         BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
         ItemStack[] items = new ItemStack[dataInput.readInt()];

         for(int i = 0; i < items.length; ++i) {
            items[i] = (ItemStack)dataInput.readObject();
         }

         dataInput.close();
         return items;
      } catch (ClassNotFoundException var6) {
         throw new IOException("Unable to decode class type.", var6);
      }
   }

   public static synchronized InventoryUtil getInstance() {
      if (instance == null) {
         instance = new InventoryUtil();
      }

      return instance;
   }
}

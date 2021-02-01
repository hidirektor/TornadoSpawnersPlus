package me.t3sl4.tornadoplus.util.api.anvil;

import me.t3sl4.tornadoplus.util.misc.api.AnvilUtil;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AnvilListener implements Listener {
   private final AnvilUtil util;

   public AnvilListener(AnvilUtil util) {
      this.util = util;
   }

   @EventHandler
   public void onInventoryClick(InventoryClickEvent e) {
      if (e.getInventory().equals(this.util.getInventory())) {
         e.setCancelled(true);
         if (e.getRawSlot() == SlotType.OUTPUT.getId()) {
            ItemStack clicked = this.util.getInventory().getItem(e.getRawSlot());
            if (clicked == null || clicked.getType() == Material.AIR) {
               return;
            }

            String ret = this.util.apply(clicked.hasItemMeta() ? clicked.getItemMeta().getDisplayName() : clicked.getType().toString());
            if (ret != null) {
               ItemMeta meta = clicked.getItemMeta();
               meta.setDisplayName(ret);
               clicked.setItemMeta(meta);
               this.util.getInventory().setItem(e.getRawSlot(), clicked);
            } else {
               this.util.close();
            }
         }
      }

   }

   @EventHandler
   public void onInventoryClose(InventoryCloseEvent e) {
      if (this.util.isOpen() && e.getInventory().equals(this.util.getInventory())) {
         this.util.close();
      }

   }
}

package me.t3sl4.tornadoplus.util.inventory.element;

import me.t3sl4.tornadoplus.util.inventory.Requirement;
import me.t3sl4.tornadoplus.util.inventory.Target;
import me.t3sl4.tornadoplus.util.inventory.requirement.ClickedElementReq;
import me.t3sl4.tornadoplus.util.inventory.requirement.DragReq;
import me.t3sl4.tornadoplus.util.inventory.requirement.OrReq;
import me.t3sl4.tornadoplus.util.misc.HiddenStringUtil;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.Inventory;
import java.util.UUID;

import me.t3sl4.tornadoplus.util.inventory.requirement.AddedElementReq;
import org.bukkit.Material;

import java.util.Objects;

import org.bukkit.inventory.ItemStack;
import me.t3sl4.tornadoplus.util.inventory.Element;

public class BasicElement implements Element
{
   private final String id;
   private final ItemStack icon;
   private final Target[] targets;
   private final Requirement requirement;

   public BasicElement(final ItemStack icon, final String id, final Target... targets) {
      this.icon = this.encrypted(Objects.requireNonNull(icon), Objects.requireNonNull(id));
      this.id = id;
      this.targets = Objects.requireNonNull(targets);
      this.requirement = new OrReq(new Requirement[] { new ClickedElementReq(this), (icon.getType() == Material.AIR) ? new DragReq() : new AddedElementReq(this) });
   }

   public BasicElement(final ItemStack icon, final Target... targets) {
      this(icon, String.valueOf(UUID.randomUUID().toString()) + System.currentTimeMillis(), targets);
   }

   public BasicElement(final ItemStack icon, final String id) {
      this(icon, id, new Target[0]);
   }

   public BasicElement(final ItemStack icon) {
      this(icon, new Target[0]);
   }

   @Override
   public void displayOn(final Inventory inventory, final int locX, final int locY) {
      inventory.setItem(locX + locY * 9, this.icon.clone());
   }

   @Override
   public void accept(final InventoryInteractEvent event) {
      if (this.requirement.control(event)) {
         Target[] targets;
         for (int length = (targets = this.targets).length, i = 0; i < length; ++i) {
            final Target target = targets[i];
            target.handle(event);
         }
      }
   }

   @Override
   public boolean is(final ItemStack itemStack) {
      if (itemStack.getType() == Material.AIR && this.icon.getType() == Material.AIR) {
         return true;
      }
      try {
         return this.decrypted(itemStack).equals(this.id);
      }
      catch (IllegalArgumentException ex) {
         return false;
      }
   }

   @Override
   public boolean is(final Element element) {
      return element instanceof BasicElement && this.is(((BasicElement)element).icon);
   }

   private ItemStack encrypted(final ItemStack itemStack, final String textToEncrypt) {
      if (itemStack.getType() == Material.AIR) {
         return itemStack;
      }
      final ItemMeta itemMeta = itemStack.getItemMeta();
      String name = (itemMeta.getDisplayName() == null) ? new StringBuilder().append(itemStack.getType().toString()).toString() : itemMeta.getDisplayName();
      name = String.valueOf(HiddenStringUtil.getInstance().encodeString(textToEncrypt)) + name;
      itemMeta.setDisplayName(name);
      final ItemStack encryptedItem = itemStack.clone();
      encryptedItem.setItemMeta(itemMeta);
      return encryptedItem;
   }

   private String decrypted(final ItemStack itemStack) throws IllegalArgumentException {
      if (itemStack.getType() == Material.AIR) {
         return "";
      }
      if (itemStack.hasItemMeta() && itemStack.getItemMeta().hasDisplayName()) {
         final String name = itemStack.getItemMeta().getDisplayName();
         return HiddenStringUtil.getInstance().extractHiddenString(name);
      }
      throw new IllegalArgumentException("The itemStack couldn't be decrypted because it has no display name\n" + itemStack);
   }
}
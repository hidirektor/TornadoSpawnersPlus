package me.t3sl4.tornadoplus.util.misc.api;

import java.util.function.BiFunction;

import me.t3sl4.tornadoplus.util.api.anvil.AnvilListener;
import me.t3sl4.tornadoplus.util.api.anvil.IAnvil;
import me.t3sl4.tornadoplus.util.api.anvil.SlotType;
import me.t3sl4.tornadoplus.util.version.VersionMatcher;
import me.t3sl4.tornadoplus.util.version.nms.anvil.Anvil1_10_R1;
import me.t3sl4.tornadoplus.util.version.nms.anvil.Anvil1_11_R1;
import me.t3sl4.tornadoplus.util.version.nms.anvil.Anvil1_12_R1;
import me.t3sl4.tornadoplus.util.version.nms.anvil.Anvil1_13_R1;
import me.t3sl4.tornadoplus.util.version.nms.anvil.Anvil1_13_R2;
import me.t3sl4.tornadoplus.util.version.nms.anvil.Anvil1_8_R1;
import me.t3sl4.tornadoplus.util.version.nms.anvil.Anvil1_8_R2;
import me.t3sl4.tornadoplus.util.version.nms.anvil.Anvil1_8_R3;
import me.t3sl4.tornadoplus.util.version.nms.anvil.Anvil1_9_R1;
import me.t3sl4.tornadoplus.util.version.nms.anvil.Anvil1_9_R2;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class AnvilUtil {
   private static final IAnvil anvil = (IAnvil)(new VersionMatcher(5, new Class[]{Anvil1_8_R1.class, Anvil1_8_R2.class, Anvil1_8_R3.class, Anvil1_9_R1.class, Anvil1_9_R2.class, Anvil1_10_R1.class, Anvil1_11_R1.class, Anvil1_12_R1.class, Anvil1_13_R1.class, Anvil1_13_R2.class})).getNmsClass();
   private final AnvilListener listener = new AnvilListener(this);
   private final Player player;
   private final JavaPlugin plugin;
   private int containerId;
   private boolean open;
   private BiFunction<Player, String, String> biFunction;
   private Inventory inventory;

   private AnvilUtil(JavaPlugin plugin, Player player) {
      this.plugin = plugin;
      this.player = player;
   }

   public void open(String insert, BiFunction<Player, String, String> biFunction) {
      this.biFunction = biFunction;
      ItemStack paper = new ItemStack(Material.PAPER);
      ItemMeta paperMeta = paper.getItemMeta();
      paperMeta.setDisplayName(insert);
      paper.setItemMeta(paperMeta);
      anvil.handleInventoryCloseEvent(this.player);
      anvil.setActiveContainerDefault(this.player);
      Bukkit.getPluginManager().registerEvents(this.listener, this.plugin);
      Object container = anvil.newContainerAnvil(this.player);
      this.inventory = anvil.toBukkitInventory(container);
      this.inventory.setItem(SlotType.INPUT_LEFT.getId(), paper);
      this.containerId = anvil.getNextContainerId(this.player);
      anvil.sendPacketOpenWindow(this.containerId, this.player);
      anvil.setActiveContainer(container, this.player);
      anvil.setActiveContainerId(container, this.containerId);
      anvil.addActiveContainerSlotListener(container, this.player);
      this.open = true;
   }

   public void close() {
      Validate.isTrue(this.open, "You can't close an inventory that isn't open!");
      this.open = false;
      anvil.handleInventoryCloseEvent(this.player);
      anvil.setActiveContainerDefault(this.player);
      anvil.sendPacketCloseWindow(this.containerId, this.player);
      HandlerList.unregisterAll(this.listener);
   }

   public String apply(String insert) {
      return (String)this.biFunction.apply(this.player, insert);
   }

   public Inventory getInventory() {
      return this.inventory;
   }

   public boolean isOpen() {
      return this.open;
   }

   public static AnvilUtil init(JavaPlugin plugin, Player holder) {
      return new AnvilUtil(plugin, holder);
   }
}

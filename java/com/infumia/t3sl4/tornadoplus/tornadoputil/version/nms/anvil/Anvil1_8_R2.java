package com.infumia.t3sl4.tornadoplus.tornadoputil.version.nms.anvil;

import com.infumia.t3sl4.tornadoplus.tornadoputil.api.anvil.IAnvil;
import net.minecraft.server.v1_8_R2.BlockPosition;
import net.minecraft.server.v1_8_R2.Blocks;
import net.minecraft.server.v1_8_R2.ChatMessage;
import net.minecraft.server.v1_8_R2.Container;
import net.minecraft.server.v1_8_R2.ContainerAnvil;
import net.minecraft.server.v1_8_R2.EntityPlayer;
import net.minecraft.server.v1_8_R2.PacketPlayOutCloseWindow;
import net.minecraft.server.v1_8_R2.PacketPlayOutOpenWindow;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R2.event.CraftEventFactory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Anvil1_8_R2 implements IAnvil {
   public int getNextContainerId(Player player) {
      return this.toNMS(player).nextContainerCounter();
   }

   public void handleInventoryCloseEvent(Player player) {
      CraftEventFactory.handleInventoryCloseEvent(this.toNMS(player));
   }

   public void sendPacketOpenWindow(int containerId, Player player) {
      this.toNMS(player).playerConnection.sendPacket(new PacketPlayOutOpenWindow(containerId, "minecraft:anvil", new ChatMessage(Blocks.ANVIL.a() + ".name", new Object[0])));
   }

   public void sendPacketCloseWindow(int containerId, Player player) {
      this.toNMS(player).playerConnection.sendPacket(new PacketPlayOutCloseWindow(containerId));
   }

   public void setActiveContainerDefault(Player player) {
      this.toNMS(player).activeContainer = this.toNMS(player).defaultContainer;
   }

   public void setActiveContainer(Object containerAnvil, Player player) {
      this.toNMS(player).activeContainer = (Container)containerAnvil;
   }

   public void setActiveContainerId(Object containerAnvil, int containerId) {
      ((Container)containerAnvil).windowId = containerId;
   }

   public void addActiveContainerSlotListener(Object containerAnvil, Player player) {
      ((Container)containerAnvil).addSlotListener(this.toNMS(player));
   }

   public Inventory toBukkitInventory(Object containerAnvil) {
      return ((Container)containerAnvil).getBukkitView().getTopInventory();
   }

   public Object newContainerAnvil(Player player) {
      return new Anvil1_8_R2.AnvilContainer(this.toNMS(player), (Anvil1_8_R2.AnvilContainer)null);
   }

   private EntityPlayer toNMS(Player player) {
      return ((CraftPlayer)player).getHandle();
   }

   private class AnvilContainer extends ContainerAnvil {
      private AnvilContainer(EntityPlayer entityPlayer) {
         super(entityPlayer.inventory, entityPlayer.world, BlockPosition.ZERO, entityPlayer);
         this.checkReachable = false;
      }

      // $FF: synthetic method
      AnvilContainer(EntityPlayer var2, Anvil1_8_R2.AnvilContainer var3) {
         this(var2);
      }
   }
}

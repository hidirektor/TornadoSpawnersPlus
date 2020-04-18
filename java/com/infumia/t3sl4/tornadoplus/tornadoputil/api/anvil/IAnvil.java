package com.infumia.t3sl4.tornadoplus.tornadoputil.api.anvil;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public interface IAnvil {
   int getNextContainerId(Player var1);

   void handleInventoryCloseEvent(Player var1);

   void sendPacketOpenWindow(int var1, Player var2);

   void sendPacketCloseWindow(int var1, Player var2);

   void setActiveContainerDefault(Player var1);

   void setActiveContainer(Object var1, Player var2);

   void setActiveContainerId(Object var1, int var2);

   void addActiveContainerSlotListener(Object var1, Player var2);

   Inventory toBukkitInventory(Object var1);

   Object newContainerAnvil(Player var1);
}

package me.t3sl4.tornadoplus.util.inventory;

import org.bukkit.entity.Player;

public interface ElementEvent {
   Player player();

   void cancel();

   void closeView();
}

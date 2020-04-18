package com.infumia.t3sl4.tornadoplus.tornadoputil.inventory;

import org.bukkit.entity.Player;

public interface ElementEvent {
   Player player();

   void cancel();

   void closeView();
}

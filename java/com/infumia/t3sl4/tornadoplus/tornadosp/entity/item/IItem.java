package com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item;

import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.IEntity;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface IItem extends IEntity {
   void drop(Location var1);

   void give(Player var1);
}

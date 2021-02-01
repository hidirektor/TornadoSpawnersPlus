package me.t3sl4.tornadoplus.entity.item;

import me.t3sl4.tornadoplus.entity.IEntity;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface IItem extends IEntity {
   void drop(Location var1);

   void give(Player var1);
}

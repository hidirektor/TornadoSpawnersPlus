package com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner;

import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.IEntity;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface ISpawner extends IEntity {
   boolean give(Player var1);

   void placeSpawner(Location var1);

   boolean breakSpawner(Location var1, Player var2);
}

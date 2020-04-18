package com.infumia.t3sl4.tornadoplus.tornadopsp.nms;

import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.IEntity;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner.ISpawner;
import org.bukkit.Location;

public interface ISpawnerUtil extends INMSUtil {
   void placeSpawner(ISpawner var1, Location var2);

   ISpawner toISpawner(Location var1);

   IEntity toIEntity(Location var1);
}

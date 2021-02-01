package me.t3sl4.tornadoplus.nms;

import me.t3sl4.tornadoplus.entity.IEntity;
import me.t3sl4.tornadoplus.entity.spawner.ISpawner;
import org.bukkit.Location;

public interface ISpawnerUtil extends INMSUtil {
   void placeSpawner(ISpawner var1, Location var2);

   ISpawner toISpawner(Location var1);

   IEntity toIEntity(Location var1);
}

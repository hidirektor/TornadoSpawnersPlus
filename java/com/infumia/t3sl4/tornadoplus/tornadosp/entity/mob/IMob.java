package com.infumia.t3sl4.tornadoplus.tornadopsp.entity.mob;

import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.IEntity;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

public interface IMob extends IEntity {
   LivingEntity create(World var1);

   Entity spawn(Location var1);

   EntityType toEntityType();
}

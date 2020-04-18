package com.infumia.t3sl4.tornadoplus.tornadopsp.nms;

import java.util.List;

import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.mob.IMob;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

public interface IMobUtil extends INMSUtil {
   Entity createEntity(EntityType var1, World var2);

   Entity spawnEntity(Entity var1, Location var2);

   IMob toIMob(LivingEntity var1);

   Creature addTarget(List<EntityType> var1, Creature var2);
}

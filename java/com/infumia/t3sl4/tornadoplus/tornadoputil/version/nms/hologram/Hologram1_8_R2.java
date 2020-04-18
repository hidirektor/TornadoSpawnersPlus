package com.infumia.t3sl4.tornadoplus.tornadoputil.version.nms.hologram;

import com.infumia.t3sl4.tornadoplus.tornadoputil.api.IHologram;
import net.minecraft.server.v1_8_R2.Entity;
import net.minecraft.server.v1_8_R2.EntityArmorStand;
import net.minecraft.server.v1_8_R2.Packet;
import net.minecraft.server.v1_8_R2.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R2.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_8_R2.World;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class Hologram1_8_R2 implements IHologram {
   public Object spawnHologram(String text, Location location) {
      World world = ((CraftWorld)location.getWorld()).getHandle();
      EntityArmorStand armorStand = new EntityArmorStand(world);
      this.configureHologram(armorStand, text, location);
      world.addEntity(armorStand, SpawnReason.CUSTOM);
      return armorStand;
   }

   public void removeHologram(org.bukkit.World world, Object entity) {
      World nmsWorld = ((CraftWorld)world).getHandle();
      nmsWorld.removeEntity((Entity)entity);
   }

   public Object[] createPacket(Location location, String text) {
      World world = ((CraftWorld)location.getWorld()).getHandle();
      EntityArmorStand armorStand = new EntityArmorStand(world);
      this.configureHologram(armorStand, text, location);
      return new Object[]{new PacketPlayOutSpawnEntityLiving(armorStand), armorStand.getId()};
   }

   public Object removePacket(int id) {
      return new PacketPlayOutEntityDestroy(new int[]{id});
   }

   public void configureHologram(Object entity, String text, Location location) {
      Entity nmsEntity = (Entity)entity;
      nmsEntity.setCustomName(text);
      nmsEntity.setCustomNameVisible(true);
      nmsEntity.b(true);
      nmsEntity.setLocation(location.getX(), location.getY(), location.getZ(), 0.0F, 0.0F);
      nmsEntity.setInvisible(true);
   }

   public void sendPacket(Player player, Object packet) {
      ((CraftPlayer)player).getHandle().playerConnection.sendPacket((Packet)packet);
   }
}

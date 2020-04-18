package com.infumia.t3sl4.tornadoplus.tornadoputil.version.nms.hologram;

import com.infumia.t3sl4.tornadoplus.tornadoputil.api.IHologram;
import net.minecraft.server.v1_14_R1.*;
import net.minecraft.server.v1_14_R1.IChatBaseComponent.ChatSerializer;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_14_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class Hologram1_14_R1 implements IHologram {
    public Object spawnHologram(String text, Location location) {
        World world = ((CraftWorld)location.getWorld()).getHandle();
        EntityArmorStand armorStand = new EntityArmorStand(EntityTypes.ARMOR_STAND, world);
        this.configureHologram(armorStand, text, location);
        world.addEntity(armorStand, SpawnReason.CUSTOM);
        return armorStand;
    }

    public void removeHologram(org.bukkit.World world, Object entity) {
        World nmsWorld = ((CraftWorld)world).getHandle();
        ((WorldServer) nmsWorld).removeEntity((Entity)entity);
    }

    public Object[] createPacket(Location location, String text) {
        World world = ((CraftWorld)location.getWorld()).getHandle();
        EntityArmorStand armorStand = new EntityArmorStand(EntityTypes.ARMOR_STAND, world);
        this.configureHologram(armorStand, text, location);
        return new Object[]{new PacketPlayOutSpawnEntityLiving(armorStand), armorStand.getId()};
    }

    public Object removePacket(int id) {
        return new PacketPlayOutEntityDestroy(new int[]{id});
    }

    public void configureHologram(Object entity, String text, Location location) {
        Entity nmsEntity = (Entity)entity;
        nmsEntity.setCustomName(ChatSerializer.a("{\"text\": \"" + text + "\"}"));
        nmsEntity.setCustomNameVisible(true);
        nmsEntity.setNoGravity(true);
        nmsEntity.setLocation(location.getX(), location.getY(), location.getZ(), 0.0F, 0.0F);
        nmsEntity.setInvisible(true);
    }

    public void sendPacket(Player player, Object packet) {
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket((Packet)packet);
    }
}

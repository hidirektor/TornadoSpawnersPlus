package me.t3sl4.tornadoplus.util.api;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public interface IHologram {
    Object spawnHologram(String paramString, Location paramLocation);

    void removeHologram(World paramWorld, Object paramObject);

    Object[] createPacket(Location paramLocation, String paramString);

    Object removePacket(int paramInt);

    void configureHologram(Object paramObject, String paramString, Location paramLocation);

    void sendPacket(Player paramPlayer, Object paramObject);
}

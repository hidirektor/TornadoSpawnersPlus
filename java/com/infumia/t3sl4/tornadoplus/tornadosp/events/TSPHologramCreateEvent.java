package com.infumia.t3sl4.tornadoplus.tornadopsp.events;

import java.util.List;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.spawner.ISpawner;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TSPHologramCreateEvent extends Event {
    public static final HandlerList handlerlist = new HandlerList();

    private Location location;

    private List<String> lines;

    private ISpawner spawner;

    public TSPHologramCreateEvent(Location location, List<String> lines, ISpawner spawner) {
        this.location = location;
        this.lines = lines;
        this.spawner = spawner;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<String> getLines() {
        return this.lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public ISpawner getSpawner() {
        return this.spawner;
    }

    public void setSpawner(ISpawner spawner) {
        this.spawner = spawner;
    }

    public HandlerList getHandlers() {
        return handlerlist;
    }
}

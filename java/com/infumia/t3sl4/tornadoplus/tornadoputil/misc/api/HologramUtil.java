package com.infumia.t3sl4.tornadoplus.tornadoputil.misc.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import com.infumia.t3sl4.tornadoplus.tornadoputil.api.IHologram;
import com.infumia.t3sl4.tornadoplus.tornadoputil.version.VersionMatcher;
import com.infumia.t3sl4.tornadoplus.tornadoputil.version.nms.hologram.*;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class HologramUtil {
    private final IHologram hologram = (IHologram)(new VersionMatcher(8, Hologram1_8_R1.class, Hologram1_8_R2.class, Hologram1_8_R3.class, Hologram1_9_R1.class, Hologram1_9_R2.class, Hologram1_10_R1.class, Hologram1_11_R1.class, Hologram1_12_R1.class, Hologram1_13_R1.class, Hologram1_13_R2.class)).getNmsClass();

    private final double offset = 0.23D;

    private final List<Integer> ids = new ArrayList<>();

    private final List<Object> entities = new ArrayList();

    private Location location;

    private List<String> lines = new ArrayList<>();

    private HologramUtil(Location location, String... text) {
        this.location = location;
        addLine(text);
    }

    public void addLine(String... text) {
        this.lines.addAll(Arrays.asList(text));
        update();
    }

    public void setLines(String... text) {
        this.lines = Arrays.asList(text);
        update();
    }

    public void teleport(Location location) {
        this.location = location;
        update();
    }

    public Location location() {
        return this.location;
    }

    public List<String> getLines() {
        return this.lines;
    }

    public void displayTo(Player... players) {
        Location current = this.location.clone().add(0.0D, 0.23D * this.lines.size() - 1.97D, 0.0D);
        for (String str : this.lines) {
            Object[] packet = this.hologram.createPacket(this.location, str);
            this.ids.add((Integer)packet[1]);
            for (Player player : players)
                this.hologram.sendPacket(player, packet[0]);
            current.subtract(0.0D, 0.23D, 0.0D);
        }
    }

    public void removeFrom(Player... players) {
        Object packet = null;
        for (Iterator<Integer> iterator = this.ids.iterator(); iterator.hasNext(); ) {
            int id = ((Integer)iterator.next()).intValue();
            packet = this.hologram.removePacket(id);
        }
        for (Player player : players) {
            if (packet != null)
                this.hologram.sendPacket(player, packet);
        }
    }

    public void spawn() {
        Location current = this.location.clone().add(0.0D, 0.23D * this.lines.size() - 1.97D, 0.0D).add(0.0D, 0.23D, 0.0D);
        for (String str : this.lines)
            this.entities.add(this.hologram.spawnHologram(str, current.subtract(0.0D, 0.23D, 0.0D)));
    }

    public void remove() {
        for (Object ent : this.entities)
            this.hologram.removeHologram(this.location.getWorld(), ent);
    }

    private void update() {
        try {
            if (!this.entities.isEmpty()) {
                for (int i = 0; i < this.entities.size(); i++) {
                    Object ent = this.entities.get(i);
                    if (i > this.lines.size() - 1)
                        this.hologram.removeHologram(this.location.getWorld(), ent);
                }
                Location current = this.location.clone().add(0.0D, 0.23D * this.lines.size() - 1.97D, 0.0D);
                for (int j = 0; j < this.lines.size(); j++) {
                    String text = this.lines.get(j);
                    if (j >= this.entities.size()) {
                        this.hologram.spawnHologram(text, current);
                    } else {
                        this.hologram.configureHologram(this.entities.get(j), text, current);
                    }
                    current.subtract(0.0D, 0.23D, 0.0D);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static HologramUtil build(Location location, String... text) {
        return new HologramUtil(location, text);
    }
}

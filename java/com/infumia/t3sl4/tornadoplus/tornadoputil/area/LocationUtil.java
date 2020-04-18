package com.infumia.t3sl4.tornadoplus.tornadoputil.area;

import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationUtil {
    private static LocationUtil instance;

    private final Pattern LOCATION_PATTERN = Pattern.compile("((?<world>[^:/]+)[:/])?(?<x>[\\-0-9\\.]+),(?<y>[\\-0-9\\.]+),(?<z>[\\-0-9\\.]+)(:(?<yaw>[\\-0-9\\.]+):(?<pitch>[\\-0-9\\.]+))?");

    public String asString(Location loc) {
        Objects.requireNonNull(loc);
        String s = "";
        if (loc.getWorld() != null && loc.getWorld().getName() != null)
            s = s + loc.getWorld().getName() + ":";
        s = s + String.format(Locale.ENGLISH, "%.2f,%.2f,%.2f", new Object[] { Double.valueOf(loc.getX()), Double.valueOf(loc.getY()), Double.valueOf(loc.getZ()) });
        if (loc.getYaw() != 0.0F || loc.getPitch() != 0.0F)
            s = s + String.format(Locale.ENGLISH, ":%.2f:%.2f", new Object[] { Float.valueOf(loc.getYaw()), Float.valueOf(loc.getPitch()) });
        return s;
    }

    public String asKey(Location loc) {
        Objects.requireNonNull(loc);
        return asString(loc).replaceAll(":", "/").replaceAll("\\.", "_");
    }

    public Location fromString(String locString) {
        Objects.requireNonNull(locString);
        if (locString.isEmpty())
            return null;
        Matcher m = this.LOCATION_PATTERN.matcher(locString.replaceAll("_", "\\."));
        if (m.matches())
            return new Location(Bukkit.getWorld(m.group("world")),
                    Double.parseDouble(m.group("x")),
                    Double.parseDouble(m.group("y")),
                    Double.parseDouble(m.group("z")),
                    (m.group("yaw") != null) ? Float.parseFloat(m.group("yaw")) : 0.0F,
                    (m.group("pitch") != null) ? Float.parseFloat(m.group("pitch")) : 0.0F);
        return null;
    }

    public Location centerOnBlock(Location loc) {
        Objects.requireNonNull(loc);
        return new Location(loc.getWorld(), loc
                .getBlockX() + 0.5D, loc.getBlockY() + 0.1D, loc.getBlockZ() + 0.5D, loc
                .getYaw(), loc.getPitch());
    }

    public Location centerInBlock(Location loc) {
        Objects.requireNonNull(loc);
        return new Location(loc.getWorld(), loc
                .getBlockX() + 0.5D, loc.getBlockY() + 0.5D, loc.getBlockZ() + 0.5D, loc
                .getYaw(), loc.getPitch());
    }

    public static synchronized LocationUtil getInstance() {
        if (instance == null)
            instance = new LocationUtil();
        return instance;
    }
}

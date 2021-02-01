package me.t3sl4.tornadoplus.util.misc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bukkit.Bukkit;

public class VersionUtil {
   public static final String VERSION = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
   public static final int MAJOR;
   public static final int MINOR;
   public static final int RELEASE;

   static {
      Pattern pattern = Pattern.compile("v([0-9]+)_([0-9]+)_R([0-9]+)");
      Matcher m = pattern.matcher(VERSION);
      if (!m.matches()) {
         throw new IllegalStateException("Cannot parse version \"" + VERSION + "\", make sure it follows \"v<major>_<minor>...\"");
      } else {
         MAJOR = Integer.parseInt(m.group(1));
         MINOR = Integer.parseInt(m.group(2));
         RELEASE = Integer.parseInt(m.group(3));
      }
   }
}

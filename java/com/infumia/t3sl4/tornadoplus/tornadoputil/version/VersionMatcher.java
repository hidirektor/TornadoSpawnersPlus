package com.infumia.t3sl4.tornadoplus.tornadoputil.version;

import java.util.Arrays;
import org.bukkit.Bukkit;
import java.util.List;

public class VersionMatcher<T>
{
   private final String serverVersion;
   private final List<Class<? extends T>> versions;
   private final int subString;
   private final T nmsClass;

   public VersionMatcher(final int subString, final Class<? extends T>... versions) {
      this.serverVersion = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3].substring(1);
      this.versions = Arrays.asList(versions);
      this.subString = subString;
      this.nmsClass = this.match();
   }

   public T getNmsClass() {
      return this.nmsClass;
   }

   private T match() {
      try {
         return ((Class<T>)this.versions.stream()
                 .filter(version -> version.getSimpleName().substring(this.subString).equals(this.serverVersion))
                 .findFirst()
                 .orElseThrow(() -> new RuntimeException("Your server version isn't supported!")))
                 .newInstance();
      } catch (IllegalAccessException|InstantiationException ex) {
         throw new RuntimeException(ex);
      }
   }
}
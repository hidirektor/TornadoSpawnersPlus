package com.infumia.t3sl4.tornadoplus.tornadoputil.configuration;

import java.util.List;
import org.bukkit.configuration.ConfigurationSection;

public interface ConfigurationFile {
   void set(String var1, Object var2);

   <T> T get(String var1);

   String getString(String var1);

   List<String> getStringList(String var1);

   int getInt(String var1);

   double getDouble(String var1);

   float getFloat(String var1);

   ConfigurationSection getConfigurationSection(String var1);

   void save();

   void load();

   void delete();

   String name();
}

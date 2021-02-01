package me.t3sl4.tornadoplus.util.configuration;

import java.io.IOException;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;

public interface YamlFile {
   void copy();

   boolean delete();

   FileConfiguration loadConfiguration();

   void load(FileConfiguration var1) throws IOException, InvalidConfigurationException;

   void save(FileConfiguration var1) throws IOException;

   void init();

   String name();
}

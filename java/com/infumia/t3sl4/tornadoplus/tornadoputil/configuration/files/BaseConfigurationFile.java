package com.infumia.t3sl4.tornadoplus.tornadoputil.configuration.files;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.infumia.t3sl4.tornadoplus.tornadoputil.configuration.ConfigurationFile;
import com.infumia.t3sl4.tornadoplus.tornadoputil.configuration.YamlFile;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.FileConfiguration;

public class BaseConfigurationFile implements ConfigurationFile {
   private final YamlFile file;
   private FileConfiguration fileConfiguration;

   public BaseConfigurationFile(YamlFile file) {
      this.file = file;
      this.fileConfiguration = file.loadConfiguration();
      this.load();
   }

   public void set(String path, Object variable) {
      Objects.requireNonNull(path);
      this.load();
      this.fileConfiguration.set(path, variable);
      this.save();
   }

   public <T> T get(String path) {
      Objects.requireNonNull(path);
      return (T) this.fileConfiguration.get(path);
   }

   public String getString(String path) {
      return (String)this.get(path);
   }

   public List<String> getStringList(String path) {
      return (List)(this.get(path) instanceof MemorySection ? new ArrayList() : (List)this.get(path));
   }

   public int getInt(String path) {
      return (Integer)this.get(path);
   }

   public double getDouble(String path) {
      return Double.parseDouble("" + this.get(path));
   }

   public float getFloat(String path) {
      return Float.parseFloat("" + this.get(path));
   }

   public ConfigurationSection getConfigurationSection(String path) {
      return (ConfigurationSection)this.get(path);
   }

   public void save() {
      try {
         this.file.save(this.fileConfiguration);
      } catch (IOException var2) {
         var2.printStackTrace();
      }

   }

   public void load() {
      try {
         this.file.load(this.fileConfiguration);
      } catch (InvalidConfigurationException | IOException var2) {
         this.file.init();
         this.file.copy();
         this.fileConfiguration = this.file.loadConfiguration();
      }

   }

   public void delete() {
      this.file.delete();
   }

   public String name() {
      return this.file.name();
   }
}

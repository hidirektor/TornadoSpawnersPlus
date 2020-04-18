package com.infumia.t3sl4.tornadoplus.tornadoputil.configuration.files;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.infumia.t3sl4.tornadoplus.tornadoputil.configuration.ConfigurationFile;
import com.infumia.t3sl4.tornadoplus.tornadoputil.configuration.YamlFile;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

public class LanguageFile implements ConfigurationFile {
   private final Map<String, ConfigurationFile> languageFiles = new HashMap();
   private final String pluginLanguage;
   private final String[] languages;
   private final Map<String, YamlFile> files = new HashMap();

   public LanguageFile(JavaPlugin plugin, String fileName, String filePath, String pluginLanguage, String... languages) {
      this.pluginLanguage = pluginLanguage;
      this.languages = languages;
      String resourcePath = filePath.equals("") ? "" : filePath + "/";
      String[] var10 = languages;
      int var9 = languages.length;

      for(int var8 = 0; var8 < var9; ++var8) {
         String language = var10[var8];
         String languagePath = resourcePath + language;
         YamlFile file = new BaseYamlFile(plugin, fileName, languagePath);
         this.files.put(language, file);
         this.languageFiles.put(language, new BaseConfigurationFile(file));
      }

   }

   public void set(String path, Object variable) {
      ((ConfigurationFile)this.languageFiles.get(this.pluginLanguage)).set(path, variable);
   }

   public <T> T get(String path) {
      return ((ConfigurationFile)this.languageFiles.get(this.pluginLanguage)).get(path);
   }

   public String getString(String path) {
      return (String)this.get(path);
   }

   public List<String> getStringList(String path) {
      return (List)this.get(path);
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
      ((ConfigurationFile)this.languageFiles.get(this.pluginLanguage)).save();
   }

   public void load() {
      ((ConfigurationFile)this.languageFiles.get(this.pluginLanguage)).load();
   }

   public void delete() {
      ((YamlFile)this.files.get(this.pluginLanguage)).delete();
   }

   public String name() {
      return ((YamlFile)this.files.get(this.pluginLanguage)).name();
   }
}

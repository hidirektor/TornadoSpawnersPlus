package com.infumia.t3sl4.tornadoplus.tornadoputil.configuration.files;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import com.infumia.t3sl4.tornadoplus.tornadoputil.configuration.YamlFile;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class BaseYamlFile implements YamlFile {
   private final JavaPlugin plugin;
   private final File file;
   private final String fileName;
   private final String filePath;
   private final String resourcePath;

   public BaseYamlFile(JavaPlugin plugin, String fileName, String filePath) {
      this.plugin = plugin;
      this.fileName = fileName.endsWith(".yml") ? fileName : fileName + ".yml";
      this.resourcePath = filePath.equals("") ? "" : filePath + "/";
      this.filePath = filePath.equals("") ? plugin.getDataFolder().getAbsolutePath() + "\\" : plugin.getDataFolder().getAbsolutePath() + "\\" + filePath + "\\";
      this.file = new File(this.filePath, this.fileName);
      if (!this.file.exists()) {
         this.init();
         this.copy();
      }
   }

   public void copy() {
      InputStream in = this.plugin.getResource(this.resourcePath + this.fileName);

      try {
         OutputStream out = new FileOutputStream(this.file);
         byte[] buf = new byte[63];

         int len;
         while((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
         }

         out.close();
         in.close();
      } catch (Exception var5) {
      }

   }

   public boolean delete() {
      return this.file.delete();
   }

   public FileConfiguration loadConfiguration() {
      return YamlConfiguration.loadConfiguration(this.file);
   }

   public void load(FileConfiguration fileConfiguration) throws IOException, InvalidConfigurationException {
      fileConfiguration.load(this.file);
   }

   public void save(FileConfiguration fileConfiguration) throws IOException {
      fileConfiguration.save(this.file);
   }

   public void init() {
      for(File __file = new File(this.filePath); !__file.exists(); __file = __file.getParentFile()) {
         __file.mkdir();
      }

   }

   public String name() {
      return this.file.getName().replaceAll(".yml", "");
   }
}

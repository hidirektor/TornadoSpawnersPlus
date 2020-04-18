package com.infumia.t3sl4.tornadoplus.tornadoputil.misc;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LogUtil {
   private static LogUtil instance;

   private LogUtil() {
   }

   public void info(String text) {
      this.log(Level.INFO, text);
   }

   public void severe(String text) {
      this.log(Level.SEVERE, text);
   }

   public void fine(String text) {
      this.log(Level.FINE, text);
   }

   public void warn(String text) {
      this.log(Level.WARNING, text);
   }

   public void log(Level level, String text) {
      Logger.getLogger("Minecraft").log(level, StringUtil.getInstance().chatColor(text));
   }

   public static synchronized LogUtil getInstance() {
      if (instance == null) {
         instance = new LogUtil();
      }

      return instance;
   }
}

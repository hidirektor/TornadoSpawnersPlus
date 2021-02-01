package me.t3sl4.tornadoplus.util.misc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Color;

public class StringUtil {
   public static final String RESET = "\u001b[0m";
   public static final String BOLD = "\u001b[1m";
   public static final String ITALIC = "\u001b[3m";
   public static final String UNDERLINE = "\u001b[4m";
   public static final String NORMAL = "\u001b[22m";
   public static final String BLACK = "\u001b[30m";
   public static final String RED = "\u001b[31m";
   public static final String GREEN = "\u001b[32m";
   public static final String YELLOW = "\u001b[33m";
   public static final String BLUE = "\u001b[34m";
   public static final String PURPLE = "\u001b[35m";
   public static final String CYAN = "\u001b[36m";
   public static final String WHITE = "\u001b[37m";
   public static final String BLACK_BACKGROUND = "\u001b[40m";
   public static final String RED_BACKGROUND = "\u001b[41m";
   public static final String GREEN_BACKGROUND = "\u001b[42m";
   public static final String YELLOW_BACKGROUND = "\u001b[43m";
   public static final String BLUE_BACKGROUND = "\u001b[44m";
   public static final String PURPLE_BACKGROUND = "\u001b[45m";
   public static final String CYAN_BACKGROUND = "\u001b[46m";
   public static final String WHITE_BACKGROUND = "\u001b[47m";
   private static StringUtil instance;

   private StringUtil() {
   }

   public List<String> replaceAllInList(List<String> stringList, String regex, String replacement) {
      ArrayList<String> tempStringList = new ArrayList();
      Iterator var6 = stringList.iterator();

      while(var6.hasNext()) {
         String text = (String)var6.next();
         tempStringList.add(text.replaceAll(regex, replacement));
      }

      return tempStringList;
   }

   public String chatColor(String text) {
      return ChatColor.translateAlternateColorCodes('&', text);
   }

   public List<String> chatColorL(List<String> stringList) {
      ArrayList<String> tempStringList = new ArrayList();
      Iterator var4 = stringList.iterator();

      while(var4.hasNext()) {
         String text = (String)var4.next();
         tempStringList.add(this.chatColor(text));
      }

      return tempStringList;
   }

   public String convertTrToEn(String text) {
      return text.replaceAll("ş", "s").replaceAll("Ş", "S").replaceAll("İ", "I").replaceAll("ç", "c").replaceAll("Ç", "C").replaceAll("ö", "o").replaceAll("Ö", "O").replaceAll("ü", "u").replaceAll("Ü", "U").replaceAll("ğ", "g").replaceAll("Ğ", "G").replaceAll("ı", "i");
   }

   public String toStringBuilder(List<String> stringList) {
      StringBuilder stringBuilder = new StringBuilder();

      for(int i = 0; i < stringList.size(); ++i) {
         stringBuilder.append((String)stringList.get(i));
         if (i != stringList.size() - 1) {
            stringBuilder.append("\n");
         }
      }

      return stringBuilder.toString();
   }

   public Color toColor(String textColor) {
      switch(textColor.hashCode()) {
      case -2027972496:
         if (textColor.equals("MAROON")) {
            return Color.MAROON;
         }
         break;
      case -1955522002:
         if (textColor.equals("ORANGE")) {
            return Color.ORANGE;
         }
         break;
      case -1923613764:
         if (textColor.equals("PURPLE")) {
            return Color.PURPLE;
         }
         break;
      case -1848981747:
         if (textColor.equals("SILVER")) {
            return Color.SILVER;
         }
         break;
      case -1680910220:
         if (textColor.equals("YELLOW")) {
            return Color.YELLOW;
         }
         break;
      case 81009:
         if (textColor.equals("RED")) {
            return Color.RED;
         }
         break;
      case 2016956:
         if (textColor.equals("AQUA")) {
            return Color.AQUA;
         }
         break;
      case 2041946:
         if (textColor.equals("BLUE")) {
            return Color.BLUE;
         }
         break;
      case 2196067:
         if (textColor.equals("GRAY")) {
            return Color.GRAY;
         }
         break;
      case 2336725:
         if (textColor.equals("LIME")) {
            return Color.LIME;
         }
         break;
      case 2388918:
         if (textColor.equals("NAVY")) {
            return Color.NAVY;
         }
         break;
      case 2570844:
         if (textColor.equals("TEAL")) {
            return Color.TEAL;
         }
         break;
      case 63281119:
         if (textColor.equals("BLACK")) {
            return Color.BLACK;
         }
         break;
      case 68081379:
         if (textColor.equals("GREEN")) {
            return Color.GREEN;
         }
         break;
      case 75295163:
         if (textColor.equals("OLIVE")) {
            return Color.OLIVE;
         }
         break;
      case 82564105:
         if (textColor.equals("WHITE")) {
            return Color.WHITE;
         }
         break;
      case 198329015:
         if (textColor.equals("FUCHSIA")) {
            return Color.FUCHSIA;
         }
      }

      return null;
   }

   public String headUpperCase(String text) {
      StringBuilder stringBuilder = new StringBuilder();

      for(int i = 0; i < text.toCharArray().length; ++i) {
         if (i == 0) {
            stringBuilder.append(Character.toUpperCase(text.charAt(i)));
         } else if (text.charAt(i) == '_' && text.toCharArray().length >= i + 1) {
            stringBuilder.append(" ").append(Character.toUpperCase(text.charAt(i + 1)));
            ++i;
         } else {
            stringBuilder.append(text.charAt(i));
         }
      }

      return stringBuilder.toString();
   }

   public static StringUtil getInstance() {
      if (instance == null) {
         instance = new StringUtil();
      }

      return instance;
   }
}

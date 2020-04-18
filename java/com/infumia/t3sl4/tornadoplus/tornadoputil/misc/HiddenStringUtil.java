package com.infumia.t3sl4.tornadoplus.tornadoputil.misc;

import java.nio.charset.Charset;
import org.bukkit.ChatColor;

public class HiddenStringUtil {
   private final String SEQUENCE_HEADER;
   private final String SEQUENCE_FOOTER;
   private static HiddenStringUtil instance;

   private HiddenStringUtil() {
      this.SEQUENCE_HEADER = "" + ChatColor.RESET + ChatColor.UNDERLINE + ChatColor.RESET;
      this.SEQUENCE_FOOTER = "" + ChatColor.RESET + ChatColor.ITALIC + ChatColor.RESET;
   }

   public String encodeString(String hiddenString) {
      return this.quote(this.stringToColors(hiddenString));
   }

   public boolean hasHiddenString(String input) {
      if (input == null) {
         return false;
      } else {
         return input.contains(this.SEQUENCE_HEADER) && input.contains(this.SEQUENCE_FOOTER);
      }
   }

   public String extractHiddenString(String input) {
      return this.colorsToString(this.extract(input));
   }

   public String replaceHiddenString(String input, String hiddenString) {
      if (input == null) {
         return null;
      } else {
         int start = input.indexOf(this.SEQUENCE_HEADER);
         int end = input.indexOf(this.SEQUENCE_FOOTER);
         return start >= 0 && end >= 0 ? input.substring(0, start + this.SEQUENCE_HEADER.length()) + this.stringToColors(hiddenString) + input.substring(end, input.length()) : null;
      }
   }

   private String quote(String input) {
      return input == null ? null : this.SEQUENCE_HEADER + input + this.SEQUENCE_FOOTER;
   }

   private String extract(String input) {
      if (input == null) {
         return null;
      } else {
         int start = input.indexOf(this.SEQUENCE_HEADER);
         int end = input.indexOf(this.SEQUENCE_FOOTER);
         return start >= 0 && end >= 0 ? input.substring(start + this.SEQUENCE_HEADER.length(), end) : null;
      }
   }

   private String stringToColors(String normal) {
      if (normal == null) {
         return null;
      } else {
         byte[] bytes = normal.getBytes(Charset.forName("UTF-8"));
         char[] chars = new char[bytes.length * 4];

         for(int i = 0; i < bytes.length; ++i) {
            char[] hex = this.byteToHex(bytes[i]);
            chars[i * 4] = 167;
            chars[i * 4 + 1] = hex[0];
            chars[i * 4 + 2] = 167;
            chars[i * 4 + 3] = hex[1];
         }

         return new String(chars);
      }
   }

   private String colorsToString(String colors) {
      if (colors == null) {
         return null;
      } else {
         if ((colors = colors.toLowerCase().replace("§", "")).length() % 2 != 0) {
            colors = colors.substring(0, colors.length() / 2 * 2);
         }

         char[] chars = colors.toCharArray();
         byte[] bytes = new byte[chars.length / 2];

         for(int i = 0; i < chars.length; i += 2) {
            bytes[i / 2] = this.hexToByte(chars[i], chars[i + 1]);
         }

         return new String(bytes, Charset.forName("UTF-8"));
      }
   }

   private int hexToUnsignedInt(char c) {
      if (c >= '0' && c <= '9') {
         return c - 48;
      } else if (c >= 'a' && c <= 'f') {
         return c - 87;
      } else {
         throw new IllegalArgumentException("Invalid hex char: out of range");
      }
   }

   private char unsignedIntToHex(int i) {
      if (i >= 0 && i <= 9) {
         return (char)(i + 48);
      } else if (i >= 10 && i <= 15) {
         return (char)(i + 87);
      } else {
         throw new IllegalArgumentException("Invalid hex int: out of range");
      }
   }

   private byte hexToByte(char hex1, char hex0) {
      return (byte)((this.hexToUnsignedInt(hex1) << 4 | this.hexToUnsignedInt(hex0)) + -128);
   }

   private char[] byteToHex(byte b) {
      int unsignedByte = b - -128;
      return new char[]{this.unsignedIntToHex(unsignedByte >> 4 & 15), this.unsignedIntToHex(unsignedByte & 15)};
   }

   public static synchronized HiddenStringUtil getInstance() {
      if (instance == null) {
         instance = new HiddenStringUtil();
      }

      return instance;
   }
}

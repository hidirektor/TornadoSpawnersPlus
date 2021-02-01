package me.t3sl4.tornadoplus.util.exceptions;

import me.t3sl4.tornadoplus.util.misc.VersionUtil;

public class UnsupportedVersionException extends RuntimeException {
   public UnsupportedVersionException() {
      super("Unsupported version \"" + VersionUtil.VERSION + "\", report this to the developers");
   }
}

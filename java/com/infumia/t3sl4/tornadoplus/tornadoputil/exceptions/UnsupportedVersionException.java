package com.infumia.t3sl4.tornadoplus.tornadoputil.exceptions;

import com.infumia.t3sl4.tornadoplus.tornadoputil.misc.VersionUtil;

public class UnsupportedVersionException extends RuntimeException {
   public UnsupportedVersionException() {
      super("Unsupported version \"" + VersionUtil.VERSION + "\", report this to the developers");
   }
}

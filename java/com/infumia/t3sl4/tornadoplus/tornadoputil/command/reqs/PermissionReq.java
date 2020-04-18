package com.infumia.t3sl4.tornadoplus.tornadoputil.command.reqs;

import com.infumia.t3sl4.tornadoplus.tornadoputil.command.ErrorType;
import com.infumia.t3sl4.tornadoplus.tornadoputil.command.IArgumentReq;
import org.bukkit.command.CommandSender;

public class PermissionReq implements IArgumentReq {
   private final String permission;

   public PermissionReq(String permission) {
      this.permission = permission;
   }

   public ErrorType control(CommandSender sender, String[] args) {
      return sender.hasPermission(this.permission) ? ErrorType.NONE : ErrorType.PERMISSION;
   }
}

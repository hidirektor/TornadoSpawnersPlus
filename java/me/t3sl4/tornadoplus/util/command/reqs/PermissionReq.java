package me.t3sl4.tornadoplus.util.command.reqs;

import me.t3sl4.tornadoplus.util.command.ErrorType;
import me.t3sl4.tornadoplus.util.command.IArgumentReq;
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

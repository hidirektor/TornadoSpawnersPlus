package me.t3sl4.tornadoplus.util.command;

import me.t3sl4.tornadoplus.util.command.reqs.PermissionReq;
import org.bukkit.command.CommandSender;

public class BaseCommand implements ICommand {
   private final IArgumentReq[] argumentReqs;
   private final IArgument[] arguments;

   public BaseCommand(IArgumentReq[] argumentReqs, IArgument... arguments) {
      this.argumentReqs = argumentReqs;
      this.arguments = arguments;
   }

   public BaseCommand(String permission, IArgument... arguments) {
      this(new IArgumentReq[]{new PermissionReq(permission)}, arguments);
   }

   public BaseCommand(IArgument... arguments) {
      this(new IArgumentReq[0], arguments);
   }

   public void process(CommandSender commandSender, String[] args) {
      throw new UnsupportedOperationException();
   }

   public IArgument isFinalArgument(String arg) {
      IArgument[] var5;
      int var4 = (var5 = this.arguments).length;

      for(int var3 = 0; var3 < var4; ++var3) {
         IArgument argument = var5[var3];
         if (argument.isFinalArgument(arg) != null) {
            return argument;
         }
      }

      return this;
   }

   public ErrorType control(CommandSender commandSender, String[] args) {
      IArgumentReq[] var6;
      int var5 = (var6 = this.argumentReqs).length;

      for(int var4 = 0; var4 < var5; ++var4) {
         IArgumentReq argumentReq = var6[var4];
         ErrorType type = argumentReq.control(commandSender, args);
         if (type != ErrorType.NONE) {
            return type;
         }
      }

      return ErrorType.NONE;
   }
}

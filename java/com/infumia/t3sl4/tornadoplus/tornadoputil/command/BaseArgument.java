package com.infumia.t3sl4.tornadoplus.tornadoputil.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.infumia.t3sl4.tornadoplus.tornadoputil.command.reqs.PermissionReq;
import com.infumia.t3sl4.tornadoplus.tornadoputil.command.reqs.WithArgReq;
import org.bukkit.command.CommandSender;

public class BaseArgument implements IArgument {
   private final String arg;
   private final List<IArgumentReq> argumentReqs;

   private BaseArgument(String arg, List<IArgumentReq> argumentReqs) {
      this.arg = arg;
      this.argumentReqs = new ArrayList(argumentReqs);
   }

   public BaseArgument(String arg, IArgumentReq... argumentReqs) {
      this(arg, Arrays.asList(argumentReqs));
   }

   public BaseArgument(String arg, String permission, IArgumentReq... argumentReqs) {
      this(arg);
      this.argumentReqs.add(new PermissionReq(permission));
      this.argumentReqs.addAll(Arrays.asList(argumentReqs));
   }

   public BaseArgument(String arg, String permission, int minArg, int maxArg, IArgumentReq... argumentReqs) {
      this(arg, permission, new WithArgReq(minArg, maxArg));
      this.argumentReqs.addAll(Arrays.asList(argumentReqs));
   }

   public void process(CommandSender commandSender, String[] args) {
      throw new UnsupportedOperationException();
   }

   public IArgument isFinalArgument(String arg) {
      return this.arg.equalsIgnoreCase(arg) ? this : null;
   }

   public ErrorType control(CommandSender commandSender, String[] args) {
      Iterator var4 = this.argumentReqs.iterator();

      while(var4.hasNext()) {
         IArgumentReq argumentReq = (IArgumentReq)var4.next();
         ErrorType type = argumentReq.control(commandSender, args);
         if (type != ErrorType.NONE) {
            return type;
         }
      }

      return ErrorType.NONE;
   }
}

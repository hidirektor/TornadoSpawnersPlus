package com.infumia.t3sl4.tornadoplus.tornadoputil.observer.source;

import java.util.Iterator;
import java.util.Objects;
import java.util.Vector;
import com.infumia.t3sl4.tornadoplus.tornadoputil.observer.Source;
import com.infumia.t3sl4.tornadoplus.tornadoputil.observer.Target;

public class BasicSource implements Source<Object> {
   private final Vector<Target<Object>> targets = new Vector();

   public void subscribe(Target<Object> target) {
      if (!this.targets.contains(target)) {
         this.targets.add((Target)Objects.requireNonNull(target));
      }

   }

   public void unsubscribe(Target<Object> target) {
      this.targets.remove(target);
   }

   public void notifyTargets(Object argument) {
      Iterator var3 = this.targets.iterator();

      while(var3.hasNext()) {
         Target<Object> target = (Target)var3.next();
         target.update(argument);
      }

   }
}

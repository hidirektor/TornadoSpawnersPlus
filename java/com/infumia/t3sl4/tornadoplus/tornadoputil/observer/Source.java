package com.infumia.t3sl4.tornadoplus.tornadoputil.observer;

public interface Source<Object> {
   void subscribe(Target<Object> var1);

   void unsubscribe(Target<Object> var1);

   void notifyTargets(Object var1);
}

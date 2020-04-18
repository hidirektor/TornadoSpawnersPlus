package com.infumia.t3sl4.tornadoplus.tornadopsp.entity.mob.argument;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.IItem;
import com.infumia.t3sl4.tornadoplus.tornadopsp.TornadoSpawnersPlus;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.mob.IMobArgument;

public class DropArgument implements IMobArgument {
   private final List<String> dropStringList;

   public DropArgument(List<String> dropStringList) {
      this.dropStringList = dropStringList;
   }

   public Map<IItem, Integer> convert() {
      Map<IItem, Integer> iItems = new HashMap();
      Iterator var2 = this.dropStringList.iterator();

      while(var2.hasNext()) {
         String dropString = (String)var2.next();
         String[] dropStrings = dropString.split(":");
         IItem IItem = (IItem)TornadoSpawnersPlus.ITEMS.get(dropStrings[0]);
         if (IItem != null) {
            if (dropStrings.length == 1) {
               iItems.put(IItem, 100);
            } else if (dropStrings.length == 2) {
               iItems.put(IItem, Integer.parseInt(dropStrings[1]));
            }
         }
      }

      return iItems;
   }
}

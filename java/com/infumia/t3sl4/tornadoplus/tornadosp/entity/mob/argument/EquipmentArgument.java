package com.infumia.t3sl4.tornadoplus.tornadopsp.entity.mob.argument;

import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.IItem;
import com.infumia.t3sl4.tornadoplus.tornadopsp.TornadoSpawnersPlus;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.mob.IMobArgument;

public class EquipmentArgument implements IMobArgument {
   private final String headString;
   private final String chestplateString;
   private final String leggingString;
   private final String bootsString;

   public EquipmentArgument(String headString, String chestplateString, String leggingString, String bootsString) {
      this.headString = headString;
      this.chestplateString = chestplateString;
      this.leggingString = leggingString;
      this.bootsString = bootsString;
   }

   public IItem[] convert() {
      return new IItem[]{(IItem)TornadoSpawnersPlus.ITEMS.get(this.headString), (IItem)TornadoSpawnersPlus.ITEMS.get(this.chestplateString), (IItem)TornadoSpawnersPlus.ITEMS.get(this.leggingString), (IItem)TornadoSpawnersPlus.ITEMS.get(this.bootsString)};
   }
}

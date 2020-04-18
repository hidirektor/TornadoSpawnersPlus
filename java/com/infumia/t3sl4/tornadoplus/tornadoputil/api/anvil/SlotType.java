package com.infumia.t3sl4.tornadoplus.tornadoputil.api.anvil;

public enum SlotType {
   INPUT_LEFT(0),
   INPUT_RIGHT(1),
   OUTPUT(2);

   int id;

   private SlotType(int id) {
      this.id = id;
   }

   public int getId() {
      return this.id;
   }
}

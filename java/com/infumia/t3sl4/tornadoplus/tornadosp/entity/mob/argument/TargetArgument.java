package com.infumia.t3sl4.tornadoplus.tornadopsp.entity.mob.argument;

import java.util.ArrayList;
import java.util.List;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.mob.IMobArgument;
import org.bukkit.entity.EntityType;

public class TargetArgument implements IMobArgument {
   private final List<String> targetStringList;

   public TargetArgument(List<String> targetStringList) {
      this.targetStringList = targetStringList;
   }

   public List<EntityType> convert() {
      List<EntityType> entityTypes = new ArrayList();
      this.targetStringList.forEach((target) -> {
         entityTypes.add(EntityType.valueOf(target));
      });
      return entityTypes;
   }
}

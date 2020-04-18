package com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.argument.meta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.IItemArgument;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.inventory.ItemStack;

public class FireworkMeta implements IItemArgument {
   private final List<String> fireworkEffectStringList;

   public FireworkMeta(List<String> fireworkEffectStringList) {
      this.fireworkEffectStringList = fireworkEffectStringList;
   }

   public List<FireworkEffect> convert() {
      List<FireworkEffect> fireworkEffects = new ArrayList();
      Iterator var2 = this.fireworkEffectStringList.iterator();

      while(var2.hasNext()) {
         String fireworkString = (String)var2.next();
         String[] split1 = fireworkString.split(";");
         String type = split1[0];
         String flicker = split1[1];
         String trail = split1[2];
         String colors = split1[3];
         String fadeColors = split1[4];
         Map<String, Object> map = new HashMap();
         map.put("type", type.split(":")[1]);
         map.put("flicker", flicker.split(":")[1]);
         map.put("trail", trail.split(":")[1]);
         List<Color> colorList = new ArrayList();
         String allColors = colors.split(":")[1];
         String withOutParantesise = allColors.replaceAll("\\[", "");
         withOutParantesise = withOutParantesise.replaceAll("]", "");
         String[] var14 = withOutParantesise.split(",");
         int var15 = var14.length;

         String allFadeColors;
         for(int var16 = 0; var16 < var15; ++var16) {
            allFadeColors = var14[var16];
            colorList.add(Color.fromRGB(Integer.parseInt(allFadeColors)));
         }

         map.put("colors", colorList);
         List<Color> fadeList = new ArrayList();
         allFadeColors = colors.split(":")[1];
         withOutParantesise = allFadeColors.replaceAll("\\[", "");
         withOutParantesise = withOutParantesise.replaceAll("]", "");
         String[] var22 = withOutParantesise.split(",");
         int var23 = var22.length;

         for(int var18 = 0; var18 < var23; ++var18) {
            String s = var22[var18];
            fadeList.add(Color.fromRGB(Integer.parseInt(s)));
         }

         map.put("fade-colors", fadeList);
         FireworkEffect.deserialize(map);
      }

      return fireworkEffects;
   }

   public void add(ItemStack itemStack) {
      org.bukkit.inventory.meta.FireworkMeta fireworkMeta = (org.bukkit.inventory.meta.FireworkMeta)itemStack.getItemMeta();
      fireworkMeta.clearEffects();
      this.convert().forEach((xva$0) -> {
         fireworkMeta.addEffects(new FireworkEffect[]{xva$0});
      });
      itemStack.setItemMeta(fireworkMeta);
   }
}

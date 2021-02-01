package me.t3sl4.tornadoplus.entity.item.argument.meta;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import me.t3sl4.tornadoplus.entity.item.IItemArgument;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionMeta implements IItemArgument {
   private final List<String> potionEffectStringList;

   public PotionMeta(List<String> potionEffectStringList) {
      this.potionEffectStringList = potionEffectStringList;
   }

   public List<PotionEffect> convert() {
      List<PotionEffect> potionEffects = new ArrayList();
      Iterator var2 = this.potionEffectStringList.iterator();

      while(var2.hasNext()) {
         String potionEffectStringList = (String)var2.next();
         String[] potionEffectArray = potionEffectStringList.split(":");
         if (potionEffectArray.length > 2) {
            potionEffects.add(new PotionEffect(PotionEffectType.getByName(potionEffectArray[0]), Integer.parseInt(potionEffectArray[1]), Integer.parseInt(potionEffectArray[2])));
         }
      }

      return potionEffects;
   }

   public void add(ItemStack itemStack) {
      org.bukkit.inventory.meta.PotionMeta potionMeta = (org.bukkit.inventory.meta.PotionMeta)itemStack.getItemMeta();
      this.convert().forEach((x) -> {
         potionMeta.addCustomEffect(x, true);
      });
      itemStack.setItemMeta(potionMeta);
   }
}

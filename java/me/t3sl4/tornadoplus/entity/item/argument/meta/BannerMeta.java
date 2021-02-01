package me.t3sl4.tornadoplus.entity.item.argument.meta;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import me.t3sl4.tornadoplus.entity.item.IItemArgument;
import org.bukkit.DyeColor;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.inventory.ItemStack;

public class BannerMeta implements IItemArgument {
   private final List<String> patternStringList;

   public BannerMeta(List<String> patternStringList) {
      this.patternStringList = patternStringList;
   }

   public List<Pattern> convert() {
      List<Pattern> patterns = new ArrayList();
      Iterator var2 = this.patternStringList.iterator();

      while(var2.hasNext()) {
         String patternStringList = (String)var2.next();
         patterns.add(new Pattern(DyeColor.getByWoolData(Byte.parseByte(patternStringList.split(":")[0])), PatternType.getByIdentifier(patternStringList.split(":")[1])));
      }

      return patterns;
   }

   public void add(ItemStack itemStack) {
      org.bukkit.inventory.meta.BannerMeta bannerMeta = (org.bukkit.inventory.meta.BannerMeta)itemStack.getItemMeta();

      for(int i = 0; i < this.convert().size(); ++i) {
         bannerMeta.setPattern(i, (Pattern)this.convert().get(i));
      }

      itemStack.setItemMeta(bannerMeta);
   }
}

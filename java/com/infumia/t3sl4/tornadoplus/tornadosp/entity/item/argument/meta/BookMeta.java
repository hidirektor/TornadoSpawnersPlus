package com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.argument.meta;

import java.util.Arrays;
import java.util.List;

import com.infumia.t3sl4.tornadoplus.tornadoputil.misc.StringUtil;
import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.item.IItemArgument;
import org.bukkit.inventory.ItemStack;

public class BookMeta implements IItemArgument {
   private final String author;
   private final String title;
   private final List<String> pages;

   public BookMeta(String author, String title, List<String> pages) {
      this.author = author;
      this.title = title;
      this.pages = pages;
   }

   public List<Object> convert() {
      return Arrays.asList(this.author, this.title, this.pages);
   }

   public void add(ItemStack itemStack) {
      org.bukkit.inventory.meta.BookMeta bookMeta = (org.bukkit.inventory.meta.BookMeta)itemStack.getItemMeta();
      bookMeta.setAuthor(StringUtil.getInstance().chatColor(this.author));
      bookMeta.setTitle(StringUtil.getInstance().chatColor(this.title));
      bookMeta.setPages(StringUtil.getInstance().chatColorL(this.pages));
      itemStack.setItemMeta(bookMeta);
   }
}

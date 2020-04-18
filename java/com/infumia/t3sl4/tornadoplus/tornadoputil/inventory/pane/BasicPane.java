package com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.pane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.requirement.SlotReq;
import com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.Element;
import com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.Pane;
import com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.element.BasicElement;
import com.infumia.t3sl4.tornadoplus.tornadoputil.observer.Source;
import com.infumia.t3sl4.tornadoplus.tornadoputil.observer.Target;
import com.infumia.t3sl4.tornadoplus.tornadoputil.observer.source.BasicSource;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BasicPane implements Pane {
   private static final String LOC_OUT = "The specified location [%s][%s] is out of bounds";
   private final Source<Object> source;
   private final Element[][] elements;
   private final int locX;
   private final int locY;

   public BasicPane(int locX, int locY, int height, int length) {
      this.source = new BasicSource();
      this.locX = locX;
      this.locY = locY;
      this.elements = new Element[height][length];
      this.clear();
   }

   public BasicPane(int locX, int locY, int height, int length, Element element) {
      this(locX, locY, height, length);
      this.replaceAll((Element)Objects.requireNonNull(element));
   }

   public BasicPane(int locX, int locY, int height, int length, Element... elements) {
      this(locX, locY, height, length);
      this.add(elements);
   }

   private int length() {
      return this.elements[0].length;
   }

   private int height() {
      return this.elements.length;
   }

   private Element emptyElement() {
      return new BasicElement(new ItemStack(Material.TNT), "emptyElement");
   }

   private void validate(int inventorySize) throws IllegalArgumentException {
      boolean locXFaulty = this.locX < 0;
      boolean locYFaulty = this.locY < 0;
      boolean heightFaulty = this.locY + this.height() > inventorySize / 9 || this.height() <= 0;
      boolean lengthFaulty = this.locX + this.length() > 9 || this.length() <= 0;
      if (locXFaulty || locYFaulty || heightFaulty || lengthFaulty) {
         throw new IllegalArgumentException(String.format("Validation for the newest created Pane failed.%nlocX (%s) is faulty: %s, locY (%s) is faulty: %s, height (%s) is faulty: %s, length (%s) is faulty: %s", this.locX, locXFaulty, this.locY, locYFaulty, this.height(), heightFaulty, this.length(), lengthFaulty));
      }
   }

   private boolean isWithinBounds(int xToCheck, int yToCheck) {
      return xToCheck < this.length() && yToCheck < this.height() && xToCheck >= 0 && yToCheck >= 0;
   }

   private void shiftElementAt(int xToShift, int yToShift) {
      for(int y = this.height() - 1; y >= 0; --y) {
         for(int x = this.length() - 1; x >= 0; --x) {
            if (y >= yToShift && (y != yToShift || x >= xToShift)) {
               if (x + 1 < this.length()) {
                  this.elements[y][x + 1] = this.elements[y][x];
               } else if (y + 1 < this.height()) {
                  this.elements[y + 1][0] = this.elements[y][x];
               }
            }
         }
      }

      this.elements[yToShift][xToShift] = this.emptyElement();
   }

   private boolean forEachSlot(BiFunction<Integer, Integer, Boolean> action) {
      for(int y = 0; this.isWithinBounds(0, y); ++y) {
         for(int x = 0; this.isWithinBounds(x, y); ++x) {
            if ((Boolean)action.apply(y, x)) {
               return true;
            }
         }
      }

      return false;
   }

   private void forEachSlot(BiConsumer<Integer, Integer> action) {
      this.forEachSlot((y, x) -> {
         action.accept(y, x);
         return false;
      });
   }

   public void fill(Element element) {
      this.fill((Element)Objects.requireNonNull(element));
      this.source.notifyTargets(new Object());
   }

   public void fill(Element... elements) {
      Queue<Element> queue = new LinkedList(Arrays.asList((Object[])Objects.requireNonNull(elements)));
      this.forEachSlot((y, x) -> {
         if (queue.isEmpty()) {
            queue.addAll(Arrays.asList(elements));
         }

         if (this.elements[y][x].is(this.emptyElement())) {
            this.elements[y][x] = (Element)queue.poll();
         }

      });
      this.source.notifyTargets(new Object());
   }

   public void clear() {
      this.replaceAll(this.emptyElement());
   }

   public boolean add(Element element) {
      return this.forEachSlot((y, x) -> {
         if (this.elements[y][x].is(this.emptyElement())) {
            this.elements[y][x] = (Element)Objects.requireNonNull(element);
            this.source.notifyTargets(new Object());
            return true;
         } else {
            return false;
         }
      });
   }

   public Element[] add(Element... elements) {
      ArrayList<Element> remainings = new ArrayList();
      Element[] var3 = (Element[])Objects.requireNonNull(elements);
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         Element element = var3[var5];
         if (!this.add(element)) {
            remainings.add(element);
         }
      }

      return (Element[])remainings.toArray(new Element[0]);
   }

   public void insert(Element element, int locX, int locY, boolean shift) throws IllegalArgumentException {
      if (this.isWithinBounds(locX, locY)) {
         if (shift && !this.elements[locY][locX].is(this.emptyElement())) {
            this.shiftElementAt(locX, locY);
         }

         this.elements[locY][locX] = (Element)Objects.requireNonNull(element);
         this.source.notifyTargets(new Object());
      } else {
         throw new IllegalArgumentException(String.format("The specified location [%s][%s] is out of bounds", locX, locY));
      }
   }

   public void replaceAll(Element... elements) {
      Queue<Element> queue = new LinkedList(Arrays.asList((Object[])Objects.requireNonNull(elements)));
      this.forEachSlot((y, x) -> {
         if (queue.isEmpty()) {
            queue.addAll(Arrays.asList(elements));
         }

         this.elements[y][x] = (Element)queue.poll();
      });
      this.source.notifyTargets(new Object());
   }

   public void remove(int locX, int locY) throws IllegalArgumentException {
      if (this.isWithinBounds(locX, locY)) {
         this.elements[locY][locX] = this.emptyElement();
         this.source.notifyTargets(new Object());
      } else {
         throw new IllegalArgumentException(String.format("The specified location [%s][%s] is out of bounds", locX, locY));
      }
   }

   public void subscribe(Target<Object> target) {
      this.source.subscribe((Target)Objects.requireNonNull(target));
   }

   public boolean contains(ItemStack icon) {
      return this.forEachSlot((y, x) -> {
         return this.elements[y][x].is(icon);
      });
   }

   public void accept(InventoryInteractEvent event) {
      this.forEachSlot((y, x) -> {
         if ((new SlotReq(this.locX + x + (this.locY + y) * 9)).control(event)) {
            this.elements[y][x].accept(event);
         }

      });
   }

   public void displayOn(Inventory inventory) {
      try {
         this.validate(inventory.getSize());
      } catch (IllegalArgumentException var3) {
         Bukkit.getLogger().severe(var3.toString());
         return;
      }

      this.forEachSlot((y, x) -> {
         Element element = this.elements[y][x];
         if (!element.is(this.emptyElement())) {
            element.displayOn(inventory, this.locX + x, this.locY + y);
         }

      });
   }
}

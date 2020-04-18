package com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.page;

import com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.Page;
import com.infumia.t3sl4.tornadoplus.tornadoputil.inventory.Pane;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import org.bukkit.entity.Player;

import java.util.List;

public class ChestPage implements Page
{
   private final String title;
   private final int size;
   private final List<Pane> panes;
   private final List<Player> viewers;
   private Page holder;

   public ChestPage(final String title, final int size, final Pane... panes) {
      this.title = Objects.requireNonNull(title);
      this.size = ((size < 9) ? 9 : size);
      this.panes = new ArrayList<Pane>(Arrays.asList(panes));
      this.viewers = new ArrayList<Player>();
      this.holder = this;
      Arrays.stream(panes).forEach(pane -> pane.subscribe(this));
   }

   @Override
   public void add(final Pane pane, final int position) {
      this.panes.add((position > this.panes.size()) ? this.panes.size() : ((int)Objects.requireNonNull(position)), Objects.requireNonNull(pane));
      this.update(new Object());
   }

   @Override
   public void remove(final int position) {
      this.panes.remove(position);
      this.update(new Object());
   }

   @Override
   public void rearrange(final int paneIndex, final int position) {
      final Pane pane = this.panes.get(paneIndex);
      this.panes.remove(paneIndex);
      this.panes.add((position > this.panes.size()) ? this.panes.size() : position, pane);
      this.update(new Object());
   }

   @Override
   public void defineHolder(final Page holder) {
      this.holder = Objects.requireNonNull(holder);
   }

   @Override
   public void showTo(final Player player) {
      final Inventory inventory = Bukkit.createInventory((InventoryHolder)this.holder, this.size, this.title);
      for (final Pane pane : this.panes) {
         pane.displayOn(inventory);
      }
      player.openInventory(inventory);
      if (!this.viewers.contains(player)) {
         this.viewers.add(player);
      }
   }

   @Override
   public void handleClose(final InventoryCloseEvent event) {
      this.viewers.remove(event.getPlayer());
   }

   public void update(final Object argument) {
      Bukkit.getScheduler().runTask(
              Bukkit.getPluginManager().getPlugins()[0], () ->
                      viewers.forEach(viewer -> {
                         final Inventory inventory = viewer.getOpenInventory().getTopInventory();
                         inventory.clear();
                         panes.forEach(pane -> pane.displayOn(inventory));
                      })
      );
   }

   @Deprecated
   public Inventory getInventory() {
      return Bukkit.createInventory((InventoryHolder)null, 9);
   }

   @Override
   public void accept(final InventoryInteractEvent event) {
      new ArrayList<>(panes).forEach(pane -> pane.accept(event));
   }
}
package me.t3sl4.tornadoplus.events;

import me.t3sl4.tornadoplus.entity.spawner.ISpawner;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TSPSpawnerBreakEvent extends Event implements Cancellable {
   private static final HandlerList handlers = new HandlerList();
   private Player player;
   private ISpawner iSpawner;
   private Block block;
   private int expDrop;
   private boolean cancelled;

   public TSPSpawnerBreakEvent(Player player, ISpawner iSpawner, Block block, int expDrop) {
      this.player = player;
      this.iSpawner = iSpawner;
      this.block = block;
      this.expDrop = expDrop;
   }

   public Player getPlayer() {
      return this.player;
   }

   public void setPlayer(Player player) {
      this.player = player;
   }

   public ISpawner getISpawner() {
      return this.iSpawner;
   }

   public void setISpawner(ISpawner iSpawner) {
      this.iSpawner = iSpawner;
   }

   public Block getBlock() {
      return this.block;
   }

   public void setBlock(Block block) {
      this.block = block;
   }

   public int getExpDrop() {
      return this.expDrop;
   }

   public void setExpDrop(int expDrop) {
      this.expDrop = expDrop;
   }

   public boolean isCancelled() {
      return this.cancelled;
   }

   public void setCancelled(boolean cancel) {
      this.cancelled = cancel;
   }

   public HandlerList getHandlers() {
      return handlers;
   }

   public static HandlerList getHandlerList() {
      return handlers;
   }
}

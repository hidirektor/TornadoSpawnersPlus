package com.infumia.t3sl4.tornadoplus.tornadopsp.events;

import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.IEntity;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntitySpawnEvent;

public class TSPEntitySpawnEvent extends EntitySpawnEvent implements Cancellable {
   private static final HandlerList handlers = new HandlerList();
   private IEntity iEntity;
   private boolean cancelled;

   public TSPEntitySpawnEvent(Entity what, IEntity iEntity) {
      super(what);
      this.iEntity = iEntity;
   }

   public IEntity getiEntity() {
      return this.iEntity;
   }

   public void setiEntity(IEntity iEntity) {
      this.iEntity = iEntity;
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
}

package com.infumia.t3sl4.tornadoplus.tornadopsp.events;

import com.infumia.t3sl4.tornadoplus.tornadopsp.entity.mob.IMob;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TSPMobDeathEvent extends Event implements Cancellable {
   private static final HandlerList handlers = new HandlerList();
   private IMob iMob;
   private LivingEntity entity;
   private boolean cancelled;

   public TSPMobDeathEvent(LivingEntity entity, IMob iMob) {
      this.entity = entity;
      this.iMob = iMob;
   }

   public LivingEntity getEntity() {
      return this.entity;
   }

   public void setEntity(LivingEntity entity) {
      this.entity = entity;
   }

   public IMob getIMob() {
      return this.iMob;
   }

   public void setIMob(IMob iMob) {
      this.iMob = iMob;
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

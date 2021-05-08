/*    */ package com.ngames.nclient.event;
/*    */ 
/*    */ import com.ngames.nclient.NClient;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NClientEvent
/*    */ {
/*    */   private boolean canceled = false;
/*    */   
/*    */   public static boolean callEvent(NClientEvent event) {
/* 12 */     for (int i = 0; i < NClient.listeners.size(); i++) {
/*    */       
/* 14 */       Listener l = NClient.listeners.get(i);
/* 15 */       if (l.getEventType() == event.getClass())
/* 16 */         l.invoke(event); 
/* 17 */       if (!l.hack.isEnabled())
/* 18 */         NClient.listeners.remove(l); 
/* 19 */       if (event.isCanceled())
/* 20 */         return true; 
/*    */     } 
/* 22 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCalceled() {
/* 27 */     this.canceled = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCanceled() {
/* 32 */     return this.canceled;
/*    */   }
/*    */   
/*    */   public static class PlayerHealthChangeEvent extends NClientEvent {}
/*    */   
/*    */   public static class PlayerFoodStatsChangeEvent extends NClientEvent {}
/*    */   
/*    */   public static class PlayerJoinWorldEvent extends NClientEvent {}
/*    */   
/*    */   public static class PlayerSwingArmEvent extends NClientEvent {}
/*    */   
/*    */   public static class PlayerAttackedEntityEvent extends NClientEvent {}
/*    */   
/*    */   public static class RunTickKeyboardEvent extends NClientEvent {}
/*    */   
/*    */   public static class OnPlayerUpdateEvent extends NClientEvent {}
/*    */   
/*    */   public static class OnPlayerUpdatedEvent extends NClientEvent {}
/*    */   
/*    */   public static class PlayerJumpEvent extends NClientEvent {}
/*    */   
/*    */   public static class LivingUpdatedEvent extends NClientEvent {}
/*    */   
/*    */   public static class PotionEffectRemovedEvent extends NClientEvent {}
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/event/NClientEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package com.ngames.nclient.event;
/*    */ 
/*    */ import com.ngames.nclient.NClient;
/*    */ import com.ngames.nclient.hack.Hack;
/*    */ 
/*    */ 
/*    */ public class Listener
/*    */ {
/*    */   public Hack hack;
/*    */   private Class<? extends NClientEvent> eventType;
/*    */   
/*    */   public Listener(Class<? extends NClientEvent> eventType, Hack hack) {
/* 13 */     this.hack = hack;
/* 14 */     this.eventType = eventType;
/* 15 */     NClient.listeners.add(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void invoke(NClientEvent event) {
/* 20 */     this.hack.onInvoke(event);
/*    */   }
/*    */ 
/*    */   
/*    */   public Class<? extends NClientEvent> getEventType() {
/* 25 */     return this.eventType;
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/event/Listener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
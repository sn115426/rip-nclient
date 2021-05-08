/*    */ package com.ngames.nclient.hack.hacks;
/*    */ 
/*    */ import com.ngames.nclient.baritone.Baritone;
/*    */ import com.ngames.nclient.event.Listener;
/*    */ import com.ngames.nclient.event.NClientEvent;
/*    */ import com.ngames.nclient.hack.Category;
/*    */ import com.ngames.nclient.hack.Hack;
/*    */ import com.ngames.nclient.hack.NClientHack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @NClientHack(category = Category.MOVEMENT, description = "Automatically jumping", name = "AutoJump", words = "AutoJump AntiAFK")
/*    */ public class AutoJump
/*    */   extends Hack
/*    */ {
/*    */   public void onEnable() {
/* 26 */     super.onEnable();
/* 27 */     new Listener(NClientEvent.RunTickKeyboardEvent.class, this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onInvoke(NClientEvent event) {
/* 33 */     Baritone.setJumping(this.enabled);
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/hacks/AutoJump.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
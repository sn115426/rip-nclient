/*    */ package com.ngames.nclient.hack.hacks;
/*    */ 
/*    */ import com.ngames.nclient.NClient;
/*    */ import com.ngames.nclient.event.Listener;
/*    */ import com.ngames.nclient.event.NClientEvent;
/*    */ import com.ngames.nclient.hack.Category;
/*    */ import com.ngames.nclient.hack.Hack;
/*    */ import com.ngames.nclient.hack.NClientHack;
/*    */ import com.ngames.nclient.hack.settings.SettingBoolean;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @NClientHack(category = Category.MOVEMENT, description = "Automatically toggle sprint", name = "AutoSprint", words = "AutoSprint Sprint Speed Strafe")
/*    */ public class AutoSprint
/*    */   extends Hack
/*    */ {
/* 21 */   private SettingBoolean onlyForward = new SettingBoolean("OnlyForward", true);
/*    */ 
/*    */   
/*    */   public AutoSprint() {
/* 25 */     this.settings = Hack.addSettings(this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 31 */     super.onEnable();
/* 32 */     new Listener(NClientEvent.RunTickKeyboardEvent.class, this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onInvoke(NClientEvent event) {
/* 38 */     boolean fow = NClient.MC.gameSettings.keyBindForward.isKeyDown();
/* 39 */     boolean back = NClient.MC.gameSettings.keyBindBack.isKeyDown();
/* 40 */     boolean left = NClient.MC.gameSettings.keyBindLeft.isKeyDown();
/* 41 */     boolean right = NClient.MC.gameSettings.keyBindRight.isKeyDown();
/* 42 */     if ((fow || (!this.onlyForward.getValue() && (fow || back || left || right))) && !NClient.MC.player.isSprinting())
/* 43 */       NClient.MC.player.setSprinting(true); 
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/hacks/AutoSprint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
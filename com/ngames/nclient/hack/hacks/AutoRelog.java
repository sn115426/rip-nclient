/*    */ package com.ngames.nclient.hack.hacks;
/*    */ 
/*    */ import com.ngames.nclient.NClient;
/*    */ import com.ngames.nclient.baritone.BUtils;
/*    */ import com.ngames.nclient.event.Listener;
/*    */ import com.ngames.nclient.event.NClientEvent;
/*    */ import com.ngames.nclient.hack.Category;
/*    */ import com.ngames.nclient.hack.Hack;
/*    */ import com.ngames.nclient.hack.NClientHack;
/*    */ import com.ngames.nclient.hack.settings.SettingString;
/*    */ import com.ngames.nclient.hack.settings.SettingValue;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @NClientHack(category = Category.PLAYER, description = "Automatically relogin on server", name = "AutoRelog", words = "AutoRelog relogin")
/*    */ public class AutoRelog
/*    */   extends Hack
/*    */ {
/* 23 */   private final SettingString command = new SettingString("Command", "/skypvp", 255);
/* 24 */   private final SettingValue attemps = new SettingValue("Attemps", 100.0F, 1.0F, 100.0F);
/* 25 */   private final SettingValue delay = new SettingValue("Delay", 200.0F, 0.0F, 10000.0F);
/* 26 */   private final SettingValue radius = new SettingValue("SpawnRadius", 1.0F, 0.0F, 30000.0F);
/* 27 */   private final SettingValue x = new SettingValue("X", -5.0F, -3.0E7F, 3.0E7F);
/* 28 */   private final SettingValue y = new SettingValue("Y", 82.0F, -3.0E7F, 3.0E7F);
/* 29 */   private final SettingValue z = new SettingValue("Z", 43.0F, -3.0E7F, 3.0E7F);
/*    */   
/*    */   private boolean isRun;
/*    */ 
/*    */   
/*    */   public AutoRelog() {
/* 35 */     this.settings = Hack.addSettings(this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 41 */     super.onEnable();
/* 42 */     new Listener(NClientEvent.PlayerJoinWorldEvent.class, this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onInvoke(NClientEvent event) {
/* 48 */     if (!this.isRun) {
/* 49 */       (new Thread(() -> {
/*    */             this.isRun = true;
/*    */             for (int i = 0; i < this.attemps.getValue(); i++) {
/*    */               if (!isEnabled()) {
/*    */                 break;
/*    */               }
/*    */               BUtils.sleep((int)this.delay.getValue());
/*    */               double x = NClient.MC.player.lastTickPosX;
/*    */               double y = NClient.MC.player.lastTickPosY;
/*    */               double z = NClient.MC.player.lastTickPosZ;
/*    */               if (x > (this.x.getValue() - this.radius.getValue()) && x < (this.x.getValue() + this.radius.getValue()) && y > (this.y.getValue() - this.radius.getValue()) && y < (this.y.getValue() + this.radius.getValue()) && z > (this.z.getValue() - this.radius.getValue()) && z < (this.z.getValue() + this.radius.getValue())) {
/*    */                 NClient.MC.player.sendChatMessage(this.command.getValue());
/*    */               }
/*    */             } 
/*    */             this.isRun = false;
/* 64 */           })).start();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 70 */     super.onUpdate();
/* 71 */     onInvoke((NClientEvent)new NClientEvent.PlayerJoinWorldEvent());
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/hacks/AutoRelog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package com.ngames.nclient.hack.hacks;
/*    */ 
/*    */ import com.ngames.nclient.baritone.BUtils;
/*    */ import com.ngames.nclient.baritone.Baritone;
/*    */ import com.ngames.nclient.baritone.SafeThread;
/*    */ import com.ngames.nclient.hack.Category;
/*    */ import com.ngames.nclient.hack.Hack;
/*    */ import com.ngames.nclient.hack.NClientHack;
/*    */ import com.ngames.nclient.hack.settings.SettingBoolean;
/*    */ import com.ngames.nclient.hack.settings.SettingValue;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @NClientHack(category = Category.MOVEMENT, description = "Automatically spin around", name = "AutoSpin", words = "AutoSpin Spin SpinAround")
/*    */ public class AutoSpin
/*    */   extends Hack
/*    */ {
/* 19 */   private final SettingValue speed = new SettingValue("Speed", 3.0F, 1.0F, 10.0F);
/* 20 */   private final SettingBoolean onlyServer = new SettingBoolean("OnlyServer", true);
/*    */ 
/*    */   
/*    */   public AutoSpin() {
/* 24 */     this.settings = Hack.addSettings(this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 30 */     super.onEnable();
/* 31 */     Baritone.refreshRotation();
/* 32 */     Baritone.overrideRotation = true;
/* 33 */     (new SafeThread(() -> { Baritone.addRotationPlayer(1.1F - this.speed.getValue(), 0.0F, this.onlyServer.getValue()); BUtils.sleep((long)(11.0F - this.speed.getValue())); Baritone.overrideRotation = true; }this))
/*    */ 
/*    */ 
/*    */       
/* 37 */       .start();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onDisable() {
/* 43 */     Baritone.overrideRotation = false;
/* 44 */     Baritone.refreshRotation();
/* 45 */     super.onDisable();
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/hacks/AutoSpin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
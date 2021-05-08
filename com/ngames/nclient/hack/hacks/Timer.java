/*    */ package com.ngames.nclient.hack.hacks;
/*    */ 
/*    */ import com.ngames.nclient.baritone.Baritone;
/*    */ import com.ngames.nclient.hack.Category;
/*    */ import com.ngames.nclient.hack.Hack;
/*    */ import com.ngames.nclient.hack.NClientHack;
/*    */ import com.ngames.nclient.hack.settings.SettingValue;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @NClientHack(category = Category.WORLD, description = "Change minecraft tick speed", name = "Timer", words = "Timer speed")
/*    */ public class Timer
/*    */   extends Hack
/*    */ {
/* 16 */   private SettingValue speed = new SettingValue("Speed", 4.0F, 0.0F, 50.0F);
/*    */ 
/*    */   
/*    */   public Timer() {
/* 20 */     this.settings = Hack.addSettings(this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 26 */     Baritone.setTimer(new net.minecraft.util.Timer(20.0F * this.speed.getValue()));
/* 27 */     super.onEnable();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onDisable() {
/* 33 */     Baritone.setTimer(new net.minecraft.util.Timer(20.0F));
/* 34 */     super.onDisable();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 40 */     Baritone.setTimer(new net.minecraft.util.Timer(20.0F * this.speed.getValue()));
/* 41 */     super.onUpdate();
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/hacks/Timer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
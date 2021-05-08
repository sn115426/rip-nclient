/*    */ package com.ngames.nclient.hack.hacks;
/*    */ 
/*    */ import com.ngames.nclient.NClient;
/*    */ import com.ngames.nclient.baritone.BUtils;
/*    */ import com.ngames.nclient.baritone.Baritone;
/*    */ import com.ngames.nclient.baritone.SafeThread;
/*    */ import com.ngames.nclient.event.Listener;
/*    */ import com.ngames.nclient.event.NClientEvent;
/*    */ import com.ngames.nclient.hack.Category;
/*    */ import com.ngames.nclient.hack.Hack;
/*    */ import com.ngames.nclient.hack.Hacks;
/*    */ import com.ngames.nclient.hack.NClientHack;
/*    */ import com.ngames.nclient.hack.settings.SettingBoolean;
/*    */ import com.ngames.nclient.hack.settings.SettingChoose;
/*    */ import com.ngames.nclient.hack.settings.SettingValue;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @NClientHack(category = Category.COMBAT, description = "Automatically clicking when mouse button is down", name = "AutoClicker", words = "AutoClicker KillAura")
/*    */ public class AutoClicker
/*    */   extends Hack
/*    */ {
/* 28 */   private final SettingChoose DelayType = new SettingChoose("DelayType", (byte)1, new String[] { "simple", "random", "advanced" });
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 33 */   private final SettingValue CPSMin = new SettingValue("CPSMin", 17.0F, 1.0F, 100.0F);
/* 34 */   private final SettingValue CPSMax = new SettingValue("CPSMax", 21.0F, 1.0F, 100.0F);
/* 35 */   private final SettingChoose ClickType = new SettingChoose("ClickType", (byte)1, new String[] { "legit", "AAC" });
/*    */ 
/*    */ 
/*    */   
/* 39 */   private final SettingBoolean TickSync = new SettingBoolean("TickSync", true);
/*    */   
/*    */   boolean waitClick;
/*    */ 
/*    */   
/*    */   public AutoClicker() {
/* 45 */     this.settings = Hack.addSettings(this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 51 */     super.onEnable();
/* 52 */     new Listener(NClientEvent.RunTickKeyboardEvent.class, this);
/* 53 */     (new SafeThread(() -> { List<Integer> delays = new ArrayList<>(); if (this.DelayType.getValue() == 0) { delays = BUtils.genSimpleDelays(this.CPSMax.getValue()); } else if (this.DelayType.getValue() == 1) { delays = BUtils.genDelayNoise(BUtils.randomInRange(Math.round(this.CPSMin.getValue()), Math.round(this.CPSMax.getValue()))); } else if (this.DelayType.getValue() == 2) { delays = Hacks.advClickerDelays.genAdvancedDelayNoise(this.CPSMin.getValue(), this.CPSMax.getValue()); }  Iterator<Integer> iterator = delays.iterator(); while (iterator.hasNext()) { int delay = ((Integer)iterator.next()).intValue(); if (this.enabled) { if (NClient.MC.world != null && !Baritone.clickSync && NClient.MC.objectMouseOver.entityHit != null && NClient.isLeftPressed) if (this.TickSync.getValue()) { this.waitClick = true; } else { attack(); }   BUtils.sleep(delay, BUtils.randomInRange(0, 999999)); }  }  }this))
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
/* 76 */       .start();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onInvoke(NClientEvent event) {
/* 82 */     if (this.enabled)
/*    */     {
/* 84 */       if (event instanceof NClientEvent.RunTickKeyboardEvent && this.waitClick) {
/*    */         
/* 86 */         attack();
/* 87 */         this.waitClick = false;
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   private void attack() {
/* 94 */     if (this.ClickType.getValue() == 0) {
/* 95 */       Baritone.leftClickMouse();
/* 96 */     } else if (this.ClickType.getValue() == 1) {
/* 97 */       Baritone.attackEntity();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/hacks/AutoClicker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
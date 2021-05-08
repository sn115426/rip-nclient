/*     */ package com.ngames.nclient.hack.hacks;
/*     */ 
/*     */ import com.ngames.nclient.NClient;
/*     */ import com.ngames.nclient.baritone.BUtils;
/*     */ import com.ngames.nclient.baritone.Baritone;
/*     */ import com.ngames.nclient.event.Listener;
/*     */ import com.ngames.nclient.event.NClientEvent;
/*     */ import com.ngames.nclient.hack.Category;
/*     */ import com.ngames.nclient.hack.Hack;
/*     */ import com.ngames.nclient.hack.NClientHack;
/*     */ import com.ngames.nclient.hack.settings.SettingBoolean;
/*     */ import com.ngames.nclient.hack.settings.SettingValue;
/*     */ import net.minecraft.init.Items;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @NClientHack(category = Category.PLAYER, description = "Automatically eat soup", name = "AutoSoup", words = "AutoSoup Regen")
/*     */ public class AutoSoup
/*     */   extends Hack
/*     */ {
/*  24 */   private final SettingValue health = new SettingValue("Health", 12.0F, 1.0F, 19.0F);
/*  25 */   private final SettingValue stopHealth = new SettingValue("stopHealth", 18.0F, 1.0F, 20.0F);
/*  26 */   private final SettingValue attemps = new SettingValue("Attemps", 5.0F, 1.0F, 1000.0F);
/*  27 */   private final SettingValue useMinDelay = new SettingValue("UseMinDelay", 30.0F, 0.0F, 1000.0F);
/*  28 */   private final SettingValue useMaxDelay = new SettingValue("UseMaxDelay", 50.0F, 0.0F, 1000.0F);
/*  29 */   private final SettingBoolean fastUse = new SettingBoolean("FastUse", true);
/*  30 */   private final SettingBoolean onlyHotbar = new SettingBoolean("OnlyHotbar", false);
/*  31 */   private final SettingBoolean setBack = new SettingBoolean("SetBack", true);
/*  32 */   private final SettingValue setBackSlot = new SettingValue("SetBackSlot", 0.0F, 0.0F, 8.0F);
/*     */   
/*     */   private boolean isRun;
/*     */ 
/*     */   
/*     */   public AutoSoup() {
/*  38 */     this.settings = Hack.addSettings(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEnable() {
/*  44 */     super.onEnable();
/*  45 */     new Listener(NClientEvent.PlayerHealthChangeEvent.class, this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onInvoke(NClientEvent event) {
/*  51 */     if (!this.isRun) {
/*  52 */       (new Thread(() -> {
/*     */             this.isRun = true;
/*     */             
/*     */             if (NClient.MC.player.getHealth() <= this.health.getValue()) {
/*     */               int prevSlot = (int)this.setBackSlot.getValue();
/*     */               int slot = Baritone.getSlotFor(Items.MUSHROOM_STEW, this.onlyHotbar.getValue());
/*     */               if (slot != -1) {
/*     */                 int slot2 = Baritone.getSlotFor(Items.BOWL, true);
/*     */                 if (slot2 != -1) {
/*     */                   Baritone.setMainHand(slot2);
/*     */                   Baritone.dropCurrentItem();
/*     */                 } 
/*     */                 if (!this.onlyHotbar.getValue() && slot > 8) {
/*     */                   Baritone.putInAir(slot);
/*     */                 }
/*     */                 slot = Baritone.getSlotFor(Items.MUSHROOM_STEW, true);
/*     */                 if (slot != -1 && slot < 9) {
/*     */                   Baritone.setMainHand(slot);
/*     */                   eat();
/*     */                   if (Baritone.isInMainHand(Items.BOWL) || Baritone.isInMainHand(Items.MUSHROOM_STEW)) {
/*     */                     Baritone.dropCurrentItem();
/*     */                   }
/*     */                   if (this.setBack.getValue()) {
/*     */                     Baritone.setMainHand(prevSlot);
/*     */                   }
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */             this.isRun = false;
/*  81 */           })).start();
/*     */     }
/*     */   }
/*     */   
/*     */   private void eat() {
/*  86 */     Baritone.clickSync = true;
/*  87 */     int i = 0;
/*  88 */     while (NClient.MC.player.getHealth() < this.stopHealth.getValue() && i < this.attemps.getValue()) {
/*     */       
/*  90 */       if (this.fastUse.getValue() && Baritone.isInMainHand(Items.MUSHROOM_STEW)) {
/*     */         
/*  92 */         Baritone.rightClickMouse();
/*  93 */         BUtils.sleep(BUtils.randomInRange((int)this.useMinDelay.getValue(), (int)this.useMaxDelay.getValue()));
/*     */       } 
/*  95 */       if (!this.fastUse.getValue() && Baritone.isInMainHand(Items.MUSHROOM_STEW)) {
/*     */         
/*  97 */         Baritone.useItem();
/*  98 */         BUtils.sleep(BUtils.randomInRange((int)this.useMinDelay.getValue(), (int)this.useMaxDelay.getValue()));
/*     */       } 
/* 100 */       if (!this.fastUse.getValue())
/* 101 */         Baritone.usedItem(); 
/* 102 */       i++;
/*     */     } 
/* 104 */     Baritone.clickSync = false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 110 */     super.onUpdate();
/* 111 */     onInvoke((NClientEvent)new NClientEvent.PlayerHealthChangeEvent());
/*     */   }
/*     */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/hacks/AutoSoup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
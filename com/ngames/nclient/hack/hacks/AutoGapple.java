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
/*     */ import net.minecraft.potion.Potion;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @NClientHack(category = Category.PLAYER, description = "Automatically eat gapples", name = "AutoGapple", words = "AutoGapple AutoEat")
/*     */ public class AutoGapple
/*     */   extends Hack
/*     */ {
/*  27 */   private final SettingBoolean allowInPvP = new SettingBoolean("AllowInPvP", false);
/*  28 */   private final SettingValue health = new SettingValue("Health", 10.0F, 0.0F, 20.0F);
/*  29 */   private final SettingBoolean regenEffect = new SettingBoolean("RegenEffect", true);
/*  30 */   private final SettingBoolean fireProtectEffect = new SettingBoolean("FireProtectEffect", false);
/*  31 */   private final SettingBoolean offHand = new SettingBoolean("OffHand", false);
/*  32 */   private final SettingBoolean onlyHotbar = new SettingBoolean("OnlyHotbar", false);
/*  33 */   private final SettingBoolean setBack = new SettingBoolean("SetBack", true);
/*     */   
/*     */   private boolean isRun = false;
/*     */ 
/*     */   
/*     */   public AutoGapple() {
/*  39 */     this.settings = Hack.addSettings(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEnable() {
/*  45 */     onUpdate();
/*  46 */     new Listener(NClientEvent.PlayerFoodStatsChangeEvent.class, this);
/*  47 */     new Listener(NClientEvent.PotionEffectRemovedEvent.class, this);
/*  48 */     new Listener(NClientEvent.PlayerHealthChangeEvent.class, this);
/*  49 */     this.enabled = true;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean calcUsing() {
/*  54 */     return ((this.allowInPvP.getValue() || !NClient.inPvP) && (this.health.getValue() >= NClient.MC.player.getHealth() || (
/*  55 */       !NClient.MC.player.isPotionActive(Potion.getPotionById(10)) && this.regenEffect.getValue()) || (
/*  56 */       !NClient.MC.player.isPotionActive(Potion.getPotionById(12)) && this.fireProtectEffect.getValue())));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onInvoke(NClientEvent event) {
/*  62 */     if (!this.isRun) {
/*  63 */       (new Thread(() -> {
/*     */             this.isRun = true;
/*     */             
/*     */             if (calcUsing()) {
/*     */               int slotIn = Baritone.getSlotFor(Items.GOLDEN_APPLE, this.onlyHotbar.getValue());
/*     */               
/*     */               int currSlot = NClient.MC.player.inventory.currentItem;
/*     */               if (slotIn != -1) {
/*     */                 if (slotIn > 8 && !this.onlyHotbar.getValue()) {
/*     */                   Baritone.putInMainHand(slotIn);
/*     */                 } else if (slotIn < 9) {
/*     */                   Baritone.setMainHand(slotIn);
/*     */                 } else if (this.offHand.getValue()) {
/*     */                   Baritone.putInOffHand(slotIn);
/*     */                 } else {
/*     */                   slotIn = -1;
/*     */                 } 
/*     */               }
/*     */               if (slotIn != -1) {
/*     */                 Baritone.useItem();
/*     */                 while (calcUsing() && this.enabled) {
/*     */                   BUtils.sleep(100L);
/*     */                 }
/*     */                 Baritone.usedItem();
/*     */               } 
/*     */               if (this.setBack.getValue()) {
/*     */                 if (this.offHand.getValue()) {
/*     */                   Baritone.putInOffHand(slotIn);
/*     */                 } else if (slotIn != -1) {
/*     */                   if (slotIn < 9) {
/*     */                     Baritone.setMainHand(currSlot);
/*     */                   } else {
/*     */                     Baritone.putInMainHand(slotIn);
/*     */                   } 
/*     */                 } 
/*     */               }
/*     */             } 
/*     */             this.isRun = false;
/* 101 */           })).start();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 107 */     super.onUpdate();
/* 108 */     onInvoke((NClientEvent)new NClientEvent.PlayerFoodStatsChangeEvent());
/*     */   }
/*     */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/hacks/AutoGapple.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
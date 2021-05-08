/*    */ package com.ngames.nclient.hack.hacks;
/*    */ 
/*    */ import com.ngames.nclient.NClient;
/*    */ import com.ngames.nclient.baritone.BUtils;
/*    */ import com.ngames.nclient.baritone.Baritone;
/*    */ import com.ngames.nclient.event.Listener;
/*    */ import com.ngames.nclient.event.NClientEvent;
/*    */ import com.ngames.nclient.hack.Category;
/*    */ import com.ngames.nclient.hack.Hack;
/*    */ import com.ngames.nclient.hack.NClientHack;
/*    */ import com.ngames.nclient.hack.settings.SettingBoolean;
/*    */ import com.ngames.nclient.hack.settings.SettingChoose;
/*    */ import com.ngames.nclient.hack.settings.SettingValue;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @NClientHack(category = Category.PLAYER, description = "Automatically eat food when hungry", name = "AutoEat", words = "AutoEat Eat")
/*    */ public class AutoEat
/*    */   extends Hack
/*    */ {
/* 23 */   private final SettingValue foodLvl = new SettingValue("FoodLvl", 10.0F, 0.0F, 20.0F);
/* 24 */   private final SettingChoose preferFood = new SettingChoose("PreferFood", (byte)0, new String[] { "best", "wise" });
/*    */ 
/*    */ 
/*    */   
/* 28 */   private final SettingBoolean setBack = new SettingBoolean("SetBack", true);
/* 29 */   private final SettingBoolean eatInPvP = new SettingBoolean("EatInPvP", false);
/*    */   
/*    */   private boolean isRun = false;
/*    */ 
/*    */   
/*    */   public AutoEat() {
/* 35 */     this.settings = Hack.addSettings(this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 41 */     super.onEnable();
/* 42 */     new Listener(NClientEvent.PlayerFoodStatsChangeEvent.class, this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onInvoke(NClientEvent event) {
/* 48 */     if (!this.isRun) {
/* 49 */       (new Thread(() -> {
/*    */             this.isRun = true;
/*    */             
/*    */             if (NClient.MC.player.getFoodStats().getFoodLevel() <= this.foodLvl.getValue()) {
/*    */               while (Baritone.getFoodStats().needFood() && this.enabled) {
/*    */                 byte isEaten = (byte)Baritone.getFoodStats().getFoodLevel();
/*    */                 
/*    */                 if ((!this.eatInPvP.getValue() && !NClient.inPvP) || this.eatInPvP.getValue()) {
/*    */                   int slotId = -1;
/*    */                   
/*    */                   int hand = NClient.MC.player.inventory.currentItem;
/*    */                   if (this.preferFood.getValue() == 0) {
/*    */                     slotId = Baritone.getBestFood(true);
/*    */                   }
/*    */                   if (this.preferFood.getValue() == 1) {
/*    */                     slotId = Baritone.getFoodWithHeal((byte)Baritone.getFoodStats().getFoodLevel(), true);
/*    */                   }
/*    */                   if (NClient.MC.player.canEat(false) && slotId != -1) {
/*    */                     Baritone.setMainHand(slotId);
/*    */                     Baritone.useItem();
/*    */                     while (isEaten >= Baritone.getFoodStats().getFoodLevel() && this.enabled) {
/*    */                       if (NClient.MC.player.inventory.currentItem != slotId) {
/*    */                         Baritone.setMainHand(slotId);
/*    */                       }
/*    */                       BUtils.sleep(100L);
/*    */                     } 
/*    */                     Baritone.usedItem();
/*    */                     if (this.setBack.getValue()) {
/*    */                       Baritone.setMainHand(hand);
/*    */                     }
/*    */                   } 
/*    */                 } 
/*    */               } 
/*    */             }
/*    */             this.isRun = false;
/* 84 */           })).start();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 90 */     super.onUpdate();
/* 91 */     onInvoke((NClientEvent)new NClientEvent.PlayerFoodStatsChangeEvent());
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/hacks/AutoEat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
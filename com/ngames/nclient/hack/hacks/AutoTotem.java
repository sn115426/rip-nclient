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
/*    */ import net.minecraft.init.Items;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @NClientHack(category = Category.PLAYER, description = "Automatically put totems in offhand", name = "AutoTotem", words = "AutoTotem")
/*    */ public class AutoTotem
/*    */   extends Hack
/*    */ {
/* 26 */   private final SettingChoose replace = new SettingChoose("Replace", (byte)2, new String[] { "all", "empty", "exceptCrystals" });
/* 27 */   private final SettingValue health = new SettingValue("Health", 8.0F, 1.0F, 20.0F);
/* 28 */   private final SettingBoolean setBack = new SettingBoolean("SetBack", true);
/* 29 */   private final SettingValue setBackHealth = new SettingValue("SetBackHealth", 15.0F, 2.0F, 20.0F);
/*    */   private boolean isRun;
/* 31 */   private int setBackSlot = -1;
/*    */ 
/*    */   
/*    */   public AutoTotem() {
/* 35 */     this.settings = Hack.addSettings(this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 41 */     super.onEnable();
/* 42 */     new Listener(NClientEvent.LivingUpdatedEvent.class, this);
/* 43 */     new Listener(NClientEvent.PlayerHealthChangeEvent.class, this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onInvoke(NClientEvent event) {
/* 49 */     if (this.isRun && event instanceof NClientEvent.PlayerHealthChangeEvent)
/*    */       return; 
/* 51 */     if (event instanceof NClientEvent.LivingUpdatedEvent) {
/*    */       
/* 53 */       if (!Baritone.isInOffHand(Items.TOTEM_OF_UNDYING)) {
/*    */         
/* 55 */         int slotFor = Baritone.getSlotFor(Items.TOTEM_OF_UNDYING, false);
/* 56 */         if (slotFor != -1) {
/*    */           
/* 58 */           if (NClient.MC.player.getHealth() <= this.health.getValue())
/*    */           {
/* 60 */             if ((this.replace.getValue() == 1 && Baritone.isEmptyInOffHand()) || this.replace.getValue() == 0 || (this.replace
/* 61 */               .getValue() == 2 && !Baritone.isInOffHand(Items.END_CRYSTAL)))
/* 62 */               Baritone.putInOffHand(slotFor); 
/*    */           }
/* 64 */           this.setBackSlot = slotFor;
/*    */         } 
/*    */       } 
/*    */     } else {
/* 68 */       (new Thread(() -> {
/*    */             this.isRun = true;
/*    */             
/*    */             while (this.enabled && NClient.MC.world != null && this.setBack.getValue() && this.setBackSlot != -1) {
/*    */               if (NClient.MC.player.getHealth() >= this.setBackHealth.getValue()) {
/*    */                 Baritone.putInOffHand(this.setBackSlot);
/*    */                 
/*    */                 break;
/*    */               } 
/*    */               BUtils.sleep(50L);
/*    */             } 
/*    */             this.isRun = false;
/* 80 */           })).start();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/hacks/AutoTotem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
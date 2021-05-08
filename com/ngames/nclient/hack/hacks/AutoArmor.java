/*     */ package com.ngames.nclient.hack.hacks;
/*     */ 
/*     */ import com.ngames.nclient.NClient;
/*     */ import com.ngames.nclient.baritone.BUtils;
/*     */ import com.ngames.nclient.event.Listener;
/*     */ import com.ngames.nclient.event.NClientEvent;
/*     */ import com.ngames.nclient.hack.Category;
/*     */ import com.ngames.nclient.hack.Hack;
/*     */ import com.ngames.nclient.hack.NClientHack;
/*     */ import com.ngames.nclient.hack.settings.SettingBoolean;
/*     */ import com.ngames.nclient.hack.settings.SettingValue;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.ClickType;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @NClientHack(category = Category.PLAYER, description = "Automatically equip best armor in your inventory", name = "AutoArmor", words = "AutoArmor Armor")
/*     */ public class AutoArmor
/*     */   extends Hack
/*     */ {
/*  27 */   private SettingValue equipDelay = new SettingValue("EquipDelay", 100.0F, 0.0F, 3000.0F);
/*  28 */   private SettingBoolean equipWhenInvOpen = new SettingBoolean("EquipWhenInvOpen", false);
/*     */   
/*  30 */   private int[] bestArmorSlots = new int[4];
/*     */   
/*     */   private boolean isRun;
/*     */   
/*     */   public AutoArmor() {
/*  35 */     this.settings = Hack.addSettings(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEnable() {
/*  41 */     super.onEnable();
/*  42 */     new Listener(NClientEvent.LivingUpdatedEvent.class, this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onInvoke(NClientEvent event) {
/*  48 */     if (this.isRun) {
/*     */       return;
/*     */     }
/*  51 */     (new Thread(() -> {
/*     */           this.isRun = true;
/*     */           
/*     */           if (NClient.MC.currentScreen instanceof net.minecraft.client.gui.inventory.GuiInventory && !this.equipWhenInvOpen.getValue()) {
/*     */             this.isRun = false;
/*     */             
/*     */             return;
/*     */           } 
/*     */           
/*     */           getBestArmor();
/*     */           for (int i = 0; i < 4; i++) {
/*     */             int armorValue = getArmorValue(((ItemStack)NClient.MC.player.inventory.armorInventory.get(i)).getItem());
/*     */             if (armorValue < this.bestArmorSlots[i]) {
/*     */               if (armorValue == -1) {
/*     */                 NClient.MC.playerController.windowClick(0, this.bestArmorSlots[i], 0, ClickType.QUICK_MOVE, (EntityPlayer)NClient.MC.player);
/*     */               } else {
/*     */                 NClient.MC.playerController.windowClick(0, this.bestArmorSlots[i], 0, ClickType.PICKUP, (EntityPlayer)NClient.MC.player);
/*     */                 NClient.MC.playerController.windowClick(0, 5 + i, 0, ClickType.PICKUP, (EntityPlayer)NClient.MC.player);
/*     */               } 
/*     */               BUtils.sleep((int)this.equipDelay.getValue());
/*     */             } 
/*     */           } 
/*     */           this.isRun = false;
/*  74 */         })).start();
/*     */   }
/*     */ 
/*     */   
/*     */   private void getBestArmor() {
/*  79 */     int[] bestArmorValues = new int[4];
/*     */     
/*  81 */     for (int slot = 0; slot < 36; slot++) {
/*     */       
/*  83 */       ItemStack stack = NClient.MC.player.inventory.getStackInSlot(slot);
/*     */       
/*  85 */       if (stack.getCount() <= 1)
/*     */       {
/*     */         
/*  88 */         if (stack != null && stack.getItem() instanceof ItemArmor) {
/*     */ 
/*     */           
/*  91 */           ItemArmor armor = (ItemArmor)stack.getItem();
/*  92 */           int armorType = armor.armorType.ordinal() - 2;
/*     */           
/*  94 */           if (armorType != 2 || !NClient.MC.player.inventory.armorItemInSlot(armorType).getItem().equals(Items.ELYTRA)) {
/*     */             
/*  96 */             int armorValue = armor.damageReduceAmount;
/*     */             
/*  98 */             if (armorValue > bestArmorValues[armorType]) {
/*     */               
/* 100 */               this.bestArmorSlots[armorType] = slot;
/* 101 */               bestArmorValues[armorType] = armorValue;
/*     */             } 
/*     */           } 
/*     */         }  } 
/*     */     } 
/*     */   }
/*     */   private int getArmorValue(Item itemArmor) {
/* 108 */     return (itemArmor == null) ? -1 : ((ItemArmor)itemArmor).damageReduceAmount;
/*     */   }
/*     */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/hacks/AutoArmor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
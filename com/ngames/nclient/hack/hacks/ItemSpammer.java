/*    */ package com.ngames.nclient.hack.hacks;
/*    */ 
/*    */ import com.ngames.nclient.NClient;
/*    */ import com.ngames.nclient.baritone.BUtils;
/*    */ import com.ngames.nclient.baritone.Baritone;
/*    */ import com.ngames.nclient.baritone.SafeThread;
/*    */ import com.ngames.nclient.hack.Category;
/*    */ import com.ngames.nclient.hack.Hack;
/*    */ import com.ngames.nclient.hack.NClientHack;
/*    */ import com.ngames.nclient.hack.settings.SettingValue;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @NClientHack(category = Category.EXPLOIT, description = "Shutdown server with dropping items from free item sign", name = "ItemSpammer", words = "ItemSpammer exploit shutdown crash killer server", keyBind = 0)
/*    */ public class ItemSpammer
/*    */   extends Hack
/*    */ {
/* 24 */   private final SettingValue minDelay = new SettingValue("MinDelay", 35.0F, 0.0F, 8.64E7F);
/* 25 */   private final SettingValue maxDelay = new SettingValue("MaxDelay", 40.0F, 0.0F, 8.64E7F);
/* 26 */   private final SettingValue dropMinDelay = new SettingValue("MinDropDelay", 5.0F, 0.0F, 8.64E7F);
/* 27 */   private final SettingValue dropMaxDelay = new SettingValue("MaxDropDelay", 8.0F, 0.0F, 8.64E7F);
/*    */ 
/*    */   
/*    */   public ItemSpammer() {
/* 31 */     this.settings = Hack.addSettings(this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 37 */     super.onEnable();
/* 38 */     (new SafeThread(() -> { Baritone.rightClickMouse(); while (!(NClient.MC.currentScreen instanceof net.minecraft.client.gui.inventory.GuiContainer) && this.enabled) { while (NClient.MC.world == null) BUtils.sleep(10L);  BUtils.sleep((long)BUtils.randomInRange(this.minDelay.getValue(), this.maxDelay.getValue()), BUtils.randomInRange(0, 999999)); Baritone.rightClickMouse(); }  if (this.dropMaxDelay.getValue() == 0.0F) { Baritone.dropAllItems(); } else { Baritone.dropAllItems(this.dropMinDelay.getValue(), this.dropMaxDelay.getValue()); }  Baritone.closeInventory(); while (NClient.MC.currentScreen instanceof net.minecraft.client.gui.inventory.GuiContainer && this.enabled) BUtils.sleep(10L);  }this))
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
/* 57 */       .start();
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/hacks/ItemSpammer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
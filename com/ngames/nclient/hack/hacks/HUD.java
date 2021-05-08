/*    */ package com.ngames.nclient.hack.hacks;
/*    */ 
/*    */ import com.ngames.nclient.NClient;
/*    */ import com.ngames.nclient.gui.Hud;
/*    */ import com.ngames.nclient.hack.Category;
/*    */ import com.ngames.nclient.hack.Hack;
/*    */ import com.ngames.nclient.hack.NClientHack;
/*    */ import com.ngames.nclient.hack.settings.SettingChoose;
/*    */ import com.ngames.nclient.hack.settings.SettingValue;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @NClientHack(category = Category.RENDER, description = "Ingame HUD", name = "HUD", words = "HUD InGameGUI watermark")
/*    */ public class HUD
/*    */   extends Hack
/*    */ {
/* 19 */   public final SettingChoose theme = new SettingChoose("Theme", (byte)0, new String[] { "RG", "RB", "BlackWhite", "RGB" });
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 24 */   private final SettingValue x = new SettingValue("X", 10.0F, 0.0F, 8096.0F);
/* 25 */   private final SettingValue y = new SettingValue("Y", 10.0F, 0.0F, 8096.0F);
/*    */ 
/*    */   
/*    */   public HUD() {
/* 29 */     this.settings = Hack.addSettings(this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 35 */     super.onEnable();
/* 36 */     Hud.mainColor.setTheme(this.theme.getValue());
/* 37 */     Hud.x = (int)this.x.getValue();
/* 38 */     Hud.y = (int)this.y.getValue();
/* 39 */     Hud.updateTheme();
/* 40 */     Hud.enable();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 46 */     super.onUpdate();
/* 47 */     if (NClient.MC.currentScreen != null) {
/*    */       
/* 49 */       this.x.max = NClient.MC.currentScreen.width;
/* 50 */       this.y.max = NClient.MC.currentScreen.height;
/*    */     } 
/* 52 */     Hud.updateTheme();
/* 53 */     Hud.x = (int)this.x.getValue();
/* 54 */     Hud.y = (int)this.y.getValue();
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/hacks/HUD.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package com.ngames.nclient.hack.hacks;
/*    */ 
/*    */ import com.ngames.nclient.NClient;
/*    */ import com.ngames.nclient.gui.Gui;
/*    */ import com.ngames.nclient.hack.Category;
/*    */ import com.ngames.nclient.hack.Hack;
/*    */ import com.ngames.nclient.hack.NClientHack;
/*    */ import net.minecraft.client.gui.GuiScreen;
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
/*    */ @NClientHack(category = Category.RENDER, description = "Open the clickGUI", name = "ClickGUI", words = "ClickGUI Menu Hud HudEditor", keyBind = 54)
/*    */ public class ClickGUI
/*    */   extends Hack
/*    */ {
/*    */   public void onEnable() {
/* 31 */     super.onEnable();
/* 32 */     NClient.gui = new Gui(NClient.MC.currentScreen);
/* 33 */     NClient.MC.displayGuiScreen((GuiScreen)NClient.gui);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onDisable() {
/* 39 */     NClient.MC.displayGuiScreen((GuiScreen)null);
/* 40 */     if (NClient.MC.currentScreen == null)
/*    */     {
/* 42 */       NClient.MC.setIngameFocus();
/*    */     }
/* 44 */     Gui.justPressed = true;
/* 45 */     super.onDisable();
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/hacks/ClickGUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
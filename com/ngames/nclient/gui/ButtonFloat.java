/*    */ package com.ngames.nclient.gui;
/*    */ 
/*    */ import com.ngames.nclient.NClient;
/*    */ import com.ngames.nclient.hack.settings.Setting;
/*    */ import com.ngames.nclient.hack.settings.SettingValue;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ 
/*    */ public class ButtonFloat
/*    */   extends Button
/*    */ {
/*    */   SettingValue parent;
/*    */   public String ds;
/*    */   
/*    */   public ButtonFloat(int buttonId, int x, int y, String buttonText, SettingValue parent) {
/* 16 */     super(buttonId, x, y, 120, 20, buttonText);
/* 17 */     this.parent = parent;
/* 18 */     this.ds = this.parent.name + ": " + this.parent.getValue();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
/* 24 */     if (this.visible) {
/*    */       
/* 26 */       mc.getTextureManager().bindTexture(BUTTON_TEXTURES);
/* 27 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 28 */       this.hovered = (mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height);
/* 29 */       int i = getHoverState(this.hovered);
/* 30 */       GlStateManager.enableBlend();
/* 31 */       GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
/* 32 */       GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
/* 33 */       drawTexturedModalRect(this.x, this.y, 0, 46 + i * 20, this.width / 2, this.height);
/* 34 */       drawTexturedModalRect(this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);
/* 35 */       mouseDragged(mc, mouseX, mouseY);
/* 36 */       int j = 14737632;
/*    */       
/* 38 */       if (this.packedFGColour != 0) {
/*    */         
/* 40 */         j = this.packedFGColour;
/*    */       
/*    */       }
/* 43 */       else if (!this.enabled) {
/*    */         
/* 45 */         j = 10526880;
/*    */       }
/* 47 */       else if (this.hovered) {
/*    */         
/* 49 */         j = 16777120;
/*    */       } 
/* 51 */       boolean isListining = (NClient.gui.listen && NClient.gui.listining != null && NClient.gui.listining.id == this.id);
/* 52 */       if (isListining)
/*    */       {
/* 54 */         j = listenColor;
/*    */       }
/* 56 */       if (!NClient.gui.cache.equals("null") && isListining)
/* 57 */         this.ds = this.parent.name + ": " + NClient.gui.cache; 
/* 58 */       drawCenteredString(NClient.MC.fontRenderer, this.ds, this.x + this.width / 2, this.y + (this.height - 8) / 2, j);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void mouseReleased(int mouseX, int mouseY) {
/* 65 */     if (NClient.gui.listining.id == this.id) {
/*    */       
/* 67 */       NClient.gui.listen = false;
/* 68 */       NClient.gui.listining = null;
/*    */     } else {
/* 70 */       NClient.gui.listining = (Setting)this.parent;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/gui/ButtonFloat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
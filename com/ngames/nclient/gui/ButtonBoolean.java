/*    */ package com.ngames.nclient.gui;
/*    */ 
/*    */ import com.ngames.nclient.NClient;
/*    */ import com.ngames.nclient.hack.settings.SettingBoolean;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ButtonBoolean
/*    */   extends Button
/*    */ {
/*    */   SettingBoolean parent;
/*    */   public String ds;
/*    */   
/*    */   public ButtonBoolean(int buttonId, int x, int y, String buttonText, SettingBoolean parent) {
/* 18 */     super(buttonId, x, y, 120, 20, buttonText);
/* 19 */     this.parent = parent;
/* 20 */     String r = this.parent.getValue() ? "ON" : "OFF";
/* 21 */     this.ds = this.parent.name + ": " + r;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
/* 27 */     if (this.visible) {
/*    */       
/* 29 */       mc.getTextureManager().bindTexture(BUTTON_TEXTURES);
/* 30 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 31 */       this.hovered = (mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height);
/* 32 */       int i = getHoverState(this.hovered);
/* 33 */       GlStateManager.enableBlend();
/* 34 */       GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
/* 35 */       GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
/* 36 */       drawTexturedModalRect(this.x, this.y, 0, 46 + i * 20, this.width / 2, this.height);
/* 37 */       drawTexturedModalRect(this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);
/* 38 */       mouseDragged(mc, mouseX, mouseY);
/* 39 */       int j = 14737632;
/*    */       
/* 41 */       if (this.packedFGColour != 0) {
/*    */         
/* 43 */         j = this.packedFGColour;
/*    */       
/*    */       }
/* 46 */       else if (!this.enabled) {
/*    */         
/* 48 */         j = 10526880;
/*    */       }
/* 50 */       else if (this.hovered) {
/*    */         
/* 52 */         j = 16777120;
/*    */       } 
/*    */       
/* 55 */       drawCenteredString(NClient.MC.fontRenderer, this.ds, this.x + this.width / 2, this.y + (this.height - 8) / 2, j);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void mouseReleased(int mouseX, int mouseY) {
/* 62 */     this.parent.setValue();
/* 63 */     String r = this.parent.getValue() ? "ON" : "OFF";
/* 64 */     this.ds = this.parent.name + ": " + r;
/* 65 */     this.parent.onUpdate();
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/gui/ButtonBoolean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package com.ngames.nclient.gui;
/*    */ 
/*    */ import com.ngames.nclient.NClient;
/*    */ import com.ngames.nclient.hack.settings.SettingChoose;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ButtonChoose
/*    */   extends Button
/*    */ {
/*    */   SettingChoose parent;
/*    */   public String ds;
/*    */   
/*    */   public ButtonChoose(int buttonId, int x, int y, String buttonText, SettingChoose parent) {
/* 18 */     super(buttonId, x, y, 120, 20, buttonText);
/* 19 */     this.parent = parent;
/* 20 */     this.ds = this.parent.name + ": " + (String)this.parent.mapping.get(this.parent.getValue());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
/* 26 */     if (this.visible) {
/*    */       
/* 28 */       mc.getTextureManager().bindTexture(BUTTON_TEXTURES);
/* 29 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 30 */       this.hovered = (mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height);
/* 31 */       int i = getHoverState(this.hovered);
/* 32 */       GlStateManager.enableBlend();
/* 33 */       GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
/* 34 */       GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
/* 35 */       drawTexturedModalRect(this.x, this.y, 0, 46 + i * 20, this.width / 2, this.height);
/* 36 */       drawTexturedModalRect(this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);
/* 37 */       mouseDragged(mc, mouseX, mouseY);
/* 38 */       int j = 14737632;
/*    */       
/* 40 */       if (this.packedFGColour != 0) {
/*    */         
/* 42 */         j = this.packedFGColour;
/*    */       
/*    */       }
/* 45 */       else if (!this.enabled) {
/*    */         
/* 47 */         j = 10526880;
/*    */       }
/* 49 */       else if (this.hovered) {
/*    */         
/* 51 */         j = 16777120;
/*    */       } 
/* 53 */       drawCenteredString(NClient.MC.fontRenderer, this.ds, this.x + this.width / 2, this.y + (this.height - 8) / 2, j);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void mouseReleased(int mouseX, int mouseY) {
/* 60 */     this.parent.setValue();
/* 61 */     this.ds = this.parent.name + ": " + (String)this.parent.mapping.get(this.parent.getValue());
/* 62 */     this.parent.onUpdate();
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/gui/ButtonChoose.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
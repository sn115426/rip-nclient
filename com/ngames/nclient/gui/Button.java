/*     */ package com.ngames.nclient.gui;
/*     */ 
/*     */ import com.ngames.nclient.NClient;
/*     */ import com.ngames.nclient.hack.settings.Setting;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Button
/*     */   extends GuiButton
/*     */ {
/*  14 */   public static final int enabledColor = Theme.getRGB(-1, 45, 32);
/*  15 */   public static final int listenColor = Theme.getRGB(86, 64, -63);
/*     */ 
/*     */   
/*     */   public Button(int buttonId, int x, int y, String buttonText) {
/*  19 */     super(buttonId, x, y, buttonText);
/*     */   }
/*     */ 
/*     */   
/*     */   public Button(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
/*  24 */     super(buttonId, x, y, widthIn, heightIn, buttonText);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
/*  30 */     if (this.visible) {
/*     */       
/*  32 */       mc.getTextureManager().bindTexture(BUTTON_TEXTURES);
/*  33 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*  34 */       this.hovered = (mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height);
/*  35 */       int i = getHoverState(this.hovered);
/*  36 */       GlStateManager.enableBlend();
/*  37 */       GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
/*  38 */       GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
/*  39 */       drawTexturedModalRect(this.x, this.y, 0, 46 + i * 20, this.width / 2, this.height);
/*  40 */       drawTexturedModalRect(this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);
/*  41 */       mouseDragged(mc, mouseX, mouseY);
/*  42 */       int j = 14737632;
/*     */       
/*  44 */       if (this.packedFGColour != 0) {
/*     */         
/*  46 */         j = this.packedFGColour;
/*     */       
/*     */       }
/*  49 */       else if (!this.enabled) {
/*     */         
/*  51 */         j = 10526880;
/*     */       }
/*  53 */       else if (this.hovered) {
/*     */         
/*  55 */         j = 16777120;
/*  56 */         NClient.gui.mouseOverButton = this;
/*     */       } 
/*     */       
/*  59 */       if (NClient.isHackExist(this.displayString) && NClient.getHack(this.displayString).isEnabled())
/*     */       {
/*  61 */         j = enabledColor;
/*     */       }
/*     */       
/*  64 */       drawCenteredString(NClient.MC.fontRenderer, this.displayString, this.x + this.width / 2, this.y + (this.height - 8) / 2, j);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Setting getSetting() {
/*  70 */     if (this instanceof ButtonFloat)
/*     */     {
/*  72 */       return (Setting)((ButtonFloat)this).parent;
/*     */     }
/*  74 */     if (this instanceof ButtonString)
/*     */     {
/*  76 */       return (Setting)((ButtonString)this).parent;
/*     */     }
/*  78 */     if (this instanceof ButtonChoose)
/*     */     {
/*  80 */       return (Setting)((ButtonChoose)this).parent;
/*     */     }
/*  82 */     if (this instanceof ButtonBoolean)
/*     */     {
/*  84 */       return (Setting)((ButtonBoolean)this).parent;
/*     */     }
/*  86 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Setting getSetting(GuiButton button) {
/*  91 */     if (button instanceof ButtonFloat)
/*     */     {
/*  93 */       return (Setting)((ButtonFloat)button).parent;
/*     */     }
/*  95 */     if (button instanceof ButtonString)
/*     */     {
/*  97 */       return (Setting)((ButtonString)button).parent;
/*     */     }
/*  99 */     if (button instanceof ButtonChoose)
/*     */     {
/* 101 */       return (Setting)((ButtonChoose)button).parent;
/*     */     }
/* 103 */     if (button instanceof ButtonBoolean)
/*     */     {
/* 105 */       return (Setting)((ButtonBoolean)button).parent;
/*     */     }
/* 107 */     return null;
/*     */   }
/*     */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/gui/Button.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
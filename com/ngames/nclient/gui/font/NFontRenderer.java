/*     */ package com.ngames.nclient.gui.font;
/*     */ 
/*     */ import com.ngames.nclient.NClient;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.regex.Pattern;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.util.StringUtils;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.newdawn.slick.Color;
/*     */ import org.newdawn.slick.SlickException;
/*     */ import org.newdawn.slick.UnicodeFont;
/*     */ import org.newdawn.slick.font.effects.ColorEffect;
/*     */ 
/*     */ public class NFontRenderer
/*     */ {
/*  24 */   private static final Pattern COLOR_CODE_PATTERN = Pattern.compile("§[0123456789abcdefklmnor]");
/*  25 */   public final int FONT_HEIGHT = 9;
/*  26 */   private final int[] colorCodes = new int[] { 0, 170, 43520, 43690, 11141120, 11141290, 16755200, 11184810, 5592405, 5592575, 5635925, 5636095, 16733525, 16733695, 16777045, 16777215 };
/*     */   
/*  28 */   private final Map<String, Float> cachedStringWidth = new HashMap<>();
/*     */   private float antiAliasingFactor;
/*     */   private UnicodeFont unicodeFont;
/*  31 */   private int prevScaleFactor = (new ScaledResolution(Minecraft.getMinecraft())).getScaleFactor();
/*     */   
/*     */   private Font font;
/*     */ 
/*     */   
/*     */   public NFontRenderer(Font font, int fontSize) {
/*  37 */     ScaledResolution resolution = new ScaledResolution(Minecraft.getMinecraft());
/*  38 */     this.prevScaleFactor = resolution.getScaleFactor();
/*  39 */     this.unicodeFont = new UnicodeFont(font.deriveFont(font.getSize() * this.prevScaleFactor / 2.0F), fontSize, false, false);
/*  40 */     this.unicodeFont.addAsciiGlyphs();
/*  41 */     this.unicodeFont.getEffects().add(new ColorEffect(Color.WHITE));
/*     */     try {
/*  43 */       this.unicodeFont.loadGlyphs();
/*  44 */     } catch (SlickException e) {
/*  45 */       e.printStackTrace();
/*     */     } 
/*  47 */     this.antiAliasingFactor = resolution.getScaleFactor();
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawStringScaled(String text, int givenX, int givenY, int color, double givenScale) {
/*  52 */     GL11.glPushMatrix();
/*  53 */     GL11.glTranslated(givenX, givenY, 0.0D);
/*  54 */     GL11.glScaled(givenScale, givenScale, givenScale);
/*  55 */     drawString(text, 0.0F, 0.0F, color);
/*  56 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int drawString(String text, float x, float y, int color) {
/*  62 */     if (text == null) {
/*  63 */       return 0;
/*     */     }
/*  65 */     ScaledResolution resolution = new ScaledResolution(Minecraft.getMinecraft());
/*     */ 
/*     */     
/*     */     try {
/*  69 */       if (resolution.getScaleFactor() != this.prevScaleFactor)
/*     */       {
/*  71 */         this.prevScaleFactor = resolution.getScaleFactor();
/*  72 */         this.unicodeFont = new UnicodeFont(this.font.deriveFont(this.font.getSize() * this.prevScaleFactor / 2.0F));
/*  73 */         this.unicodeFont.addAsciiGlyphs();
/*  74 */         this.unicodeFont.getEffects().add(new ColorEffect(Color.WHITE));
/*  75 */         this.unicodeFont.loadGlyphs();
/*     */       }
/*     */     
/*  78 */     } catch (SlickException e) {
/*     */       
/*  80 */       e.printStackTrace();
/*     */     } 
/*     */     
/*  83 */     this.antiAliasingFactor = resolution.getScaleFactor();
/*     */     
/*  85 */     GL11.glPushMatrix();
/*  86 */     GlStateManager.scale(1.0F / this.antiAliasingFactor, 1.0F / this.antiAliasingFactor, 1.0F / this.antiAliasingFactor);
/*  87 */     x *= this.antiAliasingFactor;
/*  88 */     y *= this.antiAliasingFactor;
/*  89 */     float originalX = x;
/*  90 */     float red = (color >> 16 & 0xFF) / 255.0F;
/*  91 */     float green = (color >> 8 & 0xFF) / 255.0F;
/*  92 */     float blue = (color & 0xFF) / 255.0F;
/*  93 */     float alpha = (color >> 24 & 0xFF) / 255.0F;
/*  94 */     GlStateManager.color(red, green, blue, alpha);
/*     */     
/*  96 */     int currentColor = color;
/*     */     
/*  98 */     char[] characters = text.toCharArray();
/*     */     
/* 100 */     GlStateManager.disableLighting();
/* 101 */     GlStateManager.enableBlend();
/* 102 */     GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/* 103 */     GlStateManager.blendFunc(770, 771);
/*     */     
/* 105 */     String[] parts = COLOR_CODE_PATTERN.split(text);
/* 106 */     int index = 0;
/* 107 */     for (String s : parts) {
/*     */       
/* 109 */       for (String s2 : s.split("\n")) {
/*     */         
/* 111 */         for (String s3 : s2.split("\r")) {
/*     */ 
/*     */           
/* 114 */           this.unicodeFont.drawString(x, y, s3, new Color(currentColor));
/* 115 */           x += this.unicodeFont.getWidth(s3);
/*     */           
/* 117 */           index += s3.length();
/* 118 */           if (index < characters.length && characters[index] == '\r') {
/*     */             
/* 120 */             x = originalX;
/* 121 */             index++;
/*     */           } 
/*     */         } 
/* 124 */         if (index < characters.length && characters[index] == '\n') {
/*     */           
/* 126 */           x = originalX;
/* 127 */           y += getHeight(s2) * 2.0F;
/* 128 */           index++;
/*     */         } 
/*     */       } 
/* 131 */       if (index < characters.length) {
/*     */         
/* 133 */         char colorCode = characters[index];
/* 134 */         if (colorCode == '§') {
/*     */           
/* 136 */           char colorChar = characters[index + 1];
/* 137 */           int codeIndex = "0123456789abcdef".indexOf(colorChar);
/* 138 */           if (codeIndex < 0) {
/*     */             
/* 140 */             if (colorChar == 'r')
/*     */             {
/* 142 */               currentColor = color;
/*     */             }
/*     */           }
/*     */           else {
/*     */             
/* 147 */             currentColor = this.colorCodes[codeIndex];
/*     */           } 
/* 149 */           index += 2;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 154 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 155 */     GlStateManager.bindTexture(0);
/* 156 */     GlStateManager.popMatrix();
/* 157 */     return (int)getWidth(text);
/*     */   }
/*     */ 
/*     */   
/*     */   public int drawStringWithShadow(String text, float x, float y, int color) {
/* 162 */     if (text == null || text == "") {
/* 163 */       return 0;
/*     */     }
/* 165 */     drawString(StringUtils.stripControlCodes(text), x + 0.5F, y + 0.5F, 0);
/* 166 */     return drawString(text, x, y, color);
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawCenteredString(String text, float x, float y, int color) {
/* 171 */     drawString(text, x - ((int)getWidth(text) >> 1), y - 2.0F, color);
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawCenteredTextScaled(String text, int givenX, int givenY, int color, double givenScale) {
/* 176 */     GL11.glPushMatrix();
/* 177 */     GL11.glTranslated(givenX, givenY, 0.0D);
/* 178 */     GL11.glScaled(givenScale, givenScale, givenScale);
/* 179 */     drawCenteredString(text, 0.0F, 0.0F, color);
/* 180 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawCenteredStringWithShadow(String text, float x, float y, int color) {
/* 185 */     drawCenteredString(StringUtils.stripControlCodes(text), x + 0.5F, y + 0.5F, color);
/* 186 */     drawCenteredString(text, x, y, color);
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawHoveringText(String text, int x, int y) {
/* 191 */     if (!text.isEmpty()) {
/*     */       
/* 193 */       GlStateManager.disableRescaleNormal();
/* 194 */       RenderHelper.disableStandardItemLighting();
/* 195 */       GlStateManager.disableLighting();
/* 196 */       GlStateManager.disableDepth();
/* 197 */       int i = (int)getWidth(text);
/* 198 */       int l1 = x + 12;
/* 199 */       int i2 = y - 12;
/* 200 */       int k = 8;
/*     */       
/* 202 */       if (l1 + i > NClient.MC.displayWidth)
/*     */       {
/* 204 */         l1 -= 28 + i;
/*     */       }
/*     */       
/* 207 */       if (i2 + k + 6 > NClient.MC.displayHeight)
/*     */       {
/* 209 */         i2 = NClient.MC.displayHeight - k - 6;
/*     */       }
/*     */       
/* 212 */       NClient.gui._drawGradientRect(l1 - 3, i2 - 4, l1 + i + 3, i2 - 3, -267386864, -267386864);
/* 213 */       NClient.gui._drawGradientRect(l1 - 3, i2 + k + 3, l1 + i + 3, i2 + k + 4, -267386864, -267386864);
/* 214 */       NClient.gui._drawGradientRect(l1 - 3, i2 - 3, l1 + i + 3, i2 + k + 3, -267386864, -267386864);
/* 215 */       NClient.gui._drawGradientRect(l1 - 4, i2 - 3, l1 - 3, i2 + k + 3, -267386864, -267386864);
/* 216 */       NClient.gui._drawGradientRect(l1 + i + 3, i2 - 3, l1 + i + 4, i2 + k + 3, -267386864, -267386864);
/* 217 */       NClient.gui._drawGradientRect(l1 - 3, i2 - 3 + 1, l1 - 3 + 1, i2 + k + 3 - 1, 1347420415, 1344798847);
/* 218 */       NClient.gui._drawGradientRect(l1 + i + 2, i2 - 3 + 1, l1 + i + 3, i2 + k + 3 - 1, 1347420415, 1344798847);
/* 219 */       NClient.gui._drawGradientRect(l1 - 3, i2 - 3, l1 + i + 3, i2 - 3 + 1, 1347420415, 1347420415);
/* 220 */       NClient.gui._drawGradientRect(l1 - 3, i2 + k + 2, l1 + i + 3, i2 + k + 3, 1344798847, 1344798847);
/*     */       
/* 222 */       drawStringWithShadow(text, l1, i2, -1);
/* 223 */       GlStateManager.enableLighting();
/* 224 */       GlStateManager.enableDepth();
/* 225 */       RenderHelper.enableStandardItemLighting();
/* 226 */       GlStateManager.enableRescaleNormal();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public float getWidth(String text) {
/* 232 */     if (this.cachedStringWidth.size() > 1000)
/* 233 */       this.cachedStringWidth.clear(); 
/* 234 */     return ((Float)this.cachedStringWidth.computeIfAbsent(text, e -> Float.valueOf(this.unicodeFont.getWidth(text.replaceAll(COLOR_CODE_PATTERN.toString(), "")) / this.antiAliasingFactor))).floatValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public float getCharWidth(char c) {
/* 239 */     return this.unicodeFont.getWidth(String.valueOf(c));
/*     */   }
/*     */ 
/*     */   
/*     */   public float getHeight(String s) {
/* 244 */     return this.unicodeFont.getHeight(s) / 2.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public UnicodeFont getFont() {
/* 249 */     return this.unicodeFont;
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawSplitString(ArrayList<String> lines, int x, int y, int color) {
/* 254 */     drawString(String.join("\n\r", (Iterable)lines), x, y, color);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> splitString(String text, int wrapWidth) {
/* 259 */     List<String> lines = new ArrayList<>();
/*     */     
/* 261 */     String[] splitText = text.split(" ");
/* 262 */     StringBuilder currentString = new StringBuilder();
/*     */     
/* 264 */     for (String word : splitText) {
/*     */       
/* 266 */       String potential = currentString + " " + word;
/*     */       
/* 268 */       if (getWidth(potential) >= wrapWidth) {
/*     */         
/* 270 */         lines.add(currentString.toString());
/* 271 */         currentString = new StringBuilder();
/*     */       } 
/*     */       
/* 274 */       currentString.append(word).append(" ");
/*     */     } 
/*     */     
/* 277 */     lines.add(currentString.toString());
/* 278 */     return lines;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStringWidth(String p_Name) {
/* 283 */     return this.unicodeFont.getWidth(p_Name.replaceAll(COLOR_CODE_PATTERN.toString(), "")) / 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getStringHeight(String p_Name) {
/* 288 */     return getHeight(p_Name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String trimStringToWidth(String text, int width) {
/* 296 */     return trimStringToWidth(text, width, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public String trimStringToWidth(String text, int width, boolean reverse) {
/* 301 */     StringBuilder stringbuilder = new StringBuilder();
/* 302 */     int i = 0;
/* 303 */     int j = reverse ? (text.length() - 1) : 0;
/* 304 */     int k = reverse ? -1 : 1;
/* 305 */     boolean flag = false;
/* 306 */     boolean flag1 = false;
/*     */     int l;
/* 308 */     for (l = j; l >= 0 && l < text.length() && i < width; l += k) {
/*     */       
/* 310 */       char c0 = text.charAt(l);
/* 311 */       float i1 = getWidth(text);
/*     */       
/* 313 */       if (flag) {
/*     */         
/* 315 */         flag = false;
/*     */         
/* 317 */         if (c0 != 'l' && c0 != 'L')
/*     */         {
/* 319 */           if (c0 == 'r' || c0 == 'R')
/*     */           {
/* 321 */             flag1 = false;
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 326 */           flag1 = true;
/*     */         }
/*     */       
/* 329 */       } else if (i1 < 0.0F) {
/*     */         
/* 331 */         flag = true;
/*     */       }
/*     */       else {
/*     */         
/* 335 */         i = (int)(i + i1);
/*     */         
/* 337 */         if (flag1)
/*     */         {
/* 339 */           i++;
/*     */         }
/*     */       } 
/*     */       
/* 343 */       if (i > width) {
/*     */         break;
/*     */       }
/*     */ 
/*     */       
/* 348 */       if (reverse) {
/*     */         
/* 350 */         stringbuilder.insert(0, c0);
/*     */       }
/*     */       else {
/*     */         
/* 354 */         stringbuilder.append(c0);
/*     */       } 
/*     */     } 
/*     */     
/* 358 */     return stringbuilder.toString();
/*     */   }
/*     */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/gui/font/NFontRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
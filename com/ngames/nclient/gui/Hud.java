/*     */ package com.ngames.nclient.gui;
/*     */ 
/*     */ import com.ngames.nclient.NClient;
/*     */ import com.ngames.nclient.baritone.BUtils;
/*     */ import com.ngames.nclient.baritone.SafeThread;
/*     */ import com.ngames.nclient.hack.Hack;
/*     */ import com.ngames.nclient.hack.HackUtils;
/*     */ import com.ngames.nclient.hack.Hacks;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Hud
/*     */ {
/*     */   public static int x;
/*     */   public static int y;
/*  22 */   public static ColorBuffer mainColor = new ColorBuffer((byte)0);
/*     */ 
/*     */   
/*     */   public static void drawHUD() {
/*  26 */     if (mainColor.theme != Hacks.hud.theme.getValue())
/*  27 */       updateTheme(); 
/*  28 */     List<String> StringList = new ArrayList<>();
/*  29 */     Map<String, String> inHuds = new HashMap<>();
/*  30 */     for (Hack h : NClient.HackList) {
/*     */       
/*  32 */       if (h.isEnabled() && !h.hidden.getValue()) {
/*     */         
/*  34 */         StringList.add(HackUtils.getName(h));
/*  35 */         if (h.inHud != null)
/*  36 */           inHuds.put(HackUtils.getName(h), h.inHud.toString()); 
/*     */       } 
/*     */     } 
/*  39 */     Collections.sort(StringList, new Comparator<String>()
/*     */         {
/*     */           public int compare(String s1, String s2) {
/*  42 */             return Integer.compare(NClient.MC.fontRenderer.getStringWidth(s1), NClient.MC.fontRenderer.getStringWidth(s2));
/*     */           }
/*     */         });
/*  45 */     StringList.add(0, "N-Client B1");
/*  46 */     int yStep = Math.round(mainColor.currentTheme.size() / StringList.size()) / mainColor.currentTheme.size() / 255;
/*  47 */     int xStep = Math.round(mainColor.currentTheme.size() / ((String)StringList.get(StringList.size() - 1)).length()) / mainColor.currentTheme.size() / 255;
/*  48 */     int dy = y;
/*  49 */     ColorBuffer vbuff = new ColorBuffer(mainColor);
/*  50 */     for (String s : StringList) {
/*     */       
/*  52 */       ColorBuffer hbuff = new ColorBuffer(vbuff);
/*  53 */       int dx = x; char[] arrayOfChar; int i; byte b;
/*  54 */       for (arrayOfChar = s.toCharArray(), i = arrayOfChar.length, b = 0; b < i; ) { Character c = Character.valueOf(arrayOfChar[b]);
/*     */         
/*  56 */         NClient.MC.fontRenderer.drawStringWithShadow(c.toString(), dx, dy, hbuff.next(xStep));
/*  57 */         dx += NClient.MC.fontRenderer.getStringWidth(c.toString()); b++; }
/*     */       
/*  59 */       if (inHuds.containsKey(s))
/*  60 */         NClient.MC.fontRenderer.drawString(inHuds.get(s), x + 3 + NClient.MC.fontRenderer.getStringWidth(s), dy, -1); 
/*  61 */       dy += 15;
/*  62 */       vbuff.next(yStep);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void updateTheme() {
/*  68 */     mainColor.setTheme(Hacks.hud.theme.getValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public static void enable() {
/*  73 */     (new SafeThread(() -> { if (Hacks.hud.isEnabled()) { mainColor.next(); } else { mainColor.j = 0; }  BUtils.sleep(5L); }(Hack)Hacks.hud))
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  79 */       .start();
/*     */   }
/*     */   
/*     */   public static class ColorBuffer
/*     */   {
/*  84 */     private int j = 0;
/*     */     
/*     */     private boolean direction = true;
/*     */     private List<Integer> currentTheme;
/*     */     public byte theme;
/*     */     
/*     */     public ColorBuffer(byte theme) {
/*  91 */       this.currentTheme = NClient.theme.getTheme(theme);
/*  92 */       this.theme = theme;
/*     */     }
/*     */ 
/*     */     
/*     */     public ColorBuffer(ColorBuffer other) {
/*  97 */       this.j = other.j;
/*  98 */       this.direction = other.direction;
/*  99 */       this.currentTheme = other.currentTheme;
/* 100 */       this.theme = other.theme;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setTheme(byte theme) {
/* 105 */       this.currentTheme = NClient.theme.getTheme(theme);
/* 106 */       this.theme = theme;
/* 107 */       this.j = 0;
/* 108 */       this.direction = true;
/*     */     }
/*     */ 
/*     */     
/*     */     public int next() {
/* 113 */       int ret = ((Integer)this.currentTheme.get(this.j)).intValue();
/* 114 */       if (this.direction) {
/* 115 */         this.j++;
/*     */       } else {
/* 117 */         this.j--;
/* 118 */       }  if (this.j == this.currentTheme.size() - 1)
/* 119 */         this.direction = false; 
/* 120 */       if (this.j == 0)
/* 121 */         this.direction = true; 
/* 122 */       return ret;
/*     */     }
/*     */ 
/*     */     
/*     */     public int next(int step) {
/* 127 */       int ret = ((Integer)this.currentTheme.get(this.j)).intValue();
/* 128 */       if (this.direction) {
/* 129 */         this.j += step;
/*     */       } else {
/* 131 */         this.j -= step;
/* 132 */       }  if (this.j >= this.currentTheme.size() - 1) {
/*     */         
/* 134 */         this.direction = false;
/* 135 */         this.j = this.currentTheme.size() - 1;
/*     */       } 
/* 137 */       if (this.j <= 0) {
/*     */         
/* 139 */         this.direction = true;
/* 140 */         this.j = 0;
/*     */       } 
/* 142 */       return ret;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/gui/Hud.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
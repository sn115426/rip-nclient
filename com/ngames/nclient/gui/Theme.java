/*    */ package com.ngames.nclient.gui;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class Theme {
/*    */   public final List<Integer> RG;
/*    */   public final List<Integer> RB;
/*    */   
/*    */   public Theme() {
/* 10 */     List<Integer> rg = generate(255, 0, 1, true);
/* 11 */     rg.addAll(generate(255, 0, 0, false));
/* 12 */     this.RG = rg;
/* 13 */     List<Integer> rb = new ArrayList<>();
/* 14 */     rb.addAll(generate(255, 0, 2, true));
/* 15 */     rb.addAll(generate(0, 255, 0, false));
/* 16 */     this.RB = rb;
/* 17 */     List<Integer> bw = new ArrayList<>();
/* 18 */     for (int i = 0; i < 255; i++)
/* 19 */       bw.add(Integer.valueOf(getRGB(i, i, i))); 
/* 20 */     this.BlackWhite = bw;
/* 21 */     List<Integer> rgb = generate(255, 0, 2, true);
/* 22 */     rgb.addAll(generate(0, 255, 0, false));
/* 23 */     rgb.addAll(generate(0, 255, 1, true));
/* 24 */     rgb.addAll(generate(0, 255, 2, false));
/* 25 */     rgb.addAll(generate(255, 0, 0, true));
/* 26 */     rgb.addAll(generate(255, 0, 1, false));
/* 27 */     this.RGB = rgb;
/*    */   }
/*    */   public final List<Integer> BlackWhite; public final List<Integer> RGB;
/*    */   
/*    */   public static List<Integer> generate(int a, int b, int inc, boolean direction) {
/* 32 */     List<Integer> ret = new ArrayList<>();
/* 33 */     if (direction) {
/*    */       
/* 35 */       for (int i = 0; i < 255; i++)
/* 36 */         ret.add(Integer.valueOf(sortRGB(a, b, i, inc))); 
/*    */     } else {
/* 38 */       for (int i = 255; i > 0; i--)
/* 39 */         ret.add(Integer.valueOf(sortRGB(a, b, i, inc))); 
/*    */     } 
/* 41 */     return ret;
/*    */   }
/*    */ 
/*    */   
/*    */   private static int sortRGB(int a, int b, int c, int cPlace) {
/* 46 */     if (cPlace == 0)
/* 47 */       return getRGB(c, a, b); 
/* 48 */     if (cPlace == 1)
/* 49 */       return getRGB(a, c, b); 
/* 50 */     if (cPlace == 2)
/* 51 */       return getRGB(a, b, c); 
/* 52 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List<Integer> getTheme(byte id) {
/* 62 */     switch (id) {
/*    */       
/*    */       case 0:
/* 65 */         return this.RG;
/*    */       case 1:
/* 67 */         return this.RB;
/*    */       case 2:
/* 69 */         return this.BlackWhite;
/*    */       case 3:
/* 71 */         return this.RGB;
/*    */     } 
/* 73 */     return this.RGB;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static int getRGB(int r, int g, int b) {
/* 79 */     return (r << 16) + (g << 8) + b;
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/gui/Theme.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
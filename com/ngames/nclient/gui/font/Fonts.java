/*    */ package com.ngames.nclient.gui.font;
/*    */ 
/*    */ import com.ngames.nclient.NClient;
/*    */ import java.awt.Font;
/*    */ import java.awt.FontFormatException;
/*    */ import java.io.File;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Fonts
/*    */ {
/*    */   public static Font BLADRMF_;
/*    */   public static Font BRITANIC;
/*    */   public static Font BRLNSB;
/*    */   public static Font BRLNSDB;
/*    */   public static Font BRLNSR;
/*    */   public static Font BROADW;
/*    */   public static Font Elegance;
/*    */   public static Font Ellis___;
/*    */   public static Font Excess__;
/*    */   public static Font Hotmb___;
/*    */   public static Font Mandela_;
/*    */   public static Font Matte___;
/*    */   public static Font MATURASC;
/*    */   public static Font Sneabo__;
/*    */   public static Font STENCIL;
/*    */   public static Font Trendy__;
/*    */   public static Font tt0628m_;
/*    */   public static Font tt0756m_;
/*    */   public static Font TT1024M_;
/*    */   public static Font tt1247m_;
/*    */   public static Font tt1248m_;
/* 34 */   private static final File fontsDir = new File(NClient.NClientPath, "assets\\fonts");
/*    */ 
/*    */   
/*    */   public static void init() {
/*    */     try {
/* 39 */       BLADRMF_ = Font.createFont(0, new File(fontsDir, "BLADRMF_.TTF"));
/* 40 */       BRITANIC = Font.createFont(0, new File(fontsDir, "BRITANIC.TTF"));
/* 41 */       BRLNSB = Font.createFont(0, new File(fontsDir, "BRLNSB.TTF"));
/* 42 */       BRLNSDB = Font.createFont(0, new File(fontsDir, "BRLNSDB.TTF"));
/* 43 */       BRLNSR = Font.createFont(0, new File(fontsDir, "BRLNSR.TTF"));
/* 44 */       BROADW = Font.createFont(0, new File(fontsDir, "BROADW.TTF"));
/* 45 */       Elegance = Font.createFont(0, new File(fontsDir, "Elegance.ttf"));
/* 46 */       Ellis___ = Font.createFont(0, new File(fontsDir, "Ellis___.ttf"));
/* 47 */       Excess__ = Font.createFont(0, new File(fontsDir, "Excess__.ttf"));
/* 48 */       Hotmb___ = Font.createFont(0, new File(fontsDir, "Hotmb___.ttf"));
/* 49 */       Mandela_ = Font.createFont(0, new File(fontsDir, "Mandela_.ttf"));
/* 50 */       Matte___ = Font.createFont(0, new File(fontsDir, "Matte___.ttf"));
/* 51 */       MATURASC = Font.createFont(0, new File(fontsDir, "MATURASC.TTF"));
/* 52 */       Sneabo__ = Font.createFont(0, new File(fontsDir, "Sneabo__.ttf"));
/* 53 */       STENCIL = Font.createFont(0, new File(fontsDir, "STENCIL.TTF"));
/* 54 */       Trendy__ = Font.createFont(0, new File(fontsDir, "Trendy__.ttf"));
/* 55 */       tt0628m_ = Font.createFont(0, new File(fontsDir, "tt0628m_.ttf"));
/* 56 */       tt0756m_ = Font.createFont(0, new File(fontsDir, "tt0756m_.ttf"));
/* 57 */       TT1024M_ = Font.createFont(0, new File(fontsDir, "TT1024M_.TTF"));
/* 58 */       tt1247m_ = Font.createFont(0, new File(fontsDir, "tt1247m_.ttf"));
/* 59 */       tt1248m_ = Font.createFont(0, new File(fontsDir, "tt1248m_.ttf"));
/* 60 */     } catch (FontFormatException|java.io.IOException e) {
/* 61 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/gui/font/Fonts.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
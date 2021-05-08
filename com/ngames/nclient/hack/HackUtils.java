/*    */ package com.ngames.nclient.hack;
/*    */ 
/*    */ 
/*    */ public class HackUtils
/*    */ {
/*    */   public static String getName(Hack hack) {
/*  7 */     return ((NClientHack)hack.getClass().<NClientHack>getAnnotation(NClientHack.class)).name();
/*    */   }
/*    */ 
/*    */   
/*    */   public static String getDescrption(Hack hack) {
/* 12 */     return ((NClientHack)hack.getClass().<NClientHack>getAnnotation(NClientHack.class)).description();
/*    */   }
/*    */ 
/*    */   
/*    */   public static Category getCategory(Hack hack) {
/* 17 */     return ((NClientHack)hack.getClass().<NClientHack>getAnnotation(NClientHack.class)).category();
/*    */   }
/*    */ 
/*    */   
/*    */   public static String getWords(Hack hack) {
/* 22 */     return ((NClientHack)hack.getClass().<NClientHack>getAnnotation(NClientHack.class)).words();
/*    */   }
/*    */ 
/*    */   
/*    */   public static int getDefaultKeyBind(Hack hack) {
/* 27 */     return ((NClientHack)hack.getClass().<NClientHack>getAnnotation(NClientHack.class)).keyBind();
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/HackUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package com.ngames.nclient.hack;
/*    */ 
/*    */ public enum Category
/*    */ {
/*  5 */   ALL,
/*  6 */   CHAT,
/*  7 */   COMBAT,
/*  8 */   EXPLOIT,
/*  9 */   MISC,
/* 10 */   MOVEMENT,
/* 11 */   PLAYER,
/* 12 */   RENDER,
/* 13 */   WORLD;
/*    */ 
/*    */   
/*    */   public static Category getCategory(int id) {
/* 17 */     Category ret = ALL;
/* 18 */     if (id == 1) ret = MISC; 
/* 19 */     if (id == 2) ret = CHAT; 
/* 20 */     if (id == 3) ret = WORLD; 
/* 21 */     if (id == 4) ret = PLAYER; 
/* 22 */     if (id == 5) ret = RENDER; 
/* 23 */     if (id == 6) ret = COMBAT; 
/* 24 */     if (id == 7) ret = EXPLOIT; 
/* 25 */     if (id == 8) ret = MOVEMENT; 
/* 26 */     return ret;
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/Category.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
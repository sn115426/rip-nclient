/*    */ package com.ngames.nclient.hack.settings;
/*    */ 
/*    */ 
/*    */ public abstract class Setting
/*    */ {
/*    */   public int id;
/*    */   public String name;
/*    */   public Settings type;
/*    */   
/*    */   public Setting(String name, Settings type) {
/* 11 */     this.type = type;
/* 12 */     this.name = name;
/*    */   }
/*    */ 
/*    */   
/*    */   public Settings getType() {
/* 17 */     return this.type;
/*    */   }
/*    */   
/*    */   public void onUpdate() {}
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/settings/Setting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
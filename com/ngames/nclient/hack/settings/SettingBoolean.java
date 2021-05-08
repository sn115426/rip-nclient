/*    */ package com.ngames.nclient.hack.settings;
/*    */ 
/*    */ import com.ngames.nclient.gui.ButtonBoolean;
/*    */ 
/*    */ public class SettingBoolean
/*    */   extends Setting
/*    */ {
/*    */   public ButtonBoolean element;
/*    */   private boolean value;
/*    */   
/*    */   public SettingBoolean(String name, boolean value) {
/* 12 */     super(name, Settings.BOOLEAN);
/* 13 */     this.value = value;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void setValue() {
/* 24 */     this.value = !this.value;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setValue(boolean value) {
/* 29 */     this.value = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean getValue() {
/* 34 */     return this.value;
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/settings/SettingBoolean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
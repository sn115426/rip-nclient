/*    */ package com.ngames.nclient.hack.settings;
/*    */ 
/*    */ import com.ngames.nclient.gui.ButtonString;
/*    */ 
/*    */ public class SettingString
/*    */   extends Setting
/*    */ {
/*    */   public ButtonString element;
/*    */   private String value;
/*    */   public final int length;
/*    */   
/*    */   public SettingString(String name, String value, int length) {
/* 13 */     super(name, Settings.STRING_TYPE);
/* 14 */     this.value = value;
/* 15 */     this.length = length;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 21 */     this.element.ds = this.name + ": " + getValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setValue(String value) {
/* 26 */     if (value.length() <= this.length)
/*    */     {
/* 28 */       this.value = value;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String getValue() {
/* 34 */     return this.value;
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/settings/SettingString.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
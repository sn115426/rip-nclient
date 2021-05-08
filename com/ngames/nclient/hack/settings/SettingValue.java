/*    */ package com.ngames.nclient.hack.settings;
/*    */ 
/*    */ import com.ngames.nclient.gui.ButtonFloat;
/*    */ 
/*    */ public class SettingValue
/*    */   extends Setting
/*    */ {
/*    */   public ButtonFloat element;
/*    */   private float value;
/*    */   public final float min;
/*    */   public float max;
/*    */   
/*    */   public SettingValue(String name, float value, float min, float max) {
/* 14 */     super(name, Settings.VALUE_TYPE);
/* 15 */     this.value = value;
/* 16 */     this.min = min;
/* 17 */     this.max = max;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 23 */     this.element.ds = this.name + ": " + getValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setValue(float value) {
/* 28 */     if (value < this.min) {
/*    */       
/* 30 */       this.value = this.min;
/* 31 */     } else if (value > this.max) {
/*    */       
/* 33 */       this.value = this.max;
/*    */     } else {
/* 35 */       this.value = value;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public float getValue() {
/* 41 */     return this.value;
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/settings/SettingValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package com.ngames.nclient.hack.settings;
/*    */ 
/*    */ import com.ngames.nclient.gui.ButtonChoose;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class SettingChoose
/*    */   extends Setting
/*    */ {
/*    */   public ButtonChoose element;
/*    */   private byte value;
/*    */   public final int max;
/* 13 */   public List<String> mapping = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public SettingChoose(String name, byte value, String... names) {
/* 17 */     super(name, Settings.CHOOSE);
/* 18 */     this.value = value;
/* 19 */     this.max = names.length - 1;
/* 20 */     for (String vname : names) {
/* 21 */       this.mapping.add(vname);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 27 */     this.element.ds = this.name + ": " + (String)this.mapping.get(getValue());
/*    */   }
/*    */ 
/*    */   
/*    */   public void setValue() {
/* 32 */     byte value = this.value = (byte)(this.value + 1);
/* 33 */     if (value <= this.max && value >= 0)
/*    */     {
/* 35 */       this.value = value;
/*    */     }
/* 37 */     if (value > this.max && value >= 0)
/*    */     {
/* 39 */       this.value = (byte)(value % (this.max + 1));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void setValue(byte value) {
/* 45 */     if (value <= this.max && value >= 0)
/*    */     {
/* 47 */       this.value = value;
/*    */     }
/* 49 */     if (value > this.max && value >= 0)
/*    */     {
/* 51 */       this.value = (byte)(value % (this.max + 1));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public byte getValue() {
/* 57 */     return this.value;
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/settings/SettingChoose.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
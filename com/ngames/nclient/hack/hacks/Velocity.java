/*    */ package com.ngames.nclient.hack.hacks;
/*    */ 
/*    */ import com.ngames.nclient.hack.Category;
/*    */ import com.ngames.nclient.hack.Hack;
/*    */ import com.ngames.nclient.hack.NClientHack;
/*    */ import com.ngames.nclient.hack.settings.SettingValue;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @NClientHack(category = Category.COMBAT, description = "Change your knockback power", name = "Velocity", words = "Velocity AntiKnockback")
/*    */ public class Velocity
/*    */   extends Hack
/*    */ {
/* 15 */   public SettingValue verticalMin = new SettingValue("HorizontalMin", 0.0F, 0.0F, 10.0F);
/* 16 */   public SettingValue horizontalMin = new SettingValue("HorizontalMax", 0.7F, 0.0F, 10.0F);
/* 17 */   public SettingValue verticalMax = new SettingValue("VerticalMin", 0.0F, 0.0F, 10.0F);
/* 18 */   public SettingValue horizontalMax = new SettingValue("VerticalMax", 0.7F, 0.0F, 10.0F);
/*    */ 
/*    */   
/*    */   public Velocity() {
/* 22 */     this.settings = Hack.addSettings(this);
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/hacks/Velocity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
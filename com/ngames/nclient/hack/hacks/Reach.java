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
/*    */ @NClientHack(category = Category.COMBAT, description = "Set your reach distance", name = "Reach", words = "Reach")
/*    */ public class Reach
/*    */   extends Hack
/*    */ {
/* 15 */   public final SettingValue distance = new SettingValue("Distance", 6.0F, 1.0F, 6.0F);
/*    */ 
/*    */   
/*    */   public Reach() {
/* 19 */     this.settings = Hack.addSettings(this);
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/hacks/Reach.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
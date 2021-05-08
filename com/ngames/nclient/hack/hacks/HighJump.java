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
/*    */ @NClientHack(category = Category.MOVEMENT, description = "Change your jumps height", name = "HighJump", words = "HighJump SlimeJump JumpHeight")
/*    */ public class HighJump
/*    */   extends Hack
/*    */ {
/* 15 */   public SettingValue height = new SettingValue("Height", 2.0F, 0.0F, 100.0F);
/*    */ 
/*    */   
/*    */   public HighJump() {
/* 19 */     this.settings = Hack.addSettings(this);
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/hacks/HighJump.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
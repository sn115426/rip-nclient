/*    */ package com.ngames.nclient.hack.hacks;
/*    */ 
/*    */ import com.ngames.nclient.baritone.BUtils;
/*    */ import com.ngames.nclient.baritone.SafeThread;
/*    */ import com.ngames.nclient.hack.Category;
/*    */ import com.ngames.nclient.hack.Hack;
/*    */ import com.ngames.nclient.hack.NClientHack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @NClientHack(category = Category.COMBAT, description = "Display your current CPS", name = "CPSCount", words = "CPS CPSCount KillAura AutoClicker ClickerDelays")
/*    */ public class CPSCount
/*    */   extends Hack
/*    */ {
/*    */   public int clicks;
/*    */   public float cps;
/*    */   
/*    */   public void onEnable() {
/* 29 */     super.onEnable();
/* 30 */     (new SafeThread(() -> { BUtils.sleep(500L); this.cps = (this.clicks * 2); this.inHud.set(Float.valueOf(this.cps)); this.clicks = 0; }this))
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 35 */       .start();
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/hacks/CPSCount.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
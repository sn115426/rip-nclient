/*    */ package com.ngames.nclient.hack.hacks;
/*    */ 
/*    */ import com.ngames.nclient.baritone.Baritone;
/*    */ import com.ngames.nclient.event.Listener;
/*    */ import com.ngames.nclient.event.NClientEvent;
/*    */ import com.ngames.nclient.hack.Category;
/*    */ import com.ngames.nclient.hack.Hack;
/*    */ import com.ngames.nclient.hack.NClientHack;
/*    */ import com.ngames.nclient.hack.settings.SettingValue;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @NClientHack(category = Category.MOVEMENT, description = "looking in direction that you set", name = "LookingForward", words = "LookingForward Rotate")
/*    */ public class LookingForward
/*    */   extends Hack
/*    */ {
/* 19 */   private final SettingValue yaw = new SettingValue("Yaw", -90.0F, -180.0F, 180.0F);
/* 20 */   private final SettingValue pitch = new SettingValue("Pitch", 0.0F, -90.0F, 90.0F);
/*    */ 
/*    */   
/*    */   public LookingForward() {
/* 24 */     this.settings = Hack.addSettings(this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 30 */     super.onEnable();
/* 31 */     new Listener(NClientEvent.RunTickKeyboardEvent.class, this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onInvoke(NClientEvent event) {
/* 37 */     Baritone.rotatePlayer(this.yaw.getValue(), this.pitch.getValue());
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/hacks/LookingForward.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
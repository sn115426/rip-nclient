/*    */ package com.ngames.nclient.hack.hacks;
/*    */ 
/*    */ import com.ngames.nclient.NClient;
/*    */ import com.ngames.nclient.baritone.BUtils;
/*    */ import com.ngames.nclient.baritone.Baritone;
/*    */ import com.ngames.nclient.baritone.SafeThread;
/*    */ import com.ngames.nclient.hack.Category;
/*    */ import com.ngames.nclient.hack.Hack;
/*    */ import com.ngames.nclient.hack.Hacks;
/*    */ import com.ngames.nclient.hack.NClientHack;
/*    */ import com.ngames.nclient.hack.settings.SettingBoolean;
/*    */ import com.ngames.nclient.hack.settings.SettingValue;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @NClientHack(category = Category.COMBAT, description = "Automatically aiming at the entity", name = "AimAssist", words = "Aim AimAssist AimAssistent Aimbot KillAura")
/*    */ public class AimAssist
/*    */   extends Hack
/*    */ {
/* 24 */   private SettingValue updateDelay = new SettingValue("UpdateDelay", 10.0F, 0.0F, 1000.0F);
/* 25 */   private SettingBoolean auraTarget = new SettingBoolean("AuraTarget", true);
/* 26 */   private SettingValue range = new SettingValue("Range", 4.0F, 0.0F, 20.0F);
/* 27 */   private final SettingBoolean rtOnlyNotMO = new SettingBoolean("RTOnlyNotMO", true);
/*    */   
/*    */   private Entity target;
/*    */ 
/*    */   
/*    */   public AimAssist() {
/* 33 */     this.settings = addSettings(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 38 */     super.onEnable();
/* 39 */     (new SafeThread(() -> { if (this.auraTarget.getValue()) { this.target = Hacks.killAura18.target; } else { this.target = Baritone.getPriorityTarget(this.range.getValue(), true, false, false, null, (byte)1); }  if (this.target != null) { boolean rtr = BUtils.rayTraceBlocks(NClient.MC.player.getPositionEyes(NClient.MC.getRenderPartialTicks()), new Vec3d(this.target.posX + this.target.width * 0.5D, this.target.posY + this.target.height * 0.5D, this.target.posZ + this.target.width * 0.5D)); if (rtr && Baritone.isAlive(this.target) && NClient.MC.player.getDistance(this.target) <= this.range.getValue() && (Hacks.killAura18.isEnabled() || !this.auraTarget.getValue()) && ((this.rtOnlyNotMO.getValue() && NClient.MC.objectMouseOver.entityHit != null) || !this.rtOnlyNotMO.getValue())) Baritone.setRotationToEntity(this.target);  }  BUtils.sleep((int)this.updateDelay.getValue()); }this))
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
/*    */       
/* 54 */       .start();
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/hacks/AimAssist.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
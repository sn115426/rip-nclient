/*    */ package com.ngames.nclient.hack.hacks;
/*    */ 
/*    */ import com.ngames.nclient.NClient;
/*    */ import com.ngames.nclient.event.Listener;
/*    */ import com.ngames.nclient.event.NClientEvent;
/*    */ import com.ngames.nclient.hack.Category;
/*    */ import com.ngames.nclient.hack.Hack;
/*    */ import com.ngames.nclient.hack.Hacks;
/*    */ import com.ngames.nclient.hack.NClientHack;
/*    */ import com.ngames.nclient.hack.settings.SettingValue;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.init.MobEffects;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraftforge.common.ForgeHooks;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @NClientHack(category = Category.MOVEMENT, description = "Change your jumps length", name = "LongJump", words = "LongJump JumpLength Speed")
/*    */ public class LongJump
/*    */   extends Hack
/*    */ {
/* 23 */   private SettingValue length = new SettingValue("Length", 1.1F, 0.0F, 100.0F);
/*    */ 
/*    */   
/*    */   public LongJump() {
/* 27 */     this.settings = Hack.addSettings(this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 33 */     super.onEnable();
/* 34 */     new Listener(NClientEvent.PlayerJumpEvent.class, this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onInvoke(NClientEvent event) {
/* 40 */     NClient.MC.player.motionY = Hacks.highJump.isEnabled() ? (0.42F * Hacks.highJump.height.getValue()) : 0.41999998688697815D;
/*    */     
/* 42 */     if (NClient.MC.player.isPotionActive(MobEffects.JUMP_BOOST))
/*    */     {
/* 44 */       NClient.MC.player.motionY += ((NClient.MC.player.getActivePotionEffect(MobEffects.JUMP_BOOST).getAmplifier() + 1) * 0.1F);
/*    */     }
/* 46 */     float f = NClient.MC.player.rotationYaw * 0.017453292F;
/* 47 */     if (NClient.MC.player.isSprinting()) {
/*    */       
/* 49 */       NClient.MC.player.motionX -= (MathHelper.sin(f) * 0.2F);
/* 50 */       NClient.MC.player.motionZ += (MathHelper.cos(f) * 0.2F);
/*    */     } 
/* 52 */     NClient.MC.player.motionX *= this.length.getValue();
/* 53 */     NClient.MC.player.motionZ *= this.length.getValue();
/*    */     
/* 55 */     NClient.MC.player.isAirBorne = true;
/* 56 */     ForgeHooks.onLivingJump((EntityLivingBase)NClient.MC.player);
/* 57 */     event.setCalceled();
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/hacks/LongJump.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
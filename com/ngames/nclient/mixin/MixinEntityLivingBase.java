/*    */ package com.ngames.nclient.mixin;
/*    */ 
/*    */ import com.ngames.nclient.event.NClientEvent;
/*    */ import com.ngames.nclient.hack.Hacks;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({EntityLivingBase.class})
/*    */ public class MixinEntityLivingBase
/*    */ {
/*    */   @Inject(method = {"getJumpUpwardsMotion"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void getJumpUpwardsMotion(CallbackInfoReturnable<Float> info) {
/* 21 */     if (Hacks.highJump.isEnabled())
/*    */     {
/* 23 */       info.setReturnValue(Float.valueOf(0.42F * Hacks.highJump.height.getValue()));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   @Inject(method = {"jump"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void jump(CallbackInfo info) {
/* 30 */     if (NClientEvent.callEvent((NClientEvent)new NClientEvent.PlayerJumpEvent()))
/* 31 */       info.cancel(); 
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/mixin/MixinEntityLivingBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
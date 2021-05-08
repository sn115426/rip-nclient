/*    */ package com.ngames.nclient.mixin;
/*    */ 
/*    */ import com.ngames.nclient.hack.Hacks;
/*    */ import net.minecraft.entity.passive.EntityLlama;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({EntityLlama.class})
/*    */ public class MixinEntityLlama
/*    */ {
/*    */   @Inject(method = {"canBeSteered"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void canBeSteered(CallbackInfoReturnable<Boolean> info) {
/* 18 */     if (Hacks.entityControl.isEnabled()) {
/*    */       
/* 20 */       info.setReturnValue(Boolean.valueOf(true));
/* 21 */       info.cancel();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/mixin/MixinEntityLlama.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
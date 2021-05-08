/*    */ package com.ngames.nclient.mixin;
/*    */ 
/*    */ import com.ngames.nclient.event.NClientEvent;
/*    */ import net.minecraft.util.FoodStats;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({FoodStats.class})
/*    */ public class MixinFoodStats
/*    */ {
/*    */   @Inject(method = {"setFoodLevel"}, at = {@At("TAIL")})
/*    */   private void setFoodLevel(CallbackInfo info) {
/* 19 */     if (NClientEvent.callEvent((NClientEvent)new NClientEvent.PlayerFoodStatsChangeEvent()))
/* 20 */       info.cancel(); 
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/mixin/MixinFoodStats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
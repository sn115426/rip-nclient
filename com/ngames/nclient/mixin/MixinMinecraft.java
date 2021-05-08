/*    */ package com.ngames.nclient.mixin;
/*    */ 
/*    */ import com.ngames.nclient.event.NClientEvent;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({Minecraft.class})
/*    */ public class MixinMinecraft
/*    */ {
/*    */   @Inject(method = {"runTickKeyboard"}, at = {@At("TAIL")}, cancellable = true)
/*    */   private void runTickKeyboard(CallbackInfo info) {
/* 19 */     if (NClientEvent.callEvent((NClientEvent)new NClientEvent.RunTickKeyboardEvent()))
/* 20 */       info.cancel(); 
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/mixin/MixinMinecraft.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
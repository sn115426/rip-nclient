/*    */ package com.ngames.nclient.mixin;
/*    */ 
/*    */ import com.ngames.nclient.hack.Hacks;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.client.CPacketInput;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({CPacketInput.class})
/*    */ public class MixinCPacketInput
/*    */ {
/*    */   @Shadow
/*    */   private boolean sneaking;
/*    */   
/*    */   @Inject(method = {"writePacketData"}, at = {@At("HEAD")})
/*    */   private void writePacketData(PacketBuffer buf, CallbackInfo info) {
/* 23 */     if (Hacks.sneak.isEnabled() && Hacks.sneak.onlyServer.getValue())
/* 24 */       this.sneaking = true; 
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/mixin/MixinCPacketInput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
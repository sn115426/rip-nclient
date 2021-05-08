/*    */ package com.ngames.nclient.mixin;
/*    */ 
/*    */ import com.ngames.nclient.event.NClientEvent;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ import net.minecraft.network.play.server.SPacketUpdateHealth;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({SPacketUpdateHealth.class})
/*    */ public class SPacketUpdateHealthMixin
/*    */ {
/*    */   @Inject(method = {"processPacket"}, at = {@At("TAIL")}, cancellable = true)
/*    */   private void processPacket(INetHandlerPlayClient handler, CallbackInfo info) {
/* 20 */     if (NClientEvent.callEvent((NClientEvent)new NClientEvent.PlayerHealthChangeEvent()))
/* 21 */       info.cancel(); 
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/mixin/SPacketUpdateHealthMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package com.ngames.nclient.mixin;
/*    */ 
/*    */ import com.ngames.nclient.NClient;
/*    */ import com.ngames.nclient.baritone.Baritone;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.client.CPacketPlayer;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({CPacketPlayer.PositionRotation.class})
/*    */ public class MixinCPacketPositionRotation
/*    */ {
/*    */   @Inject(method = {"writePacketData"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void writePacketData(PacketBuffer buf, CallbackInfo info) {
/* 20 */     if (Baritone.overrideRotation) {
/*    */       
/* 22 */       buf.writeDouble(NClient.MC.player.posX);
/* 23 */       buf.writeDouble((NClient.MC.player.getEntityBoundingBox()).minY);
/* 24 */       buf.writeDouble(NClient.MC.player.posZ);
/* 25 */       buf.writeFloat(Baritone.yaw);
/* 26 */       buf.writeFloat(Baritone.pitch);
/* 27 */       buf.writeByte(NClient.MC.player.onGround ? 1 : 0);
/* 28 */       info.cancel();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/mixin/MixinCPacketPositionRotation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
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
/*    */ @Mixin({CPacketPlayer.Rotation.class})
/*    */ public class MixinCPacketPlayerRotation
/*    */ {
/*    */   @Inject(method = {"writePacketData"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void writePacketData(PacketBuffer buf, CallbackInfo info) {
/* 20 */     if (Baritone.overrideRotation) {
/*    */       
/* 22 */       buf.writeFloat(Baritone.yaw);
/* 23 */       buf.writeFloat(Baritone.pitch);
/* 24 */       buf.writeByte(NClient.MC.player.onGround ? 1 : 0);
/* 25 */       info.cancel();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/mixin/MixinCPacketPlayerRotation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
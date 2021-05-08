/*    */ package com.ngames.nclient.mixin;
/*    */ 
/*    */ import com.ngames.nclient.NClient;
/*    */ import com.ngames.nclient.hack.Hacks;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.server.SPacketPlayerPosLook;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({SPacketPlayerPosLook.class})
/*    */ public class MixinSPacketPlayerPosLook
/*    */ {
/*    */   @Shadow
/*    */   private float yaw;
/*    */   @Shadow
/*    */   private float pitch;
/*    */   
/*    */   @Inject(method = {"readPacketData"}, at = {@At("TAIL")}, cancellable = true)
/*    */   private void readPacketData(PacketBuffer buf, CallbackInfo info) {
/* 26 */     if (Hacks.noRotate.isEnabled()) {
/*    */       
/* 28 */       this.yaw = NClient.MC.player.rotationYaw;
/* 29 */       this.pitch = NClient.MC.player.rotationPitch;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/mixin/MixinSPacketPlayerPosLook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
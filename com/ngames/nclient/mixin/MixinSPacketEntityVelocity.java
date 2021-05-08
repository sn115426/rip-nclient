/*    */ package com.ngames.nclient.mixin;
/*    */ 
/*    */ import com.ngames.nclient.NClient;
/*    */ import com.ngames.nclient.baritone.BUtils;
/*    */ import com.ngames.nclient.hack.Hacks;
/*    */ import com.ngames.nclient.hack.hacks.Velocity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.server.SPacketEntityVelocity;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({SPacketEntityVelocity.class})
/*    */ public class MixinSPacketEntityVelocity
/*    */ {
/*    */   @Shadow
/*    */   private int entityID;
/*    */   @Shadow
/*    */   private int motionX;
/*    */   @Shadow
/*    */   private int motionY;
/*    */   @Shadow
/*    */   private int motionZ;
/*    */   
/*    */   @Inject(method = {"readPacketData"}, at = {@At("TAIL")}, cancellable = true)
/*    */   private void readPacketData(PacketBuffer buf, CallbackInfo info) {
/* 32 */     Velocity hack = Hacks.velocity;
/* 33 */     if (hack.isEnabled() && this.entityID == NClient.MC.player.getEntityId()) {
/*    */       
/* 35 */       this.motionX = (int)(this.motionX * BUtils.randomInRange(hack.horizontalMin.getValue(), hack.horizontalMax.getValue()));
/* 36 */       this.motionY = (int)(this.motionY * BUtils.randomInRange(hack.verticalMin.getValue(), hack.verticalMax.getValue()));
/* 37 */       this.motionZ = (int)(this.motionZ * BUtils.randomInRange(hack.horizontalMin.getValue(), hack.horizontalMax.getValue()));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/mixin/MixinSPacketEntityVelocity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
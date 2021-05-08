/*    */ package com.ngames.nclient.mixin;
/*    */ 
/*    */ import com.ngames.nclient.NClient;
/*    */ import com.ngames.nclient.hack.Hacks;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ import net.minecraft.network.play.server.SPacketDestroyEntities;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({SPacketDestroyEntities.class})
/*    */ public class MixinSPacketDestroyEntities
/*    */ {
/*    */   @Shadow
/*    */   private int[] entityIDs;
/*    */   
/*    */   @Inject(method = {"processPacket"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void processPacket(INetHandlerPlayClient handler, CallbackInfo info) {
/* 28 */     for (int id : this.entityIDs) {
/*    */       
/* 30 */       Entity e = NClient.MC.world.getEntityByID(id);
/* 31 */       if (e instanceof net.minecraft.client.entity.EntityOtherPlayerMP && Hacks.autoEz.isEnabled() && Hacks.autoEz.targeted.contains(Integer.valueOf(id))) {
/*    */         
/* 33 */         NClient.MC.player.sendChatMessage(Hacks.autoEz.message.getValue().replace("{PLAYER}", e.getName()));
/* 34 */         Hacks.autoEz.targeted.remove(Integer.valueOf(id));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/mixin/MixinSPacketDestroyEntities.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
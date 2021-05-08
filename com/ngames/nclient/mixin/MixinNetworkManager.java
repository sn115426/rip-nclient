/*    */ package com.ngames.nclient.mixin;
/*    */ 
/*    */ import com.ngames.nclient.hack.Hacks;
/*    */ import net.minecraft.network.NetworkManager;
/*    */ import net.minecraft.network.Packet;
/*    */ import org.spongepowered.asm.mixin.Mixin;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({NetworkManager.class})
/*    */ public class MixinNetworkManager
/*    */ {
/*    */   @Inject(method = {"sendPacket"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void sendPacket(Packet<?> packetIn, CallbackInfo info) {
/* 30 */     if (Hacks.packetCanceller.isEnabled()) {
/*    */       
/* 32 */       if (packetIn instanceof net.minecraft.network.play.client.CPacketInput && Hacks.packetCanceller.CPacketInput.getValue())
/* 33 */         info.cancel(); 
/* 34 */       if (packetIn instanceof net.minecraft.network.play.client.CPacketPlayer.Position && Hacks.packetCanceller.CPacketPosition.getValue())
/* 35 */         info.cancel(); 
/* 36 */       if (packetIn instanceof net.minecraft.network.play.client.CPacketPlayer.PositionRotation && Hacks.packetCanceller.CPacketPositionRotation.getValue())
/* 37 */         info.cancel(); 
/* 38 */       if (packetIn instanceof net.minecraft.network.play.client.CPacketPlayer.Rotation && Hacks.packetCanceller.CPacketRotation.getValue())
/* 39 */         info.cancel(); 
/* 40 */       if (packetIn instanceof net.minecraft.network.play.client.CPacketPlayerAbilities && Hacks.packetCanceller.CPacketPlayerAbilities.getValue())
/* 41 */         info.cancel(); 
/* 42 */       if (packetIn instanceof net.minecraft.network.play.client.CPacketPlayerDigging && Hacks.packetCanceller.CPacketPlayerDigging.getValue())
/* 43 */         info.cancel(); 
/* 44 */       if (packetIn instanceof net.minecraft.network.play.client.CPacketPlayerTryUseItem && Hacks.packetCanceller.CPacketPlayerTryUseItem.getValue())
/* 45 */         info.cancel(); 
/* 46 */       if (packetIn instanceof net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock && Hacks.packetCanceller.CPacketPlayerTryUseItemOnBlock.getValue())
/* 47 */         info.cancel(); 
/* 48 */       if (packetIn instanceof net.minecraft.network.play.client.CPacketEntityAction && Hacks.packetCanceller.CPacketEntityAction.getValue())
/* 49 */         info.cancel(); 
/* 50 */       if (packetIn instanceof net.minecraft.network.play.client.CPacketUseEntity && Hacks.packetCanceller.CPacketUseEntity.getValue())
/* 51 */         info.cancel(); 
/* 52 */       if (packetIn instanceof net.minecraft.network.play.client.CPacketVehicleMove && Hacks.packetCanceller.CPacketVehicleMove.getValue())
/* 53 */         info.cancel(); 
/* 54 */       if (info.isCancelled())
/* 55 */         Hacks.packetCanceller.inHud.set(Integer.valueOf(((Integer)Hacks.packetCanceller.inHud.get()).intValue() + 1)); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/mixin/MixinNetworkManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
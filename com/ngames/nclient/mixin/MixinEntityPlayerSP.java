/*    */ package com.ngames.nclient.mixin;
/*    */ 
/*    */ import com.ngames.nclient.NClient;
/*    */ import com.ngames.nclient.baritone.Baritone;
/*    */ import com.ngames.nclient.event.NClientEvent;
/*    */ import com.ngames.nclient.hack.Hacks;
/*    */ import net.minecraft.client.entity.EntityPlayerSP;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.client.CPacketEntityAction;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.util.EnumHand;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({EntityPlayerSP.class})
/*    */ public class MixinEntityPlayerSP
/*    */ {
/*    */   @Inject(method = {"swingArm"}, at = {@At("TAIL")}, cancellable = true)
/*    */   private void swingArm(EnumHand hand, CallbackInfo info) {
/* 30 */     if (hand != EnumHand.OFF_HAND && Hacks.criticals.isEnabled() && Hacks.criticals.type.getValue() == 2) {
/*    */       
/* 32 */       NClient.MC.player.swingArm(EnumHand.OFF_HAND);
/* 33 */       info.cancel();
/*    */     } 
/* 35 */     if (NClientEvent.callEvent((NClientEvent)new NClientEvent.PlayerSwingArmEvent())) {
/* 36 */       info.cancel();
/*    */     }
/*    */   }
/*    */   
/*    */   @Inject(method = {"onUpdate"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void onUpdate(CallbackInfo info) {
/* 42 */     if (NClientEvent.callEvent((NClientEvent)new NClientEvent.OnPlayerUpdateEvent())) {
/* 43 */       info.cancel();
/*    */     }
/*    */   }
/*    */   
/*    */   @Inject(method = {"onUpdate"}, at = {@At("TAIL")}, cancellable = true)
/*    */   private void onUpdated(CallbackInfo info) {
/* 49 */     if (NClientEvent.callEvent((NClientEvent)new NClientEvent.OnPlayerUpdatedEvent())) {
/* 50 */       info.cancel();
/*    */     }
/*    */   }
/*    */   
/*    */   @Inject(method = {"isSneaking"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void isSneaking(CallbackInfoReturnable<Boolean> info) {
/* 56 */     if (Hacks.sneak.isEnabled() && Hacks.sneak.onlyServer.getValue()) {
/*    */       
/* 58 */       info.setReturnValue(Boolean.valueOf(true));
/* 59 */       info.cancel();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   @Inject(method = {"onLivingUpdate"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void onLivingUpdate(CallbackInfo info) {
/* 66 */     if (NClientEvent.callEvent((NClientEvent)new NClientEvent.LivingUpdatedEvent())) {
/* 67 */       info.cancel();
/*    */     }
/*    */   }
/*    */   
/*    */   @Inject(method = {"onUpdateWalkingPlayer"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void onUpdateWalkingPlayer(CallbackInfo info) {
/* 73 */     if (Baritone.serverSprintingState != Baritone.serverSprinting && Baritone.overrideSprinting) {
/*    */       
/* 75 */       if (Baritone.serverSprinting) {
/*    */         
/* 77 */         NClient.MC.getConnection().sendPacket((Packet)new CPacketEntityAction((Entity)NClient.MC.player, CPacketEntityAction.Action.START_SPRINTING));
/*    */       }
/*    */       else {
/*    */         
/* 81 */         NClient.MC.getConnection().sendPacket((Packet)new CPacketEntityAction((Entity)NClient.MC.player, CPacketEntityAction.Action.STOP_SPRINTING));
/*    */       } 
/* 83 */       Baritone.serverSprintingState = Baritone.serverSprinting;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   @Inject(method = {"removeActivePotionEffect"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void removeActivePotionEffect(CallbackInfoReturnable<PotionEffect> info) {
/* 90 */     if (NClientEvent.callEvent((NClientEvent)new NClientEvent.PotionEffectRemovedEvent()))
/* 91 */       info.cancel(); 
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/mixin/MixinEntityPlayerSP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
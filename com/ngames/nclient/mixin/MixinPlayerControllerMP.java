/*    */ package com.ngames.nclient.mixin;
/*    */ 
/*    */ import com.ngames.nclient.baritone.Baritone;
/*    */ import com.ngames.nclient.event.NClientEvent;
/*    */ import com.ngames.nclient.hack.Hacks;
/*    */ import net.minecraft.client.multiplayer.PlayerControllerMP;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.inventory.ClickType;
/*    */ import net.minecraft.item.ItemStack;
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
/*    */ @Mixin({PlayerControllerMP.class})
/*    */ public class MixinPlayerControllerMP
/*    */ {
/*    */   @Inject(method = {"getBlockReachDistance"}, at = {@At("TAIL")}, cancellable = true)
/*    */   private void getBlockReachDistance(CallbackInfoReturnable<Float> info) {
/* 27 */     if (Hacks.reach.isEnabled()) {
/* 28 */       info.setReturnValue(Float.valueOf(Hacks.reach.distance.getValue()));
/*    */     }
/*    */   }
/*    */   
/*    */   @Inject(method = {"extendedReach"}, at = {@At("TAIL")}, cancellable = true)
/*    */   private void extendedReach(CallbackInfoReturnable<Boolean> info) {
/* 34 */     if (Hacks.reach.isEnabled()) {
/* 35 */       info.setReturnValue(Boolean.valueOf(true));
/*    */     }
/*    */   }
/*    */   
/*    */   @Inject(method = {"attackEntity"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void attackEntity(EntityPlayer playerIn, Entity targetEntity, CallbackInfo info) {
/* 41 */     if (NClientEvent.callEvent((NClientEvent)new NClientEvent.PlayerAttackedEntityEvent())) {
/* 42 */       info.cancel();
/*    */     }
/*    */   }
/*    */   
/*    */   @Inject(method = {"windowClick"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void windowClick(int windowId, int slotId, int mouseButton, ClickType type, EntityPlayer player, CallbackInfoReturnable<ItemStack> info) {
/* 48 */     if (Hacks.invActionLogger.isEnabled())
/* 49 */       Baritone.displayMessage("windowId: " + windowId + " slotId: " + slotId + " mouseButton: " + mouseButton + " ClickType: " + type.name() + " Player: " + player.getName()); 
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/mixin/MixinPlayerControllerMP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
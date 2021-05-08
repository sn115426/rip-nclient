/*    */ package com.ngames.nclient.mixin;
/*    */ 
/*    */ import com.ngames.nclient.NClient;
/*    */ import com.ngames.nclient.hack.Hacks;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*    */ import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
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
/*    */ @Mixin({ModifiableAttributeInstance.class})
/*    */ public class MixinModifiableAttributeInstance
/*    */ {
/*    */   @Shadow
/*    */   private double baseValue;
/*    */   
/*    */   @Inject(method = {"applyModifier"}, at = {@At("TAIL")}, cancellable = true)
/*    */   private void applyModifier(AttributeModifier modifier, CallbackInfo info) {
/* 29 */     if (Hacks.strafe.isEnabled()) {
/*    */       
/* 31 */       IAttributeInstance iatt = NClient.MC.player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
/* 32 */       if (!iatt.hasModifier(Hacks.strafe.strafe))
/* 33 */         iatt.applyModifier(Hacks.strafe.strafe); 
/* 34 */       iatt.removeModifier(UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097278D"));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   @Inject(method = {"removeModifier"}, at = {@At("TAIL")}, cancellable = true)
/*    */   private void removeModifier(UUID p_188479_1_, CallbackInfo info) {
/* 41 */     if (Hacks.strafe.isEnabled()) {
/*    */       
/* 43 */       IAttributeInstance iatt = NClient.MC.player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
/* 44 */       if (!iatt.hasModifier(Hacks.strafe.strafe))
/* 45 */         iatt.applyModifier(Hacks.strafe.strafe); 
/* 46 */       iatt.removeModifier(UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097278D"));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   @Inject(method = {"removeModifier"}, at = {@At("TAIL")}, cancellable = true)
/*    */   private void removeModifier(AttributeModifier modifier, CallbackInfo info) {
/* 53 */     if (Hacks.strafe.isEnabled()) {
/*    */       
/* 55 */       IAttributeInstance iatt = NClient.MC.player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
/* 56 */       if (!iatt.hasModifier(Hacks.strafe.strafe))
/* 57 */         iatt.applyModifier(Hacks.strafe.strafe); 
/* 58 */       iatt.removeModifier(UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097278D"));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/mixin/MixinModifiableAttributeInstance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
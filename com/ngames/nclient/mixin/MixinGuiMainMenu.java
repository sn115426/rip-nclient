/*    */ package com.ngames.nclient.mixin;
/*    */ 
/*    */ import com.ngames.nclient.gui.Gui;
/*    */ import net.minecraft.client.gui.GuiMainMenu;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({GuiMainMenu.class})
/*    */ public class MixinGuiMainMenu
/*    */ {
/*    */   @Inject(method = {"rotateAndBlurSkybox"}, at = {@At("TAIL")})
/*    */   private void rotateAndBlurSkybox(CallbackInfo info) {
/* 18 */     Gui.drawBackground();
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/mixin/MixinGuiMainMenu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
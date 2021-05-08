/*    */ package com.ngames.nclient.mixin;
/*    */ 
/*    */ import com.ngames.nclient.NClient;
/*    */ import com.ngames.nclient.hack.Hacks;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.gui.GuiNewChat;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({GuiNewChat.class})
/*    */ public class MixinGuiNewChat
/*    */ {
/*    */   @Inject(method = {"addToSentMessages"}, at = {@At("TAIL")})
/*    */   private void addToSentMessages(String message, CallbackInfo info) {
/* 21 */     if (Hacks.chatPrefix.isEnabled() && message.charAt(0) != '/' && message.charAt(0) != '#' && message.charAt(0) != NClient.commandPrefix)
/* 22 */       message = message.substring(Hacks.chatPrefix.prefix.getValue().length()); 
/* 23 */     if (Hacks.chatPostfix.isEnabled() && message.charAt(0) != '/' && message.charAt(0) != '#' && message.charAt(0) != NClient.commandPrefix)
/* 24 */       message = message.substring(0, message.length() - Hacks.chatPostfix.postfix.getValue().length()); 
/* 25 */     List<String> sent = NClient.MC.ingameGUI.getChatGUI().getSentMessages();
/* 26 */     sent.set(sent.size() - 1, message);
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/mixin/MixinGuiNewChat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
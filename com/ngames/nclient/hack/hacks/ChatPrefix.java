/*    */ package com.ngames.nclient.hack.hacks;
/*    */ 
/*    */ import com.ngames.nclient.hack.Category;
/*    */ import com.ngames.nclient.hack.Hack;
/*    */ import com.ngames.nclient.hack.NClientHack;
/*    */ import com.ngames.nclient.hack.settings.SettingString;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @NClientHack(category = Category.CHAT, description = "Add prefix in your messages", name = "ChatPrefix", words = "ChatPrefix prefix ChatModdifications Chat")
/*    */ public class ChatPrefix
/*    */   extends Hack
/*    */ {
/* 15 */   public SettingString prefix = new SettingString("Prefix", " > ", 255);
/*    */ 
/*    */   
/*    */   public ChatPrefix() {
/* 19 */     this.settings = Hack.addSettings(this);
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/hacks/ChatPrefix.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
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
/*    */ @NClientHack(category = Category.CHAT, description = "Add postfix in your messages", name = "ChatPostfix", words = "ChatPostfix Postfix ChatModdifications Chat")
/*    */ public class ChatPostfix
/*    */   extends Hack
/*    */ {
/* 15 */   public SettingString postfix = new SettingString("Postfix", " » ɴ-ᴄʟɪᴇɴᴛ", 255);
/*    */ 
/*    */   
/*    */   public ChatPostfix() {
/* 19 */     this.settings = Hack.addSettings(this);
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/hacks/ChatPostfix.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
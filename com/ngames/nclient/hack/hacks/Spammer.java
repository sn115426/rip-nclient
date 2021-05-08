/*    */ package com.ngames.nclient.hack.hacks;
/*    */ 
/*    */ import com.ngames.nclient.NClient;
/*    */ import com.ngames.nclient.baritone.BUtils;
/*    */ import com.ngames.nclient.baritone.SafeThread;
/*    */ import com.ngames.nclient.hack.Category;
/*    */ import com.ngames.nclient.hack.Hack;
/*    */ import com.ngames.nclient.hack.NClientHack;
/*    */ import com.ngames.nclient.hack.settings.SettingBoolean;
/*    */ import com.ngames.nclient.hack.settings.SettingString;
/*    */ import com.ngames.nclient.hack.settings.SettingValue;
/*    */ import org.apache.commons.lang3.RandomStringUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @NClientHack(category = Category.CHAT, description = "Sending messages in chat", name = "Spammer", words = "Spammer spam chat")
/*    */ public class Spammer
/*    */   extends Hack
/*    */ {
/* 22 */   private final SettingString message = new SettingString("Message", "NClient on top!", 255);
/* 23 */   private final SettingValue delay = new SettingValue("Delay", 3100.0F, 0.0F, 8.64E7F);
/* 24 */   private final SettingBoolean random = new SettingBoolean("Random", false);
/*    */ 
/*    */   
/*    */   public Spammer() {
/* 28 */     this.settings = Hack.addSettings(this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 34 */     onUpdate();
/* 35 */     this.enabled = true;
/* 36 */     (new SafeThread(() -> { NClient.MC.player.sendChatMessage(this.message.getValue() + (this.random.getValue() ? (" [" + RandomStringUtils.random(10, true, true) + "]") : "")); BUtils.sleep((int)this.delay.getValue()); }this))
/*    */ 
/*    */ 
/*    */       
/* 40 */       .start();
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/hacks/Spammer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
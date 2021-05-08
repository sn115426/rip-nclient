/*    */ package com.ngames.nclient.hack.hacks;
/*    */ 
/*    */ import com.ngames.nclient.NClient;
/*    */ import com.ngames.nclient.baritone.Baritone;
/*    */ import com.ngames.nclient.event.Listener;
/*    */ import com.ngames.nclient.event.NClientEvent;
/*    */ import com.ngames.nclient.hack.Category;
/*    */ import com.ngames.nclient.hack.Hack;
/*    */ import com.ngames.nclient.hack.NClientHack;
/*    */ import com.ngames.nclient.hack.settings.SettingBoolean;
/*    */ import java.lang.reflect.Field;
/*    */ import net.minecraft.client.entity.EntityPlayerSP;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @NClientHack(category = Category.MOVEMENT, description = "Automatically press shift (sneaking)", name = "Sneak", words = "Sneak AutoSneak Shift")
/*    */ public class Sneak
/*    */   extends Hack
/*    */ {
/* 25 */   public SettingBoolean onlyServer = new SettingBoolean("OnlyServer", true);
/*    */ 
/*    */   
/*    */   public Sneak() {
/* 29 */     this.settings = Hack.addSettings(this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 35 */     super.onEnable();
/* 36 */     new Listener(NClientEvent.OnPlayerUpdateEvent.class, this);
/* 37 */     new Listener(NClientEvent.PlayerJoinWorldEvent.class, this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onInvoke(NClientEvent event) {
/* 43 */     if (event instanceof NClientEvent.OnPlayerUpdateEvent && !this.onlyServer.getValue())
/* 44 */       Baritone.setSneaking(this.enabled); 
/* 45 */     if (event instanceof NClientEvent.PlayerJoinWorldEvent && this.enabled && this.onlyServer.getValue()) {
/*    */       
/* 47 */       Field f = null;
/*    */       try {
/* 49 */         f = EntityPlayerSP.class.getDeclaredField("field_175170_bN");
/* 50 */       } catch (NoSuchFieldException|SecurityException e) {
/* 51 */         e.printStackTrace();
/*    */       } 
/* 53 */       f.setAccessible(true);
/*    */       try {
/* 55 */         f.setBoolean(NClient.MC.player, false);
/* 56 */       } catch (IllegalArgumentException|IllegalAccessException e) {
/* 57 */         e.printStackTrace();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/hacks/Sneak.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
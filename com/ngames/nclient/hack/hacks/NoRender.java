/*    */ package com.ngames.nclient.hack.hacks;
/*    */ 
/*    */ import com.ngames.nclient.NClient;
/*    */ import com.ngames.nclient.hack.Category;
/*    */ import com.ngames.nclient.hack.Hack;
/*    */ import com.ngames.nclient.hack.NClientHack;
/*    */ import com.ngames.nclient.hack.settings.SettingBoolean;
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @NClientHack(category = Category.RENDER, description = "Disable rending something that you will choose", name = "NoRender", words = "NoRender overlay antioverlay hide")
/*    */ public class NoRender
/*    */   extends Hack
/*    */ {
/* 21 */   public final SettingBoolean item = new SettingBoolean("Item", true);
/* 22 */   public final SettingBoolean entity = new SettingBoolean("Entity", true);
/* 23 */   public final SettingBoolean other = new SettingBoolean("Other", true);
/*    */ 
/*    */   
/*    */   public NoRender() {
/* 27 */     this.settings = Hack.addSettings(this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 33 */     super.onEnable();
/* 34 */     (new Thread(() -> {
/*    */           for (Entity e : NClient.MC.world.getLoadedEntityList()) {
/*    */             if (this.item.getValue() && e instanceof net.minecraft.entity.item.EntityItem) {
/*    */               e.onKillCommand();
/*    */             }
/*    */             if (this.entity.getValue() && e instanceof net.minecraft.entity.EntityLiving && !(e instanceof net.minecraft.client.entity.EntityOtherPlayerMP))
/*    */               e.onKillCommand(); 
/*    */             if (this.other.getValue() && !(e instanceof net.minecraft.client.entity.EntityOtherPlayerMP) && !(e instanceof net.minecraft.entity.EntityLiving) && !(e instanceof net.minecraft.entity.item.EntityItem))
/*    */               e.onKillCommand(); 
/*    */           } 
/* 44 */         })).start();
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/hacks/NoRender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package com.ngames.nclient.hack.hacks;
/*    */ 
/*    */ import com.ngames.nclient.NClient;
/*    */ import com.ngames.nclient.baritone.BUtils;
/*    */ import com.ngames.nclient.hack.Category;
/*    */ import com.ngames.nclient.hack.Hack;
/*    */ import com.ngames.nclient.hack.Hacks;
/*    */ import com.ngames.nclient.hack.NClientHack;
/*    */ import com.ngames.nclient.hack.settings.SettingChoose;
/*    */ import com.ngames.nclient.hack.settings.SettingValue;
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @NClientHack(category = Category.COMBAT, description = "Make your punches critical", name = "Criticals", words = "Criticals damage")
/*    */ public class Criticals
/*    */   extends Hack
/*    */ {
/* 21 */   public SettingChoose type = new SettingChoose("Type", (byte)1, new String[] { "Packet", "MiniJump", "OffHand" });
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 26 */   private SettingValue miniJumpHeigth = new SettingValue("MiniJumpHeight", 0.15F, 0.0F, 2.0F);
/*    */ 
/*    */   
/*    */   public Criticals() {
/* 30 */     this.settings = Hack.addSettings(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean miniJump(Entity target) {
/* 35 */     if (Hacks.criticals.isEnabled() && Hacks.criticals.type.getValue() == 1 && NClient.MC.player.onGround) {
/*    */       
/* 37 */       NClient.MC.player.motionY += Hacks.criticals.miniJumpHeigth.getValue();
/* 38 */       (new Thread(() -> {
/*    */             while (NClient.MC.player.fallDistance <= 0.0F) {
/*    */               BUtils.sleep(1L);
/*    */             }
/*    */             
/*    */             NClient.MC.player.attackTargetEntityWithCurrentItem(target);
/* 44 */           })).start();
/* 45 */       return true;
/*    */     } 
/* 47 */     return false;
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/hacks/Criticals.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package com.ngames.nclient.hack.hacks;
/*    */ 
/*    */ import com.ngames.nclient.hack.Category;
/*    */ import com.ngames.nclient.hack.Hack;
/*    */ import com.ngames.nclient.hack.NClientHack;
/*    */ import com.ngames.nclient.hack.settings.SettingString;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ @NClientHack(category = Category.CHAT, description = "Automatically say EZZ when you kill player", name = "AutoEz", words = "AutoEz KillAura Ez")
/*    */ public class AutoEz
/*    */   extends Hack
/*    */ {
/* 15 */   public SettingString message = new SettingString("Message", "{PLAYER}, you just got EZZZZZ niggered by NClient!", 255);
/*    */   
/* 17 */   public List<Integer> targeted = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public AutoEz() {
/* 21 */     this.settings = Hack.addSettings(this);
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/hacks/AutoEz.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
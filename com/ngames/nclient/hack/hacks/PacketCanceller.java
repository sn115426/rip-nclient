/*    */ package com.ngames.nclient.hack.hacks;
/*    */ 
/*    */ import com.ngames.nclient.hack.Category;
/*    */ import com.ngames.nclient.hack.Hack;
/*    */ import com.ngames.nclient.hack.InHUDValue;
/*    */ import com.ngames.nclient.hack.NClientHack;
/*    */ import com.ngames.nclient.hack.settings.SettingBoolean;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @NClientHack(category = Category.EXPLOIT, description = "Cancel client packets", name = "PacketCanceller", words = "PacketCanceller")
/*    */ public class PacketCanceller
/*    */   extends Hack
/*    */ {
/* 16 */   public final SettingBoolean CPacketInput = new SettingBoolean("CPacketInput", true);
/* 17 */   public final SettingBoolean CPacketPosition = new SettingBoolean("CPacketPosition", true);
/* 18 */   public final SettingBoolean CPacketPositionRotation = new SettingBoolean("CPacketPositionRotation", true);
/* 19 */   public final SettingBoolean CPacketRotation = new SettingBoolean("CPacketRotation", true);
/* 20 */   public final SettingBoolean CPacketPlayerAbilities = new SettingBoolean("CPacketPlayerAbilities", true);
/* 21 */   public final SettingBoolean CPacketPlayerDigging = new SettingBoolean("CPacketPlayerDigging", true);
/* 22 */   public final SettingBoolean CPacketPlayerTryUseItem = new SettingBoolean("CPacketPlayerTryUseItem", true);
/* 23 */   public final SettingBoolean CPacketPlayerTryUseItemOnBlock = new SettingBoolean("CPacketPlayerTryUseItemOnBlock", true);
/* 24 */   public final SettingBoolean CPacketEntityAction = new SettingBoolean("CPacketEntityAction", true);
/* 25 */   public final SettingBoolean CPacketUseEntity = new SettingBoolean("CPacketUseEntity", true);
/* 26 */   public final SettingBoolean CPacketVehicleMove = new SettingBoolean("CPacketVehicleMove", true);
/*    */ 
/*    */   
/*    */   public PacketCanceller() {
/* 30 */     this.settings = Hack.addSettings(this);
/* 31 */     this.inHud = new InHUDValue(Integer.valueOf(0));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onDisable() {
/* 37 */     this.inHud.set(Integer.valueOf(0));
/* 38 */     super.onDisable();
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/hacks/PacketCanceller.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
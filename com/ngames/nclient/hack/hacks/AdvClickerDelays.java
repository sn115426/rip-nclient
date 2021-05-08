/*    */ package com.ngames.nclient.hack.hacks;
/*    */ 
/*    */ import com.ngames.nclient.NClient;
/*    */ import com.ngames.nclient.baritone.BUtils;
/*    */ import com.ngames.nclient.hack.Category;
/*    */ import com.ngames.nclient.hack.Hack;
/*    */ import com.ngames.nclient.hack.NClientHack;
/*    */ import com.ngames.nclient.hack.settings.SettingValue;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @NClientHack(category = Category.COMBAT, description = "Generate click delays for hacks that use advanced click delays", name = "AdvClickerDelays", words = "AdvClickerDelays AutoClicker KillAura18 KillAura19 KillAura")
/*    */ public class AdvClickerDelays
/*    */   extends Hack
/*    */ {
/* 19 */   public final SettingValue ADSpeed = new SettingValue("ADSpeed", 700.0F, 1.0F, 10000.0F);
/* 20 */   public final SettingValue ADValueRange = new SettingValue("ADValueRange", 0.2F, 0.1F, 10.0F);
/* 21 */   public final SettingValue ADMinPhaseSize = new SettingValue("ADMinPhaseSize", 3.0F, 1.0F, 1000000.0F);
/* 22 */   public final SettingValue ADMaxPhaseSize = new SettingValue("ADMaxPhaseSize", 15.0F, 1.0F, 1000000.0F);
/* 23 */   public final SettingValue ADStressHP = new SettingValue("ADStressHP", 4.0F, 1.0F, 20.0F);
/* 24 */   public final SettingValue ADDelayMlMin = new SettingValue("ADDelayMlMin", 1.5F, 0.1F, 100.0F);
/* 25 */   public final SettingValue ADDelayMlMax = new SettingValue("ADDelayMlMax", 2.5F, 0.1F, 100.0F);
/* 26 */   public final SettingValue ADLDelayChanceMin = new SettingValue("ADLDelayChanceMin", 40.0F, 1.0F, 100.0F);
/* 27 */   public final SettingValue ADLDelayChanceMax = new SettingValue("ADLDelayChanceMax", 60.0F, 1.0F, 100.0F);
/*    */   
/*    */   private int phase;
/*    */   
/*    */   private boolean powered;
/*    */   
/*    */   public AdvClickerDelays() {
/* 34 */     this.settings = Hack.addSettings(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Integer> genAdvancedDelayNoise(float CPSMin, float CPSMax) {
/* 39 */     this.phase++;
/* 40 */     this.powered = (NClient.MC.player.getHealth() <= this.ADStressHP.getValue());
/* 41 */     return BUtils.genAdvancedDelayNoise(BUtils.randomInRange(Math.round(CPSMin), Math.round(CPSMax)), this.phase, this.powered, this.ADSpeed.getValue(), this.ADValueRange
/* 42 */         .getValue(), this.ADMinPhaseSize
/* 43 */         .getValue(), this.ADMaxPhaseSize.getValue(), this.ADDelayMlMin.getValue(), this.ADDelayMlMax.getValue(), (short)(int)this.ADLDelayChanceMin.getValue(), (short)(int)this.ADLDelayChanceMax.getValue());
/*    */   }
/*    */   
/*    */   public void onEnable() {}
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/hacks/AdvClickerDelays.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
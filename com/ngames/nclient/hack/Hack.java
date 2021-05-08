/*    */ package com.ngames.nclient.hack;
/*    */ 
/*    */ import com.ngames.nclient.event.NClientEvent;
/*    */ import com.ngames.nclient.fileUtils;
/*    */ import com.ngames.nclient.hack.settings.Setting;
/*    */ import com.ngames.nclient.hack.settings.SettingBoolean;
/*    */ import java.lang.reflect.Field;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ public abstract class Hack
/*    */ {
/*    */   protected boolean enabled;
/* 15 */   public List<Setting> settings = new ArrayList<>();
/*    */   public InHUDValue inHud;
/* 17 */   public final SettingBoolean hidden = new SettingBoolean("Hidden", false);
/*    */ 
/*    */   
/*    */   public Hack() {
/* 21 */     this.settings = addSettings(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onToggle() {
/* 26 */     if (this.enabled) {
/* 27 */       onDisable();
/*    */     } else {
/* 29 */       onEnable();
/* 30 */     }  fileUtils.saveAll();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onDisable() {
/* 35 */     this.enabled = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 40 */     onUpdate();
/* 41 */     this.enabled = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isEnabled() {
/* 46 */     return this.enabled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 51 */     fileUtils.saveAll();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onInvoke(NClientEvent event) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public static List<Setting> addSettings(Hack hack) {
/* 61 */     List<Setting> ret = new ArrayList<>();
/* 62 */     for (Field f : hack.getClass().getDeclaredFields()) {
/*    */       
/* 64 */       if (f.getType().getSuperclass() == Setting.class) {
/*    */         
/* 66 */         f.setAccessible(true);
/*    */         try {
/* 68 */           ret.add((Setting)f.get(hack));
/* 69 */         } catch (IllegalArgumentException|IllegalAccessException e) {
/* 70 */           e.printStackTrace();
/*    */         } 
/*    */       } 
/*    */     } 
/* 74 */     ret.add(hack.hidden);
/* 75 */     return ret;
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/Hack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
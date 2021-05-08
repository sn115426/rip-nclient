/*    */ package com.ngames.nclient;
/*    */ 
/*    */ import com.ngames.nclient.hack.Hack;
/*    */ import com.ngames.nclient.hack.HackUtils;
/*    */ import com.ngames.nclient.hack.settings.Setting;
/*    */ import com.ngames.nclient.hack.settings.SettingBoolean;
/*    */ import com.ngames.nclient.hack.settings.SettingChoose;
/*    */ import com.ngames.nclient.hack.settings.SettingString;
/*    */ import com.ngames.nclient.hack.settings.SettingValue;
/*    */ import com.ngames.nclient.hack.settings.Settings;
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import java.util.regex.Pattern;
/*    */ import org.apache.commons.io.FileUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class fileUtils
/*    */ {
/*    */   public static void saveAll() {
/* 22 */     String output = "";
/* 23 */     for (Hack h : NClient.HackList) {
/*    */       
/* 25 */       output = output + HackUtils.getName(h) + "%D%";
/* 26 */       output = output + String.valueOf(h.isEnabled()) + "%D%";
/* 27 */       for (Setting s : h.settings) {
/*    */         
/* 29 */         output = output + s.name + "%SD%";
/* 30 */         if (s.type == Settings.BOOLEAN) {
/* 31 */           output = output + String.valueOf(((SettingBoolean)s).getValue());
/* 32 */         } else if (s.type == Settings.CHOOSE) {
/* 33 */           output = output + String.valueOf(((SettingChoose)s).getValue());
/* 34 */         } else if (s.type == Settings.STRING_TYPE) {
/* 35 */           output = output + String.valueOf(((SettingString)s).getValue());
/* 36 */         } else if (s.type == Settings.VALUE_TYPE) {
/* 37 */           output = output + String.valueOf(((SettingValue)s).getValue());
/* 38 */         }  output = output + "%D%";
/*    */       } 
/* 40 */       if (output.charAt(output.length() - 1) == '%')
/* 41 */         output = output.substring(0, output.length() - 3); 
/* 42 */       output = output + "\n";
/*    */     } 
/*    */     try {
/* 45 */       FileUtils.write(NClient.settings, output, "UTF-8");
/* 46 */     } catch (IOException e) {
/* 47 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void loadAll() {
/* 53 */     List<String> input = null;
/*    */     try {
/* 55 */       input = FileUtils.readLines(NClient.settings, "UTF-8");
/* 56 */     } catch (IOException e) {
/* 57 */       e.printStackTrace();
/*    */     } 
/* 59 */     if (input == null || input.isEmpty())
/*    */       return; 
/* 61 */     for (String s : input) {
/*    */       
/* 63 */       String[] params = s.split(Pattern.quote("%D%"));
/* 64 */       String name = params[0];
/* 65 */       boolean isEnabled = Boolean.valueOf(params[1]).booleanValue();
/* 66 */       Hack hack = NClient.getHack(name);
/* 67 */       for (int i = 2; i < params.length; i++) {
/*    */         
/* 69 */         String[] setting = params[i].split(Pattern.quote("%SD%"));
/* 70 */         String settingName = setting[0];
/* 71 */         String settingValue = setting[1];
/* 72 */         for (Setting stng : hack.settings) {
/*    */           
/* 74 */           if (stng.name.equals(settingName)) {
/*    */             
/* 76 */             if (stng.type == Settings.BOOLEAN) {
/* 77 */               ((SettingBoolean)stng).setValue(Boolean.valueOf(settingValue).booleanValue()); continue;
/* 78 */             }  if (stng.type == Settings.CHOOSE) {
/* 79 */               ((SettingChoose)stng).setValue(Byte.valueOf(settingValue).byteValue()); continue;
/* 80 */             }  if (stng.type == Settings.STRING_TYPE) {
/* 81 */               ((SettingString)stng).setValue(String.valueOf(settingValue)); continue;
/* 82 */             }  if (stng.type == Settings.VALUE_TYPE)
/* 83 */               ((SettingValue)stng).setValue(Float.valueOf(settingValue).floatValue()); 
/*    */           } 
/*    */         } 
/*    */       } 
/* 87 */       if (!hack.isEnabled() && isEnabled)
/* 88 */         hack.onEnable(); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/fileUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
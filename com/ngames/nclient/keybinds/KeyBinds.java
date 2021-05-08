/*    */ package com.ngames.nclient.keybinds;
/*    */ 
/*    */ import com.ngames.nclient.NClient;
/*    */ import com.ngames.nclient.hack.Hack;
/*    */ import com.ngames.nclient.hack.NClientHack;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.settings.KeyBinding;
/*    */ import net.minecraftforge.fml.client.registry.ClientRegistry;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class KeyBinds
/*    */ {
/*    */   private static final String catergory = "N-Client";
/* 16 */   public static List<KeyBinding> keyBinding = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public static void register() {
/* 20 */     for (Hack h : NClient.HackList)
/*    */     {
/* 22 */       keyBinding.add(new KeyBinding(((NClientHack)h.getClass().<NClientHack>getAnnotation(NClientHack.class)).name(), ((NClientHack)h.getClass().<NClientHack>getAnnotation(NClientHack.class)).keyBind(), "N-Client"));
/*    */     }
/* 24 */     for (KeyBinding kb : keyBinding)
/*    */     {
/* 26 */       setRegister(kb);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   private static void setRegister(KeyBinding binding) {
/* 32 */     ClientRegistry.registerKeyBinding(binding);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void getBind(int keyCode) {
/* 37 */     for (KeyBinding kb : keyBinding) {
/*    */       
/* 39 */       if (kb.getKeyCode() == keyCode)
/*    */       {
/* 41 */         NClient.getHack(kb.getKeyDescription()).onToggle();
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static int getHackKeyBind(String name) {
/* 48 */     int ret = 0;
/* 49 */     for (KeyBinding kb : keyBinding) {
/*    */       
/* 51 */       if (kb.getKeyDescription().equals(name))
/*    */       {
/* 53 */         ret = kb.getKeyCode();
/*    */       }
/*    */     } 
/* 56 */     return ret;
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/keybinds/KeyBinds.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
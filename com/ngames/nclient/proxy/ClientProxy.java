/*    */ package com.ngames.nclient.proxy;
/*    */ 
/*    */ import com.ngames.nclient.keybinds.KeyBinds;
/*    */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*    */ import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
/*    */ import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ClientProxy
/*    */   extends CommonProxy
/*    */ {
/*    */   public void preInit(FMLPreInitializationEvent event) {
/* 14 */     super.preInit(event);
/* 15 */     KeyBinds.register();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void init(FMLInitializationEvent event) {
/* 21 */     super.init(event);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void postInit(FMLPostInitializationEvent event) {
/* 28 */     super.postInit(event);
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/proxy/ClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
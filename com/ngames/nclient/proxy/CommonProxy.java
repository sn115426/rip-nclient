/*    */ package com.ngames.nclient.proxy;
/*    */ 
/*    */ import com.ngames.nclient.EventsHandler;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*    */ import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
/*    */ import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommonProxy
/*    */ {
/*    */   public void preInit(FMLPreInitializationEvent event) {
/* 14 */     MinecraftForge.EVENT_BUS.register(new EventsHandler());
/*    */   }
/*    */   
/*    */   public void init(FMLInitializationEvent event) {}
/*    */   
/*    */   public void postInit(FMLPostInitializationEvent event) {}
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/proxy/CommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
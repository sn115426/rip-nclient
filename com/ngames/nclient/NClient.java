/*     */ package com.ngames.nclient;
/*     */ 
/*     */ import com.ngames.nclient.baritone.BUtils;
/*     */ import com.ngames.nclient.event.Listener;
/*     */ import com.ngames.nclient.gui.Gui;
/*     */ import com.ngames.nclient.gui.Theme;
/*     */ import com.ngames.nclient.gui.font.NFontRenderer;
/*     */ import com.ngames.nclient.hack.Hack;
/*     */ import com.ngames.nclient.hack.Hacks;
/*     */ import com.ngames.nclient.proxy.CommonProxy;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fml.common.Mod;
/*     */ import net.minecraftforge.fml.common.Mod.EventHandler;
/*     */ import net.minecraftforge.fml.common.SidedProxy;
/*     */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*     */ import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
/*     */ import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.lwjgl.opengl.Display;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Mod(modid = "nclient", name = "N-Client", version = "B1", acceptedMinecraftVersions = "[1.12]")
/*     */ public class NClient
/*     */ {
/*     */   public static final String MODID = "nclient";
/*     */   public static final String NAME = "N-Client";
/*     */   public static final String VERSION = "B1";
/*  39 */   public static final Minecraft MC = Minecraft.getMinecraft();
/*  40 */   public static final ResourceLocation background = new ResourceLocation("textures/gui/background.png");
/*  41 */   public static final ResourceLocation blur = new ResourceLocation("textures/gui/blur.png");
/*     */   
/*     */   public static NFontRenderer chatFont;
/*     */   
/*     */   public static Logger logger;
/*     */   
/*     */   public static Gui gui;
/*  48 */   public static Theme theme = new Theme();
/*     */   
/*  50 */   public static File path = MC.gameDir;
/*  51 */   public static File NClientPath = new File(path, "N-Client");
/*  52 */   public static File settings = new File(NClientPath, "Settings.json");
/*     */   
/*     */   @SidedProxy(clientSide = "com.ngames.nclient.proxy.ClientProxy", serverSide = "com.ngames.nclient.proxy.CommonProxy")
/*     */   public static CommonProxy proxy;
/*     */   
/*  57 */   public static char commandPrefix = '.';
/*  58 */   public static HashMap<String, Integer> hacks = new HashMap<>();
/*  59 */   public static List<Hack> HackList = new ArrayList<>();
/*     */   public static boolean inPvP = false;
/*     */   public static boolean isLeftPressed = false;
/*  62 */   public static List<Listener> listeners = new ArrayList<>();
/*     */   
/*  64 */   public static List<Long> c = new ArrayList<>();
/*  65 */   public static long t = 0L;
/*     */   
/*     */   public static byte tps;
/*     */   public static byte ticks;
/*     */   
/*     */   @EventHandler
/*     */   public void preInit(FMLPreInitializationEvent event) {
/*  72 */     logger = event.getModLog();
/*  73 */     HackList = Hacks.initL();
/*  74 */     hacks = Hacks.init();
/*  75 */     proxy.preInit(event);
/*  76 */     if (!NClientPath.exists())
/*  77 */       NClientPath.mkdir(); 
/*     */     try {
/*  79 */       if (!settings.exists())
/*  80 */         settings.createNewFile(); 
/*  81 */     } catch (IOException e) {
/*  82 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void init(FMLInitializationEvent event) {
/*  89 */     proxy.init(event);
/*  90 */     c.add(Long.valueOf(0L));
/*  91 */     (new Thread(() -> {
/*     */           while (true) {
/*     */             t++;
/*     */             
/*     */             BUtils.sleep(1L);
/*     */           } 
/*  97 */         })).start();
/*  98 */     (new Thread(() -> {
/*     */           while (true) {
/*     */             tps = ticks;
/*     */             
/*     */             ticks = 0;
/*     */             
/*     */             BUtils.sleep(1000L);
/*     */           } 
/* 106 */         })).start();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void postInit(FMLPostInitializationEvent event) {
/* 116 */     Display.setTitle("N-Client B1");
/* 117 */     proxy.postInit(event);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Hack getHack(String name) {
/* 122 */     return HackList.get(((Integer)hacks.get(name)).intValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isHackExist(String name) {
/* 127 */     boolean ret = false;
/* 128 */     if (hacks.containsKey(name))
/*     */     {
/* 130 */       ret = true;
/*     */     }
/* 132 */     return ret;
/*     */   }
/*     */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/NClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
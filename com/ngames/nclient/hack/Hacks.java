/*     */ package com.ngames.nclient.hack;
/*     */ 
/*     */ import com.ngames.nclient.NClient;
/*     */ import com.ngames.nclient.hack.hacks.AdvClickerDelays;
/*     */ import com.ngames.nclient.hack.hacks.AimAssist;
/*     */ import com.ngames.nclient.hack.hacks.AutoArmor;
/*     */ import com.ngames.nclient.hack.hacks.AutoClicker;
/*     */ import com.ngames.nclient.hack.hacks.AutoDupe;
/*     */ import com.ngames.nclient.hack.hacks.AutoEat;
/*     */ import com.ngames.nclient.hack.hacks.AutoEz;
/*     */ import com.ngames.nclient.hack.hacks.AutoGapple;
/*     */ import com.ngames.nclient.hack.hacks.AutoJump;
/*     */ import com.ngames.nclient.hack.hacks.AutoRelog;
/*     */ import com.ngames.nclient.hack.hacks.AutoSoup;
/*     */ import com.ngames.nclient.hack.hacks.AutoSpin;
/*     */ import com.ngames.nclient.hack.hacks.AutoSprint;
/*     */ import com.ngames.nclient.hack.hacks.AutoTotem;
/*     */ import com.ngames.nclient.hack.hacks.CPSCount;
/*     */ import com.ngames.nclient.hack.hacks.ChatPostfix;
/*     */ import com.ngames.nclient.hack.hacks.ChatPrefix;
/*     */ import com.ngames.nclient.hack.hacks.ClickGUI;
/*     */ import com.ngames.nclient.hack.hacks.Criticals;
/*     */ import com.ngames.nclient.hack.hacks.EntityControl;
/*     */ import com.ngames.nclient.hack.hacks.HUD;
/*     */ import com.ngames.nclient.hack.hacks.HighJump;
/*     */ import com.ngames.nclient.hack.hacks.InvActionLogger;
/*     */ import com.ngames.nclient.hack.hacks.ItemSpammer;
/*     */ import com.ngames.nclient.hack.hacks.KillAura18;
/*     */ import com.ngames.nclient.hack.hacks.LongJump;
/*     */ import com.ngames.nclient.hack.hacks.LookingForward;
/*     */ import com.ngames.nclient.hack.hacks.NoRender;
/*     */ import com.ngames.nclient.hack.hacks.NoRotate;
/*     */ import com.ngames.nclient.hack.hacks.PacketCanceller;
/*     */ import com.ngames.nclient.hack.hacks.Reach;
/*     */ import com.ngames.nclient.hack.hacks.Sneak;
/*     */ import com.ngames.nclient.hack.hacks.Spammer;
/*     */ import com.ngames.nclient.hack.hacks.Speed;
/*     */ import com.ngames.nclient.hack.hacks.Strafe;
/*     */ import com.ngames.nclient.hack.hacks.Timer;
/*     */ import com.ngames.nclient.hack.hacks.Velocity;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ public class Hacks
/*     */ {
/*  49 */   public static final AdvClickerDelays advClickerDelays = new AdvClickerDelays();
/*  50 */   public static final AimAssist aimAssist = new AimAssist();
/*  51 */   public static final AutoArmor autoArmor = new AutoArmor();
/*  52 */   public static final AutoClicker autoClicker = new AutoClicker();
/*  53 */   public static final AutoDupe autoDupe = new AutoDupe();
/*  54 */   public static final AutoEat autoEat = new AutoEat();
/*  55 */   public static final AutoEz autoEz = new AutoEz();
/*  56 */   public static final AutoGapple autoGapple = new AutoGapple();
/*  57 */   public static final AutoJump autoJump = new AutoJump();
/*  58 */   public static final AutoRelog autoRelog = new AutoRelog();
/*  59 */   public static final AutoSoup autoSoup = new AutoSoup();
/*  60 */   public static final AutoSpin autoSpin = new AutoSpin();
/*  61 */   public static final AutoSprint autoSprint = new AutoSprint();
/*  62 */   public static final AutoTotem autoTotem = new AutoTotem();
/*  63 */   public static final ChatPostfix chatPostfix = new ChatPostfix();
/*  64 */   public static final ChatPrefix chatPrefix = new ChatPrefix();
/*  65 */   public static final ClickGUI clickGUI = new ClickGUI();
/*  66 */   public static final CPSCount cpsCount = new CPSCount();
/*  67 */   public static final Criticals criticals = new Criticals();
/*  68 */   public static final EntityControl entityControl = new EntityControl();
/*  69 */   public static final HighJump highJump = new HighJump();
/*  70 */   public static final HUD hud = new HUD();
/*  71 */   public static final InvActionLogger invActionLogger = new InvActionLogger();
/*  72 */   public static final ItemSpammer itemSpammer = new ItemSpammer();
/*  73 */   public static final KillAura18 killAura18 = new KillAura18();
/*  74 */   public static final LongJump longJump = new LongJump();
/*  75 */   public static final LookingForward lookingForward = new LookingForward();
/*  76 */   public static final NoRender noRender = new NoRender();
/*  77 */   public static final PacketCanceller packetCanceller = new PacketCanceller();
/*  78 */   public static final NoRotate noRotate = new NoRotate();
/*  79 */   public static final Reach reach = new Reach();
/*  80 */   public static final Sneak sneak = new Sneak();
/*  81 */   public static final Spammer spammer = new Spammer();
/*  82 */   public static final Speed speed = new Speed();
/*  83 */   public static final Strafe strafe = new Strafe();
/*  84 */   public static final Timer timer = new Timer();
/*  85 */   public static final Velocity velocity = new Velocity();
/*     */ 
/*     */   
/*     */   public static HashMap<String, Integer> init() {
/*  89 */     HashMap<String, Integer> hacks = new HashMap<>();
/*  90 */     int i = 0;
/*  91 */     for (Hack h : NClient.HackList) {
/*     */       
/*  93 */       hacks.put(((NClientHack)h.getClass().<NClientHack>getAnnotation(NClientHack.class)).name(), Integer.valueOf(i));
/*  94 */       i++;
/*     */     } 
/*  96 */     return hacks;
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<Hack> initL() {
/* 101 */     List<Hack> ret = new ArrayList<>();
/* 102 */     for (Field hackField : Hacks.class.getDeclaredFields()) {
/*     */       
/* 104 */       if (hackField.getType().getSuperclass() == Hack.class) {
/*     */         
/*     */         try {
/* 107 */           ret.add((Hack)hackField.get(null));
/* 108 */         } catch (IllegalArgumentException|IllegalAccessException e) {
/* 109 */           e.printStackTrace();
/*     */         } 
/*     */       }
/*     */     } 
/* 113 */     return ret;
/*     */   }
/*     */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/Hacks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package com.ngames.nclient;
/*     */ 
/*     */ import com.ngames.nclient.baritone.BUtils;
/*     */ import com.ngames.nclient.baritone.Baritone;
/*     */ import com.ngames.nclient.event.NClientEvent;
/*     */ import com.ngames.nclient.gui.Gui;
/*     */ import com.ngames.nclient.gui.Hud;
/*     */ import com.ngames.nclient.hack.Hacks;
/*     */ import com.ngames.nclient.hack.hacks.Criticals;
/*     */ import com.ngames.nclient.hack.hacks.NoRender;
/*     */ import com.ngames.nclient.keybinds.KeyBinds;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraftforge.client.event.ClientChatEvent;
/*     */ import net.minecraftforge.client.event.GuiScreenEvent;
/*     */ import net.minecraftforge.client.event.RenderGameOverlayEvent;
/*     */ import net.minecraftforge.client.event.RenderLivingEvent;
/*     */ import net.minecraftforge.event.entity.EntityJoinWorldEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingAttackEvent;
/*     */ import net.minecraftforge.event.entity.player.AttackEntityEvent;
/*     */ import net.minecraftforge.event.entity.player.CriticalHitEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.Event;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.gameevent.InputEvent;
/*     */ import net.minecraftforge.fml.common.gameevent.TickEvent;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.lwjgl.input.Mouse;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EventsHandler
/*     */ {
/*     */   @SubscribeEvent
/*     */   public void onJoin(EntityJoinWorldEvent event) {
/*  47 */     Entity e = event.getEntity();
/*  48 */     if (e instanceof net.minecraft.client.entity.EntityPlayerSP) {
/*     */       
/*  50 */       fileUtils.loadAll();
/*  51 */       if (NClientEvent.callEvent((NClientEvent)new NClientEvent.PlayerJoinWorldEvent()))
/*  52 */         event.setCanceled(true); 
/*     */     } 
/*  54 */     if (Hacks.noRender.isEnabled()) {
/*     */       
/*  56 */       NoRender nr = Hacks.noRender;
/*  57 */       if (nr.item.getValue() && e instanceof net.minecraft.entity.item.EntityItem)
/*  58 */         e.onKillCommand(); 
/*  59 */       if (nr.entity.getValue() && e instanceof net.minecraft.entity.EntityLiving && !(e instanceof net.minecraft.client.entity.EntityOtherPlayerMP))
/*  60 */         e.onKillCommand(); 
/*  61 */       if (nr.other.getValue() && !(e instanceof net.minecraft.client.entity.EntityOtherPlayerMP) && !(e instanceof net.minecraft.entity.EntityLiving) && !(e instanceof net.minecraft.entity.item.EntityItem)) {
/*  62 */         e.onKillCommand();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onCommand(ClientChatEvent event) {
/*  69 */     if (event.getMessage().charAt(0) == NClient.commandPrefix);
/*     */     
/*  71 */     CommandExecutor.onCommand(event.getMessage());
/*     */     
/*  73 */     char first = event.getMessage().charAt(0);
/*  74 */     if (Hacks.chatPrefix.isEnabled() && first != '/' && first != NClient.commandPrefix && first != '#')
/*  75 */       event.setMessage(Hacks.chatPrefix.prefix.getValue() + event.getMessage()); 
/*  76 */     if (Hacks.chatPostfix.isEnabled() && first != '/' && first != NClient.commandPrefix && first != '#') {
/*  77 */       event.setMessage(event.getMessage() + Hacks.chatPostfix.postfix.getValue());
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onTick(TickEvent.WorldTickEvent event) {
/*  83 */     NClient.ticks = (byte)(NClient.ticks + 1);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onKeyPress(InputEvent.KeyInputEvent event) {
/*  89 */     if (NClient.MC.world != null && !(NClient.MC.currentScreen instanceof net.minecraft.client.gui.GuiChat) && !Keyboard.getEventKeyState())
/*     */     {
/*  91 */       if (Gui.justPressed == true) {
/*     */         
/*  93 */         if (Keyboard.getEventKey() != KeyBinds.getHackKeyBind("ClickGUI"))
/*     */         {
/*  95 */           KeyBinds.getBind(Keyboard.getEventKey());
/*     */         }
/*  97 */         Gui.justPressed = false;
/*     */       } else {
/*  99 */         KeyBinds.getBind(Keyboard.getEventKey());
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onCritical(CriticalHitEvent event) {
/* 107 */     if (Hacks.criticals.isEnabled() && Hacks.criticals.type.getValue() == 0) {
/* 108 */       event.setResult(Event.Result.ALLOW);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onRenderGameOverlay(RenderGameOverlayEvent event) {
/* 114 */     if (!event.isCancelable() && event.getType() == RenderGameOverlayEvent.ElementType.HELMET) {
/*     */       
/* 116 */       if (Hacks.hud.isEnabled())
/*     */       {
/* 118 */         Hud.drawHUD();
/*     */       }
/* 120 */       if (NClient.gui != null && Hacks.noRender.isEnabled())
/*     */       {
/* 122 */         NClient.gui.drawScreen(Gui.mouseX, Gui.mouseY, NClient.MC.getRenderPartialTicks());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onRenderEntity(RenderLivingEvent.Pre<?> event) {}
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onMouseClick(InputEvent.MouseInputEvent event) {
/* 135 */     if (!Mouse.getEventButtonState() && Mouse.getEventButton() == 0) {
/* 136 */       NClient.c.add(Long.valueOf(NClient.t));
/* 137 */       Hacks.cpsCount.clicks++;
/* 138 */       NClient.isLeftPressed = false;
/* 139 */     } else if (Mouse.getEventButton() == 0) {
/* 140 */       NClient.isLeftPressed = true;
/* 141 */     }  if (Mouse.getEventButtonState() && (NClient.MC.gameSettings.keyBindUseItem.isKeyDown() || (NClient.MC.gameSettings.keyBindAttack.isKeyDown() && NClient.MC.objectMouseOver.typeOfHit != RayTraceResult.Type.MISS)) && (
/* 142 */       Baritone.isInMainHand(Items.ENDER_PEARL) || Baritone.isInMainHand((Item)Items.BOW) || Baritone.isInMainHand(Items.SNOWBALL)) && Baritone.overrideRotation) {
/*     */       
/* 144 */       Baritone.prevYaw = Baritone.yaw;
/* 145 */       Baritone.prevPitch = Baritone.pitch;
/* 146 */       Baritone.yaw = NClient.MC.player.rotationYaw;
/* 147 */       Baritone.pitch = NClient.MC.player.rotationPitch;
/* 148 */       Baritone.needRotate = true;
/*     */     } 
/*     */     
/* 151 */     if (!Mouse.getEventButtonState() && (!NClient.MC.gameSettings.keyBindUseItem.isKeyDown() || (
/* 152 */       !Baritone.isInMainHand(Items.ENDER_PEARL) && !Baritone.isInMainHand((Item)Items.BOW) && !Baritone.isInMainHand(Items.SNOWBALL))) && (
/* 153 */       !NClient.MC.gameSettings.keyBindAttack.isKeyDown() || NClient.MC.objectMouseOver.typeOfHit == RayTraceResult.Type.MISS) && Baritone.needRotate && Baritone.overrideRotation) {
/*     */ 
/*     */       
/* 156 */       Baritone.yaw = Baritone.prevYaw;
/* 157 */       Baritone.pitch = Baritone.prevPitch;
/* 158 */       Baritone.needRotate = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onPvP(AttackEntityEvent event) {
/* 165 */     if (event.getEntity() instanceof net.minecraft.client.entity.EntityPlayerSP) {
/*     */       
/* 167 */       NClient.inPvP = true;
/* 168 */       (new Thread(() -> {
/*     */             BUtils.sleep(5000L);
/*     */             NClient.inPvP = false;
/* 171 */           })).start();
/* 172 */       if (!Hacks.autoEz.targeted.contains(Integer.valueOf(event.getTarget().getEntityId())))
/* 173 */         Hacks.autoEz.targeted.add(Integer.valueOf(event.getTarget().getEntityId())); 
/* 174 */       event.setCanceled(Criticals.miniJump(event.getTarget()));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onHurt(LivingAttackEvent event) {
/* 181 */     if (event.getEntity() instanceof net.minecraft.client.entity.EntityPlayerSP) {
/*     */       
/* 183 */       NClient.inPvP = true;
/* 184 */       (new Thread(() -> {
/*     */             BUtils.sleep(5000L);
/*     */             NClient.inPvP = false;
/* 187 */           })).start();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onBackgroundDrawn(GuiScreenEvent.BackgroundDrawnEvent event) {
/* 194 */     if (event.getGui() instanceof net.minecraft.client.gui.GuiIngameMenu || NClient.MC.world != null)
/*     */       return; 
/* 196 */     Gui.drawBackground();
/*     */   }
/*     */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/EventsHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
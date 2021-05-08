/*     */ package com.ngames.nclient.hack.hacks;
/*     */ 
/*     */ import com.ngames.nclient.NClient;
/*     */ import com.ngames.nclient.baritone.BUtils;
/*     */ import com.ngames.nclient.baritone.Baritone;
/*     */ import com.ngames.nclient.baritone.SafeThread;
/*     */ import com.ngames.nclient.event.Listener;
/*     */ import com.ngames.nclient.event.NClientEvent;
/*     */ import com.ngames.nclient.hack.Category;
/*     */ import com.ngames.nclient.hack.Hack;
/*     */ import com.ngames.nclient.hack.Hacks;
/*     */ import com.ngames.nclient.hack.NClientHack;
/*     */ import com.ngames.nclient.hack.settings.SettingBoolean;
/*     */ import com.ngames.nclient.hack.settings.SettingChoose;
/*     */ import com.ngames.nclient.hack.settings.SettingValue;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.Vec3d;
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
/*     */ @NClientHack(category = Category.COMBAT, description = "Automatically attack players", name = "KillAura18", words = "KillAura18 KillAura TriggerBot AutoClicker")
/*     */ public class KillAura18
/*     */   extends Hack
/*     */ {
/*  41 */   private final SettingChoose DelayType = new SettingChoose("DelayType", (byte)2, new String[] { "simple", "random", "advanced" });
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   private final SettingChoose ClickType = new SettingChoose("ClickType", (byte)3, new String[] { "ClickMouse", "AAC", "target", "AACTarget" });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  52 */   private final SettingBoolean AACTCheckEntityType = new SettingBoolean("AACTCheckEntityType", true);
/*  53 */   private final SettingValue CPSMin = new SettingValue("CPSMin", 17.0F, 1.0F, 100.0F);
/*  54 */   private final SettingValue CPSMax = new SettingValue("CPSMax", 21.0F, 1.0F, 100.0F);
/*  55 */   private final SettingValue Range = new SettingValue("Range", 6.0F, 1.0F, 10.0F);
/*  56 */   private final SettingBoolean TickSync = new SettingBoolean("TickSync", true);
/*  57 */   private final SettingBoolean RotationSync = new SettingBoolean("RotationSync", true);
/*  58 */   private final SettingValue CheckDelay = new SettingValue("CheckDelay", 10.0F, 0.0F, 1000.0F);
/*  59 */   private final SettingBoolean multipleTarget = new SettingBoolean("MultipleTarget", true);
/*  60 */   private final SettingChoose priority = new SettingChoose("Priority", (byte)0, new String[] { "health", "distance", "multi" });
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   private final SettingValue STargetAttacks = new SettingValue("STargetAttacks", 2.0F, 1.0F, 100.0F);
/*  66 */   private final SettingBoolean rayTracing = new SettingBoolean("RayTracing", true);
/*  67 */   private final SettingBoolean rtOnlyNotMO = new SettingBoolean("RTOnlyNotMO", true);
/*  68 */   private final SettingBoolean clientRotations = new SettingBoolean("ClientRotations", true);
/*  69 */   private final SettingChoose autoBlock = new SettingChoose("AutoBlock", (byte)0, new String[] { "off", "legit", "packet" });
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  74 */   private final SettingBoolean attackMobs = new SettingBoolean("AttackMobs", false);
/*  75 */   private final SettingBoolean attackPlayers = new SettingBoolean("AttackPlayers", true);
/*     */   
/*     */   public Entity target;
/*     */   private Entity botTarget;
/*     */   public boolean targeted = false;
/*     */   private boolean waitClick;
/*  81 */   private List<Entity> multiTargets = new ArrayList<>();
/*     */   int k;
/*  83 */   int m = 1;
/*     */   boolean rtr;
/*  85 */   short rotationPriority = 10;
/*     */ 
/*     */   
/*     */   public KillAura18() {
/*  89 */     this.settings = Hack.addSettings(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEnable() {
/*  95 */     super.onEnable();
/*  96 */     new Listener(NClientEvent.RunTickKeyboardEvent.class, this);
/*  97 */     new Listener(NClientEvent.OnPlayerUpdateEvent.class, this);
/*  98 */     new Listener(NClientEvent.LivingUpdatedEvent.class, this);
/*  99 */     (new SafeThread(() -> { List<Integer> delays = new ArrayList<>(); if (this.DelayType.getValue() == 0) { delays = BUtils.genSimpleDelays(this.CPSMax.getValue()); } else if (this.DelayType.getValue() == 1) { delays = BUtils.genDelayNoise(BUtils.randomInRange(Math.round(this.CPSMin.getValue()), Math.round(this.CPSMax.getValue()))); } else if (this.DelayType.getValue() == 2) { delays = Hacks.advClickerDelays.genAdvancedDelayNoise(this.CPSMin.getValue(), this.CPSMax.getValue()); }  Iterator<Integer> iterator = delays.iterator(); while (iterator.hasNext()) { int delay = ((Integer)iterator.next()).intValue(); if (this.enabled) { if (!this.RotationSync.getValue()) getTarget();  if (((this.RotationSync.getValue() && this.rtr) || (mouseOver() && this.rtOnlyNotMO.getValue()) || rayTrace()) && canBeAttacked(this.target) && !Baritone.clickSync) { if (this.TickSync.getValue()) { if (this.autoBlock.getValue() == 1) Baritone.usedItem();  if (!NClient.MC.player.isHandActive() && Baritone.getLeftClickCounter() <= 0) this.waitClick = true;  } else { attack(); }  this.targeted = true; } else { this.targeted = false; }  BUtils.sleep(delay, BUtils.randomInRange(0, 999999)); }  }  if (!this.targeted) BUtils.sleep((int)this.CheckDelay.getValue());  }this))
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 134 */       .start();
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean rayTrace() {
/* 139 */     boolean rayTraceResult = false;
/* 140 */     rayTraceResult = BUtils.rayTraceBlocks(NClient.MC.player.getPositionEyes(NClient.MC.getRenderPartialTicks()), new Vec3d(this.target.posX + this.target.width * 0.5D, this.target.posY + this.target.height * 0.5D, this.target.posZ + this.target.width * 0.5D));
/*     */     
/* 142 */     if (!this.rayTracing.getValue())
/* 143 */       rayTraceResult = true; 
/* 144 */     if (rayTraceResult) {
/* 145 */       Baritone.setRotationToEntity(this.target, !this.clientRotations.getValue());
/* 146 */       if (!this.clientRotations.getValue()) {
/*     */         
/* 148 */         Baritone.updateRotation();
/* 149 */         Baritone.overrideRotation = true;
/*     */       } 
/* 151 */       return true;
/*     */     } 
/*     */     
/* 154 */     for (int i = 0; i < 10; i++) {
/*     */       
/* 156 */       rayTraceResult = BUtils.rayTraceBlocks(NClient.MC.player.getPositionEyes(NClient.MC.getRenderPartialTicks()), new Vec3d(this.target.posX + this.target.width * 0.5D, this.target.posY + this.target.height * 0.1D * i, this.target.posZ + this.target.width * 0.5D));
/*     */       
/* 158 */       if (rayTraceResult) {
/*     */         
/* 160 */         Baritone.setRotationToEntity(this.target, (i / 10), !this.clientRotations.getValue());
/* 161 */         if (!this.clientRotations.getValue()) {
/*     */           
/* 163 */           Baritone.updateRotation();
/* 164 */           Baritone.overrideRotation = true;
/*     */         } 
/*     */         break;
/*     */       } 
/*     */     } 
/* 169 */     if (!rayTraceResult) {
/*     */       
/* 171 */       Baritone.setRotationPriority((short)0);
/* 172 */       Baritone.refreshRotation();
/*     */     } 
/* 174 */     return rayTraceResult;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onInvoke(NClientEvent event) {
/* 180 */     if (this.enabled) {
/*     */       
/* 182 */       if (event instanceof NClientEvent.RunTickKeyboardEvent && this.waitClick && canBeAttacked(this.target)) {
/*     */         
/* 184 */         attack();
/* 185 */         this.waitClick = false;
/*     */       } 
/* 187 */       if (event instanceof NClientEvent.OnPlayerUpdateEvent && this.RotationSync.getValue()) {
/*     */         
/* 189 */         Baritone.calcRotationPriority(this.rotationPriority);
/* 190 */         getTarget();
/* 191 */         if (this.target != null && canBeAttacked(this.target))
/* 192 */           this.rtr = ((mouseOver() && this.rtOnlyNotMO.getValue()) || rayTrace()); 
/*     */       } 
/* 194 */       if (event instanceof NClientEvent.LivingUpdatedEvent && !this.clientRotations.getValue()) {
/* 195 */         fixMovement();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void attack() {
/* 201 */     if (this.autoBlock.getValue() == 2)
/* 202 */       NClient.MC.getConnection().sendPacket((Packet)new CPacketPlayerTryUseItem(EnumHand.OFF_HAND)); 
/* 203 */     if (this.ClickType.getValue() == 0) {
/* 204 */       Baritone.leftClickMouse();
/* 205 */     } else if (this.ClickType.getValue() == 1) {
/* 206 */       Baritone.attackEntity();
/* 207 */     } else if (this.ClickType.getValue() == 2) {
/* 208 */       Baritone.attackEntity(this.target);
/* 209 */     } else if (this.ClickType.getValue() == 3) {
/* 210 */       Baritone.attackEntity((this.botTarget != null) ? this.botTarget : this.target);
/* 211 */     }  if (this.autoBlock.getValue() == 1)
/* 212 */       Baritone.useItem(); 
/* 213 */     Baritone.setRotationPriority((short)0);
/*     */   }
/*     */ 
/*     */   
/*     */   private void getTarget() {
/* 218 */     if (this.m == this.STargetAttacks.getValue() || this.priority.getValue() != 2 || !canBeAttacked(this.target)) {
/*     */       
/* 220 */       if (this.priority.getValue() < 2) {
/*     */         
/* 222 */         this.target = Baritone.getPriorityTarget(this.Range.getValue(), this.attackPlayers.getValue(), this.attackMobs
/* 223 */             .getValue(), !this.multipleTarget.getValue(), this.target, this.priority.getValue());
/*     */       } else {
/* 225 */         if (this.k >= this.multiTargets.size() || this.multiTargets.isEmpty()) {
/*     */           
/* 227 */           this.multiTargets = Baritone.getMultiTargets(this.Range.getValue(), this.attackPlayers.getValue(), this.attackMobs.getValue());
/* 228 */           this.k = 0;
/*     */         } 
/* 230 */         for (int i = 0; i < this.multiTargets.size();) {
/*     */           
/* 232 */           if (this.enabled && !this.multiTargets.isEmpty()) {
/*     */             
/* 234 */             if (canBeAttacked(this.multiTargets.get(i))) {
/*     */               
/* 236 */               this.target = this.multiTargets.get(i);
/*     */               break;
/*     */             } 
/*     */             i++;
/*     */           } 
/*     */         } 
/* 242 */         if (!this.multiTargets.isEmpty() && this.multiTargets.get(this.k) != null && canBeAttacked(this.multiTargets.get(this.k)))
/* 243 */           this.target = this.multiTargets.get(this.k); 
/* 244 */         this.k++;
/*     */       } 
/* 246 */       if (NClient.MC.world != null && this.target != null)
/*     */       {
/* 248 */         if (this.ClickType.getValue() == 3)
/* 249 */           this.botTarget = Baritone.getBotTarget(this.target, this.AACTCheckEntityType.getValue()); 
/*     */       }
/*     */     } 
/* 252 */     if (this.priority.getValue() == 2) {
/*     */       
/* 254 */       this.m++;
/* 255 */       if (this.m > this.STargetAttacks.getValue()) {
/* 256 */         this.m = 1;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean canBeAttacked(Entity e) {
/* 262 */     return (e != null && Baritone.isAlive(e) && NClient.MC.player.getDistance(e) <= this.Range.getValue() && ((e instanceof net.minecraft.client.entity.EntityOtherPlayerMP && this.attackPlayers
/* 263 */       .getValue()) || (e instanceof net.minecraft.entity.EntityLiving && this.attackMobs.getValue())));
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean mouseOver() {
/* 268 */     return (NClient.MC.objectMouseOver.entityHit == this.target || (this.ClickType.getValue() == 3 && NClient.MC.objectMouseOver.entityHit == this.botTarget) || (this.ClickType
/* 269 */       .getValue() == 1 && NClient.MC.objectMouseOver.entityHit != null));
/*     */   }
/*     */ 
/*     */   
/*     */   private void fixMovement() {
/* 274 */     boolean fow = NClient.MC.gameSettings.keyBindForward.isKeyDown();
/* 275 */     boolean jump = NClient.MC.gameSettings.keyBindJump.isKeyDown();
/* 276 */     if (fow && NClient.MC.player.isSprinting() && (NClient.MC.player.rotationYaw <= Baritone.yaw - 30.0F || NClient.MC.player.rotationYaw >= Baritone.yaw + 30.0F)) {
/* 277 */       NClient.MC.player.setSprinting(false);
/*     */     } else {
/* 279 */       NClient.MC.player.setSprinting(true);
/* 280 */     }  if (jump && NClient.MC.player.isSprinting() && Baritone.getJumpTicks() == 0) {
/*     */       
/* 282 */       float f = NClient.MC.player.rotationYaw * 0.017453292F;
/* 283 */       NClient.MC.player.motionX += (MathHelper.sin(f) * 0.2F);
/* 284 */       NClient.MC.player.motionZ -= (MathHelper.cos(f) * 0.2F);
/*     */     } 
/* 286 */     BUtils.Direction cdir = BUtils.getDirection(false, Baritone.yaw);
/* 287 */     BUtils.Direction sdir = BUtils.getDirection(false);
/* 288 */     float cyaw = NClient.MC.player.rotationYaw;
/* 289 */     float syaw = Baritone.yaw;
/* 290 */     if (cdir != sdir) {
/*     */       
/* 292 */       boolean moveFow = (syaw > cyaw - 30.0F && syaw < cyaw + 30.0F);
/* 293 */       boolean moveBack = (syaw < cyaw - 120.0F || syaw > cyaw + 120.0F);
/* 294 */       boolean moveLeft = (syaw < cyaw - 30.0F);
/* 295 */       boolean moveRight = (syaw > cyaw + 30.0F);
/* 296 */       if (moveFow) {
/*     */         
/* 298 */         NClient.MC.player.moveForward = 1.0F;
/* 299 */         NClient.MC.player.movementInput.moveForward = 1.0F;
/*     */       }
/*     */       else {
/*     */         
/* 303 */         NClient.MC.player.moveForward = 0.0F;
/* 304 */         NClient.MC.player.movementInput.moveForward = 0.0F;
/*     */       } 
/* 306 */       if (moveBack) {
/*     */         
/* 308 */         NClient.MC.player.moveForward = -1.0F;
/* 309 */         NClient.MC.player.movementInput.moveForward = -1.0F;
/*     */       } 
/* 311 */       if (moveLeft) {
/*     */         
/* 313 */         NClient.MC.player.moveStrafing = -1.0F;
/* 314 */         NClient.MC.player.movementInput.moveStrafe = -1.0F;
/*     */       } 
/* 316 */       if (moveRight) {
/*     */         
/* 318 */         NClient.MC.player.moveStrafing = 1.0F;
/* 319 */         NClient.MC.player.movementInput.moveStrafe = 1.0F;
/*     */       } 
/* 321 */       if (!moveRight && !moveLeft) {
/*     */         
/* 323 */         NClient.MC.player.moveStrafing = 0.0F;
/* 324 */         NClient.MC.player.movementInput.moveStrafe = 0.0F;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 332 */     Baritone.overrideRotation = !this.clientRotations.getValue();
/* 333 */     super.onUpdate();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDisable() {
/* 339 */     if (!this.clientRotations.getValue())
/* 340 */       Baritone.overrideRotation = false; 
/* 341 */     super.onDisable();
/*     */   }
/*     */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/hacks/KillAura18.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package com.ngames.nclient.hack.hacks;
/*     */ 
/*     */ import com.ngames.nclient.NClient;
/*     */ import com.ngames.nclient.baritone.BUtils;
/*     */ import com.ngames.nclient.baritone.Baritone;
/*     */ import com.ngames.nclient.event.Listener;
/*     */ import com.ngames.nclient.event.NClientEvent;
/*     */ import com.ngames.nclient.hack.Category;
/*     */ import com.ngames.nclient.hack.Hack;
/*     */ import com.ngames.nclient.hack.NClientHack;
/*     */ import com.ngames.nclient.hack.settings.SettingChoose;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @NClientHack(category = Category.MOVEMENT, description = "Change your strafe speed", name = "Strafe", words = "Strafe Speed Sprint")
/*     */ public class Strafe
/*     */   extends Hack
/*     */ {
/*  30 */   public SettingChoose mode = new SettingChoose("Mode", (byte)1, new String[] { "simple", "slient" });
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  35 */   private int leftTicks = 0;
/*  36 */   private int rightTicks = 0;
/*  37 */   private int fowTicks = 0;
/*  38 */   private int backTicks = 0;
/*  39 */   public AttributeModifier strafe = (new AttributeModifier(UUID.randomUUID(), "Strafe  NClient", 0.30000001192092896D, 2)).setSaved(false);
/*  40 */   private short rotationPriority = 2;
/*     */ 
/*     */   
/*     */   public Strafe() {
/*  44 */     this.settings = addSettings(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEnable() {
/*  50 */     super.onEnable();
/*  51 */     new Listener(NClientEvent.LivingUpdatedEvent.class, this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDisable() {
/*  57 */     Baritone.overrideRotation = false;
/*  58 */     NClient.MC.player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier(this.strafe);
/*  59 */     Baritone.setRotationPriority((short)0);
/*  60 */     super.onDisable();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onInvoke(NClientEvent event) {
/*  66 */     boolean flag = (this.mode.getValue() == 1);
/*  67 */     boolean flag2 = false;
/*  68 */     float yaw = NClient.MC.player.rotationYaw;
/*  69 */     boolean fow = NClient.MC.gameSettings.keyBindForward.isKeyDown();
/*  70 */     boolean back = NClient.MC.gameSettings.keyBindBack.isKeyDown();
/*  71 */     boolean left = NClient.MC.gameSettings.keyBindLeft.isKeyDown();
/*  72 */     boolean right = NClient.MC.gameSettings.keyBindRight.isKeyDown();
/*  73 */     this.rightTicks = right ? ++this.rightTicks : 0;
/*  74 */     this.leftTicks = left ? ++this.leftTicks : 0;
/*  75 */     this.fowTicks = fow ? ++this.fowTicks : 0;
/*  76 */     this.backTicks = back ? ++this.backTicks : 0;
/*  77 */     int latest = Math.min(Math.min((this.fowTicks == 0) ? Integer.MAX_VALUE : this.fowTicks, (this.backTicks == 0) ? Integer.MAX_VALUE : this.backTicks), 
/*  78 */         Math.min((this.leftTicks == 0) ? Integer.MAX_VALUE : this.leftTicks, (this.rightTicks == 0) ? Integer.MAX_VALUE : this.rightTicks));
/*  79 */     if (fow || left || back || right) {
/*     */       
/*  81 */       if (back && this.backTicks == latest)
/*  82 */         yaw += 180.0F; 
/*  83 */       if (right && this.rightTicks == latest)
/*  84 */         yaw += 90.0F; 
/*  85 */       if (left && this.leftTicks == latest)
/*  86 */         yaw -= 90.0F; 
/*  87 */       yaw = BUtils.toMinecraftDegrees(yaw);
/*  88 */       flag2 = true;
/*     */     } 
/*  90 */     if (flag2 && !fow && NClient.MC.player.onGround && Baritone.getJumpTicks() == 0 && NClient.MC.gameSettings.keyBindJump.isKeyDown()) {
/*     */       
/*  92 */       float f = yaw * 0.017453292F;
/*  93 */       NClient.MC.player.motionX -= (MathHelper.sin(f) * 0.2F);
/*  94 */       NClient.MC.player.motionZ += (MathHelper.cos(f) * 0.2F);
/*  95 */       this.rotationPriority = 11;
/*     */     } else {
/*     */       
/*  98 */       this.rotationPriority = 2;
/*     */     } 
/* 100 */     boolean rotate = Baritone.calcRotationPriority(this.rotationPriority);
/* 101 */     if (flag && yaw != Baritone.yaw && rotate) {
/*     */       
/* 103 */       Baritone.yaw = yaw;
/* 104 */       Baritone.overrideRotation = this.enabled;
/* 105 */       Baritone.updateRotation();
/*     */     } else {
/* 107 */       Baritone.setRotationPriority((short)0);
/* 108 */     }  if (rotate)
/* 109 */       Baritone.pitch = NClient.MC.player.rotationPitch; 
/* 110 */     float input = Math.max(Math.abs(NClient.MC.player.moveStrafing), Math.abs(NClient.MC.player.moveForward));
/* 111 */     if (!NClient.MC.player.isSprinting())
/* 112 */       NClient.MC.player.setSprinting(true); 
/* 113 */     if (flag) {
/*     */       
/* 115 */       NClient.MC.player.moveForward = input;
/* 116 */       NClient.MC.player.moveStrafing = 0.0F;
/*     */     } 
/*     */     
/* 119 */     if (isEnabled() && (rotate || NClient.MC.player.rotationYaw == yaw)) {
/*     */       
/* 121 */       IAttributeInstance iatt = NClient.MC.player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
/* 122 */       if (!iatt.hasModifier(this.strafe))
/* 123 */         iatt.applyModifier(this.strafe); 
/* 124 */       iatt.removeModifier(UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097278D"));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/hacks/Strafe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
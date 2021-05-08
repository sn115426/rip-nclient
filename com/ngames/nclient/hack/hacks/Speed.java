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
/*     */ import net.minecraft.client.settings.KeyBinding;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.util.Timer;
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
/*     */ 
/*     */ @NClientHack(category = Category.MOVEMENT, description = "several ways to moving quickly", name = "Speed", words = "Speed SpeedHack AutoJump")
/*     */ public class Speed
/*     */   extends Hack
/*     */ {
/*  33 */   public final SettingChoose type = new SettingChoose("Type", (byte)0, new String[] { "Legit", "test0.1", "advanced" });
/*     */   
/*  35 */   private final AttributeModifier entitySpeed = (new AttributeModifier(UUID.randomUUID(), "Speed NClient", 0.33000000011920927D, 2)).setSaved(false);
/*  36 */   private int leftTicks = 0;
/*  37 */   private int rightTicks = 0;
/*  38 */   private int fowTicks = 0;
/*  39 */   private int backTicks = 0;
/*     */ 
/*     */   
/*     */   public Speed() {
/*  43 */     this.settings = Hack.addSettings(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEnable() {
/*  49 */     super.onEnable();
/*  50 */     new Listener(NClientEvent.OnPlayerUpdateEvent.class, this);
/*  51 */     new Listener(NClientEvent.PlayerJumpEvent.class, this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDisable() {
/*  57 */     Baritone.overrideRotation = false;
/*  58 */     NClient.MC.player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier(this.entitySpeed);
/*  59 */     super.onDisable();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  65 */     super.onUpdate();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onInvoke(NClientEvent event) {
/*  71 */     if (event instanceof NClientEvent.PlayerJumpEvent && this.type.getValue() == 2) {
/*     */       
/*  73 */       event.setCalceled();
/*     */       return;
/*     */     } 
/*  76 */     switch (this.type.getValue()) {
/*     */       
/*     */       case 0:
/*  79 */         handleType0();
/*     */       case 1:
/*  81 */         handleType1();
/*     */       case 2:
/*  83 */         handleType2();
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void handleType0() {
/*  89 */     if (Baritone.isMoving()) {
/*     */       
/*  91 */       KeyBinding.setKeyBindState(NClient.MC.gameSettings.keyBindJump.getKeyCode(), true);
/*  92 */       if (NClient.MC.player.moveForward != 0.0F)
/*  93 */         NClient.MC.player.setSprinting(true); 
/*     */     } else {
/*  95 */       KeyBinding.setKeyBindState(NClient.MC.gameSettings.keyBindJump.getKeyCode(), false);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void handleType1() {
/* 100 */     boolean fow = NClient.MC.gameSettings.keyBindForward.isKeyDown();
/* 101 */     boolean back = NClient.MC.gameSettings.keyBindBack.isKeyDown();
/* 102 */     boolean left = NClient.MC.gameSettings.keyBindLeft.isKeyDown();
/* 103 */     boolean right = NClient.MC.gameSettings.keyBindRight.isKeyDown();
/* 104 */     if ((fow || left || back || right) && 
/* 105 */       NClient.MC.player.onGround) {
/*     */       
/* 107 */       float yaw = NClient.MC.player.rotationYaw;
/* 108 */       if (back)
/* 109 */         yaw += 180.0F; 
/* 110 */       if (right)
/* 111 */         yaw += 90.0F; 
/* 112 */       if (left)
/* 113 */         yaw -= 90.0F; 
/* 114 */       if (fow && (right || left))
/* 115 */         yaw = right ? (yaw - 45.0F) : (yaw + 45.0F); 
/* 116 */       yaw = BUtils.toMinecraftDegrees(yaw);
/* 117 */       float f = yaw * 0.017453292F;
/* 118 */       NClient.MC.player.movementInput.jump = true;
/* 119 */       NClient.MC.player.motionY = 0.41999998688697815D;
/* 120 */       NClient.MC.player.motionX -= (MathHelper.sin(f) * 0.2F);
/* 121 */       NClient.MC.player.motionZ += (MathHelper.cos(f) * 0.2F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void handleType2() {
/* 127 */     boolean flag2 = false;
/* 128 */     float yaw = NClient.MC.player.rotationYaw;
/* 129 */     boolean fow = NClient.MC.gameSettings.keyBindForward.isKeyDown();
/* 130 */     boolean back = NClient.MC.gameSettings.keyBindBack.isKeyDown();
/* 131 */     boolean left = NClient.MC.gameSettings.keyBindLeft.isKeyDown();
/* 132 */     boolean right = NClient.MC.gameSettings.keyBindRight.isKeyDown();
/* 133 */     this.rightTicks = right ? ++this.rightTicks : 0;
/* 134 */     this.leftTicks = left ? ++this.leftTicks : 0;
/* 135 */     this.fowTicks = fow ? ++this.fowTicks : 0;
/* 136 */     this.backTicks = back ? ++this.backTicks : 0;
/* 137 */     int latest = Math.min(Math.min((this.fowTicks == 0) ? Integer.MAX_VALUE : this.fowTicks, (this.backTicks == 0) ? Integer.MAX_VALUE : this.backTicks), 
/* 138 */         Math.min((this.leftTicks == 0) ? Integer.MAX_VALUE : this.leftTicks, (this.rightTicks == 0) ? Integer.MAX_VALUE : this.rightTicks));
/* 139 */     if (fow || left || back || right) {
/*     */       
/* 141 */       if (back && this.backTicks == latest)
/* 142 */         yaw += 180.0F; 
/* 143 */       if (right && this.rightTicks == latest)
/* 144 */         yaw += 90.0F; 
/* 145 */       if (left && this.leftTicks == latest)
/* 146 */         yaw -= 90.0F; 
/* 147 */       yaw = BUtils.toMinecraftDegrees(yaw);
/* 148 */       flag2 = true;
/*     */     } 
/* 150 */     Baritone.yaw = yaw;
/* 151 */     Baritone.overrideRotation = this.enabled;
/* 152 */     Baritone.updateRotation();
/* 153 */     float input = Math.max(Math.abs(NClient.MC.player.moveStrafing), Math.abs(NClient.MC.player.moveForward));
/* 154 */     if (!NClient.MC.player.isSprinting() && flag2)
/* 155 */       NClient.MC.player.setSprinting(true); 
/* 156 */     NClient.MC.player.moveForward = input;
/* 157 */     NClient.MC.player.moveStrafing = 0.0F;
/* 158 */     if (fow)
/* 159 */       NClient.MC.player.movementInput.moveForward = 1.0F; 
/* 160 */     if (back)
/* 161 */       NClient.MC.player.movementInput.moveForward = -1.0F; 
/* 162 */     if (left)
/* 163 */       NClient.MC.player.movementInput.moveStrafe = -1.0F; 
/* 164 */     if (right)
/* 165 */       NClient.MC.player.movementInput.moveForward = 1.0F; 
/* 166 */     NClient.MC.player.movementInput.jump = true;
/* 167 */     float f = yaw * 0.017453292F;
/* 168 */     if (flag2 && NClient.MC.player.onGround) {
/*     */       
/* 170 */       NClient.MC.player.motionX -= (MathHelper.sin(f) * 0.23F);
/* 171 */       NClient.MC.player.motionY = 0.42D;
/* 172 */       Baritone.setTimer(new Timer(20.0F));
/* 173 */       NClient.MC.player.motionZ += (MathHelper.cos(f) * 0.23F);
/*     */     } else {
/*     */       
/* 176 */       Baritone.setTimer(new Timer(20.3F));
/*     */     } 
/* 178 */     if (isEnabled()) {
/*     */       
/* 180 */       IAttributeInstance iatt = NClient.MC.player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
/* 181 */       if (!iatt.hasModifier(this.entitySpeed))
/* 182 */         iatt.applyModifier(this.entitySpeed); 
/* 183 */       iatt.removeModifier(UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097278D"));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/hacks/Speed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
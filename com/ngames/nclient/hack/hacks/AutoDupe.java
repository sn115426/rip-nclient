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
/*     */ import com.ngames.nclient.hack.InHUDValue;
/*     */ import com.ngames.nclient.hack.NClientHack;
/*     */ import com.ngames.nclient.hack.settings.SettingValue;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.BlockLever;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.settings.KeyBinding;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityMinecartEmpty;
/*     */ import net.minecraft.entity.passive.EntityDonkey;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.inventory.ClickType;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.util.math.Vec3i;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @NClientHack(category = Category.EXPLOIT, description = "Automatically dupe items", name = "AutoDupe", words = "AutoDupe Dupe")
/*     */ public class AutoDupe
/*     */   extends Hack
/*     */ {
/*  41 */   private final SettingValue stealDelay = new SettingValue("StealDelay", 100.0F, 0.0F, 1000.0F);
/*  42 */   private final SettingValue loadDonkeyAttemps = new SettingValue("loadDonkeyAttemps", 10.0F, 1.0F, 1000.0F);
/*  43 */   private final SettingValue loadDonkeyClickDelay = new SettingValue("loadDonkeyClickDelay", 5000.0F, 1000.0F, 20000.0F);
/*     */   
/*     */   private Vec3i startPos;
/*     */   private boolean waitF3;
/*  47 */   private Minecraft MC = NClient.MC;
/*     */   
/*     */   private BlockPos lever1;
/*     */   private BlockPos lever2;
/*     */   private BlockPos chest;
/*     */   private Entity lastMinecart;
/*     */   private Entity preLastMinecart;
/*     */   private boolean isStarted;
/*     */   
/*     */   public AutoDupe() {
/*  57 */     this.settings = Hack.addSettings(this);
/*  58 */     this.inHud = new InHUDValue(Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEnable() {
/*  64 */     super.onEnable();
/*  65 */     new Listener(NClientEvent.RunTickKeyboardEvent.class, this);
/*  66 */     Vec3d v = this.MC.player.getPositionVector();
/*  67 */     this.startPos = new Vec3i(v.x, v.y, v.z);
/*  68 */     (new Thread(() -> {
/*     */           start();
/*     */           this.isStarted = true;
/*  71 */         })).start();
/*  72 */     (new SafeThread(() -> { if (!this.isStarted) { BUtils.sleep(100L); } else { cycle(); }  }() -> { while (this.MC.world == null) BUtils.sleep(50L);  BUtils.sleep(3000L); stop(); start(); }this))
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
/*  84 */       .start();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onInvoke(NClientEvent event) {
/*  90 */     if (this.waitF3) {
/*     */       
/*  92 */       Baritone.processKeyF3(30);
/*  93 */       this.waitF3 = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void start() {
/*  99 */     List<Entity> except = new ArrayList<>();
/* 100 */     int count = 0;
/* 101 */     Hacks.packetCanceller.CPacketEntityAction.setValue(false);
/* 102 */     Hacks.packetCanceller.CPacketInput.setValue(true);
/* 103 */     Hacks.packetCanceller.CPacketPlayerAbilities.setValue(false);
/* 104 */     Hacks.packetCanceller.CPacketPlayerDigging.setValue(true);
/* 105 */     Hacks.packetCanceller.CPacketPlayerTryUseItem.setValue(false);
/* 106 */     Hacks.packetCanceller.CPacketPlayerTryUseItemOnBlock.setValue(false);
/* 107 */     Hacks.packetCanceller.CPacketPosition.setValue(true);
/* 108 */     Hacks.packetCanceller.CPacketPositionRotation.setValue(true);
/* 109 */     Hacks.packetCanceller.CPacketRotation.setValue(true);
/* 110 */     Hacks.packetCanceller.CPacketUseEntity.setValue(false);
/* 111 */     Hacks.packetCanceller.CPacketVehicleMove.setValue(true);
/* 112 */     if (!Hacks.entityControl.isEnabled())
/* 113 */       Hacks.entityControl.onToggle(); 
/* 114 */     if (!Hacks.packetCanceller.isEnabled())
/* 115 */       Hacks.packetCanceller.onToggle(); 
/* 116 */     Entity prevMinecart = null;
/* 117 */     while (count < 25 && this.enabled && this.MC.world != null) {
/*     */       
/* 119 */       Entity minecart = Baritone.findEntity(6.0F, EntityMinecartEmpty.class, except);
/* 120 */       Entity prev = this.MC.player.getRidingEntity();
/* 121 */       if (minecart == null && this.enabled) {
/*     */         
/* 123 */         Baritone.displayMessage("There aren't minecarts near you.");
/* 124 */         while (minecart == null && this.MC.world != null) {
/*     */           
/* 126 */           minecart = Baritone.findEntity(6.0F, EntityMinecartEmpty.class, except);
/* 127 */           BUtils.sleep(500L);
/*     */         } 
/*     */       } 
/* 130 */       Baritone.setRotationToEntity(minecart, 0.3F);
/* 131 */       if (prev != null) {
/*     */         
/* 133 */         int to1 = 160;
/* 134 */         while (this.MC.player.getRidingEntity() == prev && this.MC.world != null && this.enabled) {
/*     */           
/* 136 */           BUtils.sleep(50L);
/* 137 */           Baritone.interactWithEntity(minecart);
/* 138 */           to1--;
/* 139 */           if (to1 == 0) {
/*     */             
/* 141 */             while (this.MC.player.getRidingEntity() != prevMinecart && this.MC.world != null && this.enabled) {
/*     */               
/* 143 */               Baritone.interactWithEntity(prevMinecart);
/* 144 */               BUtils.sleep(100L);
/*     */             } 
/* 146 */             while (this.MC.player.getRidingEntity() != prev && this.MC.world != null && this.enabled) {
/*     */               
/* 148 */               Baritone.interactWithEntity(prev);
/* 149 */               BUtils.sleep(100L);
/*     */             } 
/* 151 */             to1 = 160;
/*     */           } 
/*     */         } 
/*     */       } else {
/*     */         
/* 156 */         while (this.MC.player.getRidingEntity() == null && this.MC.world != null) {
/*     */           
/* 158 */           BUtils.sleep(50L);
/* 159 */           Baritone.interactWithEntity(minecart);
/*     */         } 
/*     */       } 
/* 162 */       if (count == 14) {
/*     */         
/* 164 */         this.waitF3 = true;
/* 165 */         while (this.waitF3 && this.MC.world != null)
/* 166 */           BUtils.sleep(50L); 
/*     */       } 
/* 168 */       if (count == 23)
/* 169 */         this.preLastMinecart = minecart; 
/* 170 */       if (count == 24)
/* 171 */         this.lastMinecart = minecart; 
/* 172 */       except.add(minecart);
/* 173 */       prevMinecart = prev;
/* 174 */       count++;
/* 175 */       BUtils.sleep(50L);
/*     */     } 
/* 177 */     Entity donkey = Baritone.findEntity(6.0F, EntityDonkey.class);
/* 178 */     int to = 80;
/* 179 */     while (this.MC.player.getRidingEntity() != donkey && this.MC.world != null) {
/*     */       
/* 181 */       Baritone.setRotationToEntity(donkey, 0.1F);
/* 182 */       BUtils.sleep(50L);
/* 183 */       Baritone.interactWithEntity(donkey);
/* 184 */       to--;
/* 185 */       if (to == 0) {
/*     */         
/* 187 */         stop();
/* 188 */         start();
/*     */         return;
/*     */       } 
/*     */     } 
/* 192 */     getPos();
/*     */   }
/*     */ 
/*     */   
/*     */   private void getPos() {
/* 197 */     List<BlockPos> blocks = Baritone.findBlocks(Blocks.LEVER);
/* 198 */     for (int i = 0; i < blocks.size(); i++) {
/*     */       
/* 200 */       BlockPos block = blocks.get(i);
/* 201 */       if (block.getX() == (int)this.preLastMinecart.posX || block.getZ() == (int)this.preLastMinecart.posZ) {
/* 202 */         this.lever2 = block;
/* 203 */       } else if (block.getX() == (int)this.lastMinecart.posX - 1 || block.getX() == (int)this.lastMinecart.posX + 1) {
/* 204 */         this.lever1 = block;
/*     */       } 
/* 206 */     }  this.chest = new BlockPos(this.lever1.getX(), this.lever1.getY() - 2, this.lever1.getZ());
/*     */   }
/*     */ 
/*     */   
/*     */   private void stop() {
/* 211 */     stop1();
/* 212 */     stop2();
/*     */   }
/*     */ 
/*     */   
/*     */   private void stop1() {
/* 217 */     if (Hacks.entityControl.isEnabled())
/* 218 */       Hacks.entityControl.onToggle(); 
/* 219 */     if (Hacks.packetCanceller.isEnabled())
/* 220 */       Hacks.packetCanceller.onToggle(); 
/* 221 */     int to2 = 100;
/* 222 */     while (this.MC.player.getRidingEntity() != null && this.MC.world != null && this.enabled) {
/*     */       
/* 224 */       KeyBinding.setKeyBindState(this.MC.gameSettings.keyBindSneak.getKeyCode(), true);
/* 225 */       BUtils.sleep(50L);
/* 226 */       to2--;
/* 227 */       if (to2 == 0) {
/*     */         
/* 229 */         KeyBinding.setKeyBindState(this.MC.gameSettings.keyBindSneak.getKeyCode(), false);
/* 230 */         BUtils.sleep(50L);
/* 231 */         Baritone.setRotationToBlockPos(this.lever1);
/* 232 */         BUtils.sleep(100L);
/* 233 */         Baritone.rightClickBlock(this.lever1, EnumHand.MAIN_HAND);
/* 234 */         for (Entity e : this.MC.world.getLoadedEntityList()) {
/*     */           
/* 236 */           if (e instanceof EntityDonkey && e != this.MC.player.getRidingEntity()) {
/*     */             
/* 238 */             Baritone.setRotationToEntity(e);
/* 239 */             Baritone.interactWithEntity(e);
/*     */           } 
/*     */         } 
/* 242 */         BUtils.sleep(2000L);
/* 243 */         Baritone.setRotationToBlockPos(this.lever1);
/* 244 */         BUtils.sleep(100L);
/* 245 */         Baritone.rightClickBlock(this.lever1, EnumHand.MAIN_HAND);
/* 246 */         to2 = 100;
/*     */       } 
/*     */     } 
/* 249 */     KeyBinding.setKeyBindState(this.MC.gameSettings.keyBindSneak.getKeyCode(), false);
/*     */   }
/*     */ 
/*     */   
/*     */   private void stop2() {
/* 254 */     if (!Hacks.timer.isEnabled())
/* 255 */       Hacks.timer.onToggle(); 
/* 256 */     this.MC.player.sendChatMessage("#goto " + this.startPos.getX() + " " + this.startPos.getY() + " " + this.startPos.getZ());
/* 257 */     BUtils.sleep(3000L);
/* 258 */     while ((int)this.MC.player.posX != this.startPos.getX() && (int)this.MC.player.posY != this.startPos.getY() && (int)this.MC.player.posZ != (int)this.MC.player.posZ && this.enabled && this.MC.world != null) {
/*     */ 
/*     */       
/* 261 */       BUtils.sleep(2000L);
/* 262 */       this.MC.player.sendChatMessage("#goto " + this.startPos.getX() + " " + this.startPos.getY() + " " + this.startPos.getZ());
/*     */     } 
/* 264 */     BUtils.sleep(2000L);
/* 265 */     if (Hacks.timer.isEnabled()) {
/* 266 */       Hacks.timer.onToggle();
/*     */     }
/*     */   }
/*     */   
/*     */   private void lootDonkey() {
/* 271 */     for (int i = 1; i <= 16; i++) {
/*     */       
/* 273 */       BUtils.sleep((int)this.stealDelay.getValue());
/* 274 */       this.MC.playerController.windowClick(this.MC.player.openContainer.windowId, i, 0, ClickType.QUICK_MOVE, (EntityPlayer)this.MC.player);
/* 275 */       if (!this.enabled) {
/*     */         break;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void fillChest() {
/* 282 */     while (this.MC.player.openContainer.inventorySlots.size() < 54 && this.enabled && this.MC.world != null)
/* 283 */       BUtils.sleep(50L); 
/* 284 */     for (int i = 54; i <= 89; i++) {
/*     */       
/* 286 */       if (((Slot)this.MC.player.openContainer.inventorySlots.get(i)).getHasStack()) {
/*     */         
/* 288 */         BUtils.sleep((int)this.stealDelay.getValue());
/* 289 */         this.MC.playerController.windowClick(this.MC.player.openContainer.windowId, i, 0, ClickType.QUICK_MOVE, (EntityPlayer)this.MC.player);
/* 290 */         this.inHud.set(Integer.valueOf(((Integer)this.inHud.get()).intValue() + 1));
/*     */       } 
/* 292 */       if (!this.enabled) {
/*     */         break;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void fillDonkey() {
/* 299 */     for (int i = 17; i <= 52; i++) {
/*     */       
/* 301 */       boolean isFull = true;
/* 302 */       for (int k = 2; k <= 16; k++) {
/*     */         
/* 304 */         if (!((Slot)this.MC.player.openContainer.inventorySlots.get(k)).getHasStack())
/* 305 */           isFull = false; 
/*     */       } 
/* 307 */       if (isFull)
/*     */         return; 
/* 309 */       if (((Slot)this.MC.player.openContainer.inventorySlots.get(i)).getHasStack()) {
/*     */         
/* 311 */         BUtils.sleep((int)this.stealDelay.getValue());
/* 312 */         this.MC.playerController.windowClick(this.MC.player.openContainer.windowId, i, 0, ClickType.QUICK_MOVE, (EntityPlayer)this.MC.player);
/*     */       } 
/* 314 */       if (!this.enabled) {
/*     */         return;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void openInv() {
/* 321 */     this.MC.player.sendHorseInventory();
/* 322 */     int to = 200;
/* 323 */     while (this.MC.inGameHasFocus) {
/*     */       
/* 325 */       BUtils.sleep(50L);
/* 326 */       to--;
/* 327 */       if (to == 0) {
/*     */         
/* 329 */         int to2 = 50;
/* 330 */         while (this.MC.player.getRidingEntity() != this.lastMinecart) {
/*     */           
/* 332 */           Baritone.setRotationToEntity(this.lastMinecart);
/* 333 */           Baritone.interactWithEntity(this.lastMinecart);
/* 334 */           BUtils.sleep(100L);
/* 335 */           to2--;
/* 336 */           if (to2 == 0) {
/*     */             
/* 338 */             toggleLever1(false);
/* 339 */             toggleLever2(false);
/* 340 */             stop();
/* 341 */             start();
/*     */             return;
/*     */           } 
/*     */         } 
/* 345 */         this.MC.player.sendHorseInventory();
/* 346 */         int to3 = 100;
/* 347 */         while (this.MC.inGameHasFocus) {
/*     */           
/* 349 */           BUtils.sleep(50L);
/* 350 */           to3--;
/* 351 */           if (to3 == 0) {
/*     */             
/* 353 */             toggleLever1(false);
/* 354 */             toggleLever2(false);
/* 355 */             stop();
/* 356 */             start();
/*     */             return;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void cycle() {
/* 366 */     if (this.lever1 == null || this.lever2 == null || this.chest == null) {
/*     */       
/* 368 */       getPos();
/* 369 */       if (this.lever1 == null)
/* 370 */         Baritone.displayMessage("failed find: lever1"); 
/* 371 */       if (this.chest == null)
/* 372 */         Baritone.displayMessage("failed find: chest"); 
/* 373 */       if (this.lever2 == null)
/* 374 */         Baritone.displayMessage("failed find: lever2"); 
/*     */     } 
/* 376 */     toggleLever1(true);
/* 377 */     BUtils.sleep((int)this.loadDonkeyClickDelay.getValue());
/* 378 */     toggleLever2(true);
/* 379 */     BUtils.sleep(1000L);
/* 380 */     Entity donkey = null;
/* 381 */     int to1 = (int)this.loadDonkeyAttemps.getValue();
/* 382 */     while (!(donkey instanceof EntityDonkey) || (donkey == null && this.enabled)) {
/*     */       
/* 384 */       boolean currentState = this.MC.world.getBlockState(this.lever1).equals(this.MC.world.getBlockState(this.lever1).withProperty((IProperty)BlockLever.POWERED, Boolean.valueOf(true)));
/* 385 */       toggleLever1(!currentState);
/* 386 */       if (currentState) {
/* 387 */         BUtils.sleep(1000L);
/*     */       } else {
/* 389 */         BUtils.sleep((int)this.loadDonkeyClickDelay.getValue());
/* 390 */       }  Baritone.setRotationToBlockPos(this.chest);
/* 391 */       BUtils.sleep(100L);
/* 392 */       donkey = NClient.MC.objectMouseOver.entityHit;
/* 393 */       to1--;
/* 394 */       if (to1 == 0) {
/*     */         
/* 396 */         this.isStarted = false;
/* 397 */         toggleLever2(false);
/* 398 */         toggleLever1(false);
/* 399 */         stop();
/* 400 */         start();
/* 401 */         this.isStarted = true;
/*     */         return;
/*     */       } 
/*     */     } 
/* 405 */     openInv();
/* 406 */     lootDonkey();
/* 407 */     this.MC.player.closeScreen();
/* 408 */     toggleLever2(false);
/* 409 */     Baritone.interactWithEntity(donkey);
/* 410 */     BUtils.sleep(2000L);
/* 411 */     openInv();
/* 412 */     fillDonkey();
/* 413 */     this.MC.player.closeScreen();
/* 414 */     int empties = 0;
/* 415 */     for (ItemStack item : this.MC.player.inventory.mainInventory) {
/*     */       
/* 417 */       if (Baritone.isEmpty(item))
/* 418 */         empties++; 
/*     */     } 
/* 420 */     if (empties < 15) {
/*     */       
/* 422 */       boolean stop2 = false;
/* 423 */       int to = 20;
/* 424 */       while (this.MC.inGameHasFocus) {
/*     */         
/* 426 */         Baritone.setRotationToBlockPos(this.chest);
/* 427 */         Baritone.rightClickBlock(this.chest, EnumHand.MAIN_HAND);
/* 428 */         BUtils.sleep(500L);
/* 429 */         if (to == 0) {
/*     */           
/* 431 */           stop2 = true;
/* 432 */           stop1();
/* 433 */           BUtils.sleep(500L);
/* 434 */           Baritone.setRotationToBlockPos(this.chest);
/* 435 */           Baritone.rightClickBlock(this.chest, EnumHand.MAIN_HAND);
/*     */           break;
/*     */         } 
/*     */       } 
/* 439 */       fillChest();
/* 440 */       this.MC.player.closeScreen();
/* 441 */       if (stop2)
/* 442 */         stop2(); 
/*     */     } 
/* 444 */     if (((Integer)Hacks.packetCanceller.inHud.get()).intValue() >= 27000) {
/*     */       
/* 446 */       stop();
/* 447 */       start();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void toggleLever1(boolean state) {
/* 453 */     int to2 = 40;
/* 454 */     while (!this.MC.world.getBlockState(this.lever1).equals(this.MC.world.getBlockState(this.lever1).withProperty((IProperty)BlockLever.POWERED, Boolean.valueOf(state))) && this.MC.world != null && this.enabled) {
/*     */       
/* 456 */       BUtils.sleep(50L);
/* 457 */       to2--;
/* 458 */       if (to2 == 0) {
/*     */         
/* 460 */         Baritone.setRotationToBlockPos(this.lever1);
/* 461 */         BUtils.sleep(100L);
/* 462 */         Baritone.rightClickBlock(this.lever1, EnumHand.MAIN_HAND);
/* 463 */         to2 = 40;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void toggleLever2(boolean state) {
/* 470 */     int to2 = 40;
/* 471 */     while (!this.MC.world.getBlockState(this.lever2).equals(this.MC.world.getBlockState(this.lever2).withProperty((IProperty)BlockLever.POWERED, Boolean.valueOf(state))) && this.MC.world != null && this.enabled) {
/*     */       
/* 473 */       BUtils.sleep(50L);
/* 474 */       to2--;
/* 475 */       if (to2 == 0) {
/*     */         
/* 477 */         Baritone.setRotationToBlockPos(this.lever2);
/* 478 */         BUtils.sleep(100L);
/* 479 */         Baritone.rightClickBlock(this.lever2, EnumHand.MAIN_HAND);
/* 480 */         to2 = 40;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/hacks/AutoDupe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
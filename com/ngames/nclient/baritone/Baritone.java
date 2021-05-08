/*     */ package com.ngames.nclient.baritone;
/*     */ 
/*     */ import com.ngames.nclient.NClient;
/*     */ import com.ngames.nclient.hack.Hacks;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityOtherPlayerMP;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.multiplayer.WorldClient;
/*     */ import net.minecraft.client.settings.KeyBinding;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.ClickType;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemFood;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.client.CPacketPlayer;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.FoodStats;
/*     */ import net.minecraft.util.Timer;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.ChunkPos;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TextComponentString;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Baritone
/*     */ {
/*     */   public static final int CRAFTING_1 = 0;
/*     */   public static final int CRAFTING_2 = 1;
/*     */   public static final int CRAFTING_3 = 2;
/*     */   public static final int CRAFTING_4 = 3;
/*     */   public static final int ARMOR_HELMET = 4;
/*     */   public static final int ARMOR_CHESTPLACE = 5;
/*     */   public static final int ARMOR_LEGGINS = 6;
/*     */   public static final int ARMOR_BOOTS = 7;
/*     */   public static final int INVENTORY_1 = 8;
/*     */   public static final int INVENTORY_2 = 9;
/*     */   public static final int INVENTORY_3 = 10;
/*     */   public static final int INVENTORY_4 = 11;
/*     */   public static final int INVENTORY_5 = 12;
/*     */   public static final int INVENTORY_6 = 13;
/*     */   public static final int INVENTORY_7 = 14;
/*     */   public static final int INVENTORY_8 = 15;
/*     */   public static final int INVENTORY_9 = 16;
/*     */   public static final int INVENTORY_10 = 17;
/*     */   public static final int INVENTORY_11 = 18;
/*     */   public static final int INVENTORY_12 = 19;
/*     */   public static final int INVENTORY_13 = 20;
/*     */   public static final int INVENTORY_14 = 21;
/*     */   public static final int INVENTORY_15 = 22;
/*     */   public static final int INVENTORY_16 = 23;
/*     */   public static final int INVENTORY_17 = 24;
/*     */   public static final int INVENTORY_18 = 25;
/*     */   public static final int INVENTORY_19 = 26;
/*     */   public static final int INVENTORY_20 = 27;
/*     */   public static final int INVENTORY_21 = 28;
/*     */   public static final int INVENTORY_22 = 29;
/*     */   public static final int INVENTORY_23 = 30;
/*     */   public static final int INVENTORY_24 = 31;
/*     */   public static final int INVENTORY_25 = 32;
/*     */   public static final int INVENTORY_26 = 33;
/*     */   public static final int INVENTORY_27 = 34;
/*     */   public static final int HOTBAR_1 = 35;
/*     */   public static final int HOTBAR_2 = 36;
/*     */   public static final int HOTBAR_3 = 37;
/*     */   public static final int HOTBAR_4 = 38;
/*     */   public static final int HOTBAR_5 = 39;
/*     */   public static final int HOTBAR_6 = 40;
/*     */   public static final int HOTBAR_7 = 41;
/*     */   public static final int HOTBAR_8 = 42;
/*     */   public static final int HOTBAR_9 = 43;
/*     */   public static final int OFFHAND = 45;
/*     */   public static final int INVENTORY = 0;
/*     */   public static final int CHEST = 1;
/*     */   public static boolean clickSync = false;
/*     */   public static float yaw;
/*     */   public static float pitch;
/*     */   public static float prevYaw;
/*     */   public static float prevPitch;
/*     */   public static boolean overrideRotation;
/*     */   public static boolean serverSprinting;
/*     */   public static boolean serverSprintingState;
/*     */   public static boolean overrideSprinting;
/*     */   public static boolean needRotate;
/*     */   private static short rotationPriority;
/*     */   
/*     */   public static void rightClickMouse() {
/*     */     try {
/* 116 */       Method m = Minecraft.class.getDeclaredMethod("func_147121_ag", new Class[0]);
/* 117 */       m.setAccessible(true);
/* 118 */       m.invoke(NClient.MC, new Object[0]);
/* 119 */     } catch (NoSuchMethodException e) {
/* 120 */       e.printStackTrace();
/* 121 */     } catch (SecurityException e) {
/* 122 */       e.printStackTrace();
/* 123 */     } catch (IllegalAccessException e) {
/* 124 */       e.printStackTrace();
/* 125 */     } catch (IllegalArgumentException e) {
/* 126 */       e.printStackTrace();
/* 127 */     } catch (InvocationTargetException e) {
/* 128 */       e.getCause().printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void leftClickMouse() {
/*     */     try {
/* 135 */       Method m = Minecraft.class.getDeclaredMethod("func_147116_af", new Class[0]);
/* 136 */       m.setAccessible(true);
/* 137 */       m.invoke(NClient.MC, new Object[0]);
/* 138 */     } catch (NoSuchMethodException e) {
/* 139 */       e.printStackTrace();
/* 140 */     } catch (SecurityException e) {
/* 141 */       e.printStackTrace();
/* 142 */     } catch (IllegalAccessException e) {
/* 143 */       e.printStackTrace();
/* 144 */     } catch (IllegalArgumentException e) {
/* 145 */       e.printStackTrace();
/* 146 */     } catch (InvocationTargetException e) {
/* 147 */       e.printStackTrace();
/*     */     } 
/* 149 */     Hacks.cpsCount.clicks++;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void processKeyF3(int auxKey) {
/*     */     try {
/* 155 */       Method m = Minecraft.class.getDeclaredMethod("func_184122_c", new Class[] { int.class });
/* 156 */       m.setAccessible(true);
/* 157 */       m.invoke(NClient.MC, new Object[] { Integer.valueOf(auxKey) });
/* 158 */     } catch (NoSuchMethodException e) {
/* 159 */       e.printStackTrace();
/* 160 */     } catch (SecurityException e) {
/* 161 */       e.printStackTrace();
/* 162 */     } catch (IllegalAccessException e) {
/* 163 */       e.printStackTrace();
/* 164 */     } catch (IllegalArgumentException e) {
/* 165 */       e.printStackTrace();
/* 166 */     } catch (InvocationTargetException e) {
/* 167 */       e.getCause().printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void attackEntity(Entity target) {
/* 173 */     if (target != null && NClient.MC.player != null) {
/*     */       
/* 175 */       NClient.MC.playerController.attackEntity((EntityPlayer)NClient.MC.player, target);
/* 176 */       NClient.MC.player.swingArm(EnumHand.MAIN_HAND);
/*     */     } 
/* 178 */     Hacks.cpsCount.clicks++;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void attackEntity() {
/* 183 */     if (NClient.MC.player != null && NClient.MC.objectMouseOver.typeOfHit == RayTraceResult.Type.ENTITY) {
/*     */       
/* 185 */       NClient.MC.playerController.attackEntity((EntityPlayer)NClient.MC.player, NClient.MC.objectMouseOver.entityHit);
/* 186 */       NClient.MC.player.swingArm(EnumHand.MAIN_HAND);
/*     */     } 
/* 188 */     Hacks.cpsCount.clicks++;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void interactWithEntity(Entity target) {
/* 193 */     NClient.MC.playerController.interactWithEntity((EntityPlayer)NClient.MC.player, target, EnumHand.MAIN_HAND);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void rightClickBlock(BlockPos block, EnumHand hand) {
/* 198 */     NClient.MC.playerController.processRightClickBlock(NClient.MC.player, NClient.MC.world, block, EnumFacing.getDirectionFromEntityLiving(block, (EntityLivingBase)NClient.MC.player), new Vec3d(block
/* 199 */           .getX(), block.getY(), block.getZ()), hand);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getLeftClickCounter() {
/* 204 */     Field f = null;
/*     */     try {
/* 206 */       f = Minecraft.class.getDeclaredField("field_71429_W");
/* 207 */       f.setAccessible(true);
/* 208 */     } catch (NoSuchFieldException|SecurityException e) {
/* 209 */       e.printStackTrace();
/*     */     } 
/* 211 */     int ret = 10;
/*     */     try {
/* 213 */       ret = f.getInt(NClient.MC);
/* 214 */     } catch (IllegalArgumentException|IllegalAccessException e) {
/* 215 */       e.printStackTrace();
/*     */     } 
/* 217 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setLeftClickCounter() {
/* 222 */     Field f = null;
/*     */     try {
/* 224 */       f = Minecraft.class.getDeclaredField("field_71429_W");
/* 225 */       f.setAccessible(true);
/* 226 */     } catch (NoSuchFieldException|SecurityException e) {
/* 227 */       e.printStackTrace();
/*     */     } 
/*     */     try {
/* 230 */       f.setInt(NClient.MC, 10);
/* 231 */     } catch (IllegalArgumentException|IllegalAccessException e) {
/* 232 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setTimer(Timer timer) {
/* 238 */     Field f = null;
/*     */     try {
/* 240 */       f = Minecraft.class.getDeclaredField("field_71428_T");
/* 241 */       f.setAccessible(true);
/* 242 */       f.set(NClient.MC, timer);
/* 243 */     } catch (NoSuchFieldException|SecurityException|IllegalArgumentException|IllegalAccessException e) {
/* 244 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setSPRINTING_SPEED_BOOST(AttributeModifier SPRINTING_SPEED_BOOST) {
/* 250 */     Field f = null;
/*     */     try {
/* 252 */       f = EntityLivingBase.class.getDeclaredField("field_110157_c");
/* 253 */       f.setAccessible(true);
/* 254 */       f.set(NClient.MC.player, SPRINTING_SPEED_BOOST);
/* 255 */     } catch (NoSuchFieldException|SecurityException|IllegalArgumentException|IllegalAccessException e) {
/* 256 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getJumpTicks() {
/* 262 */     Field f = null;
/*     */     try {
/* 264 */       f = EntityLivingBase.class.getDeclaredField("field_70773_bE");
/* 265 */       f.setAccessible(true);
/* 266 */     } catch (NoSuchFieldException|SecurityException e) {
/* 267 */       e.printStackTrace();
/*     */     } 
/* 269 */     int ret = -1;
/*     */     try {
/* 271 */       ret = f.getInt(NClient.MC.player);
/* 272 */     } catch (IllegalArgumentException|IllegalAccessException e) {
/* 273 */       e.printStackTrace();
/*     */     } 
/* 275 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean getServerSprintState() {
/* 280 */     Field f = null;
/*     */     try {
/* 282 */       f = EntityPlayerSP.class.getDeclaredField("field_175171_bO");
/* 283 */       f.setAccessible(true);
/* 284 */     } catch (NoSuchFieldException|SecurityException e) {
/* 285 */       e.printStackTrace();
/*     */     } 
/* 287 */     boolean ret = false;
/*     */     try {
/* 289 */       ret = f.getBoolean(NClient.MC.player);
/* 290 */     } catch (IllegalArgumentException|IllegalAccessException e) {
/* 291 */       e.printStackTrace();
/*     */     } 
/* 293 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Set<ChunkPos> getVisibleChunks() {
/* 299 */     Field f = null;
/*     */     try {
/* 301 */       f = WorldClient.class.getDeclaredField("field_184157_a");
/* 302 */       f.setAccessible(true);
/* 303 */     } catch (NoSuchFieldException|SecurityException e) {
/* 304 */       e.printStackTrace();
/*     */     } 
/* 306 */     Set<ChunkPos> ret = null;
/*     */     try {
/* 308 */       ret = (Set<ChunkPos>)f.get(NClient.MC.world);
/* 309 */     } catch (IllegalArgumentException|IllegalAccessException e) {
/* 310 */       e.printStackTrace();
/*     */     } 
/* 312 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   public static FoodStats getFoodStats() {
/* 317 */     return NClient.MC.player.getFoodStats();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void dropItem(int slotId) {
/* 323 */     NClient.MC.playerController.windowClick(NClient.MC.player.openContainer.windowId, slotId, 1, ClickType.THROW, (EntityPlayer)NClient.MC.player);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void dropCurrentItem() {
/* 328 */     NClient.MC.player.dropItem(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void addRotationPlayer(float yaw, float pitch) {
/* 333 */     rotatePlayer(NClient.MC.player.rotationYaw + yaw, NClient.MC.player.rotationPitch + pitch);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void dropAllItems(float minDelay, float maxDelay) {
/* 338 */     dropAllItems((int)minDelay, (int)maxDelay);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void dropAllItems() {
/* 343 */     List<Integer> id = new ArrayList<>();
/* 344 */     for (Slot slot : NClient.MC.player.openContainer.inventorySlots)
/*     */     {
/* 346 */       id.add(Integer.valueOf(slot.getSlotIndex()));
/*     */     }
/* 348 */     for (Iterator<Integer> iterator = id.iterator(); iterator.hasNext(); ) { int cid = ((Integer)iterator.next()).intValue();
/*     */       
/* 350 */       dropItem(cid); }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   public static void dropAllItems(int minDelay, int maxDelay) {
/* 356 */     List<Integer> id = new ArrayList<>();
/* 357 */     for (Slot slot : NClient.MC.player.openContainer.inventorySlots)
/*     */     {
/* 359 */       id.add(Integer.valueOf(slot.getSlotIndex()));
/*     */     }
/* 361 */     for (Iterator<Integer> iterator = id.iterator(); iterator.hasNext(); ) { int cid = ((Integer)iterator.next()).intValue();
/*     */       
/* 363 */       BUtils.sleep(BUtils.randomInRange(minDelay, maxDelay), BUtils.randomInRange(0, 999999));
/* 364 */       dropItem(cid); }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   public static void closeInventory() {
/* 370 */     NClient.MC.player.openContainer.onContainerClosed((EntityPlayer)NClient.MC.player);
/* 371 */     NClient.MC.displayGuiScreen((GuiScreen)null);
/* 372 */     if (NClient.MC.currentScreen == null)
/*     */     {
/* 374 */       NClient.MC.setIngameFocus();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void rotatePlayer(float yaw, float pitch) {
/* 380 */     NClient.MC.player.rotationYaw = yaw;
/* 381 */     NClient.MC.player.rotationPitch = pitch;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void rotatePlayer(float yaw, float pitch, boolean onlyServer) {
/* 386 */     if (onlyServer) {
/*     */       
/* 388 */       CPacketPlayer pc = new CPacketPlayer();
/* 389 */       new CPacketPlayer.Rotation(yaw, pitch, true);
/* 390 */       NClient.MC.getConnection().sendPacket((Packet)pc);
/*     */     } else {
/*     */       
/* 393 */       rotatePlayer(yaw, pitch);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void rotateHead(float yaw) {
/* 398 */     NClient.MC.player.rotationYawHead = yaw;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void addRotationPlayer(float yaw, float pitch, boolean onlyServer) {
/* 403 */     Baritone.yaw += yaw;
/* 404 */     Baritone.pitch += pitch;
/* 405 */     if (onlyServer) {
/* 406 */       updateRotation();
/*     */     } else {
/* 408 */       addRotationPlayer(yaw, pitch);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void addRotationHead(float yaw) {
/* 413 */     rotateHead(NClient.MC.player.rotationYawHead + yaw);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setRotationToEntity(Entity entity) {
/* 418 */     setRotationToEntity(entity, 0.5F);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setRotationToEntity(Entity entity, boolean onlyServer) {
/* 423 */     setRotationToEntity(entity, 0.5F, onlyServer);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setRotationToEntity(Entity entity, float dPitch) {
/* 428 */     setRotationToEntity(entity, dPitch, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setRotationToEntity(Entity entity, float dPitch, boolean onlyServer) {
/* 433 */     BUtils.EntityLookHelper elh = new BUtils.EntityLookHelper(NClient.MC.player);
/* 434 */     elh.setLookPositionWithEntity(entity, 360.0F, 360.0F);
/* 435 */     elh.onUpdateLook(dPitch, onlyServer);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setRotationToBlockPos(BlockPos pos) {
/* 440 */     BUtils.EntityLookHelper elh = new BUtils.EntityLookHelper(NClient.MC.player);
/* 441 */     elh.setLookPosition((pos.getX() + 0.5F), (pos.getY() + 0.5F), (pos.getZ() + 0.5F), 360.0F, 360.0F);
/* 442 */     elh.onUpdateLook(0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void refreshRotation() {
/* 447 */     yaw = NClient.MC.player.rotationYaw;
/* 448 */     pitch = NClient.MC.player.rotationPitch;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void smoothRotatePlayer(float yaw, float pitch, long millis, boolean dirYaw, boolean dirPitch) {
/* 453 */     float aYaw = 0.0F;
/* 454 */     float aPitch = 0.0F;
/* 455 */     while (NClient.MC.player.rotationYaw != yaw || NClient.MC.player.rotationPitch != pitch) {
/*     */       
/* 457 */       if (NClient.MC.player.rotationYaw != yaw && dirYaw)
/* 458 */         aYaw = 0.01F; 
/* 459 */       if (NClient.MC.player.rotationPitch != pitch && dirPitch)
/* 460 */         aPitch = 0.01F; 
/* 461 */       if (NClient.MC.player.rotationYaw != yaw && !dirYaw)
/* 462 */         aYaw = -0.01F; 
/* 463 */       if (NClient.MC.player.rotationPitch != pitch && !dirPitch)
/* 464 */         aPitch = -0.01F; 
/* 465 */       if ((NClient.MC.player.rotationYaw > yaw && dirYaw) || (NClient.MC.player.rotationPitch > pitch && dirPitch) || (NClient.MC.player.rotationYaw < yaw && !dirYaw) || (NClient.MC.player.rotationPitch < pitch && !dirPitch)) {
/*     */         break;
/*     */       }
/* 468 */       addRotationPlayer(aYaw, aPitch);
/* 469 */       BUtils.sleep((long)((float)millis / 0.01F));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void smoothRotateHead(float yaw, long millis) {
/* 475 */     double yawMs = (yaw / (float)millis);
/* 476 */     for (int i = 0; i < millis; i++)
/*     */     {
/* 478 */       addRotationHead((float)yawMs);
/*     */     }
/* 480 */     rotateHead(yaw);
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<BlockPos> findBlocks(Block block) {
/* 485 */     List<BlockPos> ret = new ArrayList<>();
/* 486 */     if (NClient.MC.world != null)
/*     */     {
/* 488 */       for (ChunkPos cp : getVisibleChunks()) {
/*     */         
/* 490 */         for (int x = 0; x < 16; x++) {
/*     */           
/* 492 */           for (int y = 0; y < 256; y++) {
/*     */             
/* 494 */             for (int z = 0; z < 16; z++) {
/*     */               
/* 496 */               BlockPos pos = new BlockPos(cp.getXStart() + x, y, cp.getZStart() + z);
/* 497 */               IBlockState blockState = NClient.MC.world.getBlockState(pos);
/* 498 */               if (blockState.getBlock() == block)
/* 499 */                 ret.add(pos); 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/* 505 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Entity findEntity(float range, Class<? extends Entity> type) {
/* 510 */     Entity ret = null;
/* 511 */     double distance = Double.MAX_VALUE;
/* 512 */     for (Entity e : NClient.MC.world.getLoadedEntityList()) {
/*     */       
/* 514 */       double dis = NClient.MC.player.getDistance(e);
/* 515 */       if (e.getClass() == type && (ret == null || dis < distance) && dis <= range) {
/*     */         
/* 517 */         ret = e;
/* 518 */         distance = dis;
/*     */       } 
/*     */     } 
/* 521 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Entity findEntity(float range, Class<? extends Entity> type, List<Entity> except) {
/* 526 */     Entity ret = null;
/* 527 */     double distance = Double.MAX_VALUE;
/* 528 */     for (Entity e : NClient.MC.world.getLoadedEntityList()) {
/*     */       
/* 530 */       boolean contains = false;
/* 531 */       for (Entity e1 : except) {
/*     */         
/* 533 */         if (e1.getEntityId() == e.getEntityId()) {
/*     */           
/* 535 */           contains = true;
/*     */           break;
/*     */         } 
/*     */       } 
/* 539 */       double dis = NClient.MC.player.getDistance(e);
/* 540 */       if (e.getClass() == type && dis < distance && !contains && dis <= range) {
/*     */         
/* 542 */         distance = dis;
/* 543 */         ret = e;
/*     */       } 
/*     */     } 
/* 546 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void useItem(EnumHand hand) {
/* 551 */     NClient.MC.playerController.processRightClick((EntityPlayer)NClient.MC.player, (World)NClient.MC.world, hand);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void useItem() {
/* 556 */     KeyBinding.setKeyBindState(NClient.MC.gameSettings.keyBindUseItem.getKeyCode(), true);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void usedItem() {
/* 561 */     KeyBinding.setKeyBindState(NClient.MC.gameSettings.keyBindUseItem.getKeyCode(), false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void putInMainHand(int slotId) {
/* 567 */     NClient.MC.playerController.windowClick(NClient.MC.player.inventoryContainer.windowId, slotId, 0, ClickType.PICKUP_ALL, (EntityPlayer)NClient.MC.player);
/* 568 */     NClient.MC.playerController.windowClick(NClient.MC.player.inventoryContainer.windowId, NClient.MC.player.inventory.currentItem + 36, 0, ClickType.SWAP, (EntityPlayer)NClient.MC.player);
/* 569 */     NClient.MC.playerController.windowClick(NClient.MC.player.inventoryContainer.windowId, slotId, 0, ClickType.PICKUP_ALL, (EntityPlayer)NClient.MC.player);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void putInOffHand(int slotId) {
/* 574 */     if (slotId < 9)
/* 575 */       slotId += 36; 
/* 576 */     NClient.MC.playerController.windowClick(NClient.MC.player.inventoryContainer.windowId, slotId, 0, ClickType.PICKUP, (EntityPlayer)NClient.MC.player);
/* 577 */     NClient.MC.playerController.windowClick(NClient.MC.player.inventoryContainer.windowId, 45, 0, ClickType.PICKUP, (EntityPlayer)NClient.MC.player);
/* 578 */     NClient.MC.playerController.windowClick(NClient.MC.player.inventoryContainer.windowId, slotId, 0, ClickType.PICKUP, (EntityPlayer)NClient.MC.player);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void putInAir(int slotId) {
/* 583 */     NClient.MC.playerController.windowClick(0, slotId, 1, ClickType.QUICK_MOVE, (EntityPlayer)NClient.MC.player);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setMainHand(int slotId) {
/* 588 */     NClient.MC.player.inventory.currentItem = slotId;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getItem(ItemStack itemStack, boolean onlyHotbar) {
/* 593 */     int ret = -1;
/* 594 */     int i = 0;
/* 595 */     Item item = itemStack.getItem();
/* 596 */     for (ItemStack itemStack2 : NClient.MC.player.inventory.mainInventory) {
/*     */       
/* 598 */       Item item2 = itemStack2.getItem();
/* 599 */       if (item.equals(item2))
/*     */       {
/* 601 */         if (onlyHotbar && i > 34 && i < 44) {
/* 602 */           ret = i;
/* 603 */         } else if (!onlyHotbar) {
/* 604 */           ret = i;
/*     */         }  } 
/* 606 */       i++;
/*     */     } 
/* 608 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getBestFood(boolean onlyHotbar) {
/* 613 */     int ret = 8;
/* 614 */     boolean isFood = false;
/* 615 */     int i = 0;
/* 616 */     for (ItemStack itemStack : NClient.MC.player.inventory.mainInventory) {
/*     */       
/* 618 */       Item item = itemStack.getItem();
/* 619 */       ItemStack itemStack2 = (ItemStack)NClient.MC.player.inventory.mainInventory.get(ret);
/* 620 */       Item item2 = itemStack2.getItem();
/* 621 */       if (item instanceof ItemFood) {
/*     */         
/* 623 */         isFood = true;
/* 624 */         if (item2 instanceof ItemFood) {
/*     */           
/* 626 */           if (((ItemFood)item).getHealAmount(itemStack) > ((ItemFood)item2).getHealAmount(itemStack2))
/*     */           {
/* 628 */             if (onlyHotbar && i > 34 && i < 44) {
/* 629 */               ret = i;
/* 630 */             } else if (!onlyHotbar) {
/* 631 */               ret = i;
/*     */             }  } 
/*     */         } else {
/* 634 */           ret = i;
/*     */         } 
/* 636 */       }  i++;
/*     */     } 
/* 638 */     if (isFood) {
/* 639 */       return ret;
/*     */     }
/* 641 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getFoodWithHeal(byte healLvl, boolean onlyHotbar) {
/* 646 */     int ret = 8;
/* 647 */     boolean isFood = false;
/* 648 */     int i = 0;
/* 649 */     byte oldDelta = 21;
/* 650 */     for (ItemStack itemStack : NClient.MC.player.inventory.mainInventory) {
/*     */       
/* 652 */       Item item = itemStack.getItem();
/* 653 */       ItemStack itemStack2 = (ItemStack)NClient.MC.player.inventory.mainInventory.get(ret);
/* 654 */       Item item2 = itemStack2.getItem();
/* 655 */       if (item instanceof ItemFood) {
/*     */         
/* 657 */         isFood = true;
/* 658 */         if (item2 instanceof ItemFood) {
/*     */           
/* 660 */           byte healAmount = (byte)((ItemFood)item).getHealAmount(itemStack);
/* 661 */           byte healAmount2 = (byte)((ItemFood)item2).getHealAmount(itemStack2);
/* 662 */           byte delta = (byte)((healAmount > healAmount2) ? (healAmount - healAmount2) : (healAmount2 - healAmount));
/* 663 */           if (delta < oldDelta) {
/*     */             
/* 665 */             if (onlyHotbar && i > 34 && i < 44) {
/* 666 */               ret = i;
/* 667 */             } else if (!onlyHotbar) {
/* 668 */               ret = i;
/* 669 */             }  oldDelta = delta;
/*     */           } 
/*     */         } else {
/* 672 */           ret = i;
/*     */         } 
/* 674 */       }  i++;
/*     */     } 
/* 676 */     if (isFood) {
/* 677 */       return ret;
/*     */     }
/* 679 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Entity getPriorityTarget(float range, boolean onlyPlayers, boolean onlyMobs, boolean onlyPrevTarget, @Nullable Entity prevTarget, byte priority) {
/* 684 */     Entity target = null;
/* 685 */     float distance = Float.MAX_VALUE;
/* 686 */     float hp = Float.MAX_VALUE;
/* 687 */     if (NClient.MC.world != null)
/* 688 */       for (int i = 0; i < NClient.MC.world.getLoadedEntityList().size(); i++) {
/*     */         
/* 690 */         Entity e = NClient.MC.world.getLoadedEntityList().get(i);
/* 691 */         if (NClient.MC.world != null && NClient.MC.player.getDistance(e) <= range && isAlive(e) && ((
/* 692 */           e instanceof EntityOtherPlayerMP && onlyPlayers) || (e instanceof EntityLiving && onlyMobs))) {
/*     */           
/* 694 */           float health = (e instanceof EntityOtherPlayerMP) ? ((EntityOtherPlayerMP)e).getHealth() : ((EntityLiving)e).getHealth();
/* 695 */           float dist = NClient.MC.player.getDistance(e);
/* 696 */           if (priority == 0 && health < hp) {
/*     */             
/* 698 */             target = e;
/* 699 */             hp = health;
/*     */           } 
/* 701 */           if (priority == 1 && dist < distance) {
/*     */             
/* 703 */             target = e;
/* 704 */             distance = dist;
/*     */           } 
/* 706 */           if (health == hp && dist < distance)
/* 707 */             target = e; 
/* 708 */           if (dist < distance && health < hp) {
/* 709 */             target = e;
/*     */           }
/*     */         } 
/*     */       }  
/* 713 */     if (onlyPrevTarget && prevTarget != null && isAlive(prevTarget) && NClient.MC.player.getDistance(prevTarget) < range)
/* 714 */       target = prevTarget; 
/* 715 */     return target;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Entity getBotTarget(Entity target, boolean filterEntityType) {
/* 720 */     Entity entityBot = target;
/*     */ 
/*     */ 
/*     */     
/* 724 */     for (int i = 0; i < NClient.MC.world.getLoadedEntityList().size(); i++) {
/*     */       
/* 726 */       Entity e = NClient.MC.world.getLoadedEntityList().get(i);
/* 727 */       if (e != null && e != NClient.MC.player) {
/*     */         
/* 729 */         boolean equalsPos = (BUtils.isInRange(e.posX, entityBot.posX, 0.5F) && BUtils.isInRange(e.posY, entityBot.posY, 1.2F) && BUtils.isInRange(e.posZ, entityBot.posZ, 0.5F));
/* 730 */         boolean notPlayer = (e instanceof EntityLiving && e.getClass() != EntityOtherPlayerMP.class);
/* 731 */         boolean biggestId = (e.getEntityId() > entityBot.getEntityId());
/* 732 */         if (equalsPos && (notPlayer || !filterEntityType) && (biggestId || !notPlayer))
/* 733 */           entityBot = e; 
/*     */       } 
/*     */     } 
/* 736 */     return entityBot;
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<Entity> getMultiTargets(float range, boolean onlyPlayers, boolean onlyMobs) {
/* 741 */     List<Entity> targets = new ArrayList<>();
/* 742 */     for (int i = 0; i < NClient.MC.world.getLoadedEntityList().size(); i++) {
/*     */       
/* 744 */       Entity e = NClient.MC.world.getLoadedEntityList().get(i);
/* 745 */       if (NClient.MC.world != null && NClient.MC.player.getDistance(e) <= range && isAlive(e) && ((
/* 746 */         e instanceof EntityOtherPlayerMP && onlyPlayers) || (e instanceof EntityLiving && onlyMobs)))
/*     */       {
/* 748 */         targets.add(e);
/*     */       }
/*     */     } 
/* 751 */     return targets;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isAlive(Entity e) {
/* 756 */     return (e.isEntityAlive() && e instanceof EntityLivingBase) ? ((((EntityLivingBase)e).deathTime == 0)) : true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void displayMessage(String message) {
/* 761 */     NClient.MC.player.sendMessage((ITextComponent)new TextComponentString("\\u00A7d[NClient]\\u00A73 " + message));
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getSlotFor(Item item, boolean onlyHotbar) {
/* 766 */     InventoryPlayer inv = NClient.MC.player.inventory;
/* 767 */     int slot = -1;
/* 768 */     int i = 0;
/* 769 */     for (ItemStack itemStack : inv.mainInventory) {
/*     */       
/* 771 */       if (itemStack.getItem().getRegistryName().equals(item.getRegistryName())) {
/*     */         
/* 773 */         if (onlyHotbar && i < 9) {
/* 774 */           slot = i;
/* 775 */         } else if (!onlyHotbar) {
/* 776 */           slot = i;
/* 777 */         }  return slot;
/*     */       } 
/* 779 */       i++;
/*     */     } 
/* 781 */     return slot;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isInMainHand(Item item) {
/* 786 */     return ((ItemStack)NClient.MC.player.inventory.mainInventory.get(NClient.MC.player.inventory.currentItem)).getItem().getRegistryName().equals(item.getRegistryName());
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isInOffHand(Item item) {
/* 791 */     return (Item.getIdFromItem(((ItemStack)NClient.MC.player.inventory.offHandInventory.get(0)).getItem()) == Item.getIdFromItem(item));
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isEmptyInOffHand() {
/* 796 */     return ((ItemStack)NClient.MC.player.inventory.offHandInventory.get(0)).isItemEqual(ItemStack.EMPTY);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isEmpty(ItemStack item) {
/* 801 */     return (item == null || item.isEmpty() || item.getItem() == Items.AIR);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isMoving() {
/* 806 */     return (NClient.MC.player.moveForward != 0.0F || NClient.MC.player.moveStrafing != 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void updateRotation() {
/* 812 */     Field yaw = null;
/* 813 */     Field pitch = null;
/*     */     try {
/* 815 */       yaw = EntityPlayerSP.class.getDeclaredField("field_175164_bL");
/* 816 */       pitch = EntityPlayerSP.class.getDeclaredField("field_175165_bM");
/* 817 */     } catch (NoSuchFieldException|SecurityException e) {
/* 818 */       e.printStackTrace();
/*     */     } 
/* 820 */     yaw.setAccessible(true);
/* 821 */     pitch.setAccessible(true);
/*     */     try {
/* 823 */       yaw.set(NClient.MC.player, Float.valueOf(NClient.MC.player.rotationYaw - 1.0F));
/* 824 */       pitch.set(NClient.MC.player, Float.valueOf(NClient.MC.player.rotationPitch - 1.0F));
/* 825 */     } catch (IllegalArgumentException|IllegalAccessException e) {
/* 826 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setSneaking(boolean sneak) {
/* 832 */     if (NClient.MC.player.isSneaking() != sneak) {
/* 833 */       KeyBinding.setKeyBindState(NClient.MC.gameSettings.keyBindSneak.getKeyCode(), sneak);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void setJumping(boolean jump) {
/* 838 */     KeyBinding.setKeyBindState(NClient.MC.gameSettings.keyBindJump.getKeyCode(), jump);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setOverrideSprinting(boolean state) {
/* 843 */     serverSprintingState = getServerSprintState();
/* 844 */     overrideSprinting = state;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean calcRotationPriority(short rotationPriorityOther) {
/* 849 */     if (rotationPriorityOther >= rotationPriority) {
/*     */       
/* 851 */       rotationPriority = rotationPriorityOther;
/* 852 */       return true;
/*     */     } 
/* 854 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setRotationPriority(short rotationPriority) {
/* 859 */     Baritone.rotationPriority = rotationPriority;
/*     */   }
/*     */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/baritone/Baritone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
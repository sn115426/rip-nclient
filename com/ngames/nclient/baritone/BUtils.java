/*     */ package com.ngames.nclient.baritone;
/*     */ 
/*     */ import com.ngames.nclient.NClient;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BUtils
/*     */ {
/*     */   public static double toDegrees(double Angle) {
/*  21 */     return Angle * 180.0D / Math.PI;
/*     */   }
/*     */ 
/*     */   
/*     */   public static float toMinecraftDegrees(float angle) {
/*  26 */     if (angle > 180.0F)
/*  27 */       return -(360.0F - angle); 
/*  28 */     return angle;
/*     */   }
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
/*     */   public static double getLookingCoordinate() {
/*  63 */     double ret = 0.0D;
/*  64 */     Direction der = getDirection(false);
/*  65 */     if (der == Direction.Xneg || der == Direction.Xpos)
/*  66 */       ret = NClient.MC.player.posX; 
/*  67 */     if (der == Direction.Zneg || der == Direction.Zpos)
/*  68 */       ret = NClient.MC.player.posZ; 
/*  69 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getLookingCoordinate(Entity entity) {
/*  74 */     double ret = 0.0D;
/*  75 */     Direction der = getDirection(false);
/*  76 */     if (der == Direction.Xneg || der == Direction.Xpos)
/*  77 */       ret = entity.posX; 
/*  78 */     if (der == Direction.Zneg || der == Direction.Zpos)
/*  79 */       ret = entity.posZ; 
/*  80 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<Integer> genSimpleDelays(float cps) {
/*  85 */     List<Integer> ret = new ArrayList<>();
/*  86 */     int size = Math.round(cps);
/*  87 */     for (int i = 0; i < size; i++)
/*     */     {
/*  89 */       ret.add(Integer.valueOf(Math.round(1000.0F / cps)));
/*     */     }
/*  91 */     return ret;
/*     */   }
/*     */   
/*     */   public static List<Integer> genDelayNoise(float cps) {
/*  95 */     List<Integer> ret = new ArrayList<>();
/*  96 */     int size = Math.round(cps);
/*  97 */     for (int i = 0; i < size; i++) {
/*     */       
/*  99 */       int k = Math.round(1000.0F / cps);
/* 100 */       ret.add(Integer.valueOf(randomInRange(Math.round(k - k * 0.3F), Math.round(k + k * 0.3F))));
/*     */     } 
/* 102 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<Integer> genAdvancedDelayNoise(float cps, int phase, boolean powered, float speed, float valueRange, float minPhaseSize, float maxPhaseSize, float delayMlMin, float delayMlMax, short minLDelayChance, short maxLDelayChance) {
/* 108 */     List<Integer> ret = new ArrayList<>();
/* 109 */     int size = randomInRange(Math.round(minPhaseSize * cps), Math.round(maxPhaseSize * cps));
/*     */ 
/*     */     
/* 112 */     boolean second = false;
/* 113 */     int prev = Math.round(1000.0F / cps);
/* 114 */     for (int i = 0; i < size; i++) {
/*     */       
/* 116 */       boolean isDouble = random((short)20);
/* 117 */       int delay = Math.round(randomInRange(Math.round(speed / cps * (1.0F - valueRange)), Math.round(speed / cps * (1.0F + valueRange))) * (
/* 118 */           random((phase > 2) ? maxLDelayChance : minLDelayChance) ? ((phase > 2) ? delayMlMax : phase) : delayMlMin));
/* 119 */       if (second && isDouble)
/* 120 */         delay = randomInRange(prev - 3, prev + 3); 
/* 121 */       second = !second;
/* 122 */       ret.add(Integer.valueOf(delay));
/* 123 */       prev = delay;
/*     */     } 
/* 125 */     if (powered)
/*     */     {
/*     */ 
/*     */       
/* 129 */       for (int j = 0; j < ret.size(); j++) {
/*     */         
/* 131 */         int k = Math.round(randomInRange(3, 7) * 10.0F / ((phase > 5) ? 5 : phase));
/* 132 */         float power = (randomInRange(k - k / 20, k + k / 20) / 100);
/* 133 */         ret.set(j, Integer.valueOf(Math.round(((Integer)ret.get(j)).intValue() * (1.0F + power))));
/*     */       } 
/*     */     }
/* 136 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int randomInRange(int min, int max) {
/* 141 */     return min + (int)(Math.random() * (max - min + 1));
/*     */   }
/*     */ 
/*     */   
/*     */   public static float randomInRange(float min, float max) {
/* 146 */     return min + (int)(Math.random() * (max - min + 1.0F));
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean random() {
/* 151 */     return (randomInRange(0, 100) < 50);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean random(short chanse) {
/* 156 */     return (randomInRange(0, 100) < chanse);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isInRange(float x, float y, float range) {
/* 161 */     if (Math.max(x, y) - Math.min(x, y) < range)
/* 162 */       return true; 
/* 163 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isInRange(double x, double y, float range) {
/* 168 */     if (Math.max(x, y) - Math.min(x, y) < range)
/* 169 */       return true; 
/* 170 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getDistance(Vec3d pos1, Vec3d pos2) {
/* 175 */     double dX = Math.max(pos1.x, pos2.x) - Math.min(pos1.x, pos2.x);
/* 176 */     double dY = Math.max(pos1.y, pos2.y) - Math.min(pos1.y, pos2.y);
/* 177 */     double dZ = Math.max(pos1.z, pos2.z) - Math.min(pos1.z, pos2.z);
/* 178 */     return MathHelper.sqrt(dX * dX + dY * dY + dZ * dZ);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Vec3d getCenter(Entity entity) {
/* 183 */     double x = entity.posX + (entity.width / 2.0F);
/* 184 */     double y = entity.posY + (entity.height / 2.0F);
/* 185 */     double z = entity.posZ + (entity.width / 2.0F);
/* 186 */     return new Vec3d(x, y, z);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void sleep(long ms) {
/*     */     try {
/* 192 */       Thread.sleep(ms);
/* 193 */     } catch (InterruptedException e) {
/* 194 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void sleep(long ms, int ns) {
/*     */     try {
/* 201 */       Thread.sleep(ms, ns);
/* 202 */     } catch (InterruptedException e) {
/* 203 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static Direction getDirection(boolean two, float yaw) {
/* 209 */     Direction ret = null;
/* 210 */     if (yaw >= 46.0F && yaw <= 135.0F)
/* 211 */       ret = Direction.Xneg; 
/* 212 */     if ((yaw >= 136.0F && yaw <= 180.0F) || yaw <= -136.0F)
/* 213 */       ret = Direction.Zneg; 
/* 214 */     if ((yaw <= 45.0F && yaw <= 0.0F) || (yaw >= -45.0F && yaw <= 0.0F))
/* 215 */       ret = Direction.Zpos; 
/* 216 */     if (ret == null)
/* 217 */       ret = Direction.Xpos; 
/* 218 */     if (two) {
/*     */       
/* 220 */       if (yaw > 30.0F && yaw < 60.0F)
/* 221 */         ret = Direction.XnegZpos; 
/* 222 */       if (yaw > 120.0F && yaw < 150.0F)
/* 223 */         ret = Direction.XnegZneg; 
/* 224 */       if (yaw > -60.0F && yaw < -30.0F)
/* 225 */         ret = Direction.XposZneg; 
/* 226 */       if (yaw > -150.0F && yaw < -120.0F)
/* 227 */         ret = Direction.XposZpos; 
/*     */     } 
/* 229 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Direction getDirection(boolean two) {
/* 234 */     return getDirection(two, NClient.MC.player.rotationYaw);
/*     */   }
/*     */   
/*     */   public enum Direction
/*     */   {
/* 239 */     Xpos,
/* 240 */     Xneg,
/* 241 */     Zpos,
/* 242 */     Zneg,
/* 243 */     XposZpos,
/* 244 */     XposZneg,
/* 245 */     XnegZpos,
/* 246 */     XnegZneg;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean canAttackEntity() {
/* 251 */     return (NClient.MC.objectMouseOver.typeOfHit == RayTraceResult.Type.ENTITY);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean canAttackEntity(Entity entity) {
/* 256 */     return (NClient.MC.objectMouseOver.typeOfHit == RayTraceResult.Type.ENTITY && NClient.MC.objectMouseOver.entityHit.equals(entity));
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static boolean rayTrace(Vec3d start, Vec3d end, boolean calcEntities) {
/* 262 */     Vec3d min = new Vec3d(Math.min(start.x, end.x), Math.min(start.y, end.y), Math.min(start.z, end.z));
/* 263 */     Vec3d max = new Vec3d(Math.max(start.x, end.x), Math.max(start.y, end.y), Math.max(start.z, end.z));
/* 264 */     double x = min.x, y = min.y, z = min.z;
/* 265 */     Vec3d delta = new Vec3d(max.x - min.x, max.y - min.y, max.z - min.z);
/*     */ 
/*     */ 
/*     */     
/* 269 */     double dist = getDistance(start, end);
/* 270 */     Vec3d step = new Vec3d(delta.x / dist * 8.0D, delta.y / dist * 8.0D, delta.z / dist * 8.0D);
/*     */ 
/*     */     
/*     */     do {
/* 274 */       if (x < max.x)
/* 275 */         x += step.x; 
/* 276 */       if (y < max.y)
/* 277 */         y += step.y; 
/* 278 */       if (z < max.z)
/* 279 */         z += step.z; 
/* 280 */       IBlockState block = NClient.MC.world.getBlockState(new BlockPos(x, y, z));
/* 281 */       if (calcEntities)
/*     */       {
/* 283 */         for (Entity e : NClient.MC.world.loadedEntityList) {
/*     */           
/* 285 */           if (e.getCollisionBoundingBox().contains(new Vec3d(x, y, z)))
/* 286 */             return false; 
/*     */         } 
/*     */       }
/* 289 */       AxisAlignedBB aabb = block.getCollisionBoundingBox((IBlockAccess)NClient.MC.world, new BlockPos(x, y, z));
/* 290 */       if (NClient.MC.world != null && block.getBlock().canCollideCheck(block, false) && aabb.contains(new Vec3d(x, y, z)))
/* 291 */         return false; 
/* 292 */     } while (x < max.x || y < max.y || z < max.z);
/*     */ 
/*     */     
/* 295 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean rayTraceBlocks(Vec3d start, Vec3d end) {
/* 300 */     RayTraceResult result = NClient.MC.world.rayTraceBlocks(start, end);
/* 301 */     return (result == null || result.typeOfHit != RayTraceResult.Type.BLOCK);
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static boolean rayTrace(Vec3d start, Vec3d end) {
/* 307 */     return rayTrace(start, end, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isNearest(Entity entity1, Entity entity2) {
/* 312 */     return (NClient.MC.player.getDistance(entity1) < NClient.MC.player.getDistance(entity2));
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isSmallerBB(Entity entity1, Entity entity2, Direction dir) {
/* 317 */     AxisAlignedBB aabb1 = entity1.getEntityBoundingBox();
/* 318 */     AxisAlignedBB aabb2 = entity2.getEntityBoundingBox();
/* 319 */     boolean x = (aabb1.maxX - aabb1.minX < aabb2.maxX - aabb2.minX);
/* 320 */     boolean z = (aabb1.maxZ - aabb1.minZ < aabb2.maxZ - aabb2.minZ);
/* 321 */     return (((dir == Direction.Xneg || dir == Direction.Xpos) && x) || ((dir == Direction.Zneg || dir == Direction.Zpos) && z));
/*     */   }
/*     */ 
/*     */   
/*     */   public static class EntityLookHelper
/*     */   {
/*     */     private final EntityPlayerSP entity;
/*     */     
/*     */     private float deltaLookYaw;
/*     */     
/*     */     private float deltaLookPitch;
/*     */     
/*     */     private boolean isLooking;
/*     */     private double posX;
/*     */     private double posY;
/*     */     private double posZ;
/*     */     
/*     */     public EntityLookHelper(EntityPlayerSP entitylivingIn) {
/* 339 */       this.entity = entitylivingIn;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setLookPositionWithEntity(Entity entityIn, float deltaYaw, float deltaPitch) {
/* 347 */       this.posX = entityIn.posX;
/* 348 */       this.posY = ((entityIn.getEntityBoundingBox()).minY + (entityIn.getEntityBoundingBox()).maxY) / 2.0D;
/*     */       
/* 350 */       this.posZ = entityIn.posZ;
/* 351 */       this.deltaLookYaw = deltaYaw;
/* 352 */       this.deltaLookPitch = deltaPitch;
/* 353 */       this.isLooking = true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setLookPosition(double x, double y, double z, float deltaYaw, float deltaPitch) {
/* 361 */       this.posX = x;
/* 362 */       this.posY = y;
/* 363 */       this.posZ = z;
/* 364 */       this.deltaLookYaw = deltaYaw;
/* 365 */       this.deltaLookPitch = deltaPitch;
/* 366 */       this.isLooking = true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void onUpdateLook(float dPitch, boolean onlyServer) {
/* 374 */       float yaw = 0.0F;
/* 375 */       float pitch = 0.0F;
/* 376 */       float yawHead = 0.0F;
/*     */       
/* 378 */       if (this.isLooking) {
/*     */         
/* 380 */         this.isLooking = false;
/* 381 */         double d0 = this.posX - this.entity.posX;
/* 382 */         double d1 = this.posY - this.entity.posY + this.entity.getEyeHeight();
/* 383 */         double d2 = this.posZ - this.entity.posZ;
/* 384 */         double d3 = MathHelper.sqrt(d0 * d0 + d2 * d2);
/* 385 */         float f = (float)(MathHelper.atan2(d2, d0) * 57.29577951308232D) - 90.0F;
/* 386 */         float f1 = (float)-(MathHelper.atan2(d1, d3) * 57.29577951308232D);
/* 387 */         pitch = updateRotation(this.entity.rotationPitch, f1, this.deltaLookPitch);
/* 388 */         yaw = updateRotation(this.entity.rotationYaw, f, this.deltaLookYaw);
/*     */       }
/*     */       else {
/*     */         
/* 392 */         yawHead = updateRotation(this.entity.rotationYawHead, this.entity.renderYawOffset, 10.0F);
/*     */       } 
/*     */       
/* 395 */       float f2 = MathHelper.wrapDegrees(this.entity.rotationYawHead - this.entity.renderYawOffset);
/*     */       
/* 397 */       if (f2 < -75.0F)
/*     */       {
/* 399 */         yawHead = this.entity.renderYawOffset - 75.0F;
/*     */       }
/*     */       
/* 402 */       if (f2 > 75.0F)
/*     */       {
/* 404 */         yawHead = this.entity.renderYawOffset + 75.0F;
/*     */       }
/* 406 */       if (onlyServer) {
/*     */         
/* 408 */         Baritone.yaw = yaw;
/* 409 */         Baritone.pitch = pitch;
/*     */       } 
/* 411 */       if (!onlyServer) {
/*     */         
/* 413 */         this.entity.rotationYaw = yaw;
/* 414 */         this.entity.rotationPitch = pitch;
/*     */       } 
/* 416 */       this.entity.rotationYawHead = yawHead;
/*     */     }
/*     */ 
/*     */     
/*     */     public void onUpdateLook(float dPitch) {
/* 421 */       onUpdateLook(dPitch, false);
/*     */     }
/*     */ 
/*     */     
/*     */     private float updateRotation(float p_75652_1_, float p_75652_2_, float p_75652_3_) {
/* 426 */       float f = MathHelper.wrapDegrees(p_75652_2_ - p_75652_1_);
/*     */       
/* 428 */       if (f > p_75652_3_)
/*     */       {
/* 430 */         f = p_75652_3_;
/*     */       }
/*     */       
/* 433 */       if (f < -p_75652_3_)
/*     */       {
/* 435 */         f = -p_75652_3_;
/*     */       }
/*     */       
/* 438 */       return p_75652_1_ + f;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean getIsLooking() {
/* 443 */       return this.isLooking;
/*     */     }
/*     */ 
/*     */     
/*     */     public double getLookPosX() {
/* 448 */       return this.posX;
/*     */     }
/*     */ 
/*     */     
/*     */     public double getLookPosY() {
/* 453 */       return this.posY;
/*     */     }
/*     */ 
/*     */     
/*     */     public double getLookPosZ() {
/* 458 */       return this.posZ;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/baritone/BUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
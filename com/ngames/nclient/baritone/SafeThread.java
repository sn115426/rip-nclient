/*    */ package com.ngames.nclient.baritone;
/*    */ 
/*    */ import com.ngames.nclient.NClient;
/*    */ import com.ngames.nclient.hack.Hack;
/*    */ 
/*    */ 
/*    */ public class SafeThread
/*    */ {
/*    */   public Runnable runnable;
/*    */   public Runnable ifWorldNull;
/*    */   public Hack hack;
/*    */   
/*    */   public SafeThread(Runnable runnable, Hack hack) {
/* 14 */     this.runnable = runnable;
/* 15 */     this.hack = hack;
/*    */   }
/*    */ 
/*    */   
/*    */   public SafeThread(Runnable runnable, Runnable ifWorldNull, Hack hack) {
/* 20 */     this.runnable = runnable;
/* 21 */     this.ifWorldNull = ifWorldNull;
/* 22 */     this.hack = hack;
/*    */   }
/*    */ 
/*    */   
/*    */   public void start() {
/* 27 */     (new Thread(() -> {
/*    */           try {
/*    */             while (this.hack.isEnabled()) {
/*    */               if (NClient.MC.world == null) {
/*    */                 if (this.ifWorldNull != null) {
/*    */                   this.ifWorldNull.run();
/*    */                   
/*    */                   continue;
/*    */                 } 
/*    */                 
/*    */                 BUtils.sleep(50L);
/*    */                 continue;
/*    */               } 
/*    */               this.runnable.run();
/*    */             } 
/* 42 */           } catch (NullPointerException e) {
/*    */             if (this.hack.isEnabled()) {
/*    */               e.printStackTrace();
/*    */               
/*    */               BUtils.sleep(50L);
/*    */               start();
/*    */             } 
/*    */           } 
/* 50 */         })).start();
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/baritone/SafeThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
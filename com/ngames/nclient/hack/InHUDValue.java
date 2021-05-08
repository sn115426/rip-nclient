/*    */ package com.ngames.nclient.hack;
/*    */ 
/*    */ 
/*    */ public class InHUDValue
/*    */ {
/*    */   private Type type;
/*    */   private Object value;
/*    */   
/*    */   public InHUDValue(Object value) {
/* 10 */     this.value = value;
/* 11 */     if (value instanceof Float[]) {
/* 12 */       this.type = Type.VECTOR_FLOAT;
/* 13 */     } else if (value instanceof Integer[]) {
/* 14 */       this.type = Type.VECTOR_INTEGER;
/* 15 */     } else if (value instanceof Float) {
/* 16 */       this.type = Type.FLOAT;
/* 17 */     } else if (value instanceof Integer) {
/* 18 */       this.type = Type.INTEGER;
/* 19 */     } else if (value instanceof String) {
/* 20 */       this.type = Type.STRING;
/*    */     } else {
/* 22 */       this.type = Type.NULL;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 28 */     if (this.type == Type.VECTOR_FLOAT)
/* 29 */       return "[" + ((Float[])this.value)[0].toString() + ", " + ((Float[])this.value)[1].toString() + "]"; 
/* 30 */     if (this.type == Type.VECTOR_INTEGER)
/* 31 */       return "[" + ((Integer[])this.value)[0].toString() + ", " + ((Integer[])this.value)[1].toString() + "]"; 
/* 32 */     if (this.type == Type.FLOAT)
/* 33 */       return ((Float)this.value).toString(); 
/* 34 */     if (this.type == Type.INTEGER)
/* 35 */       return ((Integer)this.value).toString(); 
/* 36 */     if (this.type == Type.STRING)
/* 37 */       return (String)this.value; 
/* 38 */     return "";
/*    */   }
/*    */ 
/*    */   
/*    */   public void set(Object value) {
/* 43 */     this.value = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object get() {
/* 48 */     return this.value;
/*    */   }
/*    */   
/*    */   public enum Type
/*    */   {
/* 53 */     VECTOR_FLOAT,
/* 54 */     VECTOR_INTEGER,
/* 55 */     FLOAT,
/* 56 */     INTEGER,
/* 57 */     STRING,
/* 58 */     NULL;
/*    */   }
/*    */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/InHUDValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
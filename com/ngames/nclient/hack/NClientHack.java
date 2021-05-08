package com.ngames.nclient.hack;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface NClientHack {
  String name();
  
  String description();
  
  String words();
  
  int keyBind() default 0;
  
  Category category();
}


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/hack/NClientHack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
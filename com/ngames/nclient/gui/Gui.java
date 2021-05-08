/*     */ package com.ngames.nclient.gui;
/*     */ 
/*     */ import com.ngames.nclient.NClient;
/*     */ import com.ngames.nclient.hack.Category;
/*     */ import com.ngames.nclient.hack.Hack;
/*     */ import com.ngames.nclient.hack.HackUtils;
/*     */ import com.ngames.nclient.hack.Hacks;
/*     */ import com.ngames.nclient.hack.settings.Setting;
/*     */ import com.ngames.nclient.hack.settings.SettingBoolean;
/*     */ import com.ngames.nclient.hack.settings.SettingChoose;
/*     */ import com.ngames.nclient.hack.settings.SettingString;
/*     */ import com.ngames.nclient.hack.settings.SettingValue;
/*     */ import com.ngames.nclient.hack.settings.Settings;
/*     */ import com.ngames.nclient.keybinds.KeyBinds;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.renderer.BufferBuilder;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.client.shader.Framebuffer;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.commons.lang3.math.NumberUtils;
/*     */ import org.lwjgl.input.Mouse;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Gui
/*     */   extends GuiScreen
/*     */ {
/*     */   public static int mouseX;
/*     */   public static int mouseY;
/*     */   public static boolean justPressed;
/*     */   public Button selButton;
/*     */   public Button mouseOverButton;
/*     */   public Setting listining;
/*     */   public boolean listen;
/*  48 */   public String cache = "";
/*     */   
/*     */   public final GuiScreen lastScreen;
/*  51 */   public static Category currentCategory = Category.ALL;
/*     */   Framebuffer framebuffer;
/*  53 */   List<Button> buttonList = new ArrayList<>();
/*  54 */   int ButtonID = 8;
/*  55 */   List<List<Object>> buttonCoords = new ArrayList<>();
/*     */   
/*     */   boolean sync = false;
/*     */   boolean wait = true;
/*     */   
/*     */   public Gui(GuiScreen lastScreen) {
/*  61 */     this.lastScreen = lastScreen;
/*  62 */     this.framebuffer = new Framebuffer(NClient.MC.displayWidth, NClient.MC.displayHeight, false);
/*  63 */     addButtons();
/*  64 */     GL11.glEnable(3553);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/*  70 */     if (Hacks.clickGUI.isEnabled()) {
/*     */       
/*  72 */       calculateMouse();
/*  73 */       GlStateManager.color(1.0F, 1.0F, 1.0F);
/*  74 */       setFocused(true);
/*  75 */       drawGradientRect(0, 0, this.width, this.height, -1072689136, -804253680);
/*  76 */       drawInfo();
/*  77 */       for (Button gb : this.buttonList)
/*     */       {
/*  79 */         gb.drawButton(NClient.MC, mouseX, mouseY, NClient.MC.getRenderPartialTicks());
/*     */       }
/*  81 */       drawStrings();
/*  82 */       if (this.mouseOverButton != null && NClient.isHackExist(this.mouseOverButton.displayString))
/*     */       {
/*  84 */         drawHoveringText(HackUtils.getDescrption(NClient.getHack(this.mouseOverButton.displayString)), mouseX, mouseY);
/*     */       }
/*  86 */       this.mouseOverButton = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void keyTyped(char typedChar, int keyCode) {
/*  93 */     if (keyCode == 1 || keyCode == KeyBinds.getHackKeyBind("ClickGUI")) {
/*     */       
/*  95 */       this.mc.displayGuiScreen((GuiScreen)null);
/*  96 */       if (this.mc.currentScreen == null)
/*     */       {
/*  98 */         this.mc.setIngameFocus();
/*     */       }
/* 100 */       Hacks.clickGUI.onToggle();
/* 101 */       justPressed = true;
/*     */     } 
/* 103 */     if (this.listen) {
/*     */       
/* 105 */       if (this.listining.type == Settings.VALUE_TYPE && (Character.isDigit(typedChar) || typedChar == '-' || typedChar == '.')) {
/*     */         
/* 107 */         if (this.cache.equals("")) this.cache = ""; 
/* 108 */         this.cache += typedChar;
/*     */       } 
/* 110 */       if (this.listining.type == Settings.VALUE_TYPE && keyCode == 14)
/*     */       {
/* 112 */         this.cache = StringUtils.chop(this.cache);
/*     */       }
/* 114 */       if (this.listining.type == Settings.STRING_TYPE && !Character.isISOControl(typedChar)) {
/*     */         
/* 116 */         if (this.cache.equals("")) this.cache = ""; 
/* 117 */         this.cache += typedChar;
/*     */       } 
/* 119 */       if (this.listining.type == Settings.STRING_TYPE && keyCode == 14)
/*     */       {
/* 121 */         this.cache = StringUtils.chop(this.cache);
/*     */       }
/*     */     } 
/* 124 */     if (keyCode == 28) {
/*     */       
/* 126 */       this.listen = false;
/* 127 */       if (NClient.isHackExist(this.selButton.displayString)) {
/*     */         
/* 129 */         if (this.listining.type == Settings.VALUE_TYPE)
/*     */         {
/* 131 */           if (!this.cache.equals("")) {
/*     */             
/* 133 */             SettingValue sv = (SettingValue)this.listining;
/* 134 */             if (NumberUtils.isCreatable(this.cache))
/* 135 */               sv.setValue(Float.valueOf(this.cache).floatValue()); 
/* 136 */             sv.onUpdate();
/*     */           } 
/*     */         }
/* 139 */         if (this.listining.type == Settings.STRING_TYPE)
/*     */         {
/* 141 */           if (!this.cache.equals("")) {
/*     */             
/* 143 */             SettingString ss = (SettingString)this.listining;
/* 144 */             ss.setValue(this.cache);
/* 145 */             ss.onUpdate();
/*     */           } 
/*     */         }
/* 148 */         NClient.getHack(this.selButton.displayString).onUpdate();
/* 149 */         this.cache = "";
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean doesGuiPauseGame() {
/* 157 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getScale() {
/* 162 */     int scale = NClient.MC.gameSettings.guiScale;
/* 163 */     if (scale == 0)
/* 164 */       scale = 1000; 
/* 165 */     int scaleFactor = 0;
/* 166 */     while (scaleFactor < scale && NClient.MC.displayWidth / (scaleFactor + 1) >= 320 && NClient.MC.displayHeight / (scaleFactor + 1) >= 240)
/* 167 */       scaleFactor++; 
/* 168 */     if (scaleFactor == 0)
/* 169 */       scaleFactor = 1; 
/* 170 */     return scaleFactor;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseClicked(int x, int y, int mouseButton) {
/* 176 */     calculateMouse();
/* 177 */     x = mouseX;
/* 178 */     y = mouseY;
/* 179 */     for (List<Object> obj : this.buttonCoords) {
/*     */       
/* 181 */       Button b = (Button)obj.get(0);
/* 182 */       Integer x1 = (Integer)obj.get(1);
/* 183 */       Integer y1 = (Integer)obj.get(2);
/* 184 */       Integer x2 = (Integer)obj.get(3);
/* 185 */       Integer y2 = (Integer)obj.get(4);
/*     */       
/* 187 */       if (x > x1.intValue() && x < x2.intValue() && y > y1.intValue() && y < y2.intValue()) {
/*     */ 
/*     */         
/* 190 */         if (b.id < 9 && b.id > 0) {
/*     */           
/* 192 */           if (currentCategory == Category.getCategory(b.id)) {
/*     */             
/* 194 */             currentCategory = Category.ALL;
/* 195 */             reloadHacks(); continue;
/*     */           } 
/* 197 */           currentCategory = Category.getCategory(b.id);
/* 198 */           reloadHacks(); continue;
/*     */         } 
/* 200 */         if (b.id < 8096) {
/* 201 */           Hack h = NClient.getHack(b.displayString);
/* 202 */           if (mouseButton == 0)
/* 203 */             h.onToggle(); 
/* 204 */           if (mouseButton == 1)
/*     */           {
/* 206 */             (new Thread(() -> {
/*     */                   while (this.wait);
/*     */                   if (this.selButton != null && this.selButton.equals(b)) {
/*     */                     clearSettings(h);
/*     */                     this.selButton = null;
/*     */                   } else {
/*     */                     if (this.selButton != null) {
/*     */                       clearSettings(NClient.getHack(this.selButton.displayString));
/*     */                     }
/*     */                     this.selButton = b;
/*     */                     addSettings(h);
/*     */                   } 
/* 218 */                 })).start(); }  continue;
/*     */         } 
/* 220 */         if (b.id >= 8096) {
/* 221 */           if (b instanceof ButtonFloat) {
/*     */             
/* 223 */             ButtonFloat bf = (ButtonFloat)b;
/* 224 */             this.listining = (Setting)bf.parent;
/* 225 */             this.listen = true;
/* 226 */             this.listining.onUpdate();
/*     */           } 
/* 228 */           if (b instanceof ButtonString) {
/*     */             
/* 230 */             ButtonString bs = (ButtonString)b;
/* 231 */             this.listining = (Setting)bs.parent;
/* 232 */             this.listen = true;
/* 233 */             this.listining.onUpdate();
/*     */           } 
/* 235 */           if (b instanceof ButtonChoose) {
/*     */             
/* 237 */             ButtonChoose bc = (ButtonChoose)b;
/* 238 */             bc.mouseReleased(mouseX, mouseY);
/* 239 */             NClient.getHack(this.selButton.displayString).onUpdate();
/*     */           } 
/* 241 */           if (b instanceof ButtonBoolean) {
/*     */             
/* 243 */             ButtonBoolean bb = (ButtonBoolean)b;
/* 244 */             bb.mouseReleased(mouseX, mouseY);
/* 245 */             NClient.getHack(this.selButton.displayString).onUpdate();
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 250 */     this.wait = false;
/*     */   }
/*     */ 
/*     */   
/*     */   private void calculateMouse() {
/* 255 */     int scaleFactor = getScale();
/* 256 */     mouseX = Mouse.getX() / scaleFactor;
/* 257 */     mouseY = NClient.MC.displayHeight / scaleFactor - Mouse.getY() / scaleFactor - 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private void calculateButtons() {
/* 262 */     List<List<Object>> buttonCoords = new ArrayList<>();
/* 263 */     for (Button b : this.buttonList) {
/*     */       
/* 265 */       Integer _x1 = Integer.valueOf(b.x);
/* 266 */       Integer _y1 = Integer.valueOf(b.y);
/* 267 */       Integer _x2 = Integer.valueOf(b.x + b.width);
/* 268 */       Integer _y2 = Integer.valueOf(b.y + b.height);
/* 269 */       List<Object> data = new ArrayList();
/* 270 */       data.add(b);
/* 271 */       data.add(_x1);
/* 272 */       data.add(_y1);
/* 273 */       data.add(_x2);
/* 274 */       data.add(_y2);
/* 275 */       buttonCoords.add(data);
/*     */     } 
/* 277 */     this.buttonCoords = buttonCoords;
/*     */   }
/*     */ 
/*     */   
/*     */   private void addButtons() {
/* 282 */     int y = 30;
/* 283 */     this.buttonList.add(addButton(new Button(1, 10, y, 80, 20, "Misc")));
/* 284 */     y += 25; this.buttonList.add(addButton(new Button(2, 10, y, 80, 20, "Chat")));
/* 285 */     y += 25; this.buttonList.add(addButton(new Button(3, 10, y, 80, 20, "World")));
/* 286 */     y += 25; this.buttonList.add(addButton(new Button(4, 10, y, 80, 20, "Player")));
/* 287 */     y += 25; this.buttonList.add(addButton(new Button(5, 10, y, 80, 20, "Render")));
/* 288 */     y += 25; this.buttonList.add(addButton(new Button(6, 10, y, 80, 20, "Combat")));
/* 289 */     y += 25; this.buttonList.add(addButton(new Button(7, 10, y, 80, 20, "Exploit")));
/* 290 */     y += 25; this.buttonList.add(addButton(new Button(8, 10, y, 80, 20, "Movement")));
/* 291 */     addHacks();
/* 292 */     calculateButtons();
/*     */   }
/*     */ 
/*     */   
/*     */   private void addHacks() {
/* 297 */     int j = 0;
/* 298 */     int x2 = 95;
/* 299 */     int y2 = 30;
/* 300 */     List<Hack> hackList = NClient.HackList;
/* 301 */     for (int i = 0; i < hackList.size(); i++) {
/*     */       
/* 303 */       if (HackUtils.getCategory(hackList.get(i)) == currentCategory || currentCategory == Category.ALL) {
/*     */         
/* 305 */         String name = HackUtils.getName(hackList.get(i));
/* 306 */         if (j == 0)
/*     */         {
/* 308 */           this.buttonList.add(addButton(new Button(++this.ButtonID, x2, y2, 80, 20, name)));
/*     */         }
/* 310 */         if (j == 1)
/*     */         {
/* 312 */           this.buttonList.add(addButton(new Button(++this.ButtonID, x2 + 85, y2, 80, 20, name)));
/*     */         }
/* 314 */         if (j == 2) {
/*     */           
/* 316 */           this.buttonList.add(addButton(new Button(++this.ButtonID, x2 + 170, y2, 80, 20, name)));
/* 317 */           y2 += 25;
/* 318 */           j = -1;
/*     */         } 
/* 320 */         j++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void clearHacks() {
/* 327 */     for (int i = 0; i < this.buttonList.size(); i++) {
/*     */       
/* 329 */       if (NClient.isHackExist(((Button)this.buttonList.get(i)).displayString))
/*     */       {
/* 331 */         this.buttonList.remove(i);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void reloadHacks() {
/* 338 */     clearHacks();
/* 339 */     addHacks();
/* 340 */     calculateButtons();
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawStrings() {
/* 345 */     NClient.MC.fontRenderer.drawString("N-Client B1", 10, 10, -1);
/*     */   }
/*     */ 
/*     */   
/*     */   private void addSettings(Hack hack) {
/* 350 */     int settingID = 8096;
/* 351 */     int x = 355;
/* 352 */     int y = 30;
/* 353 */     int i = 0;
/* 354 */     for (Setting s : hack.settings) {
/*     */       
/* 356 */       if (s.getType() == Settings.VALUE_TYPE) {
/*     */         
/* 358 */         SettingValue sv = (SettingValue)s;
/* 359 */         Button b = new ButtonFloat(settingID, x, y, s.name, sv);
/* 360 */         sv.element = (ButtonFloat)b;
/* 361 */         this.buttonList.add(addButton(b));
/*     */       } 
/* 363 */       if (s.getType() == Settings.STRING_TYPE) {
/*     */         
/* 365 */         SettingString ss = (SettingString)s;
/* 366 */         Button b = new ButtonString(settingID, x, y, s.name, ss);
/* 367 */         ss.element = (ButtonString)b;
/* 368 */         this.buttonList.add(addButton(b));
/*     */       } 
/* 370 */       if (s.getType() == Settings.CHOOSE) {
/*     */         
/* 372 */         SettingChoose sc = (SettingChoose)s;
/* 373 */         Button b = new ButtonChoose(settingID, x, y, s.name, sc);
/* 374 */         sc.element = (ButtonChoose)b;
/* 375 */         this.buttonList.add(addButton(b));
/*     */       } 
/* 377 */       if (s.getType() == Settings.BOOLEAN) {
/*     */         
/* 379 */         SettingBoolean sb = (SettingBoolean)s;
/* 380 */         Button b = new ButtonBoolean(settingID, x, y, s.name, sb);
/* 381 */         sb.element = (ButtonBoolean)b;
/* 382 */         this.buttonList.add(addButton(b));
/*     */       } 
/* 384 */       s.id = settingID;
/* 385 */       settingID++;
/* 386 */       y += 25;
/* 387 */       i++;
/* 388 */       if (i > 18) {
/* 389 */         x += 125;
/* 390 */         y = 30;
/*     */       } 
/*     */     } 
/* 393 */     calculateButtons();
/*     */   }
/*     */   
/*     */   private void clearSettings(Hack hack) {
/*     */     int i;
/* 398 */     for (i = 0; i < this.buttonList.size(); i++) {
/*     */       
/* 400 */       for (Setting s : hack.settings) {
/*     */         
/* 402 */         if (((Button)this.buttonList.get(i)).getSetting() != null && s.id == (((Button)this.buttonList.get(i)).getSetting()).id)
/*     */         {
/* 404 */           if (((Button)this.buttonList.get(i)).id >= 8096)
/*     */           {
/* 406 */             this.buttonList.remove(i);
/*     */           }
/*     */         }
/*     */       } 
/*     */     } 
/* 411 */     for (i = 0; i < this.buttonList.size(); i++) {
/*     */       
/* 413 */       for (Setting s : hack.settings) {
/*     */         
/* 415 */         if (Button.getSetting(this.buttonList.get(i)) != null && s.id == (Button.getSetting((GuiButton)this.buttonList.get(i))).id)
/*     */         {
/* 417 */           if (((Button)this.buttonList.get(i)).id >= 8096)
/*     */           {
/* 419 */             this.buttonList.remove(i);
/*     */           }
/*     */         }
/*     */       } 
/*     */     } 
/* 424 */     calculateButtons();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawBackground(ResourceLocation texture) {
/* 429 */     GlStateManager.disableLighting();
/* 430 */     GlStateManager.disableFog();
/* 431 */     Tessellator tessellator = Tessellator.getInstance();
/* 432 */     BufferBuilder bufferbuilder = tessellator.getBuffer();
/* 433 */     NClient.MC.getTextureManager().bindTexture(texture);
/* 434 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 435 */     bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
/* 436 */     bufferbuilder.pos(0.0D, NClient.MC.currentScreen.height, 0.0D).tex(0.0D, 1.0D).color(64, 64, 64, 255).endVertex();
/* 437 */     bufferbuilder.pos(NClient.MC.currentScreen.width, NClient.MC.currentScreen.height, 0.0D).tex(1.0D, 1.0D).color(64, 64, 64, 255).endVertex();
/* 438 */     bufferbuilder.pos(NClient.MC.currentScreen.width, 0.0D, 0.0D).tex(1.0D, 0.0D).color(64, 64, 64, 255).endVertex();
/* 439 */     bufferbuilder.pos(0.0D, 0.0D, 0.0D).tex(0.0D, 0.0D).color(64, 64, 64, 255).endVertex();
/* 440 */     tessellator.draw();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawBackground() {
/* 445 */     drawBackground(NClient.background);
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawInfo() {
/* 450 */     String coords = "XYZ: [" + NClient.MC.player.posX + ", " + NClient.MC.player.posY + ", " + NClient.MC.player.posZ + "]";
/* 451 */     String nether = !(NClient.MC.world.getBiome(NClient.MC.player.getPosition()) instanceof net.minecraft.world.biome.BiomeHell) ? ("Nether: [" + (NClient.MC.player.posX / 8.0D) + ", " + NClient.MC.player.posY + ", " + (NClient.MC.player.posZ / 8.0D) + "]") : ("Overworld: [" + (NClient.MC.player.posX * 8.0D) + ", " + NClient.MC.player.posY + ", " + (NClient.MC.player.posZ * 8.0D) + "]");
/*     */ 
/*     */     
/* 454 */     String fps = "FPS: " + Minecraft.getDebugFPS();
/* 455 */     String tps = "TPS: " + NClient.tps;
/* 456 */     String ping = "PING: " + String.valueOf((NClient.MC.getCurrentServerData() == null) ? 0L : (NClient.MC.getCurrentServerData()).pingToServer);
/* 457 */     String biggest = (coords.length() > nether.length()) ? coords : nether;
/*     */     
/* 459 */     int y = 30;
/* 460 */     drawGradientRect(485, y, 490 + NClient.MC.fontRenderer.getStringWidth(biggest) + 5, y + 5 + 75, -1072689136, -804253680);
/* 461 */     y += 5; NClient.MC.fontRenderer.drawString(coords, 490, y, -1);
/* 462 */     y += 15; NClient.MC.fontRenderer.drawString(nether, 490, y, -1);
/* 463 */     y += 15; NClient.MC.fontRenderer.drawString(fps, 490, y, -1);
/* 464 */     y += 15; NClient.MC.fontRenderer.drawString(tps, 490, y, -1);
/* 465 */     y += 15; NClient.MC.fontRenderer.drawString(ping, 490, y, -1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void _drawGradientRect(int left, int top, int right, int bottom, int startColor, int endColor) {
/* 470 */     drawGradientRect(left, top, right, bottom, startColor, endColor);
/*     */   }
/*     */ }


/* Location:              /home/sn115426/Documents/NClient-B1-build0012-deobf.jar!/com/ngames/nclient/gui/Gui.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
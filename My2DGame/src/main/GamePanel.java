/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import entity.Player;
import tile.TileManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import object.SuperObject;
//import object.ObjectManager;


public class GamePanel extends JPanel implements Runnable {
    
    
    
    //GAME STATES
    public int  gameState;
    public final int pauseState = 0;
    public final int playState = 1;
//    public final int dialouges = 0;

    // SCREEN SETTINGS
    public final int tileSize = 64;
public final int screenWidth = 1024;
public final int screenHeight = 768;
public final int maxScreenCol = screenWidth / tileSize; // 21
public final int maxScreenRow = screenHeight / tileSize; // 16

    // WORLD SETTINGS
    public final int maxWorldCol = 100;
    public final int maxWorldRow = 100;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    
    public SuperObject obj[]= new SuperObject[10];
    
    // OBJECT SETTINGS
    //    public ObjectManager objManager;
        
    // FPS
    int FPS = 60;
    
//    long renderTime; // To store render time per frame
    
    long updateStart, updateEnd, renderStart, renderEnd;
    // For debug overlay
       private long updateTimeNs;
private long renderTimeNs;
private int fps;
private int frameCount;
private long fpsTimer = System.nanoTime();
private boolean showDebug = false;





    // SYSTEM
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CollisionDetection cDetect = new CollisionDetection(this);
    
   //ASSETS 
    public AssetSetter aSetter = new AssetSetter(this);
    


    // ENTITY
    public Player player = new Player(this, keyH);
    

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        
//        objManager = new ObjectManager(this);
//    objManager.addObject("/objects/tree.png", 480, 384);
//    objManager.addObject("/objects/rock.png", 720, 576);
    }
    
    public void setupGame(){
   
        aSetter.setObject();
        gameState = playState;

    
    
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
//
//   @Override
//public void run() {
//    double drawInterval = 1000000000.0 / FPS; // fixed integer division
//    double delta = 0;
//    long lastTime = System.nanoTime();
//    long currentTime;
//    long timer = 0;
//    int drawCount = 0;
//
//    while (gameThread != null) {
//        currentTime = System.nanoTime();
//        delta += (currentTime - lastTime) / drawInterval;
//        timer += (currentTime - lastTime);
//        lastTime = currentTime;
//
//        if (delta >= 1) {
//            update();
//            repaint();
//            delta--;
//            drawCount++;
//        }
//
////        if (timer >= 1000000000) {
////            System.out.println("FPS:" + drawCount);
////            drawCount = 0;
////            timer = 0;
////        }
//    }
//}

    
//    @Override
//public void run() {
//    double drawInterval = 1000000000.0 / FPS; // 1 frame = 1/60 sec
//    double delta = 0;
//    long lastTime = System.nanoTime();
//    long currentTime;
//    long timer = 0;
//    int drawCount = 0;
//    int frameCount = 0;
//
//    while (gameThread != null) {
//        currentTime = System.nanoTime();
//        delta += (currentTime - lastTime) / drawInterval;
//        timer += (currentTime - lastTime);
//        lastTime = currentTime;
//
//        if (delta >= 1) {
//
//            // ---- ⏱ Measure Update Time ----
//            updateStart = System.nanoTime();
//            update();
//            updateEnd = System.nanoTime();
//
//            // ---- ⏱ Measure Render Time ----
//            renderStart = System.nanoTime();
//            repaint();
//            renderEnd = System.nanoTime();
//
//            delta--;
//            drawCount++;
//            frameCount++;
//
//            // ---- 📊 Print timings every 60 frames (~1 sec) ----
//            if (frameCount % 60 == 0) {
//                double updateTime = (updateEnd - updateStart) / 1_000_000.0;
//                double renderTime = (renderEnd - renderStart) / 1_000_000.0;
//                System.out.printf("Update: %.4f ms | Render: %.4f ms%n", updateTime, renderTime);
//                frameCount = 0;
//            }
//        }
//
//        // Optional FPS tracker (can leave commented)
//        /*
//        if (timer >= 1000000000) {
//            System.out.println("FPS:" + drawCount);
//            drawCount = 0;
//            timer = 0;
//        }
//        */
//    }

    
    @Override
public void run() {
    double drawInterval = 1000000000.0 / FPS; // 1 frame = ~16.67ms
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;

    while (gameThread != null) {
        currentTime = System.nanoTime();
        delta += (currentTime - lastTime) / drawInterval;
        lastTime = currentTime;

        if (delta >= 1) {
            // --- Measure update ---
            updateStart = System.nanoTime();
            update();
            updateEnd = System.nanoTime();
            updateTimeNs = updateEnd - updateStart;

            // --- Measure render ---
            renderStart = System.nanoTime();
            repaint();
            renderEnd = System.nanoTime();
            renderTimeNs = renderEnd - renderStart;

            frameCount++;
            delta--;
        }

        // --- Calculate FPS every second ---
        if (System.nanoTime() - fpsTimer >= 1000000000) {
            fps = frameCount;
            frameCount = 0;
            fpsTimer = System.nanoTime();
        }
    }
}



    public void update() {
    player.update();

    // Toggle debug overlay when key pressed (one-shot)
    if (keyH.debugPressed) {
        showDebug = !showDebug;
        keyH.debugPressed = false; // consume it so press is handled once
    }

    // ... any other update logic
}


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // DRAW ORDER
        
        tileM.draw(g2);   // tiles
        
        //OBJECT
        
         for(int i = 0; i < obj.length; i++){
         
             if(obj[i] !=null){
               obj[i].draw(g2, this);
             
             }
         
         
         
         }
        
//         objManager.draw(g2);  // draw trees, rocks, etc. (original size)  
         
        player.draw(g2); // player on top
        
        
        // DEBUG OVERLAY
g2.setColor(Color.WHITE);
g2.drawString("FPS: " + fps, 20, 20);
g2.drawString(String.format("Update: %.4f ms", updateTimeNs / 1_000_000.0), 20, 40);
g2.drawString(String.format("Render: %.4f ms", renderTimeNs / 1_000_000.0), 20, 60);

        

        g2.dispose();
    }
    
    
//    int frameCount = 0;
//
//@Override
//public void paintComponent(Graphics g) {
//    super.paintComponent(g);
//    long startTime = System.nanoTime();
//
//    Graphics2D g2 = (Graphics2D) g;
//    tileM.draw(g2);
//    for (int i = 0; i < obj.length; i++) {
//        if (obj[i] != null) obj[i].draw(g2, this);
//    }
//    player.draw(g2);
//    g2.dispose();
//
//    long endTime = System.nanoTime();
//    renderTime = endTime - startTime;
//
//    frameCount++;
//    if (frameCount >= 60) {
//        System.out.println("Average render time (last 60 frames): " +
//            (renderTime / 1_000_000.0) + " ms");
//        frameCount = 0;
//    }
//}

}

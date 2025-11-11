/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//package object;
//
//import java.awt.Graphics2D;
//import java.awt.RenderingHints;
//import java.awt.image.BufferedImage;
//import java.io.IOException;
//import javax.imageio.ImageIO;
//import main.GamePanel;
//
//public class GameObject {
//
//    public BufferedImage image;
//    public String name;
//    public int worldX, worldY;
//    public int width, height;
//    public boolean collision = false;
//
//    public void loadImage(String path) {
//        try {
//            image = ImageIO.read(getClass().getResourceAsStream(path));
//            width = image.getWidth();
//            height = image.getHeight();
//            System.out.println(name + " loaded: " + width + "x" + height);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void draw(Graphics2D g2, GamePanel gp) {
//        // pixel-perfect rendering
//        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
//        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
//
//        int screenX = worldX - gp.player.worldX + gp.screenWidth / 2;
//        int screenY = worldY - gp.player.worldY + gp.screenHeight / 2;
//
//        g2.drawImage(image, screenX, screenY, width, height, null);
//        System.out.println(name + " drawn at: " + screenX + "," + screenY + " | Size: " + width + "x" + height);
//    }
//}

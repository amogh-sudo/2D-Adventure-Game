/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//package object;
//
////import java.awt.Graphics2D;
////import java.util.ArrayList;
////import javax.imageio.ImageIO;
////import main.GamePanel;
//
///**
// *
// * @author P M Amogh
// */
//public class ObjectManager {
//    
////        GamePanel gp;
////    public ArrayList<GameObject> objects = new ArrayList<>();
////
////    public ObjectManager(GamePanel gp) {
////        this.gp = gp;
////    }
////
////    public void addObject(String path, int worldX, int worldY) {
////        try {
////            GameObject obj = new GameObject();
////            obj.image = ImageIO.read(getClass().getResourceAsStream("/resources/trees/tree1.png"));
////            obj.worldX = worldX;
////            obj.worldY = worldY;
////            obj.width = obj.image.getWidth();
////            obj.height = obj.image.getHeight();
////            objects.add(obj);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////    }
////
////    public void draw(Graphics2D g2) {
////        for (GameObject obj : objects) {
////            int screenX = obj.worldX - gp.player.worldX + gp.player.screenX;
////            int screenY = obj.worldY - gp.player.worldY + gp.player.screenY;
////            g2.drawImage(obj.image, screenX, screenY, obj.width, obj.height, null);
////        }
////    }
//    
//}

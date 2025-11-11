/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    private final int solidAreaDefaultY;
    private final int solidAreaDefaultX;


    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        // Smaller collision box for tighter movement
        solidArea = new Rectangle(12, 24, gp.tileSize - 24, gp.tileSize - 24);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 24;
        worldY = gp.tileSize * 22;
        speed = 6;
        direction = "down";
    }

    public void getPlayerImage() {
      
        
        up1 = setup("boy_up_1");
        up1 = setup("boy_up_2");
        down1 = setup("boy_down_1");
        down2 = setup("boy_down_2");
        left1 = setup("boy_left_1");
        left2 = setup("boy_left_2");
        right1 = setup("boy_right_1");
        right2 = setup("boy_right_2");
    }
    
    public BufferedImage setup(String imageName){
    
        
        UtilityTool uTool = new UtilityTool ();
        BufferedImage image = null;
        
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/resources/player/"+imageName+".png"));
             image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        
        } catch(IOException e){
        
            e.printStackTrace();
        
        }
     return image;
    
    }

    public void update() {

        boolean moving = false;

        // Check input
        if (keyH.upPressed) {
            direction = "up";
            moving = true;
        } else if (keyH.downPressed) {
            direction = "down";
            moving = true;
        } else if (keyH.leftPressed) {
            direction = "left";
            moving = true;
        } else if (keyH.rightPressed) {
            direction = "right";
            moving = true;
        }

        collision = false;                  // reset collision
        gp.cDetect.checkTile(this);         // update collision based on tiles
        int objIndex = gp.cDetect.checkObject(this, true);
         pickUpObject(objIndex);

        if (moving && !collision) {         // move only if pressing keys and no collision
            switch (direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
            }

            // Animate sprite while moving
            spriteCounter++;
            if (spriteCounter > 5) {
                spriteNum = (spriteNum == 1) ? 2 : 1;
                spriteCounter = 0;
            }
        } else {
            // Reset sprite when not moving
            spriteNum = 1;
            spriteCounter = 0;
        }
    }
    
    
    
      public void pickUpObject(int i) {
    if (i != 999) {
        String objectName = gp.obj[i].name;

        switch (objectName) {
        case "Key" -> {
            hasKey++;
            System.out.println("Picked up a key! Keys now: " + hasKey);
            gp.obj[i] = null;
            }

         case "Door" -> {
         if (hasKey > 0) {
            System.out.println("Door opened!");
            gp.obj[i] = null;
            hasKey--;
          } else {
            System.out.println("You need a key!");
          }       }

        }
    }
}

    public void draw(Graphics2D g2) {

        BufferedImage image = switch (direction) {
            case "up" -> (spriteNum == 1) ? up1 : up2;
            case "down" -> (spriteNum == 1) ? down1 : down2;
            case "left" -> (spriteNum == 1) ? left1 : left2;
            case "right" -> (spriteNum == 1) ? right1 : right2;
            default -> down1;
        };

        g2.drawImage(image, screenX, screenY,  null);
//        System.out.println("Player image size: " + image.getWidth(null) + "x" + image.getHeight(null));
          
//              int playerSize = (int)(gp.tileSize * 0.75); // 75% of tile size
//g2.drawImage(image, screenX, screenY, playerSize, playerSize, null);

    }
    
  

}

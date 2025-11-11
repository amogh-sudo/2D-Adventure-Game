/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import javax.imageio.ImageIO;
import main.GamePanel;

/**
 *
 * @author P M Amogh
 */
public class OBJ_Door extends SuperObject{
    GamePanel gp;
    
        public OBJ_Door(GamePanel gp) {
            
            this.gp =gp;

      name = "Door";
      try{
      
          image = ImageIO.read(getClass().getResourceAsStream("/resources/Door/fire_door.png"));
      
         uTool.scaleImage(image,gp.tileSize,gp.tileSize);
      
      
      
      }catch(Exception e){}

  
      
      collision = true;
      solidArea.x = 0;
      solidArea.y = 0;
      solidArea.width = 48;
      solidArea.height = 48;
      solidAreaDefaultX = solidArea.x;
      solidAreaDefaultY = solidArea.y;
}
}

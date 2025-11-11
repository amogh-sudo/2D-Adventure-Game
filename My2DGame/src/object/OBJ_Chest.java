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
public class OBJ_Chest extends SuperObject {
    
      GamePanel gp;
    public OBJ_Chest(GamePanel gp) {
         this.gp =gp;
      name = "Chest";
      try{
      
          image = ImageIO.read(getClass().getResourceAsStream("/resources/tresure_chest/fire_chest.png"));
             uTool.scaleImage(image,gp.tileSize,gp.tileSize);
      
      
      
      
      }catch(Exception e){}
      
      
      collision = true;
      solidArea.x = 8;
      solidArea.y =6;
      solidArea.width = 32;
      solidArea.height = 24;
      solidAreaDefaultX = solidArea.x;
      solidAreaDefaultY = solidArea.y;

}
    
    
}

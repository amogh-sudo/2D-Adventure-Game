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
public class OBJ_Key extends SuperObject{
    
    GamePanel gp;
    
    public OBJ_Key(GamePanel gp) {
          this.gp=gp;
      name = "Key";
      try{
      
          image = ImageIO.read(getClass().getResourceAsStream("/resources/dungen1_key/fire_key.png"));
           
      uTool.scaleImage(image,gp.tileSize,gp.tileSize);
      
      
      }catch(Exception e){
      
      e.printStackTrace();
      
      }


      
      
      collision = false;  // can hover over the objects
      solidArea.x = 16;
      solidArea.y = 16;
      solidArea.width = 16;
      solidArea.height = 16;
      solidAreaDefaultX = solidArea.x;
      solidAreaDefaultY = solidArea.y;
      
}
    
    
    
    
}

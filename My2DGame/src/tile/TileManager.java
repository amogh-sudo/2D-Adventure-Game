/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author P M Amogh
 */
package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

public final class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[50]; // adjust if needed
        mapTileNum = new int[gp.maxWorldRow][gp.maxWorldCol];

        getTileImage();
        loadMap("/resources/maps/world_map01.txt");
    }

    public void getTileImage() {
        
            
            setup(0, "grass" , false);
            setup(1, "wall" , true);
            setup(2, "water" , true);
            setup(3, "earth" , false);
            setup(4, "tree" , true);
            setup(5, "sand" , false);
        
    }

    public void setup(int index, String imagePath, boolean collision){
    
    
        UtilityTool uTool = new UtilityTool();
        
        try{
        
             tile[index] = new Tile();
             tile[index].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/" + imagePath + ".png"));
             tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
             tile[index].collision = collision;
        
        
        }catch(IOException e){
        e.printStackTrace();
        }
    }
    public void loadMap(String filePath) {
        try (InputStream is = getClass().getResourceAsStream(filePath);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            for (int row = 0; row < gp.maxWorldRow; row++) {
                String line = br.readLine();
                if (line == null) break;

                String[] numbers = line.split(" ");
                for (int col = 0; col < gp.maxWorldCol; col++) {
                    mapTileNum[row][col] = Integer.parseInt(numbers[col]);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        for (int row = 0; row < gp.maxWorldRow; row++) {
            for (int col = 0; col < gp.maxWorldCol; col++) {
                int tileNum = mapTileNum[row][col];
                int worldX = col * gp.tileSize;
                int worldY = row * gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                // Draw only if within screen bounds
                if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                   g2.drawImage(tile[tileNum].image,screenX,screenY,null);
    

   
   
    
    


                }
            }
        }
    }
}

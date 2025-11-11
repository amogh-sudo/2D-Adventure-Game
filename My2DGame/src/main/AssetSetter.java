/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;
import object.SuperObject;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        // Example 1 – Key object
        gp.obj[0] = new OBJ_Key(gp);
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 8 * gp.tileSize;

        // Example 2 – Another tree object (if you add OBJ_Tree.java later)
        // gp.obj[1] = new OBJ_Tree();
        // gp.obj[1].worldX = 15 * gp.tileSize;
        // gp.obj[1].worldY = 10 * gp.tileSize;
        
        gp.obj[1] = new OBJ_Key(gp);
        gp.obj[1].worldX = 24 * gp.tileSize;
        gp.obj[1].worldY = 46 * gp.tileSize;
        
        gp.obj[2] = new OBJ_Key(gp);
        gp.obj[2].worldX = 13 * gp.tileSize;
        gp.obj[2].worldY = 14 * gp.tileSize;
        
        gp.obj[3] = new OBJ_Door(gp);
        gp.obj[3].worldX = 42 * gp.tileSize;
        gp.obj[3].worldY =21 * gp.tileSize;
        
        gp.obj[4] = new OBJ_Door(gp);
        gp.obj[4].worldX = 59 * gp.tileSize;
        gp.obj[4].worldY = 10 * gp.tileSize;
        
        gp.obj[5] = new OBJ_Door(gp);
        gp.obj[5].worldX = 59 * gp.tileSize;
        gp.obj[5].worldY = 13 * gp.tileSize;
        
        gp.obj[6] = new OBJ_Chest(gp);
        gp.obj[6].worldX = 59 * gp.tileSize;
        gp.obj[6].worldY = 8 * gp.tileSize;
        
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import entity.Entity;

/**
 *
 * @author P M Amogh
 */
public class CollisionDetection {
    
    GamePanel gp;
    public CollisionDetection(GamePanel gp){
     this.gp =gp;   
    
    }
   public void checkTile(Entity entity) {

    // Get entity edges in world coordinates
    int leftX = entity.worldX + entity.solidArea.x;
    int rightX = leftX + entity.solidArea.width;
    int topY = entity.worldY + entity.solidArea.y;
    int bottomY = topY + entity.solidArea.height;

    // Calculate current tile indices using scaled tileSize
    int leftCol = leftX / gp.tileSize;
    int rightCol = rightX / gp.tileSize;
    int topRow = topY / gp.tileSize;
    int bottomRow = bottomY / gp.tileSize;

    int tileNum1, tileNum2;

    entity.collision = false; // reset

    switch (entity.direction) {
        case "up":
            topRow = (topY - entity.speed) / gp.tileSize;
            topRow = Math.max(0, topRow);

            tileNum1 = gp.tileM.mapTileNum[topRow][leftCol];
            tileNum2 = gp.tileM.mapTileNum[topRow][rightCol];

            if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision)
                entity.collision = true;
            break;

        case "down":
            bottomRow = (bottomY + entity.speed) / gp.tileSize;
            bottomRow = Math.min(gp.maxWorldRow - 1, bottomRow);

            tileNum1 = gp.tileM.mapTileNum[bottomRow][leftCol];
            tileNum2 = gp.tileM.mapTileNum[bottomRow][rightCol];

            if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision)
                entity.collision = true;
            break;

        case "left":
            leftCol = (leftX - entity.speed) / gp.tileSize;
            leftCol = Math.max(0, leftCol);

            tileNum1 = gp.tileM.mapTileNum[topRow][leftCol];
            tileNum2 = gp.tileM.mapTileNum[bottomRow][leftCol];

            if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision)
                entity.collision = true;
            break;

        case "right":
            rightCol = (rightX + entity.speed) / gp.tileSize;
            rightCol = Math.min(gp.maxWorldCol - 1, rightCol);

            tileNum1 = gp.tileM.mapTileNum[topRow][rightCol];
            tileNum2 = gp.tileM.mapTileNum[bottomRow][rightCol];

            if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision)
                entity.collision = true;
            break;
    }
}
   
   
   public int checkObject(Entity entity, boolean player) {
    int index = 999;

    for (int i = 0; i < gp.obj.length; i++) {
        if (gp.obj[i] != null) {

            // Get entity solid area position
            entity.solidArea.x = entity.worldX + entity.solidArea.x;
            entity.solidArea.y = entity.worldY + entity.solidArea.y;

            // Get object solid area position
            gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
            gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

            // Predict movement
            switch (entity.direction) {
                case "up" -> entity.solidArea.y -= entity.speed;
                case "down" -> entity.solidArea.y += entity.speed;
                case "left" -> entity.solidArea.x -= entity.speed;
                case "right" -> entity.solidArea.x += entity.speed;
            }

            if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                if (gp.obj[i].collision) {
                    entity.collision = true;
                }
                if (player) {
                    index = i; // return index for player-object interaction
                }
            }

            // Reset positions
            entity.solidArea.x = entity.solidAreaDefaultX;
            entity.solidArea.y = entity.solidAreaDefaultY;
            gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
            gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
        }
    }

    return index;
}


    
}

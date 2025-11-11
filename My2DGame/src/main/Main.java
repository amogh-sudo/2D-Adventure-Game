/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import javax.swing.JFrame;
import database.DatabaseManager;
public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Rise of the World");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        
                DatabaseManager.connect();
        DatabaseManager.savePlayer("Amogh", 5, 1200, 90, 40);
        DatabaseManager.loadPlayers();

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}



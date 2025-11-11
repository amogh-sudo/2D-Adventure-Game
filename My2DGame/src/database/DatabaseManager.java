/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.*;

public class DatabaseManager {
    private static Connection conn;

    // 🧩 Connect to MySQL
  public static void connect() {
    try {
        // Explicitly load driver (needed sometimes in NetBeans)
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/game_data?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String user = "root"; // your MySQL username
        String pass = "4m0ghijk@2"; // your MySQL password

        conn = DriverManager.getConnection(url, user, pass);
        System.out.println("✅ Connected to MySQL 9.2.0 successfully!");
    } catch (ClassNotFoundException e) {
        System.out.println("❌ JDBC Driver not found. Make sure mysql-connector-j-9.2.0.jar is added to Libraries.");
    } catch (SQLException e) {
        System.out.println("❌ Database connection failed:");
        e.printStackTrace();
    }
}



    // 💾 Save player data
    public static void savePlayer(String name, int level, int score, int health, int coins) {
        try {
            String sql = "INSERT INTO player(name, level, score, health, coins) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, level);
            ps.setInt(3, score);
            ps.setInt(4, health);
            ps.setInt(5, coins);
            ps.executeUpdate();
            System.out.println("🎮 Player data saved!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 📖 Load player data
    public static void loadPlayers() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM player");

            while (rs.next()) {
                System.out.println(
                    "Player: " + rs.getString("name") +
                    " | Level: " + rs.getInt("level") +
                    " | Score: " + rs.getInt("score") +
                    " | HP: " + rs.getInt("health") +
                    " | Coins: " + rs.getInt("coins")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

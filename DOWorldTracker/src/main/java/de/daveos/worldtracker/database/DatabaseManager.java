package de.daveos.worldtracker.database;

import org.bukkit.configuration.file.FileConfiguration;

import java.sql.*;
import java.util.UUID;

public class DatabaseManager {

    private Connection connection;

    public void connect(FileConfiguration config) {
        String host = config.getString("database.host");
        String port = config.getString("database.port");
        String database = config.getString("database.name");
        String username = config.getString("database.username");
        String password = config.getString("database.password");

        String url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false";

        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Datenbankverbindung erfolgreich.");
        } catch (SQLException e) {
            System.err.println("Fehler beim Herstellen der Datenbankverbindung:");
            e.printStackTrace();
        }
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Fehler beim Schließen der Datenbankverbindung:");
                e.printStackTrace();
            }
        }
    }

    public void createTableIfNotExists() {
        String query = "CREATE TABLE IF NOT EXISTS player_deaths (uuid VARCHAR(36) PRIMARY KEY, deaths INT DEFAULT 0);";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Fehler beim Erstellen der Tabelle:");
            e.printStackTrace();
        }
    }

    public int getPlayerDeaths(UUID uuid) throws SQLException {
        String query = "SELECT deaths FROM player_deaths WHERE uuid = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, uuid.toString());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("deaths");
                }
            }
        }
        return 0;
    }

    public void incrementPlayerDeaths(UUID uuid) {
        try {
            int current = getPlayerDeaths(uuid) + 1;
            String query = "INSERT INTO player_deaths (uuid, deaths) VALUES (?, ?) ON DUPLICATE KEY UPDATE deaths = ?;";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, uuid.toString());
                stmt.setInt(2, current);
                stmt.setInt(3, current);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Fehler beim Aktualisieren der Todesanzahl:");
            e.printStackTrace();
        }
    }

    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
}

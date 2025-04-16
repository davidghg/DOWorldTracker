package de.daveos.worldtracker.util;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldColorUtil {

    private static FileConfiguration config;

    // Methode zum Initialisieren der Konfiguration
    public static void initialize(JavaPlugin plugin) {
        config = plugin.getConfig();
    }

    // Methode, um die Farbe für eine Welt zu bekommen
    public static String getColorForWorld(World world) {
        // Finde den Weltentyp (Normal, Nether, End)
        String worldType = world.getEnvironment().name().toLowerCase();

        // Lese die Farbwerte aus der config.yml
        String color = config.getString("worldColors." + worldType, config.getString("worldColors.default"));

        return color != null ? color : "§7";  // Standardfarbe zurückgeben, falls keine Farbe gefunden wurde
    }
}

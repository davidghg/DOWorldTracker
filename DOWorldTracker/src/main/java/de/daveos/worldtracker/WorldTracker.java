package de.daveos.worldtracker;

import de.daveos.worldtracker.command.WorldTrackerCommand;
import de.daveos.worldtracker.database.DatabaseManager;
import de.daveos.worldtracker.listener.*;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldTracker extends JavaPlugin {

    private DatabaseManager databaseManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.connect(getConfig());
        databaseManager.createTableIfNotExists();


        getCommand("worldtracker").setExecutor(new WorldTrackerCommand(this));

        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getServer().getPluginManager().registerEvents(new DeathListener(databaseManager), this);
        getServer().getPluginManager().registerEvents(new JoinListener(databaseManager), this);
        getServer().getPluginManager().registerEvents(new WorldChangeListener(databaseManager), this);

    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }
}

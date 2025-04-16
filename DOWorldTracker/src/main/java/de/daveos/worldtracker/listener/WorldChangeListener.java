package de.daveos.worldtracker.listener;

import de.daveos.worldtracker.database.DatabaseManager;
import de.daveos.worldtracker.util.WorldColorUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import java.sql.SQLException;

public class WorldChangeListener implements Listener {

    private final DatabaseManager databaseManager;

    public WorldChangeListener(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @EventHandler
    public void onPlayerChangeWorld(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        try {
            int deaths = databaseManager.getPlayerDeaths(player.getUniqueId());
            updateTabListName(player, deaths);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateTabListName(Player player, int deaths) {
        String worldColor = WorldColorUtil.getColorForWorld(player.getWorld());
        String tabListName = worldColor + ChatColor.DARK_GRAY + " | " + ChatColor.GRAY + player.getName() + " §7§e" + deaths;
        player.setPlayerListName(tabListName);
    }
}

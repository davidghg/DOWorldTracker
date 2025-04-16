package de.daveos.worldtracker.listener;

import de.daveos.worldtracker.database.DatabaseManager;
import de.daveos.worldtracker.util.WorldColorUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.sql.SQLException;
import java.util.UUID;

public class DeathListener implements Listener {

    private final DatabaseManager databaseManager;

    public DeathListener(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        UUID playerUUID = player.getUniqueId();

        try {
            // Erhöhe die Todesanzahl des Spielers in der Datenbank
            databaseManager.incrementPlayerDeaths(playerUUID);
            // Hol die Anzahl der Tode des Spielers
            int deaths = databaseManager.getPlayerDeaths(playerUUID);
            // Aktualisiere den Tab-List-Namen
            updateTabListName(player, deaths);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateTabListName(Player player, int deaths) {
        String worldColor = WorldColorUtil.getColorForWorld(player.getWorld());
        String tabListName = worldColor + " | " + player.getName() + " §7§e" + deaths;
        player.setPlayerListName(tabListName);
    }
}

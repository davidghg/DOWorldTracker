package de.daveos.worldtracker;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldTracker extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {

        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerChangeWorld(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        updateDisplayName(player);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        updateDisplayName(player);
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String displayName = player.getDisplayName();
        String message = event.getMessage();

        String formattedMessage = ChatColor.WHITE + message;

        event.setFormat(displayName + ": " + formattedMessage);
    }

    private void updateDisplayName(Player player) {
        World world = player.getWorld();
        String color;
        String worldType;

        switch (world.getEnvironment()) {
            case NORMAL -> {
                color = "§x§8§9§E§9§3§DO§x§6§9§E§9§5§3v§x§4§9§E§8§6§9e§x§2§9§E§8§7§Fr§x§0§9§E§7§9§5w§x§0§D§E§2§7§8o§x§1§1§D§E§5§Cr§x§1§4§D§9§3§Fl§x§1§8§D§4§2§2d";

            }
            case NETHER -> {
                color = "§x§E§C§1§3§1§3N§x§E§7§2§A§0§De§x§E§2§4§2§0§6t§x§D§D§5§9§0§0h§x§E§E§2§D§0§0e§x§F§F§0§0§0§0r";

            }
            case THE_END -> {
                color = "§x§C§3§8§F§D§AT§x§8§C§8§6§C§Dh§x§5§6§7§E§B§Fe§x§1§F§7§5§B§2 §x§3§8§5§8§A§EE§x§5§0§3§B§A§Bn§x§6§9§1§E§A§7d";
            }
            default -> {
                color = "§x§F§F§F§F§F§FD§x§F§F§F§F§F§Fe§x§F§F§F§F§F§Ff§x§F§F§F§F§F§Fa§x§F§F§F§F§F§Fu§x§F§F§F§F§F§Fl§x§F§F§F§F§F§Ft";
            }
        }

        String displayName = color + ChatColor.DARK_GRAY + " | " + ChatColor.WHITE + player.getName() ;
        player.setDisplayName(displayName); 
        player.setPlayerListName(displayName); 
    }

}

package de.daveos.worldtracker;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
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

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("coordinate")) {
            if (args.length == 2 && args[0].equalsIgnoreCase("get")) {
                String playerName = args[1];
                Player targetPlayer = Bukkit.getPlayer(playerName);

                if (targetPlayer != null) {
                    sender.sendMessage(getPlayerCoordinates(targetPlayer));
                } else {
                    sender.sendMessage(ChatColor.RED + "Dieser Spieler konnte nicht gefunden werden.");
                }
                return true;
            }
        }
        return false;
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
        ChatColor color;
        String worldType;

        switch (world.getEnvironment()) {
            case NORMAL -> {
                color = ChatColor.GREEN;
                worldType = "Overworld";
            }
            case NETHER -> {
                color = ChatColor.RED;
                worldType = "Nether";
            }
            case THE_END -> {
                color = ChatColor.LIGHT_PURPLE;
                worldType = "End";
            }
            default -> {
                color = ChatColor.WHITE;
                worldType = "[" + world.getName() + "]";
            }
        }

        String displayName = player.getName() + ChatColor.DARK_GRAY + " | " + color + worldType;
        player.setDisplayName(displayName); 
        player.setPlayerListName(displayName); 
    }

    public String getPlayerCoordinates(Player player) {
        World world = player.getWorld();
        ChatColor color;
        String worldType;

        switch (world.getEnvironment()) {
            case NORMAL -> {
                color = ChatColor.GREEN;
                worldType = "Overworld";
            }
            case NETHER -> {
                color = ChatColor.RED;
                worldType = "Nether";
            }
            case THE_END -> {
                color = ChatColor.LIGHT_PURPLE;
                worldType = "End";
            }
            default -> {
                color = ChatColor.WHITE;
                worldType = "[" + world.getName() + "]";
            }
        }
        int x = player.getLocation().getBlockX();
        int y = player.getLocation().getBlockY();
        int z = player.getLocation().getBlockZ();

        return ChatColor.BLUE + player.getName() + "'s Koordinaten: " + ChatColor.WHITE +
                "X: " + x + ", Y: " + y + ", Z: " + z + " | " + color + worldType;

}
}

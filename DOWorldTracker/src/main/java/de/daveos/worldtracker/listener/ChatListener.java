package de.daveos.worldtracker.listener;

import de.daveos.worldtracker.util.WorldColorUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String color = WorldColorUtil.getColorForWorld(player.getWorld());
        String name = player.getDisplayName();
        String msg = event.getMessage();

        event.setFormat(color + ChatColor.DARK_GRAY + " | " + ChatColor.GRAY + name + ": " + ChatColor.WHITE + msg);
    }
}

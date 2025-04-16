package de.daveos.worldtracker.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldTrackerCommand implements CommandExecutor {

    private final JavaPlugin plugin;

    public WorldTrackerCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("worldtracker.reload")) {
                plugin.reloadConfig();
                sender.sendMessage(ChatColor.GREEN + "Config reloaded!");
            } else {
                sender.sendMessage(ChatColor.RED + "Keine Berechtigung.");
            }
            return true;
        }

        sender.sendMessage(ChatColor.YELLOW + "Benutzung: /worldtracker reload");
        return true;
    }
}

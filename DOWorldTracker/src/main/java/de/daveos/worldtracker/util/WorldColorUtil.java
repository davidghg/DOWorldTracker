package de.daveos.worldtracker.util;

import org.bukkit.World;

public class WorldColorUtil {

    public static String getColorForWorld(World world) {
        return switch (world.getEnvironment()) {
            case NORMAL -> "§x§8§9§E§9§3§DO§x§6§9§E§9§5§3v§x§4§9§E§8§6§9e§x§2§9§E§8§7§Fr§x§0§9§E§7§9§5w§x§0§D§E§2§7§8o§x§1§1§D§E§5§Cr§x§1§4§D§9§3§Fl§x§1§8§D§4§2§2d";
            case NETHER -> "§x§E§C§1§3§1§3N§x§E§7§2§A§0§De§x§E§2§4§2§0§6t§x§D§D§5§9§0§0h§x§E§E§2§D§0§0e§x§F§F§0§0§0§0r";
            case THE_END -> "§x§C§3§8§F§D§AT§x§8§C§8§6§C§Dh§x§5§6§7§E§B§Fe§x§1§F§7§5§B§2 §x§3§8§5§8§A§EE§x§5§0§3§B§A§Bn§x§6§9§1§E§A§7d";
            default -> "§x§F§F§F§F§F§FD§x§F§F§F§F§F§Fe§x§F§F§F§F§F§Ff§x§F§F§F§F§F§Fa§x§F§F§F§F§F§Fu§x§F§F§F§F§F§Fl§x§F§F§F§F§F§Ft";
        };
    }
}

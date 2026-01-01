package me.rtpplugin.rtp.config;

import me.rtpplugin.rtp.RTPPlugin;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

    private final RTPPlugin plugin;

    public configManager(RTPPlugin plugin) {
        this.plugin = plugin;
    }

    public FileConfiguration getConfig() {
        return plugin.getConfig();
    }

    public String getMessage(String path) {
        String msg = getConfig().getString("messages." +path, "");
        return ChatColor.translateAlternateColorCodes("&", msg);
    }

    public int getMaxRadius(String worldName) {
        return getConfig().getInt("worlds." + worldName + ".max_radius", 1000);
    }

    public boolean isWorldEnabled(String worldName) {
        return getConfig().getBoolean("worlds." + worldName + ".enabled", false);
    }

    public int getMaxAttempts() {
        return getConfig().getInt("max_attempts", 10);
    }
}
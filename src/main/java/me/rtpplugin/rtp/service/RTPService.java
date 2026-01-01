package me.rtpplugin.rtp.service;

import me.rtpplugin.rtp.RTPPlugin;
import me.rtpplugin.rtp.config.ConfigManager;
import me.rtpplugin.rtp.safety.SafetyValidator;
import me.rtpplugin.rtp.util.RandomUtils;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class RTPService {

    private final RTPPlugin plugin;
    private final ConfigManager config;

    public RTPService(RTPPlugin plugin, ConfigManager config) {
        this.plugin = plugin;
        this.config = config;
    }

    public void findAndTeleport(Player player) {
        World world = player.getWorld();
        int maxRadius = config.getMaxRadius(world.getName());
        int attempts = config.getMaxAttempts();

        for (int i = 0; i < attempts; i++) {
            int x = RandomUtils.getRandomCoordinate(maxRadius);
            int z = RandomUtils.getRandomCoordinate(maxRadius);
            int y = world.getHighestBlockYAt(x, z);

            Location randomLoc = new Location(world, x + 0.5, y + 1, z + 0.5);

            if (SafetyValidator.isSafe(randomLoc)) {
                player.teleport(randomLoc);
                player.sendMessage(config.getMessage("success")
                        .replace("%x%", String.valueOf(x))
                        .replace("%z%",String.valueOf(z)));
                return;
            }
        }

        player.sendMessage(config.getMessage("fail"));
    }
}
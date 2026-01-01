package me.rtpplugin.rtp.command;

import me.rtpplugin.rtp.config.ConfigManager;
import me.rtpplugin.rtp.service.RTPService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RTPCommand implements CommandExecutor {

    private final ConfigManager config;
    private final RTPService rtpService;

    public RTPCommand(ConfigManager config, RTPService rtpService) {
        this.config = config;
        this.rtpService = rtpService;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Apenas jogadores podem usar o comando.");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("rtp.use")) {
            player.sendMessage(config.getMessage("no_permission"));
            return true;
        }

        if (!config.isWorldEnabled(player.getWorld().getName())) {
            player.sendMessage(config.getMessage("world_not_configured"));
            return true;
        }

        player.sendMessage(config.getMessage("searching"));
        rtpService.findAndTeleport(player);
        return true;
    }
}
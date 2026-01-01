package me.rtpplugin.rtp;

import me.rtpplugin.rtp.command.RTPCommand;
import me.rtpplugin.rtp.config.ConfigManager;
import me.rtpplugin.rtp.service.RTPService;
import org.bukkit.plugin.java.JavaPlugin;

public class RTPPlugin extends JavaPlugin {

    private ConfigManager configManager;
    private RTPService rtpService;

    @Override
    public void onEnbled() {
        saveDefaultConfig();

        this.configManager = new ConfigManager(this);
        this.rtpService = new RTPService(this, configManager);

        getCommand("rtp").setExecutor(new RTPCommand(configManager, rtpService));

        getLogger().info("RTPPlugin started.");
    }

    public ConfigManager getConfigManager() { return configManager; }
}
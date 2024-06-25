package org.curryman.cleverktitles;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class CleverKTitles extends JavaPlugin implements Listener {
    private static CleverKTitles instance;
    private Set<UUID> toggledPlayers;

    @Override
    public void onEnable() {
        instance = this;
        toggledPlayers = new HashSet<>();
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new PlayerKillListener(), this);
        if (getCommand("ckt") != null) {
            getCommand("ckt").setExecutor(new CKTCommand());
        } else {
            getLogger().severe("Command /ckt is not registered correctly in plugin.yml");
        }
        getLogger().info(colorize(getConfig().getString("messages.enabled", "&eCKT has been enabled!")));
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static CleverKTitles getInstance() {
        return instance;
    }

    public void reloadPluginConfig() {
        reloadConfig();
    }

    public String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public FileConfiguration getPluginConfig() {
        return getConfig();
    }

    public boolean isToggled(Player player) {
        return toggledPlayers.contains(player.getUniqueId());
    }

    public void togglePlayer(Player player) {
        if (toggledPlayers.contains(player.getUniqueId())) {
            toggledPlayers.remove(player.getUniqueId());
            player.sendMessage(colorize(getConfig().getString("messages.toggle_off", "&cKill titles disabled!")));
        } else {
            toggledPlayers.add(player.getUniqueId());
            player.sendMessage(colorize(getConfig().getString("messages.toggle_on", "&aKill titles enabled!")));
        }
    }
}
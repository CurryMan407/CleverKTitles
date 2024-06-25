package org.curryman.cleverktitles;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerKillListener implements Listener {
    @EventHandler
    public void onPlayerKill(PlayerDeathEvent event) {
        if (event.getEntity().getKiller() instanceof Player) {
            Player killer = event.getEntity().getKiller();
            Player victim = event.getEntity();
            CleverKTitles plugin = CleverKTitles.getInstance();

            if (plugin.isToggled(killer)) {
                String title = plugin.colorize(plugin.getPluginConfig().getString("title", "&cYou killed a player!").replace("%player%", victim.getName()));
                String subtitle = plugin.colorize(plugin.getPluginConfig().getString("subtitle", "&aNice job!").replace("%player%", victim.getName()));
                int duration = plugin.getPluginConfig().getInt("duration", 5) * 20;

                killer.sendTitle(title, subtitle, 10, duration, 10);
            }
        }
    }
}
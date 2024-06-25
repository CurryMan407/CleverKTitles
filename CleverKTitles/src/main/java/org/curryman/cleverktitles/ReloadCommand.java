package org.curryman.cleverktitles;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("cleverktiles.reload")) {
                CleverKTitles.getInstance().reloadPluginConfig();
                sender.sendMessage(CleverKTitles.getInstance().colorize("&aCleverKTitles config reloaded!"));
                return true;
            } else {
                sender.sendMessage(CleverKTitles.getInstance().colorize("&cYou do not have permission to use this command."));
                return true;
            }
        }
        return false;
    }
}
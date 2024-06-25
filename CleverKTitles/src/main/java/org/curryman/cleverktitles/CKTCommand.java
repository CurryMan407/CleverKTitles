package org.curryman.cleverktitles;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CKTCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            for (String line : CleverKTitles.getInstance().getPluginConfig().getStringList("messages.help")) {
                sender.sendMessage(CleverKTitles.getInstance().colorize(line));
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("cleverktiles.reload")) {
                CleverKTitles.getInstance().reloadPluginConfig();
                sender.sendMessage(CleverKTitles.getInstance().colorize(CleverKTitles.getInstance().getPluginConfig().getString("messages.reload", "&aCleverKTitles config reloaded!")));
                return true;
            } else {
                sender.sendMessage(CleverKTitles.getInstance().colorize("&cYou do not have permission to use this command."));
                return true;
            }
        }

        if (args[0].equalsIgnoreCase("toggle")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                CleverKTitles.getInstance().togglePlayer(player);
                return true;
            } else {
                sender.sendMessage(CleverKTitles.getInstance().colorize("&cOnly players can use this command."));
                return true;
            }
        }

        sender.sendMessage(CleverKTitles.getInstance().colorize(CleverKTitles.getInstance().getPluginConfig().getString("messages.invalid_command", "&cInvalid command! Use /ckt help for a list of commands.")));
        return true;
    }
}

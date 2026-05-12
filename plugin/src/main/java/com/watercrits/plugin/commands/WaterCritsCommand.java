package com.watercrits.plugin.commands;

import com.watercrits.plugin.WaterCritsPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public final class WaterCritsCommand implements CommandExecutor, TabCompleter {

    private final WaterCritsPlugin plugin;

    public WaterCritsCommand(WaterCritsPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("watercrits.admin")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
            return true;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            plugin.reloadConfig();
            plugin.getWaterConfig().reload();
            sender.sendMessage(ChatColor.GREEN + "[WaterCrits] Configuration reloaded.");
            return true;
        }

        sender.sendMessage(ChatColor.YELLOW + "WaterCrits v" + plugin.getDescription().getVersion());
        sender.sendMessage(ChatColor.GRAY + "Usage: /" + label + " reload");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) return List.of("reload");
        return List.of();
    }
}

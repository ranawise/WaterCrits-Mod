package com.watercrits.plugin;

import com.watercrits.plugin.commands.WaterCritsCommand;
import com.watercrits.plugin.listeners.AttackListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class WaterCritsPlugin extends JavaPlugin {

    private WaterCritsConfig waterConfig;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        waterConfig = new WaterCritsConfig(this);

        getServer().getPluginManager().registerEvents(new AttackListener(this), this);

        org.bukkit.command.PluginCommand cmd = getCommand("watercrits");
        if (cmd != null) {
            com.watercrits.plugin.commands.WaterCritsCommand handler = new com.watercrits.plugin.commands.WaterCritsCommand(this);
            cmd.setExecutor(handler);
            cmd.setTabCompleter(handler);
        }

        getLogger().info("WaterCrits v" + getDescription().getVersion() + " enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("WaterCrits disabled.");
    }

    public WaterCritsConfig getWaterConfig() {
        return waterConfig;
    }
}

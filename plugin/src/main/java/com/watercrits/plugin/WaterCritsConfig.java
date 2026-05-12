package com.watercrits.plugin;

import org.bukkit.configuration.file.FileConfiguration;


public final class WaterCritsConfig {

    private final WaterCritsPlugin plugin;

    private boolean blockNormalAttacks;
    private boolean blockSweepAttacks;
    private double  minCooldownForCrit;
    private boolean allowBypass;
    private String  blockedMessage;

    public WaterCritsConfig(WaterCritsPlugin plugin) {
        this.plugin = plugin;
        reload();
    }

    public void reload() {
        FileConfiguration cfg = plugin.getConfig();
        blockNormalAttacks = cfg.getBoolean("block-normal-attacks", true);
        blockSweepAttacks  = cfg.getBoolean("block-sweep-attacks",  true);
        minCooldownForCrit = cfg.getDouble("min-cooldown-for-crit", 0.9);
        allowBypass        = cfg.getBoolean("allow-bypass",         false);
        blockedMessage     = cfg.getString("blocked-message",
                "&cOnly critical hits are allowed while in water!");
    }

    public boolean isBlockNormalAttacks() { return blockNormalAttacks; }
    public boolean isBlockSweepAttacks()  { return blockSweepAttacks;  }
    public double  getMinCooldownForCrit(){ return minCooldownForCrit; }
    public boolean isAllowBypass()        { return allowBypass;        }
    public String  getBlockedMessage()    { return blockedMessage;     }
}

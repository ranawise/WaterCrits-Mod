package com.watercrits.plugin.listeners;

import com.watercrits.plugin.WaterCritsConfig;
import com.watercrits.plugin.WaterCritsPlugin;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public final class AttackListener implements Listener {

    private final WaterCritsPlugin plugin;

    public AttackListener(WaterCritsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onAttack(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;
        Player player = (Player) event.getDamager();
        
        if (!player.getLocation().getBlock().isLiquid()) return;

        WaterCritsConfig cfg = plugin.getWaterConfig();

        if (cfg.isAllowBypass() && player.hasPermission("watercrits.bypass")) return;

        DamageCause cause = event.getCause();

        if (cause.name().equals("ENTITY_SWEEP_ATTACK")) return;

        if (cause == DamageCause.ENTITY_ATTACK && cfg.isBlockNormalAttacks()) {
            event.setDamage(event.getDamage() * 1.5);
            spawnCritParticle(player, event.getEntity());
        }
    }

    private void spawnCritParticle(Player player, Entity target) {
        try {
            player.getWorld().spawnParticle(org.bukkit.Particle.valueOf("CRIT"), 
                target.getLocation().add(0, 1, 0), 15, 0.5, 0.5, 0.5, 0.1);
        } catch (Throwable t) {
        }
    }
}

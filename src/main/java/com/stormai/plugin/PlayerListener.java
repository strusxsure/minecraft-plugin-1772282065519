package com.stormai.plugin;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class PlayerListener implements Listener {

    private final Main plugin;

    public PlayerListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(EntityDeathEvent event) {
        if (!(event.getEntity() instanceof Player)) return;

        Player victim = (Player) event.getEntity();
        Player killer = victim.getKiller();

        if (killer == null || !(killer instanceof Player)) return;

        double victimHealth = killer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();

        if (victimHealth <= 2.0) {
            Bukkit.broadcastMessage(victim.getName() + " has been eliminated from the game!");
            victim.setHealth(0);
            victim.setGameMode(org.bukkit.GameMode.SPECTATOR);
            return;
        }

        double killerHealth = killer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
        if (killerHealth < 40.0) {
            killer.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(killerHealth + 2.0);
            killer.sendMessage("You gained 1 heart!");
        }

        victim.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(victimHealth - 2.0);
        victim.sendMessage("You lost 1 heart!");
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) return;

        Player player = (Player) event.getEntity();
        double currentHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();

        if (currentHealth <= 2.0 && event.getFinalDamage() >= player.getHealth()) {
            event.setCancelled(true);
            player.setHealth(0);
            player.setGameMode(org.bukkit.GameMode.SPECTATOR);
            Bukkit.broadcastMessage(player.getName() + " has been eliminated from the game!");
        }
    }
}
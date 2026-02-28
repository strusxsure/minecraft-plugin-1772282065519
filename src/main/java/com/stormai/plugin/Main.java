package com.stormai.plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("LifeSteal plugin enabled!");
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        getCommand("lifesteal").setExecutor(new LifeStealCommand(this));
        new HeartItemManager(this);
    }

    @Override
    public void onDisable() {
        getLogger().info("LifeSteal plugin disabled!");
    }
}
package com.stormai.plugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class LifeStealCommand implements CommandExecutor {

    private final Main plugin;

    public LifeStealCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage("/lifesteal withdraw <amount>");
            return true;
        }

        if (args[0].equalsIgnoreCase("withdraw")) {
            if (args.length < 2) {
                player.sendMessage("Usage: /lifesteal withdraw <amount>");
                return true;
            }

            int amount;
            try {
                amount = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                player.sendMessage("Invalid amount!");
                return true;
            }

            double currentHealth = player.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).getValue();
            double currentHP = player.getHealth();

            if (amount * 2 > currentHealth - 2) {
                player.sendMessage("You don't have enough max health to withdraw that amount!");
                return true;
            }

            for (int i = 0; i < amount; i++) {
                ItemStack heartItem = createHeartItem();
                if (player.getInventory().firstEmpty() == -1) {
                    player.getWorld().dropItemNaturally(player.getLocation(), heartItem);
                } else {
                    player.getInventory().addItem(heartItem);
                }
                player.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).setBaseValue(currentHealth - 2.0);
                currentHealth -= 2.0;
            }

            player.sendMessage("Withdrew " + amount + " heart(s)!");
            return true;
        }

        return false;
    }

    private ItemStack createHeartItem() {
        ItemStack item = new ItemStack(org.bukkit.Material.NETHER_STAR, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Heart");
        List<String> lore = new ArrayList<>();
        lore.add("Right-click to gain +1 max health");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
}
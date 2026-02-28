package com.stormai.plugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class HeartItemManager {

    private final Plugin plugin;

    public HeartItemManager(Plugin plugin) {
        this.plugin = plugin;
        registerHeartItem();
        registerRecipe();
    }

    private void registerHeartItem() {
        // Heart item is created on-demand in command
    }

    private void registerRecipe() {
        ItemStack result = new ItemStack(Material.NETHER_STAR, 1);
        ItemMeta meta = result.getItemMeta();
        meta.setDisplayName("Heart");
        List<String> lore = new ArrayList<>();
        lore.add("Right-click to gain +1 max health");
        meta.setLore(lore);
        result.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(plugin, "heart");
        ShapedRecipe recipe = new ShapedRecipe(key, result);

        recipe.shape("GGG", "GNG", "GGG");
        recipe.setIngredient('G', Material.GOLD_BLOCK);
        recipe.setIngredient('N', Material.NETHER_STAR);

        Bukkit.addRecipe(recipe);
    }
}
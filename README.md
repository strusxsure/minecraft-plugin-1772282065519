# LifeSteal Plugin for Paper/Spigot 1.21

A Minecraft plugin that implements lifesteal mechanics where players steal hearts from each other when killing.

## Features
- Players gain +1 max health when killing another player
- Victims lose -1 max health when killed
- Minimum health limit of 1 heart (2 HP)
- Maximum health limit of 20 hearts (40 HP)
- Players eliminated at 1 heart are banned (set to Spectator mode)
- Craftable Heart item using Nether Star and Gold Blocks
- Heart items can be consumed to gain +1 max health
- `/lifesteal withdraw` command to convert health to Heart items
- Health persists across server restarts

## Commands
- `/lifesteal withdraw <amount>` - Convert current health into Heart items

## Installation
1. Build the plugin using Maven: `mvn clean package`
2. Copy the generated JAR from `target/` to your server's `plugins/` directory
3. Restart your server

## Permissions
No special permissions required - all players can use the plugin features.

## Notes
- Health is managed using `Attribute.GENERIC_MAX_HEALTH` instead of deprecated methods
- The plugin uses Paper's API for 1.21 compatibility
- Heart items are crafted using: 3x3 grid with Gold Blocks surrounding a Nether Star
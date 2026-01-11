package org.xani4kaaddonfire.utils;

import org.bukkit.plugin.Plugin;

public class MessageUtils {
    
    public static void printStartupMessage(Plugin plugin) {
        plugin.getLogger().info("                      .__   _____  __            ");
        plugin.getLogger().info("___  ________    ____ |__| /  |  ||  | _______   ");
        plugin.getLogger().info("\\  \\/  /\\__  \\  /    \\|  |/   |  ||  |/ /\\__  \\  ");
        plugin.getLogger().info(" >    <  / __ \\|   |  \\  /    ^   /    <  / __ \\_");
        plugin.getLogger().info("/__/\\_ \\(____  /___|  /__\\____   ||__|_ \\(____  /");
        plugin.getLogger().info("      \\/     \\/     \\/        |__|     \\/     \\/ ");
        plugin.getLogger().info("Xani4kaAddonFire активирован!");
        plugin.getLogger().info("Telegram: https://t.me/HomeXani");
    }
    
    public static void printShutdownMessage(Plugin plugin) {
        plugin.getLogger().info("                      .__   _____  __            ");
        plugin.getLogger().info("___  ________    ____ |__| /  |  ||  | _______   ");
        plugin.getLogger().info("\\  \\/  /\\__  \\  /    \\|  |/   |  ||  |/ /\\__  \\  ");
        plugin.getLogger().info(" >    <  / __ \\|   |  \\  /    ^   /    <  / __ \\_");
        plugin.getLogger().info("/__/\\_ \\(____  /___|  /__\\____   ||__|_ \\(____  /");
        plugin.getLogger().info("      \\/     \\/     \\/        |__|     \\/     \\/ ");
        plugin.getLogger().info("Xani4kaAddonFire деактивирован!");
        plugin.getLogger().info("Telegram: https://t.me/HomeXani");
    }
}
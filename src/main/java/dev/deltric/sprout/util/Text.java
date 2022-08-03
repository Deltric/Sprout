package dev.deltric.sprout.util;

import org.bukkit.ChatColor;

public class Text {

    public static String color(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

}

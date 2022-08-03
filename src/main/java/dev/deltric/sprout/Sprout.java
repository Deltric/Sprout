package dev.deltric.sprout;

import org.bukkit.plugin.java.JavaPlugin;

public class Sprout extends JavaPlugin {

    private static Sprout instance;

    @Override
    public void onEnable() {
        instance = this;
    }

    public static Sprout getInstance() {
        return instance;
    }
}
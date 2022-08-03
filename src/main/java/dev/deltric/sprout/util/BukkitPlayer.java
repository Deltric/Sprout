package dev.deltric.sprout.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BukkitPlayer {

    public static Player from(ServerPlayerEntity player) {
        return Bukkit.getServer().getPlayer(player.getUniqueID());
    }

    public static Player from(PlayerEntity player) {
        return Bukkit.getServer().getPlayer(player.getUniqueID());
    }

    public static Player from(UUID uuid) {
        return Bukkit.getServer().getPlayer(uuid);
    }

    public static Player from(String name) {
        return Bukkit.getServer().getPlayer(name);
    }

    public static List<Player> getAll() {
        return new ArrayList<>(Bukkit.getServer().getOnlinePlayers());
    }

}
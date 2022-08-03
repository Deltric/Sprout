package dev.deltric.sprout.util;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class ForgePlayer {

    public static ServerPlayerEntity from(Player player) {
        return ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayerByUUID(player.getUniqueId());
    }

    public static ServerPlayerEntity from(UUID uuid) {
        return ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayerByUUID(uuid);
    }

    public static ServerPlayerEntity from(String name) {
        return ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayerByUsername(name);
    }

    public static List<ServerPlayerEntity> getAll() {
        return ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayers();
    }

}

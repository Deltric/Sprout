package dev.deltric.sprout.util;

import net.minecraft.entity.player.ServerPlayerEntity;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandHelper {

    /**
     * Runs a list of commands.
     * Replaces all %player% placeholders with the players name.
     *
     * @param player - player for player placeholder
     * @param commands - commands to run
     */
    public static void run(Player player, List<String> commands) {
        Server server = Bukkit.getServer();
        CommandSender console = Bukkit.getConsoleSender();

        for (String command : commands) {
            server.dispatchCommand(console, command.replaceAll("%player%", player.getName()));
        }
    }

    /**
     * Runs a list of commands.
     * Replaces all %player% placeholders with the players name.
     *
     * @param player - player for player placeholder
     * @param commands - commands to run
     */
    public static void run(ServerPlayerEntity player, List<String> commands) {
        CommandHelper.run(BukkitPlayer.from(player), commands);
    }
}
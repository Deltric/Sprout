package dev.deltric.sprout.util;

import net.minecraft.entity.player.ServerPlayerEntity;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandHelper {

    public void run(Player player, List<String> commands) {
        Server server = Bukkit.getServer();
        CommandSender console = Bukkit.getConsoleSender();

        for (String command : commands) {
            server.dispatchCommand(console, command);
        }
    }

    public void run(ServerPlayerEntity player, List<String> commands) {
        this.run(BukkitPlayer.from(player), commands);
    }

}
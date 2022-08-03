package dev.deltric.sprout.inventory;

import dev.deltric.sprout.Sprout;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public abstract class SproutInventory implements Listener {

    private Inventory rootInventory;
    private final Map<Integer, SproutButton> buttons = new HashMap<>();

    public SproutInventory(final Player player, final int rows) {
        Bukkit.getPluginManager().registerEvents(this, Sprout.getInstance());
        Bukkit.getScheduler().runTaskLater(Sprout.getInstance(), () -> {
            this.rootInventory = Bukkit.createInventory(player, rows * 9, this.getTitle(player));
            this.setupInventory(player);
            player.openInventory(this.rootInventory);
        }, 1);
    }

    public SproutInventory(final Player player, final InventoryType inventoryType) {
        this.rootInventory = Bukkit.createInventory(player, inventoryType, this.getTitle(player));
        Bukkit.getPluginManager().registerEvents(this, Sprout.getInstance());
        Bukkit.getScheduler().runTaskLater(Sprout.getInstance(), () -> {
            this.setupInventory(player);
            player.openInventory(this.rootInventory);
        }, 1);
    }

    public abstract String getTitle(final Player player);

    public void setupInventory(final Player player) {

    }

    public void closeInventory(final Player player) {

    }

    public boolean canBeClosed(final Player player) {
        return true;
    }

    public void setButton(final int slot, final SproutButton button) {
        this.buttons.put(slot, button);
    }

    public void setDisplayIcon(final int slot, final ItemStack itemStack) {
        this.rootInventory.setItem(slot, itemStack);
    }

    public void clearAll() {
        this.buttons.clear();
        this.rootInventory.clear();
    }

    @EventHandler
    private void onInventoryClick(final InventoryClickEvent event) {
        // Confirm that this is this SproutInventory
        if (event.getClickedInventory() == null) return;
        if (!event.getClickedInventory().equals(this.rootInventory)) return;

        // Cancel the event
        event.setCancelled(true);

        // Check for button
        final SproutButton button = this.buttons.get(event.getRawSlot());
        if (button != null) {
            button.onClick(event);
        }
    }

    @EventHandler
    private void closeInventory(final InventoryCloseEvent event) {
        if (!event.getInventory().equals(this.rootInventory)) return;

        final Player player = (Player) event.getPlayer();
        if (this.canBeClosed(player)) {
            event.getHandlers().unregister(this);
            this.closeInventory(player);
        } else {
            player.openInventory(this.rootInventory);
        }
    }
}
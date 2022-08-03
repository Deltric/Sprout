package dev.deltric.sprout.inventory;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class SproutButton {

    private ItemStack icon;

    public SproutButton(ItemStack icon) {
        this.icon = icon;
    }

    public void onClick(InventoryClickEvent context) {

    }

    public ItemStack getIcon() {
        return icon;
    }

    public void setIcon(ItemStack icon) {
        this.icon = icon;
    }

    public static SproutButton of(ItemStack icon) {
        return new SproutButton(icon);
    }

    public static SproutButton of(ItemStack icon, Consumer<InventoryClickEvent> onClick) {
        return new SproutButton(icon) {
            @Override
            public void onClick(InventoryClickEvent context) {
                onClick.accept(context);
            }
        };
    }

}
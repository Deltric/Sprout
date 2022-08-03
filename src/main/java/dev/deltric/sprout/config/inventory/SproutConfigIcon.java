package dev.deltric.sprout.config.inventory;

import net.minecraft.server.v1_16_R3.IRegistry;
import net.minecraft.server.v1_16_R3.Item;
import net.minecraft.server.v1_16_R3.MinecraftKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class SproutConfigIcon {

    private String type;
    private String name;
    private List<String> lore;
    private int amount;

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public List<String> getLore() {
        return lore;
    }

    public ItemStack create() {
        Item item = IRegistry.ITEM.get(MinecraftKey.a(type));
        net.minecraft.server.v1_16_R3.ItemStack nativeStack = new net.minecraft.server.v1_16_R3.ItemStack(item);
        //nativeStack.getOrCreateTag().
        ItemStack itemStack = CraftItemStack.asBukkitCopy(nativeStack);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.name);
        itemMeta.setLore(this.lore);
        itemStack.setItemMeta(itemMeta);
        itemStack.setAmount(this.amount);
        return itemStack;
    }
}
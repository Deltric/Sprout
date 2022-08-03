package dev.deltric.sprout.inventory.configured;

import dev.deltric.sprout.Sprout;
import dev.deltric.sprout.config.inventory.SproutConfigButton;
import dev.deltric.sprout.config.inventory.SproutConfigDecoration;
import dev.deltric.sprout.config.inventory.SproutConfigIcon;
import dev.deltric.sprout.config.inventory.SproutLayout;
import dev.deltric.sprout.config.inventory.SproutPage;
import dev.deltric.sprout.inventory.SproutButton;
import dev.deltric.sprout.inventory.SproutInventory;
import dev.deltric.sprout.util.Text;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.function.Function;

public abstract class SproutConfiguredInventory extends SproutInventory {

    private int pageNum = 0;
    private final SproutLayout layout;

    public SproutConfiguredInventory(Player player, SproutLayout layout) {
        super(player, layout.getInventoryRows());
        this.layout = layout;
    }

    public abstract Map<String, Function<ItemStack, SproutButton>> setupButtons();

    @Override
    public void setupInventory(Player player) {
        final Map<String, Function<ItemStack, SproutButton>> buttons = this.setupButtons();
        final SproutPage page = this.layout.getPage(pageNum);

        // Reset all past buttons and items
        this.clearAll();

        // Decorations
        for (SproutConfigDecoration decoration : page.getDecorations()) {
            final SproutConfigIcon icon = this.layout.getIconTags().get(decoration.getIconTag());

            if (icon == null) {
                // Print out that icon doesn't exist
                Sprout.getInstance().getLogger().warning("Decoration icon labeled " + decoration.getIconTag() + " does not exist in: " + this.layout.getLayoutId());
                continue;
            }

            ItemStack decorStack = icon.create();
            for (int slot : decoration.getSlots()) {
                this.setDisplayIcon(slot, decorStack);
            }
        }

        // Buttons
        for (Map.Entry<Integer, SproutConfigButton> buttonEntry : page.getButtons().entrySet()) {
            final SproutConfigButton configButton = buttonEntry.getValue();
            final SproutConfigIcon icon = this.layout.getIconTags().get(configButton.getIconTag());

            if (icon == null) {
                Sprout.getInstance().getLogger().warning("Button icon labeled " + configButton.getIconTag() + " does not exist in: " + this.layout.getLayoutId());
                continue;
            }

            if (!buttons.containsKey(configButton.getAction())) {
                // Print out that action doesn't exist
                continue;
            }

            SproutButton button = buttons.get(configButton.getAction())
                    .apply(icon.create());
            this.setButton(buttonEntry.getKey(), button);
        }
    }

    public void nextPage(Player player) {
        final SproutPage page = this.layout.getPage(this.pageNum + 1);
        if (page != null) {
            this.pageNum += 1;
            this.setupInventory(player);
        }
    }

    public void previousPage(Player player) {
        final SproutPage page = this.layout.getPage(this.pageNum - 1);
        if (page != null) {
            this.pageNum -= 1;
            this.setupInventory(player);
        }
    }

    @Override
    public String getTitle(Player player) {
        return Text.color(this.layout.getInventoryTitle());
    }
}

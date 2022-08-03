package dev.deltric.sprout.config.inventory;

import java.util.List;
import java.util.Map;

public class SproutPage {
    private List<SproutConfigDecoration> decorations;
    private Map<Integer, SproutConfigButton> buttons;

    public List<SproutConfigDecoration> getDecorations() {
        return decorations;
    }

    public Map<Integer, SproutConfigButton> getButtons() {
        return buttons;
    }
}

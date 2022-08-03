package dev.deltric.sprout.config.inventory;

import java.util.Map;

public class SproutLayout {

    private String id;
    private String inventoryTitle;
    private int inventoryRows;

    private SproutPage[] pages;
    private Map<String, SproutConfigIcon> iconTags;

    public String getLayoutId() {
        return id;
    }

    public int getInventoryRows() {
        return this.inventoryRows;
    }

    public String getInventoryTitle() {
        return inventoryTitle;
    }

    public SproutPage getPage(int pageNum) {
        if (pageNum >= this.pages.length) {
            return null;
        }
        if (pageNum < 0) {
            return null;
        }
        return this.pages[pageNum];
    }

    public Map<String, SproutConfigIcon> getIconTags() {
        return iconTags;
    }
}
package com.ultimatestattracker;


import com.google.inject.Inject;
import net.runelite.api.Client;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.PanelComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;

import static com.ultimatestattracker.StatKeys.*;
import java.awt.*;
import java.util.Map;

public class UltimateStatTrackerOverlay extends Overlay
{
    private final Client client;

    private boolean visible = false;

    private Rectangle closeButtonBounds = null;
    private final PanelComponent panel = new PanelComponent();
    private static final Map<String, String> STAT_LABELS = Map.ofEntries(
            Map.entry(EXAMINE, "Things Examined"),
            Map.entry(ITEMS_DROPPED, "Items Dropped"),
            Map.entry(SHOP_GP_SPENT, "Shop GP Spent"),
            Map.entry(SHOP_GP_GAINED, "Shop GP Gained"),
            Map.entry(SPELLS_CAST, "Spells Cast"),
            Map.entry(TELEPORTS_CAST, "Teleports Cast"),
            Map.entry(TILES_WALKED, "Tiles Walked"),
            Map.entry(TILES_RAN, "Tiles Ran"),
            Map.entry(FISH_CAUGHT, "Fish Caught"),
            Map.entry(ROCKS_MINED, "Rocks Mined"),
            Map.entry(LOGS_CHOPPED, "Logs Chopped"),
            Map.entry(BONES_BURIED, "Bones Buried"),
            Map.entry(BONES_CRUSHED, "Bones Crushed"),
            Map.entry(ASHES_SCATTERED, "Ashes Scattered"),
            Map.entry(LOGS_BURNED, "Logs Burned"),
            Map.entry(TOTAL_DEATHS, "Total Deaths"),
            Map.entry(FOOD_EATEN, "Food Eaten"),
            Map.entry(HP_REGEN, "HP Regen"),
            Map.entry(BEER_DRANK, "Beer Drank"),
            Map.entry(CABBAGE_EATEN, "Cabbage Eaten"),
            Map.entry(TROUT_EATEN, "Trout Eaten"),
            Map.entry(FLAX_PICKED, "Flax Picked"),
            Map.entry(CABBAGE_PICKED, "Cabbage Picked"),
            Map.entry(CRITTERS_PET, "Critters Pet"),
            Map.entry(POTION_SIPS_DRANK, "Potion Sips"),
            Map.entry(VIALS_SMASHED, "Vials Smashed"),
            Map.entry(PICK_POCKETS, "Pickpockets"),
            Map.entry(STALLS_THIEVED, "Stalls Thieved"),
            Map.entry(WEEDS_RAKED, "Weeds Raked"),
            Map.entry(SEEDS_PLANTED, "Seeds Planted"),
            Map.entry(MELEE_ATTACKS_LANDED, "Melee Hits"),
            Map.entry(ROOF_TOP_AGILITY, "Rooftop Laps"),
            Map.entry(NORMAL_AGILITY, "Agility Laps"),
            Map.entry(IMPLINGS_CAUGHT, "Implings Caught"),
            Map.entry(FOOD_COOKED, "Food Cooked"),
            Map.entry(FOOD_BURNED, "Food Burned"),
            Map.entry(FAILED_PICK_POCKETS, "Failed Pickpockets"),
            Map.entry(UNFINISHED_POTIONS_MADE, "Unfinished Potions"),
            Map.entry(POTIONS_MADE, "Potions Made"),
            Map.entry(HERBS_CLEANED, "Herbs Cleaned"),
            Map.entry(SPECIAL_ATTACKS_PREFORMED, "Special Attacks Performed"),
            Map.entry(TOTAL_DAMAGE_DONE, "Total Damage Done"),
            Map.entry(BIGGEST_HITSPLAT, "Biggest Hitsplat"),
            Map.entry(RUNES_CRAFTED, "Runes Crafted"),
            Map.entry(GEMS_CUT, "Gems Cut"),
            Map.entry(GLASS_BLOWN, "Glass Blown"),
            Map.entry(BOWS_FLECTHED, "Bows Fletched"),
            Map.entry(BOWS_STRUNG, "Bows Strung"),
            Map.entry(DARTS_FLECTHED, "Darts Fletched"),
            Map.entry(CREATURES_TRAPPED, "Creatures Trapped"),
            Map.entry(BARS_SMELTED, "Bars Smelted"),
            Map.entry(ITEMS_SMITHED, "Items Smithed")
    );

    // Overlay dimensions
    private final int panelWidth = 300;
    private final int panelHeight = 200;

    private StatStore statStore;

    public void setStatStore(StatStore store)
    {
        this.statStore = store;
    }

    @Inject
    public UltimateStatTrackerOverlay(Client client)
    {
        this.client = client;
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_WIDGETS);
    }

    public void open()
    {
        visible = true;
    }

    public void close()
    {
        visible = false;
    }

    public void toggle()
    {
        visible = !visible;
    }

    public boolean isInCloseButtonBounds(Point p) {
        return closeButtonBounds != null && closeButtonBounds.contains(p);
    }

    public boolean isInBounds(Point p) {
        if (!visible) return false;
        int drawX = client.getViewportXOffset() + client.getViewportWidth() / 2 - panelWidth / 2;
        int drawY = client.getViewportYOffset() + 40;
        return new Rectangle(drawX, drawY, panelWidth, panelHeight).contains(p);
    }

    public boolean isVisible() {
        return visible;
    }


    @Override
    public Dimension render(Graphics2D g)
    {
        if (!visible || statStore == null)
        {
            return null;
        }

        panel.getChildren().clear();

        panel.setPreferredSize(new Dimension(300, 0)); // height auto
        panel.setBackgroundColor(new Color(40, 35, 30, 230));

        // Title
        panel.getChildren().add(TitleComponent.builder()
                .text("Ultimate Stat Tracker")
                .color(Color.ORANGE)
                .build());

        // Stats
        for (Map.Entry<String, String> entry : STAT_LABELS.entrySet())
        {
            int value = statStore.getStat(entry.getKey());

            panel.getChildren().add(LineComponent.builder()
                    .left(entry.getValue())
                    .right(String.valueOf(value))
                    .build());
        }

        return panel.render(g);
    }
}

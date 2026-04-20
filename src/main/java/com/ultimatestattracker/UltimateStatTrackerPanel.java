package com.ultimatestattracker;

import net.runelite.client.ui.PluginPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

import static com.ultimatestattracker.StatKeys.*;

public class UltimateStatTrackerPanel extends PluginPanel
{
    private final JPanel content = new JPanel();
    private StatStore statStore;

    private String filter = "";

    private static final Map<String, String> STAT_LABELS = Map.ofEntries(
            Map.entry(EXAMINE, "Things Examined"),
            Map.entry(ITEMS_DROPPED, "Items Dropped"),
            Map.entry(SHOP_GP_SPENT, "Shop GP Spent"),
            Map.entry(SHOP_GP_GAINED, "Shop GP Gained"),
            Map.entry(TILES_WALKED, "Tiles Walked"),
            Map.entry(TILES_RAN, "Tiles Ran"),
            Map.entry(FISH_CAUGHT, "Fish Caught"),
            Map.entry(ROCKS_MINED, "Rocks Mined"),
            Map.entry(LOGS_CHOPPED, "Logs Chopped"),
            Map.entry(BONES_BURIED, "Bones Buried"),
            Map.entry(ASHES_SCATTERED, "Ashes Scattered"),
            Map.entry(LOGS_BURNED, "Logs Burned"),
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
            Map.entry(ROOF_TOP_AGILITY, "Rooftop Laps"),
            Map.entry(NORMAL_AGILITY, "Agility Laps"),
            Map.entry(IMPLINGS_CAUGHT, "Implings Caught"),
            Map.entry(FOOD_COOKED, "Food Cooked"),
            Map.entry(FOOD_BURNED, "Food Burned"),
            Map.entry(FAILED_PICK_POCKETS, "Failed Pickpockets"),
            Map.entry(UNFINISHED_POTIONS_MADE, "Unfinished Potions"),
            Map.entry(POTIONS_MADE, "Potions Made"),
            Map.entry(HERBS_CLEANED, "Herbs Cleaned"),
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

    @Override
    public void onActivate()
    {
        rebuild();
    }

    public UltimateStatTrackerPanel()
    {
        setLayout(new BorderLayout());

        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(new Color(40, 35, 30));

        JScrollPane scrollPane = new JScrollPane(content);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void setStatStore(StatStore store)
    {
        this.statStore = store;
        rebuild();
    }

    public void rebuild()
    {
        content.removeAll();


        JButton refreshButton = new JButton("Refresh");
        refreshButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        refreshButton.addActionListener(e -> rebuild());

        content.add(refreshButton);
        content.add(Box.createVerticalStrut(10));

        JTextField searchField = new JTextField();
        searchField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        searchField.setAlignmentX(Component.LEFT_ALIGNMENT);

        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener()
        {
            private void update()
            {
                filter = searchField.getText().toLowerCase();
                rebuild();
            }

            @Override public void insertUpdate(javax.swing.event.DocumentEvent e) { update(); }
            @Override public void removeUpdate(javax.swing.event.DocumentEvent e) { update(); }
            @Override public void changedUpdate(javax.swing.event.DocumentEvent e) { update(); }
        });
        add(searchField, BorderLayout.NORTH);
        JLabel title = new JLabel("Ultimate Stat Tracker");
        title.setForeground(Color.ORANGE);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        content.add(title);

        content.add(Box.createVerticalStrut(10));

        if (statStore != null)
        {
            for (Map.Entry<String, String> entry : STAT_LABELS.entrySet())
            {
                String label = entry.getValue();

                if (!filter.isEmpty() && !label.toLowerCase().contains(filter))
                {
                    continue;
                }

                int value = statStore.getStat(entry.getKey());

                JPanel row = new JPanel(new BorderLayout());
                row.setBackground(content.getBackground());

                JLabel left = new JLabel(label);
                JLabel right = new JLabel(String.valueOf(value));

                left.setForeground(Color.WHITE);
                right.setForeground(Color.WHITE);

                row.add(left, BorderLayout.WEST);
                row.add(right, BorderLayout.EAST);

                row.setAlignmentX(Component.LEFT_ALIGNMENT);
                content.add(row);
            }
        }

        revalidate();
        repaint();
    }
}
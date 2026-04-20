package com.ultimatestattracker;

import net.runelite.client.ui.PluginPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

import static com.ultimatestattracker.StatKeys.*;

public class UltimateStatTrackerPanel extends PluginPanel
{
    private final JPanel content = new JPanel();
    private final JTextField searchField = new JTextField();

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
            Map.entry(SHRIMP_CAUGHT, "Shrimp Caught"),
            Map.entry(ANCHOVIES_CAUGHT, "Anchovies Caught"),
            Map.entry(SARDINE_CAUGHT, "Sardines Caught"),
            Map.entry(HERRING_CAUGHT, "Herring Caught"),
            Map.entry(TROUT_CAUGHT, "Trout Caught"),
            Map.entry(SALMON_CAUGHT, "Salmon Caught"),
            Map.entry(PIKE_CAUGHT, "Pike Caught"),
            Map.entry(COD_CAUGHT, "Cod Caught"),
            Map.entry(BASS_CAUGHT, "Bass Caught"),
            Map.entry(TUNA_CAUGHT, "Tuna Caught"),
            Map.entry(LOBSTER_CAUGHT, "Lobsters Caught"),
            Map.entry(SWORDFISH_CAUGHT, "Swordfish Caught"),
            Map.entry(MONKFISH_CAUGHT, "Monkfish Caught"),
            Map.entry(SHARK_CAUGHT, "Sharks Caught"),
            Map.entry(SEA_TURTLE_CAUGHT, "Sea Turtles Caught"),
            Map.entry(MANTA_RAY_CAUGHT, "Manta Rays Caught"),
            Map.entry(ANGLERFISH_CAUGHT, "Anglerfish Caught"),
            Map.entry(KARAMBWAN_CAUGHT, "Karambwans Caught"),
            Map.entry(DARK_CRAB_CAUGHT, "Dark Crabs Caught"),
            Map.entry(MINNOW_CAUGHT, "Minnows Caught"),
            Map.entry(SACRED_EEL_CAUGHT, "Sacred Eels Caught"),
            Map.entry(LEAPING_TROUT_CAUGHT, "Leaping Trout Caught"),
            Map.entry(LEAPING_SALMON_CAUGHT, "Leaping Salmon Caught"),
            Map.entry(LEAPING_STURGEON_CAUGHT, "Leaping Sturgeon Caught"),
            Map.entry(ROCKS_MINED, "Rocks Mined"),
            Map.entry(LOGS_CHOPPED, "Logs Chopped"),
            Map.entry(NORMAL_LOGS_CHOPPED, "Normal Logs Chopped"),
            Map.entry(OAK_LOGS_CHOPPED, "Oak Logs Chopped"),
            Map.entry(WILLOW_LOGS_CHOPPED, "Willow Logs Chopped"),
            Map.entry(TEAK_LOGS_CHOPPED, "Teak Logs Chopped"),
            Map.entry(MAPLE_LOGS_CHOPPED, "Maple Logs Chopped"),
            Map.entry(MAHOGANY_LOGS_CHOPPED, "Mahogany Logs Chopped"),
            Map.entry(YEW_LOGS_CHOPPED, "Yew Logs Chopped"),
            Map.entry(MAGIC_LOGS_CHOPPED, "Magic Logs Chopped"),
            Map.entry(REDWOOD_LOGS_CHOPPED, "Redwood Logs Chopped"),
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
            Map.entry(ITEMS_SMITHED, "Items Smithed"),
            Map.entry(DAMAGE_DONE, "Damage Done"),
            Map.entry(DAMAGE_RECEIVED, "Damage Received"),
            Map.entry(BIGGEST_HITSPLAT, "Biggest Hit"),
            Map.entry(ATTACKS_BLOCKED, "Attacks Blocked"),
            Map.entry(ATTACKS_MISSED, "Attacks Missed")
    );

    public UltimateStatTrackerPanel()
    {
        setLayout(new BorderLayout());

        // ---- TOP BAR ----
        JPanel topBar = new JPanel();
        topBar.setLayout(new BoxLayout(topBar, BoxLayout.Y_AXIS));

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

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        refreshButton.addActionListener(e -> rebuild());

        topBar.add(searchField);
        topBar.add(refreshButton);

        add(topBar, BorderLayout.NORTH);

        // ---- MAIN CONTENT ----
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

    @Override
    public void onActivate()
    {
        rebuild();
    }

    public void rebuild()
    {
        //test code for formatting
        //statStore.setStat(LOGS_CHOPPED,Integer.MAX_VALUE);
        content.removeAll();

        JLabel title = new JLabel("Ultimate Stat Tracker");
        title.setForeground(Color.ORANGE);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        content.add(title);

        content.add(Box.createVerticalStrut(10));

        if (statStore != null)
        {
            for (Map.Entry<String, String> entry : STAT_LABELS.entrySet())
            {
                String key = entry.getKey();
                String label = entry.getValue();

                if (!filter.isEmpty() && !label.toLowerCase().contains(filter))
                {
                    continue;
                }

                int value = statStore.getStat(key);

                JPanel row = new JPanel(new BorderLayout());
                row.setBackground(content.getBackground());
                row.setAlignmentX(Component.LEFT_ALIGNMENT);

                JLabel left = new JLabel(label);

                String formatted;
                if (value == Integer.MAX_VALUE)
                {
                    formatted = "Lots!";
                }
                else
                {
                    formatted = formatStack(value);
                }

                JLabel right = new JLabel(formatted);

                left.setForeground(Color.WHITE);

                // Color logic
                if (value == Integer.MAX_VALUE || formatted.contains("M"))
                {
                    right.setForeground(Color.GREEN);
                }
                else
                {
                    right.setForeground(Color.WHITE);
                }

                row.add(left, BorderLayout.WEST);
                row.add(right, BorderLayout.EAST);

                // ---------------- RIGHT CLICK MENU ----------------
                JPopupMenu menu = new JPopupMenu();
                JMenuItem resetItem = new JMenuItem("Reset stat");
                menu.add(resetItem);

                resetItem.addActionListener(e ->
                {
                    int result = JOptionPane.showConfirmDialog(
                            this,
                            "Reset \"" + label + "\"?",
                            "Confirm Reset",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );

                    if (result == JOptionPane.YES_OPTION)
                    {
                        statStore.setStat(key, 0);
                        rebuild();
                    }
                });

                row.addMouseListener(new java.awt.event.MouseAdapter()
                {
                    @Override
                    public void mousePressed(java.awt.event.MouseEvent e)
                    {
                        if (e.isPopupTrigger())
                        {
                            menu.show(row, e.getX(), e.getY());
                        }
                    }

                    @Override
                    public void mouseReleased(java.awt.event.MouseEvent e)
                    {
                        if (e.isPopupTrigger())
                        {
                            menu.show(row, e.getX(), e.getY());
                        }
                    }
                });

                content.add(row);
            }
        }

        revalidate();
        repaint();
    }


    public static String formatStack(int amount)
    {
        if (amount < 100_000)
        {
            return String.valueOf(amount);
        }
        else if (amount < 10_000_000)
        {
            // Thousands (k)
            int thousands = amount / 1_000;
            return thousands + "k";
        }
        else
        {
            // Millions (M) with 1 decimal place
            double millions = amount / 1_000_000.0;

            // Keep one decimal only when needed
            if (amount % 1_000_000 == 0)
            {
                return String.format("%dM", (int) millions);
            }
            else
            {
                return String.format("%.1fM", millions);
            }
        }
    }
}
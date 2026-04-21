package com.ultimatestattracker;

import net.runelite.client.ui.PluginPanel;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static com.ultimatestattracker.StatKeys.*;

public class UltimateStatTrackerPanel extends PluginPanel
{
    private final JPanel content = new JPanel();
    private final JTextField searchField = new JTextField();
    private final JComboBox<String> sortBox = new JComboBox<>(new String[]{"Alphabetical", "Numeric"});

    private StatStore statStore;
    private String filter = "";

    public JScrollPane scrollPane;

    private static final Map<String, String> STAT_LABELS = new LinkedHashMap<>();

    private UltimateStatTrackerPlugin plugin;
    static
    {
        STAT_LABELS.put(EXAMINE, "Things Examined");
        STAT_LABELS.put(ITEMS_DROPPED, "Items Dropped");
        STAT_LABELS.put(SHOP_GP_SPENT, "Shop GP Spent");
        STAT_LABELS.put(SHOP_GP_GAINED, "Shop GP Gained");
        STAT_LABELS.put(TILES_WALKED, "Tiles Walked");
        STAT_LABELS.put(TILES_RAN, "Tiles Ran");
        STAT_LABELS.put(FISH_CAUGHT, "Fish Caught");
        STAT_LABELS.put(SHRIMP_CAUGHT, "Shrimp Caught");
        STAT_LABELS.put(ANCHOVIES_CAUGHT, "Anchovies Caught");
        STAT_LABELS.put(SARDINE_CAUGHT, "Sardines Caught");
        STAT_LABELS.put(HERRING_CAUGHT, "Herring Caught");
        STAT_LABELS.put(TROUT_CAUGHT, "Trout Caught");
        STAT_LABELS.put(SALMON_CAUGHT, "Salmon Caught");
        STAT_LABELS.put(PIKE_CAUGHT, "Pike Caught");
        STAT_LABELS.put(COD_CAUGHT, "Cod Caught");
        STAT_LABELS.put(BASS_CAUGHT, "Bass Caught");
        STAT_LABELS.put(TUNA_CAUGHT, "Tuna Caught");
        STAT_LABELS.put(LOBSTER_CAUGHT, "Lobsters Caught");
        STAT_LABELS.put(SWORDFISH_CAUGHT, "Swordfish Caught");
        STAT_LABELS.put(MONKFISH_CAUGHT, "Monkfish Caught");
        STAT_LABELS.put(SHARK_CAUGHT, "Sharks Caught");
        STAT_LABELS.put(SEA_TURTLE_CAUGHT, "Sea Turtles Caught");
        STAT_LABELS.put(MANTA_RAY_CAUGHT, "Manta Rays Caught");
        STAT_LABELS.put(ANGLERFISH_CAUGHT, "Anglerfish Caught");
        STAT_LABELS.put(KARAMBWAN_CAUGHT, "Karambwans Caught");
        STAT_LABELS.put(DARK_CRAB_CAUGHT, "Dark Crabs Caught");
        STAT_LABELS.put(MINNOW_CAUGHT, "Minnows Caught");
        STAT_LABELS.put(SACRED_EEL_CAUGHT, "Sacred Eels Caught");
        STAT_LABELS.put(LEAPING_TROUT_CAUGHT, "Leaping Trout Caught");
        STAT_LABELS.put(LEAPING_SALMON_CAUGHT, "Leaping Salmon Caught");
        STAT_LABELS.put(LEAPING_STURGEON_CAUGHT, "Leaping Sturgeon Caught");
        STAT_LABELS.put(ROCKS_MINED, "Rocks Mined");
        STAT_LABELS.put(CLAY_MINED, "Clay Mined");
        STAT_LABELS.put(COPPER_ORE_MINED, "Copper Ore Mined");
        STAT_LABELS.put(TIN_ORE_MINED, "Tin Ore Mined");
        STAT_LABELS.put(LIMESTONE_MINED, "Limestone Mined");
        STAT_LABELS.put(IRON_ORE_MINED, "Iron Ore Mined");
        STAT_LABELS.put(SILVER_ORE_MINED, "Silver Ore Mined");
        STAT_LABELS.put(COAL_MINED, "Coal Mined");
        STAT_LABELS.put(GOLD_ORE_MINED, "Gold Ore Mined");
        STAT_LABELS.put(MITHRIL_ORE_MINED, "Mithril Ore Mined");
        STAT_LABELS.put(ADAMANTITE_ORE_MINED, "Adamantite Ore Mined");
        STAT_LABELS.put(RUNITE_ORE_MINED, "Runite Ore Mined");
        STAT_LABELS.put(GRANITE_MINED, "Granite Mined");
        STAT_LABELS.put(SANDSTONE_MINED, "Sandstone Mined");
        STAT_LABELS.put(PAY_DIRT_MINED, "Pay-dirt Mined");
        STAT_LABELS.put(AMETHYST_MINED, "Amethyst Mined");
        STAT_LABELS.put(PURE_ESSENCE_MINED, "Pure Essence Mined");
        STAT_LABELS.put(LOGS_CHOPPED, "Logs Chopped");
        STAT_LABELS.put(NORMAL_LOGS_CHOPPED, "Normal Logs Chopped");
        STAT_LABELS.put(OAK_LOGS_CHOPPED, "Oak Logs Chopped");
        STAT_LABELS.put(WILLOW_LOGS_CHOPPED, "Willow Logs Chopped");
        STAT_LABELS.put(TEAK_LOGS_CHOPPED, "Teak Logs Chopped");
        STAT_LABELS.put(MAPLE_LOGS_CHOPPED, "Maple Logs Chopped");
        STAT_LABELS.put(MAHOGANY_LOGS_CHOPPED, "Mahogany Logs Chopped");
        STAT_LABELS.put(YEW_LOGS_CHOPPED, "Yew Logs Chopped");
        STAT_LABELS.put(MAGIC_LOGS_CHOPPED, "Magic Logs Chopped");
        STAT_LABELS.put(REDWOOD_LOGS_CHOPPED, "Redwood Logs Chopped");
        STAT_LABELS.put(BONES_BURIED, "Bones Buried");
        STAT_LABELS.put(ASHES_SCATTERED, "Ashes Scattered");
        STAT_LABELS.put(LOGS_BURNED, "Logs Burned");
        STAT_LABELS.put(FOOD_EATEN, "Food Eaten");
        STAT_LABELS.put(HP_REGEN, "HP Regen");
        STAT_LABELS.put(BEER_DRANK, "Beer Drank");
        STAT_LABELS.put(CABBAGE_EATEN, "Cabbage Eaten");
        STAT_LABELS.put(TROUT_EATEN, "Trout Eaten");
        STAT_LABELS.put(FLAX_PICKED, "Flax Picked");
        STAT_LABELS.put(CABBAGE_PICKED, "Cabbage Picked");
        STAT_LABELS.put(CRITTERS_PET, "Critters Pet");
        STAT_LABELS.put(POTION_SIPS_DRANK, "Potion Sips");
        STAT_LABELS.put(VIALS_SMASHED, "Vials Smashed");
        STAT_LABELS.put(PICK_POCKETS, "Pickpockets");
        STAT_LABELS.put(STALLS_THIEVED, "Stalls Thieved");
        STAT_LABELS.put(WEEDS_RAKED, "Weeds Raked");
        STAT_LABELS.put(SEEDS_PLANTED, "Seeds Planted");
        STAT_LABELS.put(ROOF_TOP_AGILITY, "Rooftop Laps");
        STAT_LABELS.put(NORMAL_AGILITY, "Agility Laps");
        STAT_LABELS.put(IMPLINGS_CAUGHT, "Implings Caught");
        STAT_LABELS.put(FOOD_COOKED, "Food Cooked");
        STAT_LABELS.put(FOOD_BURNED, "Food Burned");
        STAT_LABELS.put(FAILED_PICK_POCKETS, "Failed Pickpockets");
        STAT_LABELS.put(UNFINISHED_POTIONS_MADE, "Unfinished Potions");
        STAT_LABELS.put(POTIONS_MADE, "Potions Made");
        STAT_LABELS.put(HERBS_CLEANED, "Herbs Cleaned");
        STAT_LABELS.put(RUNES_CRAFTED, "Runes Crafted");
        STAT_LABELS.put(GEMS_CUT, "Gems Cut");
        STAT_LABELS.put(GLASS_BLOWN, "Glass Blown");
        STAT_LABELS.put(BOWS_FLECTHED, "Bows Fletched");
        STAT_LABELS.put(BOWS_STRUNG, "Bows Strung");
        STAT_LABELS.put(DARTS_FLECTHED, "Darts Fletched");
        STAT_LABELS.put(CREATURES_TRAPPED, "Creatures Trapped");
        STAT_LABELS.put(BARS_SMELTED, "Bars Smelted");
        STAT_LABELS.put(ITEMS_SMITHED, "Items Smithed");
        STAT_LABELS.put(DAMAGE_DONE, "Damage Done");
        STAT_LABELS.put(DAMAGE_RECEIVED, "Damage Received");
        STAT_LABELS.put(BIGGEST_HITSPLAT, "Biggest Hit");
        STAT_LABELS.put(ATTACKS_BLOCKED, "Attacks Blocked");
        STAT_LABELS.put(ATTACKS_MISSED, "Attacks Missed");
    }

    public UltimateStatTrackerPanel(UltimateStatTrackerPlugin plugin)
    {
        this.plugin = plugin;
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

        sortBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        sortBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        sortBox.addActionListener(e -> rebuild());

        topBar.add(searchField);
        topBar.add(refreshButton);
        topBar.add(sortBox);

        add(topBar, BorderLayout.NORTH);

        // ---- MAIN CONTENT ----
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(new Color(40, 35, 30));

        scrollPane = new JScrollPane(content);
        scrollPane.setWheelScrollingEnabled(true);

        scrollPane.getViewport().addMouseWheelListener(e -> {
            JScrollBar bar = scrollPane.getVerticalScrollBar();
            bar.setValue(bar.getValue() + (e.getWheelRotation() * bar.getUnitIncrement() * 3));
        });

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

    private List<Map.Entry<String, String>> getSortedEntries()
    {
        String selected = (String) sortBox.getSelectedItem();

        List<Map.Entry<String, String>> entries = new ArrayList<>(STAT_LABELS.entrySet());

        if ("Alphabetical".equals(selected))
        {
            entries.sort(Comparator.comparing(e -> e.getValue().toLowerCase()));
        }
        else if ("Numeric".equals(selected) && statStore != null)
        {
            entries.sort((a, b) -> Integer.compare(
                    statStore.getStat(b.getKey()),
                    statStore.getStat(a.getKey())
            ));
        }

        return entries;
    }

    public void rebuild()
    {
        populateDummyData();
        content.removeAll();

        JLabel title = new JLabel("Ultimate Stat Tracker");
        title.setForeground(Color.ORANGE);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        content.add(title);

        content.add(Box.createVerticalStrut(10));

        if (!plugin.loggedIn){
            JLabel notLoggedIn = new JLabel("Please log in to see your stats.");
            notLoggedIn.setForeground(Color.GRAY);
            notLoggedIn.setAlignmentX(Component.LEFT_ALIGNMENT);
            content.add(notLoggedIn);
            revalidate();
            repaint();
            return;
        }

        if (statStore != null)
        {
            for (Map.Entry<String, String> entry : getSortedEntries())
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

                JPanel leftPanel = new JPanel();
                leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.X_AXIS));
                leftPanel.setBackground(content.getBackground());

                JLabel left = new JLabel(label);
                left.setForeground(Color.WHITE);

                JLabel info = new JLabel(" ⓘ");
                info.setForeground(Color.GRAY);
                String date = statStore.getStatTrackingDate(key);
                info.setToolTipText(date != null ? "Tracking since: " + date : "No data");

                leftPanel.add(left);
                leftPanel.add(info);

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

                if (value == Integer.MAX_VALUE || formatted.contains("M"))
                {
                    right.setForeground(Color.GREEN);
                }
                else
                {
                    right.setForeground(Color.WHITE);
                }

                row.add(leftPanel, BorderLayout.WEST);
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
                        String formattedDate = java.time.Instant.ofEpochMilli(System.currentTimeMillis())
                                .atZone(java.time.ZoneId.systemDefault())
                                .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        statStore.setStatTrackingDate(key,formattedDate);
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
            int thousands = amount / 1_000;
            return thousands + "k";
        }
        else
        {
            double millions = amount / 1_000_000.0;

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

    private void populateDummyData()
    {
        Random random = new Random();
        List<String> keys = new ArrayList<>(STAT_LABELS.keySet());
        Collections.shuffle(keys);

        int half = keys.size() / 2;

        for (int i = 0; i < keys.size(); i++)
        {
            if (i < half)
            {
                // First half: under 500k
                statStore.setStat(keys.get(i), random.nextInt(500_000));
            }
            else
            {
                // Second half: anything up to max
                statStore.setStat(keys.get(i), random.nextInt(Integer.MAX_VALUE));
            }
        }

        // Guarantee at least one is max
        statStore.setStat(keys.get(keys.size() - 1), Integer.MAX_VALUE);
    }
}
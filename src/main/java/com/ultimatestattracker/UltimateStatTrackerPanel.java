package com.ultimatestattracker;

import net.runelite.client.ui.PluginPanel;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import net.runelite.client.game.SkillIconManager;

import static com.ultimatestattracker.StatKeys.*;

public class UltimateStatTrackerPanel extends PluginPanel
{
    private final JPanel content = new JPanel();
    private final JTextField searchField = new JTextField();
    private final JComboBox<String> sortBox = new JComboBox<>(new String[]{"Alphabetical", "Numeric"});
    private StatStore statStore;
    private String filter = "";
    private static final Map<String, String> STAT_LABELS = new LinkedHashMap<>();
    private static final Color COLOR_DISABLED = new Color(100, 100, 100);
    private static final String PERFORMANCE_MODE_TOOLTIP = "Disabled in performance mode";


    private final StatCategoryFilterBar categoryFilterBar;

    private UltimateStatTrackerPlugin plugin;


    public UltimateStatTrackerPanel(UltimateStatTrackerPlugin plugin, SkillIconManager skillIconManager)
    {
        this.plugin = plugin;
        categoryFilterBar = new StatCategoryFilterBar(skillIconManager, this::rebuild);
        setLayout(new BorderLayout());

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

        JButton resetAllButton = new JButton("Reset All");
        resetAllButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        resetAllButton.addActionListener(e ->
        {
            int result = JOptionPane.showConfirmDialog(
                    this,
                    "Reset ALL stats? This cannot be undone.",
                    "Confirm Reset All",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (result == JOptionPane.YES_OPTION && statStore != null)
            {
                String formattedDate = java.time.Instant.ofEpochMilli(System.currentTimeMillis())
                        .atZone(java.time.ZoneId.systemDefault())
                        .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                for (StatKey key : ALL_KEYS)
                {
                    statStore.setStat(key.getValue(), 0);
                    statStore.setStatTrackingDate(key.getValue(), formattedDate);
                }

                rebuild();
            }
        });

        sortBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        sortBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        sortBox.addActionListener(e -> rebuild());

        JPanel buttonRow = new JPanel();
        buttonRow.setLayout(new BoxLayout(buttonRow, BoxLayout.X_AXIS));
        buttonRow.setAlignmentX(Component.LEFT_ALIGNMENT);

        buttonRow.add(refreshButton);
        buttonRow.add(Box.createHorizontalGlue());
        buttonRow.add(resetAllButton);

        topBar.add(searchField);
        topBar.add(buttonRow);
        topBar.add(sortBox);
        topBar.add(categoryFilterBar);

        add(topBar, BorderLayout.NORTH);

        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(new Color(40, 35, 30));

        add(content, BorderLayout.CENTER);
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

        var entries = Arrays.stream(ALL_KEYS).map(x -> Map.entry(x.getValue(), x.displayString)).collect(Collectors.toList());

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
        int hiddenStats = 0;
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

                StatCategory activeCategory = categoryFilterBar.getSelectedCategory();
                if (activeCategory != null && StatKeys.KEY_TO_CATEGORY.get(key) != activeCategory)
                {
                    continue;
                }

                int value = statStore.getStat(key);

                if (plugin.isHideZeroStatsEnabled() && value == 0)
                {
                    hiddenStats++;
                    continue;
                }

                boolean notTracked = plugin.isPerformanceMode() && PERFORMANCE_MODE_DISABLED_KEYS.contains(key);

                JPanel row = new JPanel(new BorderLayout());
                row.setBackground(content.getBackground());
                row.setAlignmentX(Component.LEFT_ALIGNMENT);

                JPanel leftPanel = new JPanel();
                leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.X_AXIS));
                leftPanel.setBackground(content.getBackground());

                JLabel left = new JLabel(label);
                left.setForeground(notTracked ? COLOR_DISABLED : Color.WHITE);

                JLabel info = new JLabel(" ⓘ");
                info.setForeground(notTracked ? COLOR_DISABLED : Color.GRAY);
                String date = statStore.getStatTrackingDate(key);
                if (notTracked)
                {
                    info.setToolTipText("Not tracked while Performance Mode is enabled.");
                }
                else
                {
                    info.setToolTipText(date != null ? "Tracking since: " + date : "No data");
                }

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

                if (notTracked)
                {
                    right.setForeground(COLOR_DISABLED);
                }
                else if (value == Integer.MAX_VALUE || formatted.contains("M"))
                {
                    right.setForeground(Color.GREEN);
                }
                else
                {
                    right.setForeground(Color.WHITE);
                }

                row.add(leftPanel, BorderLayout.WEST);
                row.add(right, BorderLayout.EAST);

                if (notTracked)
                {
                    row.setToolTipText(PERFORMANCE_MODE_TOOLTIP);
                    leftPanel.setToolTipText(PERFORMANCE_MODE_TOOLTIP);
                    left.setToolTipText(PERFORMANCE_MODE_TOOLTIP);
                    info.setToolTipText(PERFORMANCE_MODE_TOOLTIP);
                    right.setToolTipText(PERFORMANCE_MODE_TOOLTIP);
                }

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

            if (plugin.isHideZeroStatsEnabled() && hiddenStats > 0)
            {
                content.add(Box.createVerticalStrut(10));
                String hiddenText = hiddenStats == 1 ? "1 stat hidden" : hiddenStats + " stats hidden";
                JLabel hiddenLabel = new JLabel(hiddenText);
                hiddenLabel.setForeground(Color.GRAY);
                hiddenLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                content.add(hiddenLabel);
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
}
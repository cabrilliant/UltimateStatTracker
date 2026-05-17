package com.ultimatestattracker;

import net.runelite.client.game.SkillIconManager;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;
import java.util.Map;

class StatCategoryFilterBar extends JPanel
{
	private static final int COLUMNS = 7;
	private static final int BUTTON_SIZE = 24;
	private static final int GAP = 2;

	private final Map<StatCategory, JToggleButton> buttons = new EnumMap<>(StatCategory.class);
	private StatCategory selected;

	StatCategoryFilterBar(SkillIconManager skillIconManager, Runnable onFilterChanged)
	{
		int rows = (StatCategory.values().length + COLUMNS - 1) / COLUMNS;
		int height = rows * (BUTTON_SIZE + GAP) + GAP;

		setLayout(new GridLayout(rows, COLUMNS, GAP, GAP));
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setPreferredSize(new Dimension(Short.MAX_VALUE, height));
		setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
		setMinimumSize(new Dimension(0, height));

		for (StatCategory category : StatCategory.values())
		{
			JToggleButton button = new JToggleButton(new ImageIcon(StatCategoryIcons.getIcon(category, skillIconManager)));
			button.setToolTipText(category.getDisplayName());
			button.setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
			button.setFocusable(false);
			button.setMargin(new Insets(1, 1, 1, 1));

			button.addActionListener(e ->
			{
				if (button.isSelected())
				{
					if (selected != null && selected != category)
					{
						buttons.get(selected).setSelected(false);
					}
					selected = category;
				}
				else
				{
					selected = null;
				}
				onFilterChanged.run();
			});

			buttons.put(category, button);
			add(button);
		}
	}

	StatCategory getSelectedCategory()
	{
		return selected;
	}
}

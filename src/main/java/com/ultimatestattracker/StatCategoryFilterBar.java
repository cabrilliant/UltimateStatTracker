package com.ultimatestattracker;

import net.runelite.client.game.SkillIconManager;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;
import java.util.Map;

class StatCategoryFilterBar extends JPanel
{
	private final Map<StatCategory, JToggleButton> buttons = new EnumMap<>(StatCategory.class);
	private StatCategory selected;

	StatCategoryFilterBar(SkillIconManager skillIconManager, Runnable onFilterChanged)
	{
		setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));

		for (StatCategory category : StatCategory.values())
		{
			JToggleButton button = new JToggleButton(new ImageIcon(StatCategoryIcons.getIcon(category, skillIconManager)));
			button.setToolTipText(category.getDisplayName());
			button.setPreferredSize(new Dimension(24, 24));
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

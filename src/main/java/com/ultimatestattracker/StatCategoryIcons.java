package com.ultimatestattracker;

import net.runelite.api.Skill;
import net.runelite.client.game.SkillIconManager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

final class StatCategoryIcons
{
	private static final int ICON_SIZE = 20;
	private static BufferedImage unknownIcon;

	private StatCategoryIcons()
	{
	}

	static BufferedImage getIcon(StatCategory category, SkillIconManager skillIconManager)
	{
		Skill skill = skillFor(category);
		if (skill != null)
		{
			return skillIconManager.getSkillImage(skill, true);
		}
		return getUnknownIcon();
	}

	private static Skill skillFor(StatCategory category)
	{
		switch (category)
		{
			case FISHING:
				return Skill.FISHING;
			case MINING:
				return Skill.MINING;
			case WOODCUTTING:
				return Skill.WOODCUTTING;
			case FARMING:
				return Skill.FARMING;
			case PRAYER:
				return Skill.PRAYER;
			case FIREMAKING:
				return Skill.FIREMAKING;
			case FOOD_AND_HEALING:
				return Skill.HITPOINTS;
			case THIEVING:
				return Skill.THIEVING;
			case AGILITY:
				return Skill.AGILITY;
			case HERBLORE:
				return Skill.HERBLORE;
			case RUNECRAFT:
				return Skill.RUNECRAFT;
			case CRAFTING:
				return Skill.CRAFTING;
			case FLETCHING:
				return Skill.FLETCHING;
			case HUNTER:
				return Skill.HUNTER;
			case SMITHING:
				return Skill.SMITHING;
			case COMBAT:
				return Skill.ATTACK;
			default:
				return null;
		}
	}

	private static BufferedImage getUnknownIcon()
	{
		if (unknownIcon == null)
		{
			BufferedImage img = new BufferedImage(ICON_SIZE, ICON_SIZE, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = img.createGraphics();
			g.setColor(new Color(180, 180, 180));
			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
			g.drawString("?", 5, 15);
			g.dispose();
			unknownIcon = img;
		}
		return unknownIcon;
	}
}

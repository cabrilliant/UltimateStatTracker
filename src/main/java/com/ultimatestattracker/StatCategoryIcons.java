package com.ultimatestattracker;

import net.runelite.api.Skill;
import net.runelite.client.game.SkillIconManager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import net.runelite.client.util.ImageUtil;

final class StatCategoryIcons
{
	private static final int ICON_SIZE = 20;

	private static BufferedImage unknownIcon;
	private static BufferedImage coinsIcon;
	private static BufferedImage transportIcon;
	private static BufferedImage flaxIcon;
	private static BufferedImage bankNoteIcon;

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

		switch (category)
		{
			case SHOPS:
				return getCoinsIcon();

			case MOVEMENT:
				return getTransportIcon();

			case GATHERING:
				return getFlaxIcon();

			case ITEMS:
				return getBankNoteIcon();

			default:
				return getUnknownIcon();
		}
	}

	private static BufferedImage getCoinsIcon()
	{
		if (coinsIcon == null)
		{
			coinsIcon = ImageUtil.loadImageResource(
					StatCategoryIcons.class,
					"/icons/coins_10000.png"
			);
		}
		return coinsIcon;
	}

	private static BufferedImage getTransportIcon()
	{
		if (transportIcon == null)
		{
			transportIcon = ImageUtil.loadImageResource(
					StatCategoryIcons.class,
					"/icons/transportation_icon.png"
			);
		}
		return transportIcon;
	}

	private static BufferedImage getFlaxIcon()
	{
		if (flaxIcon == null)
		{
			flaxIcon = ImageUtil.loadImageResource(
					StatCategoryIcons.class,
					"/icons/flax.png"
			);
		}
		return flaxIcon;
	}

	private static BufferedImage getBankNoteIcon()
	{
		if (bankNoteIcon == null)
		{
			bankNoteIcon = ImageUtil.loadImageResource(
					StatCategoryIcons.class,
					"/icons/bank_note.png"
			);
		}
		return bankNoteIcon;
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
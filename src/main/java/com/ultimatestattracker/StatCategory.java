package com.ultimatestattracker;

public enum StatCategory
{
	ITEMS,
	SHOPS,
	MOVEMENT,
	FISHING,
	MINING,
	WOODCUTTING,
	GATHERING,
	FARMING,
	MISC,
	PRAYER,
	FIREMAKING,
	FOOD_AND_HEALING,
	THIEVING,
	AGILITY,
	HERBLORE,
	RUNECRAFT,
	CRAFTING,
	FLETCHING,
	HUNTER,
	SMITHING,
	COMBAT;

	public String getDisplayName()
	{
		switch (this)
		{
			case ITEMS:
				return "Items";
			case SHOPS:
				return "Shops";
			case MOVEMENT:
				return "Movement";
			case FOOD_AND_HEALING:
				return "Food & Healing";
			case GATHERING:
				return "Gathering";
			default:
				String name = name();
				return name.charAt(0) + name.substring(1).toLowerCase().replace('_', ' ');
		}
	}
}

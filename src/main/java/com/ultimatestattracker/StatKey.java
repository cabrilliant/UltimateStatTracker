package com.ultimatestattracker;

public final class StatKey
{
	private final String value;
	private final StatCategory category;

	public StatKey(String value, StatCategory category, String displayString)
	{
		this.value = value;
		this.category = category;
		this.displayString = displayString;
	}

	public String getValue()
	{
		return value;
	}
	public StatCategory getCategory()
	{
		return category;
	}
	public String displayString;
}

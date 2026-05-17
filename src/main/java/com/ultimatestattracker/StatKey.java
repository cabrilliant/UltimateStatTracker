package com.ultimatestattracker;

public final class StatKey
{
	private final String value;
	private final StatCategory category;

	public StatKey(String value, StatCategory category)
	{
		this.value = value;
		this.category = category;
	}

	public String getValue()
	{
		return value;
	}

	public StatCategory getCategory()
	{
		return category;
	}
}

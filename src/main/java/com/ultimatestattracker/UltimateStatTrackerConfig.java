package com.ultimatestattracker;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("UltimateStatTracker")
public interface UltimateStatTrackerConfig extends Config
{
    @ConfigItem(
            keyName = "performanceMode",
            name = "Enable Performance Mode",
            description = "Enable if you are seeing performance issues. Disables some of the more system heavy tracking.",
            position = 1
    )
    default boolean performanceMode()
    {
        return true;
    }

    @ConfigItem(
            keyName = "hideZeroStats",
            name = "Hide Zero-Value Stats",
            description = "Hide stats that are still at zero from the panel.",
            position = 2
    )
    default boolean hideZeroStats()
    {
        return false;
    }
}

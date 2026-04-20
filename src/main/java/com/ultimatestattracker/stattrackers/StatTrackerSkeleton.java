package com.ultimatestattracker.stattrackers;

import com.ultimatestattracker.StatStore;
import net.runelite.api.Client;
import net.runelite.api.events.*;

public class StatTrackerSkeleton implements StatTracker{
    private StatStore statStore;
    private Client client;

    public StatTrackerSkeleton(StatStore statStore, Client client)
    {
        this.statStore = statStore;
        this.client = client;
    }
    
    @Override
    public void onMenuOptionClicked(MenuOptionClicked event) {

    }

    @Override
    public void onWidgetLoaded(WidgetLoaded event) {

    }

    @Override
    public void onWidgetClosed(WidgetClosed event) {

    }

    @Override
    public void onGameTick(GameTick event) {

    }

    @Override
    public void onGameStateChanged(GameStateChanged gameStateChanged) {

    }

    @Override
    public void onChatMessage(ChatMessage event) {

    }

    @Override
    public void onHitsplatApplied(HitsplatApplied event) {

    }
}

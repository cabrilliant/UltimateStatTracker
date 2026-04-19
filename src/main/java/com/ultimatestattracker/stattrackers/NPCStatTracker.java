package com.ultimatestattracker.stattrackers;

import com.ultimatestattracker.StatStore;
import net.runelite.api.Client;
import net.runelite.api.events.*;

import java.util.Objects;

import static com.ultimatestattracker.StatKeys.CRITTERS_PET;

public class NPCStatTracker implements StatTracker{
    private StatStore statStore;
    private Client client;

    boolean evenTick = false;

    boolean pendingPet = false;
    public NPCStatTracker(StatStore statStore, Client client)
    {
        this.statStore = statStore;
        this.client = client;
    }
    
    @Override
    public void onMenuOptionClicked(MenuOptionClicked event) {
        if (Objects.equals(event.getMenuOption(), "Pet"))
        {
            pendingPet = true;
        }
    }

    @Override
    public void onWidgetLoaded(WidgetLoaded event) {

    }

    @Override
    public void onWidgetClosed(WidgetClosed event) {

    }

    @Override
    public void onGameTick(GameTick event) {
        if (pendingPet && evenTick){ //2 tick limited since petting is 2 tick limited
            statStore.incrementStat(CRITTERS_PET);
            pendingPet = false;
        }

        evenTick = !evenTick;
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

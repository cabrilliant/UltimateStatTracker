package com.ultimatestattracker.stattrackers;

import com.ultimatestattracker.StatStore;
import net.runelite.api.Client;
import net.runelite.api.Skill;
import net.runelite.api.events.*;

import static com.ultimatestattracker.StatKeys.TOTAL_DEATHS;

public class PlayerStatTracker implements StatTracker{
    private StatStore statStore;
    private Client client;

    private boolean deathProcessed = false;

    public PlayerStatTracker(StatStore statStore, Client client)
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
        int hp = client.getBoostedSkillLevel(Skill.HITPOINTS);
        if (hp == 0 & !deathProcessed)
        {
            statStore.incrementStat(TOTAL_DEATHS);
            deathProcessed = true;
        }
        else if (hp > 0)
        {
            deathProcessed = false;
        }
    }

    @Override
    public void onGameStateChanged(GameStateChanged gameStateChanged) {

    }

    @Override
    public void onChatMessage(ChatMessage event) {

    }
}

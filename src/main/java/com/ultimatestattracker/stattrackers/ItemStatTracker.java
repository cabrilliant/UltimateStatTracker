package com.ultimatestattracker.stattrackers;

import com.ultimatestattracker.StatStore;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.*;

import java.util.Objects;

import static com.ultimatestattracker.StatKeys.*;

@Slf4j
public class ItemStatTracker implements StatTracker{
    private StatStore statStore;
    private Client client;

    public ItemStatTracker(StatStore statStore, Client client)
    {
        this.statStore = statStore;
        this.client = client;
    }

    @Override
    public void onMenuOptionClicked(MenuOptionClicked event) {
        if (Objects.equals(event.getMenuOption(), "Examine"))
        {
            statStore.incrementStat(EXAMINE);
            log.debug("Item examine clicked: {}", event.getMenuTarget());
        }
        else if (Objects.equals(event.getMenuOption(), "Drop"))
        {
            statStore.incrementStat(ITEMS_DROPPED);
            log.debug("Item drop clicked: {}", event.getMenuTarget());
        }
        else if (Objects.equals(event.getMenuOption(), "Bury")){
            statStore.incrementStat(BONES_BURIED);
            log.debug("Bones bury clicked: {}", event.getMenuTarget());
        }
        else if (Objects.equals(event.getMenuOption(), "Scatter")){
            statStore.incrementStat(ASHES_SCATTERED);
            log.debug("Ashses Scatered clicked: {}", event.getMenuTarget());
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

    }

    @Override
    public void onGameStateChanged(GameStateChanged gameStateChanged) {

    }

    @Override
    public void onChatMessage(ChatMessage event) {

    }
}

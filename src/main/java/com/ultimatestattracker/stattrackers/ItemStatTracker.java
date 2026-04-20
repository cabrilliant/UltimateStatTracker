package com.ultimatestattracker.stattrackers;

import com.ultimatestattracker.StatStore;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.InventoryID;
import net.runelite.api.events.*;
import net.runelite.client.util.Text;

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
        if (event.getType() != ChatMessageType.SPAM
                && event.getType() != ChatMessageType.GAMEMESSAGE
                && event.getType() != ChatMessageType.MESBOX)
        {
            return;
        }

        String msg = event.getMessage();
        if (msg.contains(("You pick a"))|| msg.contains("You pick some")){
            //item name is last space to first period
            String itemName = msg.substring(msg.lastIndexOf(" ") + 1, msg.indexOf("."));
            log.debug("suspected picked item name is {}", itemName);
            if (itemName.equals("Cabbage")){
                statStore.incrementStat(CABBAGE_PICKED);
                log.debug("Cabbage picked message: {}", msg);
            }
            else if (itemName.equals("Flax")){
                statStore.incrementStat(FLAX_PICKED);
                log.debug("Flax picked message: {}", msg);
            }
        }
        else if (msg.contains("You scatter the")){
            statStore.incrementStat(ASHES_SCATTERED);
        }
    }

    @Override
    public void onHitsplatApplied(HitsplatApplied event) {

    }
}

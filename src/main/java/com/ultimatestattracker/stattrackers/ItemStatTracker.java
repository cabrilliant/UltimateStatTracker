package com.ultimatestattracker.stattrackers;

import com.ultimatestattracker.StatStore;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.InventoryID;
import net.runelite.api.events.*;
import net.runelite.client.util.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.ultimatestattracker.StatKeys.*;

@Slf4j
public class ItemStatTracker implements StatTracker{
    private StatStore statStore;
    private Client client;

    // Maps the canonical (lower-case, no "grimy " prefix) herb name as it appears
    // in OSRS chat messages to the per-herb placed stat key. Used to track
    // grimy and clean variants of the same herb under a single counter.
    private static final Map<String, String> HERB_NAME_TO_STAT = new HashMap<>();
    static {
        HERB_NAME_TO_STAT.put("guam leaf", GUAM_LEAF_PLACED);
        HERB_NAME_TO_STAT.put("marrentill", MARRENTILL_PLACED);
        HERB_NAME_TO_STAT.put("tarromin", TARROMIN_PLACED);
        HERB_NAME_TO_STAT.put("harralander", HARRALANDER_PLACED);
        HERB_NAME_TO_STAT.put("ranarr weed", RANARR_WEED_PLACED);
        HERB_NAME_TO_STAT.put("toadflax", TOADFLAX_PLACED);
        HERB_NAME_TO_STAT.put("irit leaf", IRIT_LEAF_PLACED);
        HERB_NAME_TO_STAT.put("avantoe", AVANTOE_PLACED);
        HERB_NAME_TO_STAT.put("kwuarm", KWUARM_PLACED);
        HERB_NAME_TO_STAT.put("huasca", HUASCA_PLACED);
        HERB_NAME_TO_STAT.put("snapdragon", SNAPDRAGON_PLACED);
        HERB_NAME_TO_STAT.put("cadantine", CADANTINE_PLACED);
        HERB_NAME_TO_STAT.put("lantadyme", LANTADYME_PLACED);
        HERB_NAME_TO_STAT.put("dwarf weed", DWARF_WEED_PLACED);
        HERB_NAME_TO_STAT.put("torstol", TORSTOL_PLACED);
    }


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
            if (itemName.equals("cabbage")){
                statStore.incrementStat(CABBAGE_PICKED);
                log.debug("Cabbage picked message: {}", msg);
            }
            else if (itemName.equals("flax")){
                statStore.incrementStat(FLAX_PICKED);
                log.debug("Flax picked message: {}", msg);
            }
        }
        else if (msg.contains("You scatter the")){
            statStore.incrementStat(ASHES_SCATTERED);
        }
        else if (msg.contains("into your herb sack")){
            statStore.incrementStat(HERBS_PLACED_IN_HERB_SACK);
            log.debug("Herb placed in herb sack: {}", msg);

            // Extract the herb name from the message:
            //   "You put the <herb name> herb into your herb sack."
            // The herb name appears after "the " and before the first " herb".
            // It may optionally be prefixed with "grimy " (e.g. "grimy ranarr weed")
            // — strip that prefix so grimy and clean herbs share a counter.
            int herbStart = msg.indexOf("the ") + "the ".length();
            int herbEnd = msg.indexOf(" herb", herbStart);
            if (herbEnd > herbStart) {
                String herbName = msg.substring(herbStart, herbEnd).trim().toLowerCase();
                if (herbName.startsWith("grimy ")) {
                    herbName = herbName.substring("grimy ".length());
                }
                String herbStat = HERB_NAME_TO_STAT.get(herbName);
                if (herbStat != null) {
                    statStore.incrementStat(herbStat);
                } else {
                    log.debug("Unknown herb name parsed from message: {}", herbName);
                }
            }
        }
    }

    @Override
    public void onHitsplatApplied(HitsplatApplied event) {

    }

    @Override
    public void onAnimationChanged(AnimationChanged event) {

    }
}

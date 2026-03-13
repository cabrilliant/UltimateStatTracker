package com.ultimatestattracker.stattrackers;

import com.ultimatestattracker.StatStore;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.*;
import net.runelite.client.plugins.xptracker.XpTrackerService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Pattern;

import static com.ultimatestattracker.StatKeys.*;

@Slf4j
public class SkillingStatTracker implements StatTracker{
    private StatStore statStore;
    private Client client;
    private XpTrackerService xpService;
    HashMap<Skill,Integer> previousSkillActions = new HashMap<>();
    //from woodcutting plugin
    private static final Pattern WOOD_CUT_PATTERN = Pattern.compile("You get (?:some|an)[\\w ]+(?:logs?|mushrooms)\\.");

    private int prevWeedCount = 0;


    public SkillingStatTracker(StatStore statStore, Client client, XpTrackerService xpTrackerService)
    {
        this.statStore = statStore;
        this.client = client;
        xpService = xpTrackerService;
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
        processActions(Skill.FISHING, FISH_CAUGHT);
        processActions(Skill.MINING, ROCKS_MINED);
        processActions(Skill.FIREMAKING, LOGS_BURNED);

        ItemContainer inv = client.getItemContainer(InventoryID.INVENTORY);
        if (inv == null)
        {
            return;
        }

        int currentWeedCount = Arrays.stream(inv.getItems())
                .filter(i -> i.getId() == ItemID.WEEDS)
                .mapToInt(Item::getQuantity)
                .sum();

        if (currentWeedCount > prevWeedCount)
        {
            statStore.incrementStat(WEEDS_RAKED);
        }

        prevWeedCount = currentWeedCount;
    }

    @Override
    public void onGameStateChanged(GameStateChanged gameStateChanged) {

    }

    @Override
    public void onChatMessage(ChatMessage event) {
        //from woodcutting plugin
        if (event.getType() != ChatMessageType.SPAM
                && event.getType() != ChatMessageType.GAMEMESSAGE
                && event.getType() != ChatMessageType.MESBOX)
        {
            return;
        }

        final var msg = event.getMessage();

        if (WOOD_CUT_PATTERN.matcher(msg).matches())
        {
            statStore.incrementStat(LOGS_CHOPPED);
        }

        if(msg.contains("You pick the") && msg.contains("pocket")){
            statStore.incrementStat(PICK_POCKETS);
        }

        if(msg.contains("You steal a")){
            statStore.incrementStat(STALLS_THIEVED);
        }

        if (msg.contains("You plant a")){
            statStore.incrementStat(SEEDS_PLANTED);
        }
    }

    private void processActions(Skill skill, String statKey){
        int currentActions = xpService.getActions(skill);
        if (!previousSkillActions.containsKey(skill)){
            previousSkillActions.put(skill,currentActions);
            return;
        }
        int previousActions = previousSkillActions.get(skill);
        if (currentActions > previousActions)
        {
            int actionsGained = currentActions - previousActions;
            statStore.incrementStatBy(statKey, actionsGained);
        }
        previousSkillActions.put(skill,currentActions);
    }
}

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
        if (event.getType() != ChatMessageType.SPAM
                && event.getType() != ChatMessageType.GAMEMESSAGE
                && event.getType() != ChatMessageType.MESBOX)
        {
            return;
        }

        final var msg = event.getMessage();

        //from woodcutting plugin
        if (WOOD_CUT_PATTERN.matcher(msg).matches())
        {
            statStore.incrementStat(LOGS_CHOPPED);
        }

        else if(msg.contains("You pick the") && msg.contains("pocket") && !msg.contains("attempt")){
            statStore.incrementStat(PICK_POCKETS);
        }

        else if(msg.contains("You fail to pick the") && msg.contains("pocket")){
            statStore.incrementStat(FAILED_PICK_POCKETS);
        }

        else if(msg.contains("You steal a")){
            statStore.incrementStat(STALLS_THIEVED);
        }

        else if (msg.contains("You plant a")){
            statStore.incrementStat(SEEDS_PLANTED);
        }

        else if (msg.contains("Rooftop lap")){
            statStore.incrementStat(ROOF_TOP_AGILITY);
        }

        else if (msg.contains("Agility lap")){
            statStore.incrementStat(NORMAL_AGILITY);
        }

        //todo this msg was barehanded catch with no jar in inventory. ensure works for nets and when jar is in inv
        else if (msg.contains("You manage to catch the impling")){
            statStore.incrementStat(IMPLINGS_CAUGHT);
        }

        else if (msg.contains("You successfully cook")){
            statStore.incrementStat(FOOD_COOKED);
        }

        else if (msg.contains("You accidentally burn")){
            statStore.incrementStat(FOOD_BURNED);
        }

        else if (msg.contains("You put the") && msg.contains("vial")){
            statStore.incrementStat(UNFINISHED_POTIONS_MADE);
        }

        else if (msg.contains("You mix the") && msg.contains("potion")){
            statStore.incrementStat(POTIONS_MADE);
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

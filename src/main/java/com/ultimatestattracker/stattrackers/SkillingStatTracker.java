package com.ultimatestattracker.stattrackers;

import com.ultimatestattracker.StatStore;
import com.ultimatestattracker.support.RuneList;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.*;
import net.runelite.client.plugins.xptracker.XpTrackerService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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

    private int prevRuneCount = 0;

    private boolean runecraftXpGainedThisTick = false;
    private int prevRunecraftXp = 0;
    private int currentRunecraftXp = 0;

    private String[] glassItems = {
            "light orb",
            "lantern lens",
            "orb",
            "fishbowl",
            "vial",
            "oil lamp",
            "candle lantern",
            "beer glass"
    };

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
        prevRunecraftXp = currentRunecraftXp;
        currentRunecraftXp = client.getSkillExperience(Skill.RUNECRAFT);
        if (currentRunecraftXp > prevRunecraftXp) runecraftXpGainedThisTick = true;
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

        int currentRuneCount = getCurrentRuneCount();
        if (prevRuneCount < currentRuneCount){
            if (runecraftXpGainedThisTick) statStore.incrementStatBy(RUNES_CRAFTED, currentRuneCount - prevRuneCount);
        }

        prevRuneCount = currentRuneCount;
        runecraftXpGainedThisTick = false;
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

        else if (msg.contains("lap count") &&  !msg.contains("Rooftop")){
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

        else if (msg.contains("You clean the Grimy")){
            statStore.incrementStat(HERBS_CLEANED);
        }

        else if (msg.contains("You carefully cut the")){
            statStore.incrementStat(BOWS_FLECTHED);
        }

        else if (msg.contains("You cut the")){ //todo probably make this more robust, could be prone to false matches
            statStore.incrementStat(GEMS_CUT);
        }

        else if (msg.contains("You smelt the")){
            statStore.incrementStat(BARS_SMELTED);
        }

        else if (msg.contains("You hammer the")){
            statStore.incrementStat(ITEMS_SMITHED);
        }

        else if (msg.contains("You add a string to the bow")){
            statStore.incrementStat(BOWS_STRUNG);
        }

        else if (msg.contains("You make a") && Arrays.stream(glassItems).anyMatch(msg.toLowerCase()::contains)){
            statStore.incrementStat(GLASS_BLOWN);
        }

        else if (msg.contains("You finish making") &&msg.contains("darts")){
            //parse the number of darts made. it will be in between the words making and darts
                String numberStr = msg.substring(msg.indexOf("making ") + "making ".length(), msg.indexOf(" darts")).trim();
                try {
                    int number = Integer.parseInt(numberStr);
                    statStore.incrementStatBy(DARTS_FLECTHED, number);
                } catch (NumberFormatException e) {
                    log.warn("Failed to parse number of darts fletched from message: {}", msg);
                }
        }

        else if (msg.contains("You've caught a") || msg.contains("You manage to catch") ){
            statStore.incrementStat(CREATURES_TRAPPED);
        }

        else if (msg.contains("You bury the")){
            statStore.incrementStat(BONES_BURIED);
        }
    }

    @Override
    public void onHitsplatApplied(HitsplatApplied event) {

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

    private int getCurrentRuneCount()
    {
        ItemContainer inventory = client.getItemContainer(InventoryID.INVENTORY);

        if (inventory == null)
        {
            return 0;
        }

        int total = 0;

        for (Item item : inventory.getItems())
        {
            if (item == null)
            {
                continue;
            }

            String name = client.getItemDefinition(item.getId()).getName();

            if (name.endsWith("rune"))
            {
                total += item.getQuantity();
            }
        }

        return total;
    }
}

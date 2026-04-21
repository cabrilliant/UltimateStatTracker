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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.ultimatestattracker.StatKeys.*;

@Slf4j
public class SkillingStatTracker implements StatTracker{
    private StatStore statStore;
    private Client client;
    private XpTrackerService xpService;
    HashMap<Skill,Integer> previousSkillActions = new HashMap<>();
    //from woodcutting plugin
    private static final Pattern WOOD_CUT_PATTERN = Pattern.compile("You get (?:some|an)\\s+(?:([\\w ]+?)\\s+)?(logs?|mushrooms)\\.");
    private static final Pattern FISH_CATCH_PATTERN =
            Pattern.compile("You catch (?:a|an|some) ([\\w'\\- ]+)[.!?]?");
    private static final Pattern MINE_OR_QUARRY_SOME_PATTERN =
            Pattern.compile("You manage to (?:mine|quarry) some (.+)\\.");

    private int prevWeedCount = 0;

    private int prevRuneCount = -1;

    private boolean runecraftXpGainedThisTick = false;
    private int prevRunecraftXp = -1;
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
        processActions(Skill.FIREMAKING, LOGS_BURNED);
        prevRunecraftXp = currentRunecraftXp;
        currentRunecraftXp = client.getSkillExperience(Skill.RUNECRAFT);
        if (currentRunecraftXp > prevRunecraftXp && prevRunecraftXp != -1) runecraftXpGainedThisTick = true;
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
        if (prevRuneCount != -1 && prevRuneCount < currentRuneCount){
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
        final Matcher woodCutMatcher = WOOD_CUT_PATTERN.matcher(msg);
        final Matcher fishMatcher = FISH_CATCH_PATTERN.matcher(msg);
        final Matcher mineOrQuarrySomeMatcher = MINE_OR_QUARRY_SOME_PATTERN.matcher(msg);
        if (woodCutMatcher.matches())
        {
            statStore.incrementStat(LOGS_CHOPPED);
            if (woodCutMatcher.group(2).startsWith("log"))
            {
                incrementTypedLogs(woodCutMatcher.group(1));
            }
        }

        else if (fishMatcher.matches())
        {
            statStore.incrementStat(FISH_CAUGHT);
            incrementTypedFish(fishMatcher.group(1));
        }

        else if (mineOrQuarrySomeMatcher.matches())
        {
            statStore.incrementStat(ROCKS_MINED);
            incrementTypedMined(mineOrQuarrySomeMatcher.group(1));
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

    private void incrementTypedLogs(String logType)
    {
        if (logType == null)
        {
            statStore.incrementStat(NORMAL_LOGS_CHOPPED);
            return;
        }

        String t = logType.trim().toLowerCase();

        if (t.isEmpty() || t.equals("normal"))
        {
            statStore.incrementStat(NORMAL_LOGS_CHOPPED);
        }
        else if (t.equals("oak"))
        {
            statStore.incrementStat(OAK_LOGS_CHOPPED);
        }
        else if (t.equals("willow"))
        {
            statStore.incrementStat(WILLOW_LOGS_CHOPPED);
        }
        else if (t.equals("teak"))
        {
            statStore.incrementStat(TEAK_LOGS_CHOPPED);
        }
        else if (t.equals("maple"))
        {
            statStore.incrementStat(MAPLE_LOGS_CHOPPED);
        }
        else if (t.equals("mahogany"))
        {
            statStore.incrementStat(MAHOGANY_LOGS_CHOPPED);
        }
        else if (t.equals("yew"))
        {
            statStore.incrementStat(YEW_LOGS_CHOPPED);
        }
        else if (t.equals("magic"))
        {
            statStore.incrementStat(MAGIC_LOGS_CHOPPED);
        }
        else if (t.equals("redwood"))
        {
            statStore.incrementStat(REDWOOD_LOGS_CHOPPED);
        }
        else
        {
            //unknown logs type
            
        }
    }

    private void incrementTypedFish(String fishType)
    {
        log.debug("detected fish message with type {}", fishType );
        if (fishType == null)
        {
            return;
        }

        String t = fishType.trim().toLowerCase();
        if (t.startsWith("raw "))
        {
            t = t.substring("raw ".length()).trim();
        }

        switch (t)
        {
            case "shrimps":
            case "shrimp":
                statStore.incrementStat(SHRIMP_CAUGHT);
                break;
            case "anchovies":
            case "anchovy":
                statStore.incrementStat(ANCHOVIES_CAUGHT);
                break;
            case "sardine":
                statStore.incrementStat(SARDINE_CAUGHT);
                break;
            case "herring":
                statStore.incrementStat(HERRING_CAUGHT);
                break;
            case "trout":
                statStore.incrementStat(TROUT_CAUGHT);
                break;
            case "salmon":
                statStore.incrementStat(SALMON_CAUGHT);
                break;
            case "pike":
                statStore.incrementStat(PIKE_CAUGHT);
                break;
            case "cod":
                statStore.incrementStat(COD_CAUGHT);
                break;
            case "bass":
                statStore.incrementStat(BASS_CAUGHT);
                break;
            case "tuna":
                statStore.incrementStat(TUNA_CAUGHT);
                break;
            case "lobster":
                statStore.incrementStat(LOBSTER_CAUGHT);
                break;
            case "swordfish":
                statStore.incrementStat(SWORDFISH_CAUGHT);
                break;
            case "monkfish":
                statStore.incrementStat(MONKFISH_CAUGHT);
                break;
            case "shark":
                statStore.incrementStat(SHARK_CAUGHT);
                break;
            case "sea turtle":
                statStore.incrementStat(SEA_TURTLE_CAUGHT);
                break;
            case "manta ray":
                statStore.incrementStat(MANTA_RAY_CAUGHT);
                break;
            case "anglerfish":
                statStore.incrementStat(ANGLERFISH_CAUGHT);
                break;
            case "karambwan":
                statStore.incrementStat(KARAMBWAN_CAUGHT);
                break;
            case "dark crab":
                statStore.incrementStat(DARK_CRAB_CAUGHT);
                break;
            case "minnow":
            case "minnows":
                statStore.incrementStat(MINNOW_CAUGHT);
                break;
            case "sacred eel":
                statStore.incrementStat(SACRED_EEL_CAUGHT);
                break;
            case "leaping trout":
                statStore.incrementStat(LEAPING_TROUT_CAUGHT);
                break;
            case "leaping salmon":
                statStore.incrementStat(LEAPING_SALMON_CAUGHT);
                break;
            case "leaping sturgeon":
                statStore.incrementStat(LEAPING_STURGEON_CAUGHT);
                break;
        }
    }

    private void incrementTypedMined(String minedType)
    {
        if (minedType == null)
        {
            return;
        }

        String t = minedType.trim().toLowerCase();
        if (t.startsWith("granite"))
        {
            statStore.incrementStat(GRANITE_MINED);
            return;
        }
        if (t.startsWith("sandstone"))
        {
            statStore.incrementStat(SANDSTONE_MINED);
            return;
        }

        switch (t)
        {
            case "clay":
                statStore.incrementStat(CLAY_MINED);
                break;
            case "copper":
                statStore.incrementStat(COPPER_ORE_MINED);
                break;
            case "tin":
                statStore.incrementStat(TIN_ORE_MINED);
                break;
            case "limestone":
                statStore.incrementStat(LIMESTONE_MINED);
                break;
            case "iron":
                statStore.incrementStat(IRON_ORE_MINED);
                break;
            case "silver":
                statStore.incrementStat(SILVER_ORE_MINED);
                break;
            case "coal":
                statStore.incrementStat(COAL_MINED);
                break;
            case "gold":
                statStore.incrementStat(GOLD_ORE_MINED);
                break;
            case "mithril":
                statStore.incrementStat(MITHRIL_ORE_MINED);
                break;
            case "adamantite":
                statStore.incrementStat(ADAMANTITE_ORE_MINED);
                break;
            case "runite":
                statStore.incrementStat(RUNITE_ORE_MINED);
                break;
            case "pay-dirt":
                statStore.incrementStat(PAY_DIRT_MINED);
                break;
            case "amethyst":
                statStore.incrementStat(AMETHYST_MINED);
                break;
            case "pure essence":
                statStore.incrementStat(PURE_ESSENCE_MINED);
                break;
        }
    }
}

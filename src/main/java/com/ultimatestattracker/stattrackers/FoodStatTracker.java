package com.ultimatestattracker.stattrackers;

import com.ultimatestattracker.StatStore;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.*;
import net.runelite.client.util.Text;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.ultimatestattracker.StatKeys.*;

@Slf4j
public class FoodStatTracker implements StatTracker{
    private StatStore statStore;
    private Client client;
    private String lastThingConsumed;
    private int lastHp = -1;

    // Maps OSRS chat food names (lowercase, with spaces as shown in-game) to the
    // specific stat key that should be incremented when that food is eaten.
    // Falls back to existing FOOD_EATEN which is incremented for every food.
    private static final Map<String, String> FOOD_EATEN_MAP = new HashMap<>();
    static {
        // Non-fish foods (already supported)
        FOOD_EATEN_MAP.put("cabbage", CABBAGE_EATEN);
        FOOD_EATEN_MAP.put("trout", TROUT_EATEN);
        // Main OSRS fish that can be eaten
        FOOD_EATEN_MAP.put("shrimp", SHRIMP_EATEN);
        FOOD_EATEN_MAP.put("anchovies", ANCHOVIES_EATEN);
        FOOD_EATEN_MAP.put("sardine", SARDINE_EATEN);
        FOOD_EATEN_MAP.put("herring", HERRING_EATEN);
        FOOD_EATEN_MAP.put("pike", PIKE_EATEN);
        FOOD_EATEN_MAP.put("cod", COD_EATEN);
        FOOD_EATEN_MAP.put("salmon", SALMON_EATEN);
        FOOD_EATEN_MAP.put("tuna", TUNA_EATEN);
        FOOD_EATEN_MAP.put("bass", BASS_EATEN);
        FOOD_EATEN_MAP.put("lobster", LOBSTER_EATEN);
        FOOD_EATEN_MAP.put("swordfish", SWORDFISH_EATEN);
        FOOD_EATEN_MAP.put("monkfish", MONKFISH_EATEN);
        FOOD_EATEN_MAP.put("shark", SHARK_EATEN);
        FOOD_EATEN_MAP.put("manta ray", MANTA_RAY_EATEN);
        FOOD_EATEN_MAP.put("anglerfish", ANGLERFISH_EATEN);
        FOOD_EATEN_MAP.put("karambwan", KARAMBWAN_EATEN);
        FOOD_EATEN_MAP.put("dark crab", DARK_CRAB_EATEN);
        FOOD_EATEN_MAP.put("sea turtle", SEA_TURTLE_EATEN);
    }


    private String[] oneHPFood = {
            "Anchovies",
            "Chopped onion",
            "Asgarnian ale",
            "Wizard's mind bomb",
            "Greenman's ale",
            "Dragon bitter",
            "Dwarven stout",
            "Beer",
            "Potato",
            "Onion",
            "Cabbage",
            "Equa leaves",
            "Pot of cream",
            "Nettle-water",
            "Bandit's brew",
            "Axeman's folly",
            "Chef's delight",
            "Slayer's respite",
            "Cider",
            "Fresh monkfish",
            "Elven dawn",
            "Kovac's grog"
    };

    public FoodStatTracker(StatStore statStore, Client client)
    {
        this.statStore = statStore;
        this.client = client;
    }
    
    @Override
    public void onMenuOptionClicked(MenuOptionClicked event) {
        if (Objects.equals(event.getMenuOption(), "Eat"))
        {
            lastThingConsumed = Text.removeTags(event.getMenuTarget());
        }
        else if (Objects.equals(event.getMenuOption(), "Drink")) {
            lastThingConsumed = event.getMenuTarget();
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

        //check if hp changed
        //if did by 1 and last thing consumed was not a one hp food then natural health regen +1
        int currentHp = client.getBoostedSkillLevel(Skill.HITPOINTS);
        if (lastHp != -1 && currentHp == lastHp + 1) {
            //then if we didnt eat a 1 hp food most recently, this was probably natural regen
            //note if someone were to eat a more than 1 hp food at 98 and go to 99 (or any time they are 1 hp below their cap)
            //this would mistakenly be counted as regen
            //however i dont really think thats a huge problem as that should be rathar rare
            //maybe todo to improve this to have better food tracking and thus be more accurate
            if (lastThingConsumed == null) {
                statStore.incrementStat(HP_REGEN);
            } else if (Arrays.stream(oneHPFood).noneMatch(food -> Text.removeTags(lastThingConsumed).contains(food))) {
                statStore.incrementStat(HP_REGEN);
            } else
            {
                // If the last thingConsumed is not cleared or changed, and it was a 1 hp regen item, then hp regen stops tracking.
                // With this clear at most 1 hp regen is lost per "eat" of these items.
                lastThingConsumed = null;
            }
        }

        lastHp = currentHp;

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
        // Cake has 3 different chat message formats for its 3 slices, which we
        // track in CAKE_SLICES_EATEN. Compute once and reuse below.
        boolean isCakeSlice = msg.contains("part of the cake.")
                || msg.contains("some more of the cake.")
                || msg.contains("slice of cake");

        // Bump the generic FOOD_EATEN counter for any eating event, including the
        // "part of"/"some more of" cake variants that don't fit the "You eat the X." pattern below.
        if (msg.contains("You eat the") || isCakeSlice) {
            statStore.incrementStat(FOOD_EATEN);
        }

        if (msg.contains("You eat the")){
            //get the food name from the message, which is from the first space after "you eat the"
            //to the first period
            String foodName = msg
                    .substring(msg.indexOf("the ") + "the ".length(),msg.indexOf(".")).trim();

            log.debug("Suspected food name is {}",foodName);
            // Increment a specific fish/food counter if this food is known
            String matchingStat = FOOD_EATEN_MAP.get(foodName.toLowerCase());
            if (matchingStat != null) {
                statStore.incrementStat(matchingStat);
            }
        }

        if (isCakeSlice) {
            log.debug("Detected cake slice eaten: {}", msg);
            statStore.incrementStat(CAKE_SLICES_EATEN);
        }

        //for drinks it says "you drink the <x>"
        //for potions it says "you drink some of the <x>"
        else if (event.getMessage().contains("You drink")){
            //food name in this case is everything from "the" to the first period.
            String foodName = event.getMessage()
                    .substring(event.getMessage().indexOf("the ") + "the ".length(),event.getMessage().indexOf(".")).trim(); //todo bad repeated code ugly

            log.debug("Suspected food name is {}",foodName);
            if (foodName.equals("beer")){
                statStore.incrementStat(BEER_DRANK);
            }

            if (event.getMessage().contains(("You drink some of the")) || event.getMessage().contains(("You drink some of your"))){
                statStore.incrementStat(POTION_SIPS_DRANK);

                if (event.getMessage().contains("divine")) {
                    statStore.incrementStatBy(DIVINE_DAMAGE, 10); // Divine pots always do 10 damage
                }
            }
        }

        if(event.getMessage().contains("You quickly smash the empty vial")){
            statStore.incrementStat(VIALS_SMASHED);
        }
    }

    @Override
    public void onHitsplatApplied(HitsplatApplied event) {

    }

    @Override
    public void onAnimationChanged(AnimationChanged event) {

    }
}

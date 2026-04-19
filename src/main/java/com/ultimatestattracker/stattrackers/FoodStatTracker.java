package com.ultimatestattracker.stattrackers;

import com.ultimatestattracker.StatStore;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.*;
import net.runelite.client.util.Text;

import java.util.Arrays;
import java.util.Objects;

import static com.ultimatestattracker.StatKeys.*;

@Slf4j
public class FoodStatTracker implements StatTracker{
    private StatStore statStore;
    private Client client;
    private String lastThingConsumed;
    private int lastHp = -1;


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
        if (lastHp != -1 && currentHp == lastHp + 1)
        {
            //then if we didnt eat a 1 hp food most recently, this was probably natural regen
            //note if someone were to eat a more than 1 hp food at 98 and go to 99 (or any time they are 1 hp below their cap)
            //this would mistakenly be counted as regen
            //however i dont really think thats a huge problem as that should be rathar rare
            //maybe todo to improve this to have better food tracking and thus be more accurate
            if (lastThingConsumed == null)
            {
                statStore.incrementStat(HP_REGEN);
            }
            else if (Arrays.stream(oneHPFood).noneMatch(food -> Text.removeTags(lastThingConsumed).contains(food)))
            {
                statStore.incrementStat(HP_REGEN);
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

        if (event.getMessage().contains("You eat the")){
            //get the food name from the message, which is from the first space after "you eat the"
            //to the first period
            statStore.incrementStat(FOOD_EATEN);
            String foodName = event.getMessage()
                    .substring(event.getMessage().indexOf("You eat the ") + "You eat the ".length()).trim();

            //todo make case insensitive
            if (foodName.equals("trout")){
                statStore.incrementStat(TROUT_EATEN);
            }
            else if (foodName.equals("cabbage")){
                statStore.incrementStat(CABBAGE_EATEN);
            }
        }

        //for drinks it says "you drink the <x>"
        //for potions it says "you drink some of the <x>"
        else if (event.getMessage().contains("You drink")){
            //food name in this case is everything from "the" to the first period.
            String foodName = event.getMessage()
                    .substring(event.getMessage().indexOf("the ") + "the ".length()).trim();

            log.debug("Suspected food name is {}",foodName);
            if (foodName.equals("beer")){
                statStore.incrementStat(BEER_DRANK);
            }

            if (event.getMessage().contains(("you drink some of the"))){
                statStore.incrementStat(POTION_SIPS_DRANK);
            }
        }

        if(event.getMessage().contains("You quickly smash the empty vial")){
            statStore.incrementStat(VIALS_SMASHED);
        }
    }

    @Override
    public void onHitsplatApplied(HitsplatApplied event) {

    }
}

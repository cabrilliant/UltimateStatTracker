package com.ultimatestattracker.stattrackers;

import com.ultimatestattracker.StatStore;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.*;
import net.runelite.client.util.Text;

import java.util.Objects;

import static com.ultimatestattracker.StatKeys.FOOD_EATEN;

@Slf4j
public class FoodStatTracker implements StatTracker{
    private StatStore statStore;
    private Client client;
    private String lastThingConsumed;

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
            statStore.incrementStat(FOOD_EATEN);
            log.debug("Item eat clicked: {}", event.getMenuTarget());
            lastThingConsumed = Text.removeTags(event.getMenuTarget());
        }
        else if (Objects.equals(event.getMenuOption(), "Drink"))
        {
            //todo add some logic to tell if this was a potion or food
            log.debug("Item drink clicked: {}", event.getMenuTarget());
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
    }

    @Override
    public void onGameStateChanged(GameStateChanged gameStateChanged) {

    }

    @Override
    public void onChatMessage(ChatMessage event) {

    }
}

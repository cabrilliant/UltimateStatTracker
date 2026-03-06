package com.ultimatestattracker.stattrackers;

import com.ultimatestattracker.StatStore;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.Skill;
import net.runelite.api.events.*;
import net.runelite.client.plugins.xptracker.XpTrackerService;

import java.util.HashMap;
import java.util.regex.Pattern;

import static com.ultimatestattracker.StatKeys.*;

public class SkillingStatTracker implements StatTracker{
    private StatStore statStore;
    private Client client;
    private XpTrackerService xpService;
    HashMap<Skill,Integer> previousSkillActions = new HashMap<>();
    //from woodcutting plugin
    private static final Pattern WOOD_CUT_PATTERN = Pattern.compile("You get (?:some|an)[\\w ]+(?:logs?|mushrooms)\\.");


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

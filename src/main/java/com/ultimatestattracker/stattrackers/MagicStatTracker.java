package com.ultimatestattracker.stattrackers;

import com.ultimatestattracker.StatStore;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.*;

import static com.ultimatestattracker.StatKeys.SPELLS_CAST;

//todo need a special case for home tele as it doesnt give xp
@Slf4j
public class MagicStatTracker implements StatTracker{
    private StatStore statStore;
    private Client client;


    private int lastMagicXp = -1;
    private boolean pendingSpellPress = false;

    private int pendingSpellTickCounter = 0;
    private static final int SPELL_PRESS_TIMEOUT_TICKS = 17; // ~10 seconds

    public MagicStatTracker(StatStore statStore, Client client)
    {
        this.statStore = statStore;
        this.client = client;
    }
    
    @Override
    public void onMenuOptionClicked(MenuOptionClicked event) {
        //-> is to ignore the second cast when an offensive spell is used on a target.  we just count the first spell click.
        if(event.getMenuOption().contains("Cast") && !event.getMenuOption().contains("->")){
            pendingSpellPress = true;
            pendingSpellTickCounter = 0;
            log.debug("Spell cast clicked: {}", event.getMenuTarget());
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
        Player local = client.getLocalPlayer();
        NPC target = local.getInteracting() instanceof NPC ? (NPC) local.getInteracting() : null;
        boolean playerInCombat = target != null;
        int currentMagicXp = client.getSkillExperience(Skill.MAGIC);
        if (lastMagicXp == -1){
            lastMagicXp  = currentMagicXp;
        }
        if (currentMagicXp != lastMagicXp) {
            if (pendingSpellPress || playerInCombat ) {
                statStore.incrementStat(SPELLS_CAST);
                log.debug("Gained magic xp");
                lastMagicXp = currentMagicXp;
                pendingSpellPress = false;
            }
        }

        if (pendingSpellTickCounter >= SPELL_PRESS_TIMEOUT_TICKS && pendingSpellPress) {
            MenuEntry[] entries = client.getMenuEntries();
            boolean stillCasting = false;

            if (entries != null && entries.length > 0) {
                MenuEntry lastEntry = entries[entries.length - 1];
                //todo
                //this doesnt actualyl work, it will only sitll show cast if mouse is hovered over something castable
                //if possible, track spell widget, as it highlights when its in this state
                if (lastEntry.getOption() != null && lastEntry.getOption().contains("Cast")) {
                    log.debug("still casting after 10 seconds");
                    stillCasting = true;
                }
            }

            if (stillCasting) {

                pendingSpellTickCounter = 0;
                log.debug("Pending spell press reset as we are still casting");
            }

            else{
                pendingSpellPress = false;
                log.debug("Pending spell press timed out, no longer waiting for magic xp gain");
            }
        }

        if(pendingSpellPress){
            pendingSpellTickCounter++;
        }
    }

    @Override
    public void onGameStateChanged(GameStateChanged gameStateChanged) {

    }
}

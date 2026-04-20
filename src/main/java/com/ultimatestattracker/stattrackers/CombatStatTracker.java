package com.ultimatestattracker.stattrackers;

import com.ultimatestattracker.StatStore;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.*;

import static com.ultimatestattracker.StatKeys.*;

@Slf4j
public class CombatStatTracker implements StatTracker{
    private StatStore statStore;
    private Client client;

    private int prevAttackXp;
    private int prevStrengthXp;
    private int prevDefenceXp;
    private int prevHpXp;

    private int prevSpecialAttackPercent = -1;

    public CombatStatTracker(StatStore statStore, Client client)
    {
        this.statStore = statStore;
        this.client = client;
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
    public void onHitsplatApplied(HitsplatApplied event)
    {
        Hitsplat hitsplat = event.getHitsplat();
        Actor actor = event.getActor();
        log.debug("Hitsplat type {}",hitsplat.getHitsplatType());
        switch(hitsplat.getHitsplatType()) {
            case HitsplatID.DAMAGE_ME: //this triggers both on damage done BY and TO the player
                if (actor == client.getLocalPlayer()) { //we took damage
                    statStore.incrementStatBy(DAMAGE_RECEIVED, hitsplat.getAmount());
                }
                else { //we did damage to something else
                    statStore.incrementStatBy(DAMAGE_DONE, hitsplat.getAmount());
                    if (statStore.getStat(BIGGEST_HITSPLAT) < hitsplat.getAmount()) {
                        statStore.setStat(BIGGEST_HITSPLAT, hitsplat.getAmount());
                    }
                }
                break;
            case HitsplatID.BLOCK_ME:
                if (actor == client.getLocalPlayer()) {
                    statStore.incrementStat(ATTACKS_BLOCKED);
                }
                else{
                    statStore.incrementStat(ATTACKS_MISSED);
                }
                break;
        }
    }

    @Override
    public void onGameTick(GameTick event) {

    }

    @Override
    public void onGameStateChanged(GameStateChanged gameStateChanged) {

    }

    @Override
    public void onChatMessage(ChatMessage event) {

    }
}

package com.ultimatestattracker.stattrackers;

import com.ultimatestattracker.StatStore;
import net.runelite.api.*;
import net.runelite.api.events.*;
import net.runelite.client.eventbus.Subscribe;

import static com.ultimatestattracker.StatKeys.MELEE_ATTACKS_LANDED;
import static com.ultimatestattracker.StatKeys.TOTAL_DAMAGE_DONE;

public class CombatStatTracker implements StatTracker{
    private StatStore statStore;
    private Client client;

    private int prevAttackXp;
    private int prevStrengthXp;
    private int prevDefenceXp;
    private int prevHpXp;

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
        Actor target = event.getActor(); // who got hit
        Hitsplat hitsplat = event.getHitsplat();

        // Only count damage hitsplats
        if (hitsplat.getHitsplatType() == HitsplatID.DAMAGE_OTHER)
        {
            // Check if YOU are the attacker
            if (target.getInteracting() == client.getLocalPlayer())
            {
                statStore.incrementStatBy(TOTAL_DAMAGE_DONE, hitsplat.getAmount());
            }
        }
    }

    @Override
    public void onGameTick(GameTick event) {
        Player local = client.getLocalPlayer();
        NPC target = local.getInteracting() instanceof NPC ? (NPC) local.getInteracting() : null;
        boolean playerInCombat = target != null;
        int attackXp = client.getSkillExperience(Skill.ATTACK);
        int strengthXp = client.getSkillExperience(Skill.STRENGTH);
        int defenceXp = client.getSkillExperience(Skill.DEFENCE);
        int hpXp = client.getSkillExperience(Skill.HITPOINTS);

        if (playerInCombat){
            boolean gainedMeleeXp =
                    attackXp > prevAttackXp ||
                            strengthXp > prevStrengthXp ||
                            defenceXp > prevDefenceXp ||
                            hpXp > prevHpXp;

            if (gainedMeleeXp)
            {
                statStore.incrementStat(MELEE_ATTACKS_LANDED);
            }

        }
        prevAttackXp = attackXp;
        prevStrengthXp = strengthXp;
        prevDefenceXp = defenceXp;
        prevHpXp = hpXp;
    }

    @Override
    public void onGameStateChanged(GameStateChanged gameStateChanged) {

    }

    @Override
    public void onChatMessage(ChatMessage event) {

    }
}

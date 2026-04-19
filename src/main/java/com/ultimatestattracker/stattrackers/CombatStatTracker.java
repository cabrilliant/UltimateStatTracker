package com.ultimatestattracker.stattrackers;

import com.ultimatestattracker.StatStore;
import net.runelite.api.*;
import net.runelite.api.events.*;

import static com.ultimatestattracker.StatKeys.*;

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

        if (hitsplat.getHitsplatType() == HitsplatID.DAMAGE_OTHER)
        {
            if (hitsplat.isMine())
            {
                statStore.incrementStatBy(TOTAL_DAMAGE_DONE, hitsplat.getAmount());
                if (statStore.getStat(BIGGEST_HITSPLAT) < hitsplat.getAmount())
                {
                    statStore.setStat(BIGGEST_HITSPLAT, hitsplat.getAmount());
                }
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

        int currentSpecialAttackEnergy = client.getVar(VarPlayer.SPECIAL_ATTACK_PERCENT);
        if (prevSpecialAttackPercent != -1 && prevSpecialAttackPercent > currentSpecialAttackEnergy){
            //if we lost special attack energy we used a special attack
            statStore.incrementStat(SPECIAL_ATTACKS_PREFORMED);
        }
        prevSpecialAttackPercent = currentSpecialAttackEnergy;
    }

    @Override
    public void onGameStateChanged(GameStateChanged gameStateChanged) {

    }

    @Override
    public void onChatMessage(ChatMessage event) {

    }
}

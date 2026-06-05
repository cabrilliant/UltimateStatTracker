package com.ultimatestattracker.stattrackers;

import com.ultimatestattracker.StatStore;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.api.events.*;

import java.util.Objects;

import static com.ultimatestattracker.StatKeys.*;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.Player;

@Slf4j
public class NPCStatTracker implements StatTracker{
    private StatStore statStore;
    private Client client;

    boolean evenTick = false;

    boolean pendingPet = false;

    private static final WorldPoint AREA_NE = new WorldPoint(2663, 3244, 0);
    private static final WorldPoint AREA_SW = new WorldPoint(2628, 3223, 0);

    private static final String TARGET_NPC_NAME = "'The Guns'";


    public NPCStatTracker(StatStore statStore, Client client)
    {
        this.statStore = statStore;
        this.client = client;
    }
    
    @Override
    public void onMenuOptionClicked(MenuOptionClicked event) {
        if (Objects.equals(event.getMenuOption(), "Pet"))
        {
            pendingPet = true;
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
        if (pendingPet && evenTick){ //2 tick limited since petting is 2 tick limited
            statStore.incrementStat(CRITTERS_PET);
            pendingPet = false;
        }

        Player localPlayer = client.getLocalPlayer();
        if (localPlayer == null)
        {
            return;
        }
        WorldPoint playerLocation = localPlayer.getWorldLocation();
        if ((isInArea(playerLocation)))
        {
            //try to find "The Guns"
            for (NPC npc : client.getNpcs()) {
                if (npc == null || npc.getName() == null) {
                    continue;
                }

                if (!npc.getName().equalsIgnoreCase(TARGET_NPC_NAME)) {
                    continue;
                }

                String overhead = npc.getOverheadText();

                if (overhead == null) {
                    continue;
                }

                //log.debug("Guns: {}", overhead);
                String digits = overhead.replaceAll("[^0-9]", "");
                if (digits.isEmpty())
                {
                    return;
                }
                int value = Integer.parseInt(digits);
                if (statStore.getStat(THE_GUNS) < value ) {
                    //new highest number seen counted
                    statStore.setStat(THE_GUNS, value);
                }
            }
        }
        evenTick = !evenTick;
    }

    private boolean isInArea(WorldPoint point)
    {
        return point.getX() >= AREA_SW.getX()
                && point.getX() <= AREA_NE.getX()
                && point.getY() >= AREA_SW.getY()
                && point.getY() <= AREA_NE.getY()
                && point.getPlane() == AREA_SW.getPlane();
    }

    @Override
    public void onGameStateChanged(GameStateChanged gameStateChanged) {

    }

    @Override
    public void onChatMessage(ChatMessage event) {

    }

    @Override
    public void onHitsplatApplied(HitsplatApplied event) {

    }

    @Override
    public void onAnimationChanged(AnimationChanged event) {

    }

}

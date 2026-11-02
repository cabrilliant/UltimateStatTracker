package com.ultimatestattracker.stattrackers;

import com.ultimatestattracker.StatStore;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Actor;
import net.runelite.api.Client;
import net.runelite.api.MenuAction;
import net.runelite.api.Player;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.*;
import net.runelite.api.widgets.Widget;
import net.runelite.client.input.KeyListener;
import net.runelite.api.Varbits;
import net.runelite.client.util.Text;

import java.awt.event.KeyEvent;

import static com.ultimatestattracker.StatKeys.*;

@Slf4j
public class MovementStatTracker implements StatTracker{
    private StatStore statStore;
    private Client client;
    private WorldPoint lastPlayerPos = null;

    private int runEnergyIconId = 10485793;
    private int runEnergyDisabledSpriteId = 1069;
    private int runEnergyEnabledSpriteId = 1070;
    private Widget runEnergyWidget = null;

    public int traversalDebounce;
    private boolean ctrlHeld = false;
    private String lastOptionClicked;

    public MovementStatTracker(StatStore statStore, Client client)
    {
        this.statStore = statStore;
        this.client = client;
    }

    public final KeyListener ctrlKeyListner = new KeyListener()
    {
        @Override
        public void keyTyped(KeyEvent e) { }

        @Override
        public void keyPressed(KeyEvent e)
        {
            if(e.getKeyCode() == KeyEvent.VK_CONTROL){
                ctrlHeld = true;
            }
        }

        @Override
        public void keyReleased(KeyEvent e)
        {
            if(e.getKeyCode() == KeyEvent.VK_CONTROL){
                ctrlHeld = false;
            }
        }
    };
    
    @Override
    public void onMenuOptionClicked(MenuOptionClicked event) {
        trackObjectInteractions(event);

        //have to handle diary teleports carefully
        if (event.getMenuTarget().toLowerCase().contains("teleport") &&
                (! (event.getMenuOption().toLowerCase().contains("grand exchange")||
                        event.getMenuOption().toLowerCase().contains("yanille") ||
                        event.getMenuOption().toLowerCase().contains("seers'")))){

                lastOptionClicked =Text.removeTags(event.getMenuTarget()).toLowerCase();
        }
        else {
            lastOptionClicked = event.getMenuOption().toLowerCase();
        }
    }

    private void trackObjectInteractions(MenuOptionClicked event) {
        if (!isGameObjectAction(event.getMenuAction()) || traversalDebounce > 0) {
            return;
        }

        String option = event.getMenuOption();
        String target = event.getMenuTarget();
        if (option == null || target == null) {
            return;
        }
        target = Text.removeTags(target).toLowerCase();

        if (option.equals("Open") && (target.contains("door") || target.contains("gate"))) {
            statStore.incrementStat(DOORS_OPENED);
            traversalDebounce = 2;
        }
        else if (option.equals("Close") && (target.contains("door") || target.contains("gate"))) {
            statStore.incrementStat(DOORS_CLOSED);
            traversalDebounce = 2;
        }
        else if (option.startsWith("Climb") && (target.contains("ladder") || target.contains("stair"))) {
            statStore.incrementStat(LADDERS_STAIRS_CLIMBED);
            traversalDebounce = 2;
        }
    }

    private boolean isGameObjectAction(MenuAction action) {
        return action == MenuAction.GAME_OBJECT_FIRST_OPTION
                || action == MenuAction.GAME_OBJECT_SECOND_OPTION
                || action == MenuAction.GAME_OBJECT_THIRD_OPTION
                || action == MenuAction.GAME_OBJECT_FOURTH_OPTION
                || action == MenuAction.GAME_OBJECT_FIFTH_OPTION;
    }

    @Override
    public void onWidgetLoaded(WidgetLoaded event) {
        if (event.getGroupId() == 193){
            log.debug("potential spirit tree popup");
            Widget chatBoxMessage = client.getWidget(193,2);
            if (chatBoxMessage == null){
                log.debug("couldnt find chatbox message widget for spirit tree");
            }
            if (chatBoxMessage!=null){
                log.debug("text: {}", chatBoxMessage.getText());
                if (chatBoxMessage.getText().contains("place your hands on the dry")){
                    statStore.incrementStat(SPIRIT_TREE_TELES);
                }
            }
        }
    }

    @Override
    public void onWidgetClosed(WidgetClosed event) {

    }

    //todo add key listener for ctrl, if held down, then player will walk.
    //maybe just use animations
    @Override
    public void onGameTick(GameTick event) {
        if (traversalDebounce > 0 ) traversalDebounce--;
        Player local = client.getLocalPlayer();
        if (local != null) {
            WorldPoint currentPos = local.getWorldLocation();
            if (lastPlayerPos != null) {
                int dx = Math.abs(currentPos.getX() - lastPlayerPos.getX());
                int dy = Math.abs(currentPos.getY() - lastPlayerPos.getY());
                int maxMove = Math.max(dx, dy);

                if (maxMove > 0 && maxMove < 3) {
                    runEnergyWidget = client.getWidget(runEnergyIconId);
                    boolean runEnabled = false;
                    if (runEnergyWidget != null && !runEnergyWidget.isHidden())
                    {
                        int spriteId = runEnergyWidget.getSpriteId();
                        runEnabled = spriteId == runEnergyEnabledSpriteId;
                    }

                    //todo in future add config setting if ctrl to inverse isnt enabled
                    if ( (runEnabled && !ctrlHeld) || (!runEnabled && ctrlHeld)){
                        statStore.incrementStatBy(TILES_RAN, maxMove);
                    }
                    else{
                        statStore.incrementStatBy(TILES_WALKED, maxMove);
                    }

                    //log.debug("Moved {} tiles (ran {})", maxMove, runEnabled);
                }
            }
            lastPlayerPos = currentPos;
        }
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

    @Override public void onAnimationChanged(AnimationChanged event)
    {
        Actor actor = event.getActor();
        if (actor == client.getLocalPlayer()) {     
            //todo export to constants
            if (actor.getAnimation() == 3265 ){
                statStore.incrementStat(FAIRY_RING_TELES);
            }

            //tele tab
            else if (actor.getAnimation() == 4069){
                trackTeleTab(lastOptionClicked);
            }

            //standard teleport
            else if(actor.getAnimation() == 714){
                trackStandardTeleport(lastOptionClicked);
            }
        }
    }

    private void trackStandardTeleport(String lastOptionClicked) {
        trackTeleport(lastOptionClicked);
    }

    private void trackTeleTab(String lastOptionClicked) {
        trackTeleport(lastOptionClicked);
    }

    private void trackTeleport(String lastOptionClicked) {
        statStore.incrementStat(TOTAL_TELEPORTS);
        //use if else instead of switch here because for non diary teles we get <x> teleport, but for diary teles we just get <x> so we need to use contains
        log.debug("last teleport clicked {}",lastOptionClicked);
        if (lastOptionClicked.contains("varrock")){
            statStore.incrementStat(VARROCK_TELEPORT);
        }
        else if (lastOptionClicked.contains("grand exchange")){
            statStore.incrementStat(GRAND_EXCHANGE_TELEPORT);
        }
        else if (lastOptionClicked.contains("lumbridge")){
            statStore.incrementStat(LUMBRIDGE_TELEPORT);
        }
        else if (lastOptionClicked.contains("falador")){
            statStore.incrementStat(FALADOR_TELEPORT);
        }
        else if (lastOptionClicked.contains("camelot")){
            statStore.incrementStat(CAMELOT_TELEPORT);
        }
        else if (lastOptionClicked.contains("seers'")){
            statStore.incrementStat(SEERS_VILLAGE_TELEPORT);
        }
        else if (lastOptionClicked.contains("ardougne")){
            statStore.incrementStat(ARDOUGNE_TELEPORT);
        }
        else if (lastOptionClicked.contains("kourend")){
            statStore.incrementStat(KOUREND_TELEPORT);
        }
        else if (lastOptionClicked.contains("civitas")){
            statStore.incrementStat(FORTIS_TELEPORT);
        }
        else if (lastOptionClicked.contains("watchtower")){
           statStore.incrementStat(WATCHTOWER_TELEPORT);
        }
        else if (lastOptionClicked.contains("yanille")){
            statStore.incrementStat(YANILLE_TELEPORT);
        }
        else if (lastOptionClicked.contains("trollheim")){
            statStore.incrementStat(TROLLHEIM_TELEPORT);
        }
        else if (lastOptionClicked.contains("ape atoll")){
            statStore.incrementStat(APE_ATOLL_TELEPORT);
        }
        else if (lastOptionClicked.contains("house")){
            statStore.incrementStat(HOUSE_TELEPORT);
        }


    }
}

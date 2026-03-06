package com.ultimatestattracker.stattrackers;

import net.runelite.api.ChatMessageType;
import net.runelite.api.GameState;
import net.runelite.api.events.*;

public interface StatTracker {
    public void onMenuOptionClicked(MenuOptionClicked event);
    public void onWidgetLoaded(WidgetLoaded event);
    public void onWidgetClosed(WidgetClosed event);
    public void onGameTick(GameTick event);
    public void onGameStateChanged(GameStateChanged gameStateChanged);
    public void onChatMessage(ChatMessage event);

}

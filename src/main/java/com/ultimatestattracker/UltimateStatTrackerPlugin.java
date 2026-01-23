package com.ultimatestattracker;

import com.google.inject.Provides;
import javax.inject.Inject;
import javax.swing.*;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.events.CommandExecuted;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.input.MouseAdapter;
import net.runelite.client.input.MouseListener;
import net.runelite.client.input.MouseManager;
import net.runelite.client.ui.overlay.OverlayManager;
import java.awt.*;
import java.awt.event.MouseEvent;

@Slf4j
@PluginDescriptor(
	name = "Ultimate Stat Tracker"
)
public class UltimateStatTrackerPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private UltimateStatTrackerOverlay overlay;

	@Inject
	private UltimateStatTrackerConfig config;

	@Inject
	private MouseManager mouseManager;

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(overlay);
		log.debug("Example started!");
		mouseManager.registerMouseListener(mouseListener);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
		log.debug("Example stopped!");
		mouseManager.unregisterMouseListener(mouseListener);
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example says " + config.greeting(), null);
		}
	}

	@Provides
	UltimateStatTrackerConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(UltimateStatTrackerConfig.class);
	}

	@Subscribe
	public void onCommandExecuted(CommandExecuted event)
	{
		log.info("Command executed: {}", event.getCommand());
		if (!event.getCommand().equalsIgnoreCase("ust"))
		{
			return;
		}

		overlay.toggle();
	}

	private final MouseListener mouseListener = new MouseAdapter()
	{
		@Override
		public MouseEvent mousePressed(MouseEvent event)
		{
			if (!overlay.isVisible() || !SwingUtilities.isLeftMouseButton(event))
			{
				return event;
			}

			Point mouse = event.getPoint();

			// Clicked on the close button
			if (overlay.isInCloseButtonBounds(mouse))
			{
				log.info("close clicekd");
				overlay.close();
				event.consume();
				return event;
			}

			// Clicked inside overlay (can add more interactions later)
			if (overlay.isInBounds(mouse))
			{
				log.info("overlay clicekd");
				event.consume();
				return event;
			}

			// Clicked outside overlay (optional: dismiss)
			return event;
		}
	};

}

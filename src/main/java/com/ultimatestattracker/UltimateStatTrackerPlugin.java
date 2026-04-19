package com.ultimatestattracker;

import com.google.inject.Provides;
import javax.inject.Inject;
import javax.swing.*;

import com.ultimatestattracker.stattrackers.GoldStatTracker;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.*;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.input.KeyManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDependency;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.input.MouseAdapter;
import net.runelite.client.input.MouseListener;
import net.runelite.client.input.MouseManager;
import net.runelite.client.plugins.xptracker.XpTrackerPlugin;
import net.runelite.client.plugins.xptracker.XpTrackerService;
import net.runelite.client.ui.overlay.OverlayManager;
import java.awt.*;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Objects;
import net.runelite.api.widgets.WidgetInfo;

import net.runelite.api.ChatMessageType;

import static com.ultimatestattracker.StatKeys.*;
import com.ultimatestattracker.stattrackers.*;

@PluginDependency(XpTrackerPlugin.class)
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

	private StatStore statStore;

	@Inject
	private ConfigManager configManager;

	@Inject
	private XpTrackerService trackerService;

	private GoldStatTracker goldStatTracker;
	private ItemStatTracker itemStatTracker;
	private MagicStatTracker magicStatTracker;
	private MovementStatTracker movementStatTracker;
	private SkillingStatTracker skillingStatTracker;
	private PlayerStatTracker playerStatTracker;
	private FoodStatTracker foodStatTracker;
	private NPCStatTracker npcStatTracker;
	private CombatStatTracker combatStatTracker;

	@Inject
	private KeyManager keyManager;

	@Override
	protected void startUp() throws Exception
	{
		statStore = new StatStore(configManager);
		overlay.setStatStore(statStore);
		overlayManager.add(overlay);
		log.debug("Example started!");
		mouseManager.registerMouseListener(mouseListener);

		goldStatTracker = new GoldStatTracker(statStore, client);
		itemStatTracker = new ItemStatTracker(statStore, client);
		magicStatTracker = new MagicStatTracker(statStore, client);
		movementStatTracker = new MovementStatTracker(statStore,client);
		skillingStatTracker = new SkillingStatTracker(statStore, client, trackerService);
		playerStatTracker = new PlayerStatTracker(statStore, client);
		foodStatTracker = new FoodStatTracker(statStore, client);
		npcStatTracker = new NPCStatTracker(statStore, client);
		combatStatTracker = new CombatStatTracker(statStore, client);

		keyManager.registerKeyListener(movementStatTracker.ctrlKeyListner);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
		log.debug("Example stopped!");
		keyManager.unregisterKeyListener(movementStatTracker.ctrlKeyListner);
		mouseManager.unregisterMouseListener(mouseListener);
	}

	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked event)
	{
		log.debug(event.getMenuTarget());
		log.debug(event.getMenuOption());

		itemStatTracker.onMenuOptionClicked(event);
		magicStatTracker.onMenuOptionClicked(event);
		foodStatTracker.onMenuOptionClicked(event);
		npcStatTracker.onMenuOptionClicked(event);

	}

	@Subscribe
	public void onWidgetLoaded(WidgetLoaded event)
	{
		goldStatTracker.onWidgetLoaded(event);
	}

	@Subscribe
	public void onWidgetClosed(WidgetClosed event)
	{
		goldStatTracker.onWidgetClosed(event);
	}

	@Subscribe
	public void onGameTick(GameTick event) {
		goldStatTracker.onGameTick(event);
		magicStatTracker.onGameTick(event);
		movementStatTracker.onGameTick(event);
		skillingStatTracker.onGameTick(event);
		playerStatTracker.onGameTick(event);
		itemStatTracker.onGameTick(event);
		foodStatTracker.onGameTick(event);
		combatStatTracker.onGameTick(event);
		npcStatTracker.onGameTick(event);
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

	@Subscribe
	public void onChatMessage(ChatMessage event)
	{
		skillingStatTracker.onChatMessage(event);
		foodStatTracker.onChatMessage(event);
		itemStatTracker.onChatMessage(event);
	}

	@Subscribe
	public void onHitsplatApplied(HitsplatApplied event)
	{
		playerStatTracker.onHitsplatApplied(event);
		combatStatTracker.onHitsplatApplied(event);
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

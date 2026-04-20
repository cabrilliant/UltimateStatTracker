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
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.ui.overlay.OverlayManager;
import java.awt.*;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Objects;
import net.runelite.api.widgets.WidgetInfo;

import net.runelite.api.ChatMessageType;

import static com.ultimatestattracker.StatKeys.*;
import com.ultimatestattracker.stattrackers.*;
import net.runelite.client.util.ImageUtil;

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
	private ClientToolbar clientToolbar;

	private NavigationButton navButton;

	private UltimateStatTrackerPanel panel;
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
	private MovementStatTracker movementStatTracker;
	private SkillingStatTracker skillingStatTracker;
	private FoodStatTracker foodStatTracker;
	private NPCStatTracker npcStatTracker;

	@Inject
	private KeyManager keyManager;

	@Override
	protected void startUp() throws Exception
	{
		statStore = new StatStore(configManager);
		overlay.setStatStore(statStore);
		overlayManager.add(overlay);
		mouseManager.registerMouseListener(mouseListener);

		goldStatTracker = new GoldStatTracker(statStore, client);
		itemStatTracker = new ItemStatTracker(statStore, client);
		movementStatTracker = new MovementStatTracker(statStore,client);
		skillingStatTracker = new SkillingStatTracker(statStore, client, trackerService);
		foodStatTracker = new FoodStatTracker(statStore, client);
		npcStatTracker = new NPCStatTracker(statStore, client);

		keyManager.registerKeyListener(movementStatTracker.ctrlKeyListner);

		BufferedImage icon = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);

		panel = new UltimateStatTrackerPanel();
		panel.setStatStore(statStore);

		navButton = NavigationButton.builder()
				.tooltip("Ultimate Stats")
				.icon(icon)
				.priority(5)
				.panel(panel)
				.build();

		clientToolbar.addNavigation(navButton);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
		keyManager.unregisterKeyListener(movementStatTracker.ctrlKeyListner);
		mouseManager.unregisterMouseListener(mouseListener);
		clientToolbar.removeNavigation(navButton);
	}

	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked event)
	{
		log.debug(event.getMenuTarget());
		log.debug(event.getMenuOption());

		itemStatTracker.onMenuOptionClicked(event);
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
		movementStatTracker.onGameTick(event);
		skillingStatTracker.onGameTick(event);
		itemStatTracker.onGameTick(event);
		foodStatTracker.onGameTick(event);
		npcStatTracker.onGameTick(event);
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

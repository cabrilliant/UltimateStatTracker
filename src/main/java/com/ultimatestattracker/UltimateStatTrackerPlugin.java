package com.ultimatestattracker;

import com.google.inject.Provides;
import javax.inject.Inject;

import com.ultimatestattracker.stattrackers.GoldStatTracker;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.*;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.input.KeyManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDependency;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.input.MouseManager;
import net.runelite.client.plugins.xptracker.XpTrackerPlugin;
import net.runelite.client.plugins.xptracker.XpTrackerService;
import net.runelite.client.game.SkillIconManager;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;

import java.awt.image.BufferedImage;

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
	private ClientToolbar clientToolbar;

	private NavigationButton navButton;

	private UltimateStatTrackerPanel panel;

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
	private CombatStatTracker combatStatTracker;

	@Inject
	private KeyManager keyManager;

	@Inject
	private SkillIconManager skillIconManager;

	public boolean loggedIn;
	public boolean firstLogin = true;

	public boolean isPerformanceMode()
	{
		return config.performanceMode();
	}

	@Override
	protected void startUp() throws Exception
	{
		statStore = new StatStore(configManager);

		goldStatTracker = new GoldStatTracker(statStore, client);
		itemStatTracker = new ItemStatTracker(statStore, client);
		movementStatTracker = new MovementStatTracker(statStore,client);
		skillingStatTracker = new SkillingStatTracker(statStore, client, trackerService);
		foodStatTracker = new FoodStatTracker(statStore, client);
		npcStatTracker = new NPCStatTracker(statStore, client);
		combatStatTracker = new CombatStatTracker(statStore, client);

		keyManager.registerKeyListener(movementStatTracker.ctrlKeyListner);

		BufferedImage icon = ImageUtil.loadImageResource(getClass(), "/usticon.png");

		panel = new UltimateStatTrackerPanel(this, skillIconManager);
		panel.setStatStore(statStore);

		navButton = NavigationButton.builder()
				.tooltip("Ultimate Stat Tracker")
				.icon(icon)
				.priority(5)
				.panel(panel)
				.build();

		clientToolbar.addNavigation(navButton);
	}

	@Override
	protected void shutDown() throws Exception
	{
		keyManager.unregisterKeyListener(movementStatTracker.ctrlKeyListner);
		clientToolbar.removeNavigation(navButton);
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged event)
	{
		if (event.getGameState() == GameState.LOGGED_IN)
		{
			for (StatKey key : ALL_KEYS)
			{
				if ("0".equals(statStore.getStatTrackingDate(key.getValue())))
				{
					String formattedDate = java.time.Instant.ofEpochMilli(System.currentTimeMillis())
							.atZone(java.time.ZoneId.systemDefault())
							.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"));
					statStore.setStatTrackingDate(key.getValue(),formattedDate);
				}
				loggedIn = true;
			}
			if (firstLogin) {
				panel.rebuild();
				firstLogin = false;
			}
		}

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
		if (!config.performanceMode())
		{
			goldStatTracker.onWidgetLoaded(event);
		}
	}

	@Subscribe
	public void onWidgetClosed(WidgetClosed event)
	{
		if (!config.performanceMode())
		{
			goldStatTracker.onWidgetClosed(event);
		}
	}

	@Subscribe
	public void onGameTick(GameTick event) {
		if (!config.performanceMode()){
			movementStatTracker.onGameTick(event);
			goldStatTracker.onGameTick(event);
			skillingStatTracker.onGameTick(event);
			itemStatTracker.onGameTick(event);
			foodStatTracker.onGameTick(event);
			npcStatTracker.onGameTick(event);
		}
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event)
	{
		if ("UltimateStatTracker".equals(event.getGroup()) && panel != null)
		{
			panel.rebuild();
		}
	}

	@Provides
	UltimateStatTrackerConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(UltimateStatTrackerConfig.class);
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
		combatStatTracker.onHitsplatApplied(event);
	}
}

package com.ultimatestattracker;

import com.google.inject.Provides;
import javax.inject.Inject;

import com.ultimatestattracker.stattrackers.GoldStatTracker;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.*;
import net.runelite.client.RuneLite;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.config.RuneScapeProfileType;
import net.runelite.client.events.ClientShutdown;
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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.SwingUtilities;

import static com.ultimatestattracker.StatKeys.*;
import com.ultimatestattracker.stattrackers.*;
import net.runelite.client.util.ImageUtil;
import net.runelite.client.util.Text;
import net.runelite.http.api.RuneLiteAPI;

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

	public final String version = "1.1.4";
	private final String VERSION_KEY = "ustp.version";

	private String lastPlayerName;
	private RuneScapeProfileType lastProfileType = RuneScapeProfileType.STANDARD;

	public boolean isPerformanceMode()
	{
		return config.performanceMode();
	}

	public boolean isHideZeroStatsEnabled()
	{
		return config.hideZeroStats();
	}

	public boolean isDynamicUpdatesEnabled()
	{
		return config.enableDynamicUpdates();
	}

	public int getDynamicUpdateRate()
	{
		return Math.max(1, config.dynamicUpdateRate());
	}

	private int dynamicUpdateTickCounter = 0;



	@Override
	protected void startUp() throws Exception
	{
		statStore = new StatStore(configManager,this, new MilestoneManager(config,client));

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

	private void rememberPlayerProfile()
	{
		Player localPlayer = client.getLocalPlayer();

		if (localPlayer != null && localPlayer.getName() != null)
		{
			lastPlayerName = localPlayer.getName();
			lastProfileType = RuneScapeProfileType.getCurrent(client);
		}
	}

	private void exportStats()
	{
		if (!config.exportOnLogoutOrExit())
		{
			return;
		}

		if(statStore == null || lastPlayerName == null)
		{
			log.error("Unable to export stats, statStore or lastPlayerName was null.");
			return;
		}

		Map<String, Integer> stats = new LinkedHashMap<>();
		Map<String, String> trackingDates = new LinkedHashMap<>();

		for (StatKey key : ALL_KEYS)
		{
			stats.put(key.getValue(), statStore.getStat(key.getValue()));
			trackingDates.put(key.getValue(), statStore.getStatTrackingDate(key.getValue()));
		}

		Map<String, Object> export = new LinkedHashMap<>();
		export.put("playerName", lastPlayerName);
		export.put("profileType", lastProfileType.name());
		export.put("exportedAtUtc", java.time.Instant.now().toString());
		export.put("pluginVersion", version);
		export.put("stats", stats);
		export.put("trackingSince", trackingDates);

		String playerDirectoryName = lastPlayerName;

		if (lastProfileType != RuneScapeProfileType.STANDARD)
		{
			playerDirectoryName += "-" + Text.titleCase(lastProfileType);
		}

		Path exportDirectory = RuneLite.RUNELITE_DIR.toPath()
				.resolve("ultimate-stat-tracker")
				.resolve(playerDirectoryName);
		Path exportFile = exportDirectory.resolve("stats.json");

		try
		{
			Files.createDirectories(exportDirectory);
			Files.writeString(exportFile, RuneLiteAPI.GSON.toJson(export));
			log.info("Exported stats to {}", exportFile);
		}
		catch (IOException ex)
		{
			log.error("Unable to export stats to {}", exportFile, ex);
		}
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged event)
	{
		if (event.getGameState() == GameState.LOGGED_IN)
		{
			rememberPlayerProfile();

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
				String lastVersion = configManager.getConfiguration("ultimatestattracker", VERSION_KEY);
				//show release notes if this is a new version
				if (!version.equals(lastVersion))
				{
					String[] lines = ReleaseNotes.getReleaseNotes().split("\\n");
					for (String line : lines)
					{
						client.addChatMessage(
								ChatMessageType.GAMEMESSAGE,
								"",
								"<col=ff0000>" + line + "</col>",
								null
						);
					}
					configManager.setConfiguration("ultimatestattracker", VERSION_KEY, version);
				}
				panel.rebuild();
				firstLogin = false;
			}
		}
		else if (loggedIn && (event.getGameState() == GameState.LOGIN_SCREEN || event.getGameState() == GameState.LOGIN_SCREEN_AUTHENTICATOR))
		{
			exportStats();
			loggedIn = false;
		}

	}

	@Subscribe
	public void onClientShutdown(ClientShutdown event)
	{
		if (loggedIn)
		{
			exportStats();
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
		movementStatTracker.onMenuOptionClicked(event);

	}

	@Subscribe
	public void onWidgetLoaded(WidgetLoaded event)
	{
		if (!config.performanceMode())
		{
			goldStatTracker.onWidgetLoaded(event);
			movementStatTracker.onWidgetLoaded(event);
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
		if (loggedIn)
		{
			rememberPlayerProfile();
		}

		if (!config.performanceMode()){
			movementStatTracker.onGameTick(event);
			goldStatTracker.onGameTick(event);
			skillingStatTracker.onGameTick(event);
			itemStatTracker.onGameTick(event);
			foodStatTracker.onGameTick(event);
			npcStatTracker.onGameTick(event);
		}

		if (isDynamicUpdatesEnabled() && panel != null)
		{
			dynamicUpdateTickCounter++;
			if (dynamicUpdateTickCounter >= getDynamicUpdateRate())
			{
				dynamicUpdateTickCounter = 0;
				SwingUtilities.invokeLater(panel::rebuild);
			}
		}
		else
		{
			dynamicUpdateTickCounter = 0;
		}
	}

	@Subscribe
	public void onAnimationChanged(AnimationChanged event)
	{
		if (!config.performanceMode()) {
			movementStatTracker.onAnimationChanged(event);
		}
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event)
	{
		if ("UltimateStatTracker".equals(event.getGroup()) && panel != null)
		{
			dynamicUpdateTickCounter = 0;
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
		combatStatTracker.onChatMessage(event);
		movementStatTracker.onChatMessage(event);
	}

	@Subscribe
	public void onHitsplatApplied(HitsplatApplied event)
	{
		combatStatTracker.onHitsplatApplied(event);
	}
}
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
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.input.MouseAdapter;
import net.runelite.client.input.MouseListener;
import net.runelite.client.input.MouseManager;
import net.runelite.client.ui.overlay.OverlayManager;
import java.awt.*;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Objects;
import net.runelite.api.widgets.WidgetInfo;

import net.runelite.api.ChatMessageType;

import static com.ultimatestattracker.StatKeys.*;

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

	private int lastMagicXp = -1;
	private boolean pendingSpellPress = false;

	private int pendingSpellTickCounter = 0;
	private static final int SPELL_PRESS_TIMEOUT_TICKS = 17; // ~10 seconds

	private GoldStatTracker goldStatTracker;

	@Override
	protected void startUp() throws Exception
	{
		statStore = new StatStore(configManager);
		overlay.setStatStore(statStore);
		overlayManager.add(overlay);
		log.debug("Example started!");
		mouseManager.registerMouseListener(mouseListener);

		goldStatTracker = new GoldStatTracker(statStore, client);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
		log.debug("Example stopped!");
		mouseManager.unregisterMouseListener(mouseListener);
	}

	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked event)
	{
		log.debug(event.getMenuTarget());
		log.debug(event.getMenuOption());
		if (Objects.equals(event.getMenuOption(), "Examine"))
		{
			statStore.incrementStat(EXAMINE);
			log.debug("Item examine clicked: {}", event.getMenuTarget());
		}
		else if (Objects.equals(event.getMenuOption(), "Drop"))
		{
			statStore.incrementStat(ITEMS_DROPPED);
			log.debug("Item drop clicked: {}", event.getMenuTarget());
		}

		//-> is to ignore the second cast when an offensive spell is used on a target.  we just count the first spell click.
		else if(event.getMenuOption().contains("Cast") && !event.getMenuOption().contains("->")){
			pendingSpellPress = true;
			pendingSpellTickCounter = 0;
			log.debug("Spell cast clicked: {}", event.getMenuTarget());
		}
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

		Player local = client.getLocalPlayer();
		NPC target = local.getInteracting() instanceof NPC ? (NPC) local.getInteracting() : null;
		boolean playerInCombat = target != null;
		int currentMagicXp = client.getSkillExperience(Skill.MAGIC);
		if (lastMagicXp == -1){
			lastMagicXp  = currentMagicXp;
		}
		if (currentMagicXp != lastMagicXp) {
			if (pendingSpellPress || playerInCombat ) {
				statStore.incrementStat(SPELLS_CAST);
				log.debug("Gained magic xp");
				lastMagicXp = currentMagicXp;
				pendingSpellPress = false;
			}
		}

		if (pendingSpellTickCounter >= SPELL_PRESS_TIMEOUT_TICKS && pendingSpellPress) {
			MenuEntry[] entries = client.getMenuEntries();
			boolean stillCasting = false;

			if (entries != null && entries.length > 0) {
				MenuEntry lastEntry = entries[entries.length - 1];
				if (lastEntry.getOption() != null && lastEntry.getOption().contains("Cast")) {
					log.debug("still casting after 10 seconds");
					stillCasting = true;
				}
			}

			if (stillCasting) {
				pendingSpellTickCounter = 0;
				log.debug("Pending spell press reset as we are still casting");
			}

			else{
				pendingSpellPress = false;
				log.debug("Pending spell press timed out, no longer waiting for magic xp gain");
			}
		}

		if(pendingSpellPress){
			pendingSpellTickCounter++;
		}

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

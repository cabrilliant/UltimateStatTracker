package com.ultimatestattracker;

import com.google.inject.Provides;
import javax.inject.Inject;
import javax.swing.*;

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

	private boolean shopOpen = false;
	private int lastShopGold = 0;

	private int lastMagicXp = 0;
	private boolean pendingSpellPress = false;

	@Override
	protected void startUp() throws Exception
	{
		statStore = new StatStore(configManager);
		overlay.setStatStore(statStore);
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

		//todo , this will still fire even if the player doesnt have the runes.
		else if(event.getMenuOption().contains("Cast")){
			pendingSpellPress = true;
			log.debug("Spell cast clicked: {}", event.getMenuTarget());
		}
	}

	@Subscribe
	public void onWidgetLoaded(WidgetLoaded event)
	{
		log.debug(String.valueOf(event.getGroupId()));
		//shop spent at gp tracking
		if (event.getGroupId() == WidgetInfo.SHOP_INVENTORY_ITEMS_CONTAINER.getGroupId())
		{
			shopOpen = true;
			lastShopGold = client.getItemContainer(InventoryID.INVENTORY).count(ItemID.COINS_995);
			log.debug("Shop opened, starting GP tracking. Current gold: {}", lastShopGold);
		}
	}

	@Subscribe
	public void onWidgetClosed(WidgetClosed event)
	{
		if (event.getGroupId() == WidgetInfo.SHOP_INVENTORY_ITEMS_CONTAINER.getGroupId())
		{
			shopOpen = false;
			lastShopGold = -1;
			log.debug("Shop closed, stopping GP tracking.");
		}
	}

	@Subscribe
	public void onGameTick(GameTick event) {
		if (shopOpen) {
			ItemContainer inv = client.getItemContainer(InventoryID.INVENTORY);
			if (inv == null) {
				log.debug("Inventory is null, cannot track GP");
				return;
			}

			int currentGold = inv.count(ItemID.COINS_995);
			if (lastShopGold == -1) {
				lastShopGold = currentGold;
				return;
			}

			if (currentGold < lastShopGold) {
				int spent = lastShopGold - currentGold;
				statStore.incrementStatBy(SHOP_GP_SPENT, spent);
				log.debug("Spent {} gp, total: {}", spent, statStore.getStat(SHOP_GP_SPENT));
			} else if (currentGold > lastShopGold) {
				int gained = currentGold - lastShopGold;
				statStore.incrementStatBy(SHOP_GP_GAINED, gained);
				log.debug("Gained {} gp, total: {}", gained, statStore.getStat(SHOP_GP_GAINED));
			}

			lastShopGold = currentGold;
		}

		Player local = client.getLocalPlayer();
		NPC target = local.getInteracting() instanceof NPC ? (NPC) local.getInteracting() : null;
		boolean playerInCombat = target != null;
		int currentMagicXp = client.getSkillExperience(Skill.MAGIC);
		if (currentMagicXp != lastMagicXp) {
			if (pendingSpellPress || playerInCombat ) {
				statStore.incrementStat(SPELLS_CAST);
				log.debug("Gained magic xp");
				lastMagicXp = currentMagicXp;
				pendingSpellPress = false;
			}
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

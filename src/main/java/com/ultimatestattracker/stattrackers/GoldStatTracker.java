package com.ultimatestattracker.stattrackers;


import com.ultimatestattracker.StatStore;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.InventoryID;
import net.runelite.api.ItemContainer;
import net.runelite.api.ItemID;
import net.runelite.api.events.*;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.api.widgets.InterfaceID;

import static com.ultimatestattracker.StatKeys.SHOP_GP_GAINED;
import static com.ultimatestattracker.StatKeys.SHOP_GP_SPENT;

/**
 *  Handles All stat tracking logic for gold related stats
 */
@Slf4j
public class GoldStatTracker implements StatTracker {
    private StatStore statStore;
    private Client client;
    private boolean shopOpen = false;
    private int lastShopGold = -1;

    public GoldStatTracker(StatStore statStore, Client client)
    {
        this.statStore = statStore;
        this.client = client;
    }

    @Override
    public void onMenuOptionClicked(MenuOptionClicked event) {

    }

    @Override
    public void onWidgetLoaded(WidgetLoaded event) {
        log.debug(String.valueOf(event.getGroupId()));
        //shop spent at gp tracking
        if (event.getGroupId() == InterfaceID.SHOP_INVENTORY)
        {
            shopOpen = true;
            lastShopGold = client.getItemContainer(InventoryID.INVENTORY).count(ItemID.COINS_995);
            log.debug("Shop opened, starting GP tracking. Current gold: {}", lastShopGold);
        }
    }

    @Override
    public void onWidgetClosed(WidgetClosed event) {
        if (event.getGroupId() == InterfaceID.SHOP_INVENTORY)
        {
            shopOpen = false;
            lastShopGold = -1;
            log.debug("Shop closed, stopping GP tracking.");
        }
    }

    @Override
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
}


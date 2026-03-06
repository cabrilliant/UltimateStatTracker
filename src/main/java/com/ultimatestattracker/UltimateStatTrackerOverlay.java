package com.ultimatestattracker;


import com.google.inject.Inject;
import net.runelite.api.Client;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import static com.ultimatestattracker.StatKeys.*;
import java.awt.*;

public class UltimateStatTrackerOverlay extends Overlay
{
    private final Client client;

    private boolean visible = false;

    private Rectangle closeButtonBounds = null;


    // Overlay dimensions
    private final int panelWidth = 300;
    private final int panelHeight = 200;

    private StatStore statStore;

    public void setStatStore(StatStore store)
    {
        this.statStore = store;
    }

    @Inject
    public UltimateStatTrackerOverlay(Client client)
    {
        this.client = client;
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_WIDGETS);
    }

    public void open()
    {
        visible = true;
    }

    public void close()
    {
        visible = false;
    }

    public void toggle()
    {
        visible = !visible;
    }

    public boolean isInCloseButtonBounds(Point p) {
        return closeButtonBounds != null && closeButtonBounds.contains(p);
    }

    public boolean isInBounds(Point p) {
        if (!visible) return false;
        int drawX = client.getViewportXOffset() + client.getViewportWidth() / 2 - panelWidth / 2;
        int drawY = client.getViewportYOffset() + 40;
        return new Rectangle(drawX, drawY, panelWidth, panelHeight).contains(p);
    }

    public boolean isVisible() {
        return visible;
    }


    @Override
    public Dimension render(Graphics2D g)
    {
        if (!visible)
        {
            return null;
        }

        int x = client.getViewportXOffset();
        int y = client.getViewportYOffset();
        int w = client.getViewportWidth();

        int panelWidth = 300;
        int panelHeight = 200;

        int drawX = x + (w / 2) - (panelWidth / 2);
        int drawY = y + 40;

        g.setColor(new Color(40, 35, 30, 230));
        g.fillRoundRect(drawX, drawY, panelWidth, panelHeight, 12, 12);

        g.setColor(Color.ORANGE);
        g.drawString("Ultimate Stat Tracker", drawX + 12, drawY + 20);

        // Close button (top-right)
        int btnSize = 16;
        int btnX = drawX + panelWidth - btnSize - 8;
        int btnY = drawY + 8;

        g.setColor(Color.RED);
        g.fillOval(btnX, btnY, btnSize, btnSize);

        g.setColor(Color.WHITE);
        g.setStroke(new BasicStroke(2));
        g.drawLine(btnX + 4, btnY + 4, btnX + btnSize - 4, btnY + btnSize - 4);
        g.drawLine(btnX + btnSize - 4, btnY + 4, btnX + 4, btnY + btnSize - 4);

        // Save bounds for click detection
        closeButtonBounds = new Rectangle(btnX, btnY, btnSize, btnSize);

        if (statStore != null)
        {
            int itemsExamined = statStore.getStat(EXAMINE);
            g.setColor(Color.WHITE);
            g.drawString("Things Examined: " + itemsExamined, drawX + 12, drawY + 45);

            int goldSpentAtShops = statStore.getStat(SHOP_GP_SPENT);
            g.drawString("SHOP GP SPENT: " + goldSpentAtShops, drawX + 12, drawY + 60);

            int goldGainedFromShops = statStore.getStat(SHOP_GP_GAINED);
            g.drawString("SHOP GP GAINED: " + goldGainedFromShops, drawX + 12, drawY + 75);

            int itemsDropped = statStore.getStat(ITEMS_DROPPED);
            g.drawString("Items Dropped: " + itemsDropped, drawX + 12, drawY + 90);

            int spellsCast = statStore.getStat(SPELLS_CAST);
            g.drawString("Spells Cast: " + spellsCast, drawX + 12, drawY + 105);

            int tilesWalked = statStore.getStat(TILES_WALKED);
            g.drawString("Tiles Walked: " + tilesWalked, drawX + 12, drawY + 120);

            int tilesRan = statStore.getStat(TILES_RAN);
            g.drawString("Tiles Ran: " + tilesRan, drawX + 12, drawY + 135);

            int fishCaught = statStore.getStat(FISH_CAUGHT);
            g.drawString("Fish Caught: " + fishCaught, drawX + 12, drawY + 150);

            int rocksMined = statStore.getStat(ROCKS_MINED);
            g.drawString("Rocks Mined: " + rocksMined, drawX + 12, drawY + 165);

            int logsChopped = statStore.getStat(LOGS_CHOPPED);
            g.drawString("Logs Chopped: " + logsChopped, drawX + 12, drawY + 180);
        }

        return null;
    }
}

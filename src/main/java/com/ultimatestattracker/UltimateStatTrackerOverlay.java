package com.ultimatestattracker;


import com.google.inject.Inject;
import net.runelite.api.Client;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

import java.awt.*;

public class UltimateStatTrackerOverlay extends Overlay
{
    private final Client client;

    private boolean visible = false;

    private Rectangle closeButtonBounds = null;


    // Overlay dimensions
    private final int panelWidth = 300;
    private final int panelHeight = 200;

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


        return null;
    }
}

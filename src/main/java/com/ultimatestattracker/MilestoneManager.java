package com.ultimatestattracker;

import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;

import java.awt.*;
import java.util.Arrays;

public class MilestoneManager {

    private final Client client;
    private final UltimateStatTrackerConfig config;
    public boolean Enabled = true;

    public int[] quantityMilestones = {
            100,500,
            1000, 5000,
            10000, 25000, 50000,
            100000, 500000,
            1000000, 5000000,
            10000000, 25000000, 50000000,
            100000000, 250000000,500000000,
            1000000000};

    public MilestoneManager(UltimateStatTrackerConfig config, Client client) {
        Enabled = config.milestoneMessages();
        this.config = config;
        this.client = client;
    }

    public void SendMilestoneMessage(int quantity, StatKey stat) {
        int milestone = 0;

        for (int i = 0; i <= quantityMilestones.length-1; i++)
        {
            if (quantity >= quantityMilestones[i])
            {
                milestone = quantityMilestones[i];
            }
            else{
                //since our list is in order, once we are lower than the milestone we can break out of the loop
                break;
            }
        }


        Color color = config.milestoneColor();

        String hex = String.format("%02x%02x%02x",
                color.getRed(),
                color.getGreen(),
                color.getBlue());

        if (Enabled) {
            String message = String.format(
                    "Congratulations! You've reached a <col=%s>%s</col> milestone of <col=%s>%d</col>!",
                    hex,
                    stat.displayString,
                    hex,
                    milestone
            );
            client.addChatMessage(ChatMessageType.GAMEMESSAGE, "Milestone Tracker", message, null);
        }
    }

    public boolean MilestoneReached(int quantity, int previous) {
        //check if theres any milestone that the previous did not fulfil but the current quantity does
        return Arrays.stream(quantityMilestones).anyMatch(milestone -> previous < milestone && quantity >= milestone);
    }
}

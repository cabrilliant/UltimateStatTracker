package com.ultimatestattracker;

import lombok.extern.slf4j.Slf4j;
import net.runelite.client.config.ConfigManager;

@Slf4j
public class StatStore {
    private static final String CONFIG_GROUP = "ultimatestattracker";
    private ConfigManager cfg;

    public StatStore(ConfigManager cfg){
        this.cfg = cfg;
    }

    public void storeStat(String key, int value){
        log.debug("Previous stat value for {}: {}", key, getStat(key));
        cfg.setRSProfileConfiguration(CONFIG_GROUP, key, value);
        log.debug("new stat value for {}: {}", key, getStat(key));
    }

    public int getStat(String key)
    {
        Integer value = cfg.getRSProfileConfiguration(CONFIG_GROUP, key, Integer.class);
        return value == null ? 0 : value;
    }

    public void incrementStat(String key)
    {
        log.debug("increment called");
        incrementStatBy(key,1);
    }

    public void incrementStatBy(String key, int value)
    {
        log.debug("increment by called");
        int current=getStat(key);
        if(current>Integer.MAX_VALUE-value){ //overflow protection
            storeStat(key,Integer.MAX_VALUE);
            return;
        }
        storeStat(key,current+value);
    }

    public void setStat(String key, int value){
        storeStat(key, value);
    }
}
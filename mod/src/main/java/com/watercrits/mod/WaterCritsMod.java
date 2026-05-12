package com.watercrits.mod;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class WaterCritsMod implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("WaterCrits");
    private static WaterCritsConfig config;

    @Override
    public void onInitialize() {
        config = WaterCritsConfig.load();
        LOGGER.info("WaterCrits initialized.");
    }

    public static WaterCritsConfig getConfig() {
        return config;
    }
}

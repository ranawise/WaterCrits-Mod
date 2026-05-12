package com.watercrits.mod;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.*;


public final class WaterCritsConfig {

    public static final Logger LOGGER = LoggerFactory.getLogger("WaterCrits");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH =
            FabricLoader.getInstance().getConfigDir().resolve("watercrits.json");

    public boolean blockNormalAttacks = true;
    public boolean blockSweepAttacks  = true;
    public double  minCooldownForCrit = 0.9;
    public String  blockedMessage     = "Only critical hits are allowed while in water!";

    public static WaterCritsConfig load() {
        if (!Files.exists(CONFIG_PATH)) {
            WaterCritsConfig defaults = new WaterCritsConfig();
            defaults.save();
            return defaults;
        }
        try (Reader r = Files.newBufferedReader(CONFIG_PATH)) {
            return GSON.fromJson(r, WaterCritsConfig.class);
        } catch (IOException e) {
            LOGGER.error("[WaterCrits] Failed to read config — using defaults.", e);
            return new WaterCritsConfig();
        }
    }

    public void save() {
        try (Writer w = Files.newBufferedWriter(CONFIG_PATH)) {
            GSON.toJson(this, w);
        } catch (IOException e) {
            LOGGER.error("[WaterCrits] Failed to write config.", e);
        }
    }
}

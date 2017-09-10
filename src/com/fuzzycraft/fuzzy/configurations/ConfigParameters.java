package com.fuzzycraft.fuzzy.configurations;

import com.fuzzycraft.fuzzy.utilities.*;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import com.fuzzycraft.fuzzy.constants.Defaults;
import com.fuzzycraft.fuzzy.constants.Paths;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

/**
 * @author Allen Flickinger (allen.flickinger@gmail.com)
 */

public class ConfigParameters {

    private ConfigAccessor configAccessor;
    private FileConfiguration config;
    private JavaPlugin plugin;
    private World world;
    private String filename;

    public ConfigParameters(JavaPlugin plugin, World world, String filename) {
        this.plugin = plugin;

        if (!this.plugin.isEnabled()) {
            throw new IllegalArgumentException("Plugin must be initialized!");
        }

        this.world = world;
        this.filename = filename;
        this.configAccessor = new ConfigAccessor(this.plugin, filename);
        this.config = configAccessor.getConfig();
    }

    public void setDefaults() {
        this.config.set(Paths.ACTIVE, Defaults.ACTIVE);
        Vector vector = new Vector(Defaults.X, Defaults.Y, Defaults.Z);
        this.config.set(Paths.LOCATION, new SerializableVector(vector).serialize());
        this.config.set(Paths.AMOUNT, Defaults.AMOUNT);
        this.config.set(Paths.TIME, Defaults.TIME);
        this.config.set(Paths.MSG, Defaults.MSG);
        this.config.set(Paths.RESPAWN_CRYSTALS, Defaults.RESPAWN_CRYSTALS);
        this.config.set(Paths.RESPAWN_OBSIDIAN, Defaults.RESPAWN_OBSIDIAN);
        this.config.set(Paths.CREATE_PORTAL, Defaults.CREATE_PORTAL);
        this.config.set(Paths.CREATE_EGG, Defaults.CREATE_EGG);
        this.configAccessor.saveConfig();
    }

    public World getWorld() {
        return this.world;
    }

    public boolean getActive() {
        return this.config.getBoolean(Paths.ACTIVE);
    }

    public Location getLocation() {
        YamlVector yv = new YamlVector(this.config, Paths.LOCATION);
        SerializableVector sv = new SerializableVector(yv.getVectorMap());
        Vector vector = sv.getVector();
        return new Location(this.world, vector.getX(), vector.getY(), vector.getZ());
    }

    public int getAmount() {
        return this.config.getInt(Paths.AMOUNT);
    }

    public int getTime() {
        return this.config.getInt(Paths.TIME);
    }

    public String getMsg() {
        return this.config.getString(Paths.MSG);
    }

    public boolean getRespawnCrystals() {
        return this.config.getBoolean(Paths.RESPAWN_CRYSTALS);
    }

    public boolean getRespawnObsidian() {
        return this.config.getBoolean(Paths.RESPAWN_OBSIDIAN);
    }

    public boolean getCreatePortal() {
        return this.config.getBoolean(Paths.CREATE_PORTAL);
    }

    public boolean getCreateEgg() {
        return this.config.getBoolean(Paths.CREATE_EGG);
    }

    public ConfigAccessor getConfigAccessor() {
        return this.configAccessor;
    }
}
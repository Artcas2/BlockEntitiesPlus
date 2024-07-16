package fr.artcas2.BlockEntitiesPlus.Utils;

import fr.artcas2.BlockEntitiesPlus.Constants.Defaults;
import fr.artcas2.BlockEntitiesPlus.Constants.Keys;
import fr.artcas2.BlockEntitiesPlus.BlockEntitiesPlus;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.Map;

public class Settings {
    private BlockEntitiesPlus plugin;
    private FileConfiguration config;

    public Settings (BlockEntitiesPlus plugin) {
        this.plugin = plugin;
        this.setupConfig();
    }

    public void setupConfig () {
        this.plugin.saveDefaultConfig();
        this.config = this.plugin.getConfig();
        this.setupDefaults();
    }

    private void setupDefaults () {
        this.config.addDefault(Keys.DEBUG, Defaults.DEBUG);
        this.config.addDefault(Keys.BLOCKS, Defaults.BLOCKS);
    }

    public void reloadConfig () {
        this.plugin.reloadConfig();
        this.config = this.plugin.getConfig();
    }

    public void saveConfig () {
        this.plugin.saveConfig();
    }

    public boolean isDebug () {
        return this.config.getBoolean(Keys.DEBUG);
    }

    public List<Map<?, ?>> getMapList (String path) {
        return this.config.getMapList(path);
    }

    public String getString (String path) {
        return this.config.getString(path);
    }

    public void set (String path, Object value) {
        this.config.set(path, value);
        this.saveConfig();
    }
}

package fr.artcas2.BlockEntitiesPlus.Utils;

import fr.artcas2.BlockEntitiesPlus.BlockEntitiesPlus;

public class Logger {
    static Logger singleton = null;
    static BlockEntitiesPlus plugin;

    public static void setPlugin (BlockEntitiesPlus plugin) {
        Logger.plugin = plugin;
    }

    private static void createInstance() {
        if (singleton == null) {
            singleton = new Logger();
        }
    }

    public static void info (String msg) {
        Logger.createInstance();
        if (plugin.settings.isDebug()) {
            plugin.getLogger().info(msg);
        }
    }

    public static void warning (String msg) {
        Logger.createInstance();
        plugin.getLogger().warning(msg);
    }

    public static void importantInfo (String msg) {
        Logger.createInstance();
        plugin.getLogger().info(msg);
    }
}

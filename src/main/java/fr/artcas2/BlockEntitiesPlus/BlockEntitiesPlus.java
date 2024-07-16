package fr.artcas2.BlockEntitiesPlus;

import fr.artcas2.BlockEntitiesPlus.Commands.BlockCommand;
import fr.artcas2.BlockEntitiesPlus.Commands.MainCommand;
import fr.artcas2.BlockEntitiesPlus.Commands.MainCompleter;
import fr.artcas2.BlockEntitiesPlus.Constants.Commands;
import fr.artcas2.BlockEntitiesPlus.Listeners.BlockListener;
import fr.artcas2.BlockEntitiesPlus.Managers.BlockManager;
import fr.artcas2.BlockEntitiesPlus.Utils.Logger;
import fr.artcas2.BlockEntitiesPlus.Utils.Settings;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockEntitiesPlus extends JavaPlugin {
    public Settings settings;

    @Override
    public void onEnable() {
        this.settings = new Settings(this);
        Logger.setPlugin(this);
        Logger.info("Enabled plugin PackManager");

        BlockManager blockManager = new BlockManager(this);
        getServer().getPluginManager().registerEvents(new BlockListener(this, blockManager), this);

        BlockCommand blockCommand = new BlockCommand(this);
        getCommand(Commands.BASE).setExecutor(new MainCommand(this, blockCommand));
        getCommand(Commands.BASE).setTabCompleter(new MainCompleter(blockCommand));
    }
}

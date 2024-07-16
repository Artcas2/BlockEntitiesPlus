package fr.artcas2.BlockEntitiesPlus.Commands;

import fr.artcas2.BlockEntitiesPlus.BlockEntitiesPlus;
import fr.artcas2.BlockEntitiesPlus.Constants.Commands;
import fr.artcas2.BlockEntitiesPlus.Constants.Keys;
import fr.artcas2.BlockEntitiesPlus.Constants.Permissions;
import fr.artcas2.BlockEntitiesPlus.Models.BlockItem;
import fr.artcas2.BlockEntitiesPlus.Utils.Helpers;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

public class MainCommand implements CommandExecutor {
    private final BlockEntitiesPlus plugin;
    private final BlockCommand blockCommand;

    public MainCommand (BlockEntitiesPlus plugin, BlockCommand blockCommand) {
        this.plugin = plugin;
        this.blockCommand = blockCommand;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            return this.help(sender);
        }

        if (args[0].equalsIgnoreCase(Commands.HELP)) {
            return this.help(sender);
        }

        if (args[0].equalsIgnoreCase(Commands.RELOAD)) {
            return this.reload(sender);
        }

        if (args[0].equalsIgnoreCase(Commands.GET)) {
            return this.blockCommand.onCommand(sender, args);
        }

        return false;
    }

    private boolean reload (CommandSender sender) {
        if (sender instanceof Player && !Helpers.hasPermission((Player) sender, Permissions.RELOAD)) {
            Helpers.sendMessage(this.plugin, sender, Keys.MESSAGES_CANT_RELOAD);
            return false;
        }

        this.plugin.settings.reloadConfig();
        this.blockCommand.reload();
        Helpers.sendMessage(this.plugin, sender, Keys.MESSAGES_RELOADED);
        return true;
    }

    private boolean help(CommandSender sender) {
        Helpers.sendFormattedMessage(sender,"&a&o&l[Block&b&o&lEntities] &e&o&lList of Commands");
        Helpers.sendFormattedMessage(sender,"");
        Helpers.sendFormattedMessage(sender,"&6&o/blockentities help &f&o→ Show this help dialog, duh!");
        Helpers.sendFormattedMessage(sender,"&6&o/blockentities reload &f&o→ Reload plugin settings");
        Helpers.sendFormattedMessage(sender,"");
        Helpers.sendFormattedMessage(sender,"&e&o&lHOW TO GET A BLOCK");
        Helpers.sendFormattedMessage(sender,"&6&o/blockentities get [block] <amount>");
        Helpers.sendFormattedMessage(sender,"");
        Helpers.sendFormattedMessage(sender,"&e&o&lAVAILABLE BLOCKS");
        Helpers.sendFormattedMessage(sender,"");

        for (Map.Entry<String, BlockItem> entry : this.blockCommand.blockTypes.entrySet()) {
            String blockID = entry.getKey();
            BlockItem blockItem = entry.getValue();
            Helpers.sendFormattedMessage(sender,"&7&o- &f&o" + blockID + " &3&o→ &r" + blockItem.getDisplayName());
        }

        return true;
    }
}

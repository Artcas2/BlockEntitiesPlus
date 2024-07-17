package fr.artcas2.BlockEntitiesPlus.Listeners;

import fr.artcas2.BlockEntitiesPlus.Managers.BlockManager;
import fr.artcas2.BlockEntitiesPlus.Models.BlockItem;
import fr.artcas2.BlockEntitiesPlus.Utils.Helpers;
import fr.artcas2.BlockEntitiesPlus.BlockEntitiesPlus;
import fr.artcas2.BlockEntitiesPlus.Constants.Keys;
import fr.artcas2.BlockEntitiesPlus.Constants.Permissions;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class BlockListener implements Listener {
    private BlockEntitiesPlus plugin;
    private BlockManager blockManager;

    public BlockListener (BlockEntitiesPlus plugin, BlockManager blockManager) {
        this.plugin = plugin;
        this.blockManager = blockManager;
    }

    @EventHandler
    public void onBlockPlaced (BlockPlaceEvent event) {
        ItemStack item = event.getItemInHand();
        Material material = item.getType();

        if (material.equals(Material.STONE) && item.getItemMeta().hasCustomModelData()) {
            Player player = event.getPlayer();

            if (!Helpers.hasPermission(player, Permissions.PLACE)) {
                Helpers.sendMessage(this.plugin, player, Keys.MESSAGES_CANT_PLACE);
                event.setCancelled(true);
                return;
            }

            Location location = event.getBlockPlaced().getLocation();
            BlockItem blockItem = new BlockItem(item);
            this.blockManager.addBlock(location, blockItem);
        }
    }

    @EventHandler
    public void onWantBlockBroke (PlayerInteractEvent event) {
        if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            Location location = event.getClickedBlock().getLocation();
            Player player = event.getPlayer();

            if (blockManager.checkBlock(location) && !Helpers.hasPermission(player, Permissions.BREAK)) {
                Helpers.sendMessage(this.plugin, player, Keys.MESSAGES_CANT_BREAK);
                event.setCancelled(true);
                return;
            }

            this.blockManager.breakBlock(location, player);
        }
    }
}

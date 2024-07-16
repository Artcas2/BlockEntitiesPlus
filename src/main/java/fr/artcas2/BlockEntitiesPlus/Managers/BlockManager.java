package fr.artcas2.BlockEntitiesPlus.Managers;

import com.google.common.base.Splitter;
import fr.artcas2.BlockEntitiesPlus.BlockEntitiesPlus;
import fr.artcas2.BlockEntitiesPlus.Models.BlockEntity;
import fr.artcas2.BlockEntitiesPlus.Models.BlockItem;
import fr.artcas2.BlockEntitiesPlus.Utils.Helpers;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class BlockManager {
    private final BlockEntitiesPlus plugin;
    File blocksFile;
    private HashMap<String, BlockEntity> blockCollection;

    public BlockManager (BlockEntitiesPlus plugin) {
        blocksFile = new File(plugin.getDataFolder(), "locations.yml");
        blockCollection = new HashMap<>();
        this.plugin = plugin;
        this.preload();
    }

    public void addBlock (Location location, BlockItem blockItem) {
        String blockID = Helpers.locationToString(location);
        BlockEntity blockEntity = new BlockEntity(location, blockItem);
        YamlConfiguration blocksYml = YamlConfiguration.loadConfiguration(blocksFile);

        List<Location> locations = Objects.requireNonNullElse((List<Location>) blocksYml.getList("locations"), new ArrayList<>());

        if (!locations.contains(location)) {
            locations.add(location);
            blocksYml.set("locations", locations);

            try {
                blocksYml.save(blocksFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        blockCollection.put(blockID, blockEntity);
    }

    public void removeBlock (Location location) {
        String blockID = Helpers.locationToString(location);
        YamlConfiguration blocksYml = YamlConfiguration.loadConfiguration(blocksFile);
        List<Location> locations = (List<Location>) blocksYml.getList("locations");

        if (locations != null) {
            locations.remove(location);
            blocksYml.set("locations", locations);

            try {
                blocksYml.save(blocksFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (blockCollection.containsKey(blockID)) {
            BlockEntity blockEntity = blockCollection.get(blockID);
            blockEntity.breakBlock();
            blockCollection.remove(blockID);
        }
    }

    public void breakBlock (Location location, Player player) {
        String blockID = Helpers.locationToString(location);
        if (blockCollection.containsKey(blockID)) {
            BlockEntity blockEntity = blockCollection.get(blockID);
            BlockItem blockItem = blockEntity.getBlockItem();

            this.removeBlock(location);
            player.playSound(location, Sound.BLOCK_STONE_BREAK, 1, 1);

            if (player.getGameMode() == GameMode.SURVIVAL) {
                World world = location.getWorld();
                world.dropItemNaturally(location, blockItem.getItem(1));
            }
        }
    }

    private Location getLocationFromEntity (Location location) {
        Location entityLocation = location.clone();
        entityLocation.setX( location.getX() - 0.5 );
        entityLocation.setY( location.getY() - 0.5 );
        entityLocation.setZ( location.getZ() - 0.5 );

        return entityLocation;
    }

    private void preload () {
        plugin.saveResource("locations.yml", false);
        List<Location> locations = (List<Location>) YamlConfiguration.loadConfiguration(blocksFile).getList("locations");

        if (locations != null) {
            for (Location location : locations) {
                if (location.isWorldLoaded()) {
                    location.getWorld().getChunkAt(location);
                }
            }
        }

        for (World world : this.plugin.getServer().getWorlds()) {
            for (ItemDisplay entity : world.getEntitiesByClass(ItemDisplay.class)) {
                Location location = getLocationFromEntity(entity.getLocation());
                ItemStack itemStack = entity.getItemStack();
                BlockItem blockItem = new BlockItem(itemStack);
                entity.remove();
                this.addBlock(location, blockItem);
            }
        }
    }
}

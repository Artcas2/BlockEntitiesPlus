# Block Entities Plus

**BlockEntitiesPlus** is a fork of **BlockEntities** plugin that uses the new [Display Entities](https://minecraft.fandom.com/wiki/Display) from Minecraft version **1.19.4** to create custom blocks. The plugin's goal is to explore the possibilities that these entities offer.

![Placing Blocks](https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExMTA2OWUwM2Y5MTcyNWVlYzViOWUxMDY3ZDU4MDM3ODQyZDM2MGRhZCZjdD1n/CEU0cdRdsgkDpkU4Cc/giphy.gif)
![Breaking Blocks](https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExOWEyNTdiOTRmNThkNzc3Y2U4N2YyNzVkNjU4MDkwYmNkMTkzNDljZSZjdD1n/G2GYMImNuaPYhDCHiK/giphy-downsized-large.gif)

To function, the plugin requires a texture pack that allows the Display Entity to render the desired block. Inside, the plugin creates a Display Entity with **CustomModelData** and adds a **Barrier** to give it a hitbox.

Currently, the plugin has basic functions to place and break blocks. In the future, the ability to orient blocks, use specific tools to remove them, and allow them to receive/emit _redstone_ could be added.

Likewise, the possibility of assigning commands when interacting with blocks is being considered for a future update.

### How to Install

To get the plugin to work correctly, the Minecraft server needs to be version **1.19.4+** and run using **Java 17+**. Additionally, a compatible texture pack must be configured, which can be found at the following link [Texture Pack](https://github.com/Artcas2/BlockEntitiesPlus/raw/main/assets/texturepack/BlockEntitiesPlusTexturePack.zip). You can also refer to the Wiki for information on how to create a texture pack compatible with the plugin.

Installing the plugin is simple, just place the plugin's JAR file in the server's plugins folder and restart the server. The configuration file will be automatically generated when the server starts.

### WIKI

For additional information on the plugin, please refer to the following entries in the Wiki:

- [Block Configuration](https://github.com/Artcas2/BlockEntitiesPlus/wiki/Block-Configuration)
- [How to create a texture pack](https://github.com/Artcas2/BlockEntitiesPlus/wiki/How-to-create-a-texture-pack)
- [Commands and Permissions](https://github.com/Artcas2/BlockEntitiesPlus/wiki/Commands-and-Permissions)

These entries provide detailed explanations on how to configure and customize blocks, create a texture pack, and use the necessary commands and permissions for plugin usage.

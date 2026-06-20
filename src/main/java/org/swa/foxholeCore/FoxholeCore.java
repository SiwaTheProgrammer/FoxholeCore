package org.swa.foxholeCore;

import org.bukkit.plugin.java.JavaPlugin;
import org.swa.foxholeCore.GUI.FactionGUI;
import org.swa.foxholeCore.chat.ChatManager;
import org.swa.foxholeCore.command.FactionChatCommand;
import org.swa.foxholeCore.factions.FactionManager;
import org.swa.foxholeCore.spawn.SpawnManager;
import org.swa.foxholeCore.listeners.*;

public final class FoxholeCore extends JavaPlugin {

    private static FoxholeCore instance;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        FactionManager factionManager = new FactionManager();
        FactionGUI factionGUI = new FactionGUI();
        SpawnManager spawnManager = new SpawnManager(this);
        ChatManager chatManager = new ChatManager();

        getServer().getPluginManager().registerEvents(
                new JoinListener(factionManager, factionGUI),
                this
        );

        getServer().getPluginManager().registerEvents(
                new FactionGUIListener(factionManager,spawnManager),
                this
        );

        getServer().getPluginManager().registerEvents(
                new MoveListener(factionManager),
                this
        );

        getServer().getPluginManager().registerEvents(
                new FactionCloseListener(factionManager, factionGUI),
                this
        );

        getServer().getPluginManager().registerEvents(
                new RespawnListener(factionManager, spawnManager),
                this
        );

        getServer().getPluginManager().registerEvents(
                new ChatListener(chatManager,factionManager),this
        );

        getCommand("factionChat").setExecutor(new FactionChatCommand(chatManager));
    }

    @Override
    public void onDisable() {
        getLogger().info("FoxholeCore enabled");
    }

    public static FoxholeCore getInstance() {
        return instance;
    }
}

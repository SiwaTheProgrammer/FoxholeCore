package org.swa.foxholeCore.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.swa.foxholeCore.factions.Faction;
import org.swa.foxholeCore.factions.FactionManager;
import org.swa.foxholeCore.spawn.SpawnManager;

public class RespawnListener implements Listener {

    private final FactionManager factionManager;
    private final SpawnManager spawnManager;

    public RespawnListener(FactionManager factionManager, SpawnManager spawnManager) {
        this.factionManager = factionManager;
        this.spawnManager = spawnManager;
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {

        Faction faction = factionManager.getFaction(event.getPlayer().getUniqueId());

        if (faction == null) {
            return;
        }

        switch (faction) {
            case WARDENS -> event.setRespawnLocation(spawnManager.getWardenSpawn());
            case COLONIALS -> event.setRespawnLocation(spawnManager.getColonialSpawn());
        }
    }
}

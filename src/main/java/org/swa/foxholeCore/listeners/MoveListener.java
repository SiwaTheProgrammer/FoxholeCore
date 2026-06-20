package org.swa.foxholeCore.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.swa.foxholeCore.factions.FactionManager;

public class MoveListener implements Listener {

    private final FactionManager factionManager;

    public MoveListener(FactionManager factionManager) {
        this.factionManager = factionManager;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (factionManager.hasFaction(event.getPlayer().getUniqueId()))
            return;

        event.setCancelled(true);
    }
}

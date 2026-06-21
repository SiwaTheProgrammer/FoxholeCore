package org.swa.foxholeCore.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.swa.foxholeCore.GUI.FactionGUI;
import org.swa.foxholeCore.factions.FactionManager;

public class JoinListener implements Listener {

    private final FactionManager factionManager;
    private final FactionGUI factionGUI;

    public JoinListener(FactionManager factionManager, FactionGUI factionGUI) {
        this.factionManager = factionManager;
        this.factionGUI = factionGUI;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        factionManager.loadPlayer(player.getUniqueId());

        if (!factionManager.hasFaction(player.getUniqueId()))
            factionGUI.open(player);
    }
}

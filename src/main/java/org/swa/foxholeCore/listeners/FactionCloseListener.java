package org.swa.foxholeCore.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.swa.foxholeCore.FoxholeCore;
import org.swa.foxholeCore.GUI.FactionGUI;
import org.swa.foxholeCore.factions.FactionManager;

public class FactionCloseListener implements Listener {

    private final FactionManager factionManager;
    private final FactionGUI factionGUI;

    public FactionCloseListener(FactionManager factionManager, FactionGUI factionGUI) {
        this.factionManager = factionManager;
        this.factionGUI = factionGUI;
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        if (!event.getView().getTitle().equals(factionGUI.TITLE))
            return;

        Player player = (Player) event.getPlayer();

        if (factionManager.hasFaction(player.getUniqueId()))
            return;

        player.getServer().getScheduler().runTask(
                FoxholeCore.getInstance(),
                () -> factionGUI.open(player)
        );
    }
}

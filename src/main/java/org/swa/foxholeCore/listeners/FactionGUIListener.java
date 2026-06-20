package org.swa.foxholeCore.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.swa.foxholeCore.GUI.FactionGUI;
import org.swa.foxholeCore.factions.Faction;
import org.swa.foxholeCore.factions.FactionManager;
import org.swa.foxholeCore.spawn.SpawnManager;

public class FactionGUIListener implements Listener {

    private final FactionManager factionManager;
    public final  SpawnManager spawnManager;

    public FactionGUIListener(FactionManager factionManager, SpawnManager spawnManager) {
        this.factionManager = factionManager;
        this.spawnManager = spawnManager;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {

        if (!event.getView().getTitle().equals(FactionGUI.TITLE))
            return;

        event.setCancelled(true);

        if (event.getCurrentItem() == null)
            return;

        Player player = (Player) event.getWhoClicked();

        switch (event.getSlot()) {
            case 11 -> {
                factionManager.setFaction(player.getUniqueId(), Faction.WARDENS);

                spawnManager.teleportToWardenSpawn(player);

                player.sendMessage("§9Вы присягнули на службу к Варденам");
                player.closeInventory();

                player.sendTitle(
                        "§9Варден",
                        "§fДобро пожаловать на службу",
                        10,
                        70,
                        20
                );
            }

            case 15 -> {
                factionManager.setFaction(player.getUniqueId(), Faction.COLONIALS);

                spawnManager.teleportToColonialSpawn(player);

                player.sendMessage("§9Вы присягнули на службу к Колонистам");
                player.closeInventory();

                player.sendTitle(
                        "§2Колонист",
                        "§fДобро пожаловать на службу",
                        10,
                        70,
                        20
                );
            }
        }
    }
}

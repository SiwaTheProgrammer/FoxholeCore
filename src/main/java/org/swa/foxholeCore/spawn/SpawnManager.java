package org.swa.foxholeCore.spawn;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.swa.foxholeCore.FoxholeCore;

public class SpawnManager {

    private final FoxholeCore plugin;

    public SpawnManager(FoxholeCore plugin) {
        this.plugin = plugin;
    }

    public void teleportToWardenSpawn(Player player) {
        Location loc = getWardenSpawn();
        player.teleport(loc);
        player.setRespawnLocation(loc, true);
    }
    public void teleportToColonialSpawn(Player player) {
        Location loc = getColonialSpawn();
        player.teleport(loc);
        player.setRespawnLocation(loc, true);
    }

    public Location getWardenSpawn() {

        String worldName =
                plugin.getConfig().getString("spawns.wardens.world");

        assert worldName != null;
        World world = Bukkit.getWorld(worldName);

        double x =
                plugin.getConfig().getDouble("spawns.wardens.x");

        double y =
                plugin.getConfig().getDouble("spawns.wardens.y");

        double z =
                plugin.getConfig().getDouble("spawns.wardens.z");

        return new Location(world, x, y, z);
    }

    public Location getColonialSpawn() {

        String worldName =
                plugin.getConfig().getString("spawns.colonials.world");

        World world = Bukkit.getWorld(worldName);

        double x =
                plugin.getConfig().getDouble("spawns.colonials.x");

        double y =
                plugin.getConfig().getDouble("spawns.colonials.y");

        double z =
                plugin.getConfig().getDouble("spawns.colonials.z");

        return new Location(world, x, y, z);
    }
}

package org.swa.foxholeCore.factions;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FactionManager {

    private final Map<UUID, Faction> factions  = new HashMap<>();

    public void setFaction(UUID uuid, Faction faction) {
        factions.put(uuid, faction);
    }

    public Faction getFaction(UUID uuid) {
        return factions.get(uuid);
    }

    public boolean hasFaction(UUID uuid) {
        return factions.containsKey(uuid);
    }
}

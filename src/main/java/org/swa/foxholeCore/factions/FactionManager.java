package org.swa.foxholeCore.factions;

import org.swa.foxholeCore.database.DatabaseManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FactionManager {

    private final Map<UUID, Faction> factions  = new HashMap<>();
    private final DatabaseManager database;

    public FactionManager(DatabaseManager database) {
        this.database = database;
    }

    public void loadPlayer(UUID uuid) {
        try (PreparedStatement statement = database.getConnection().prepareStatement(
                "SELECT faction FROM players WHERE uuid = ?"
        )){
            statement.setString(1, uuid.toString());

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                Faction faction = Faction.valueOf(rs.getString("faction"));

                factions.put(uuid,faction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setFaction(UUID uuid, Faction faction) {

        factions.put(uuid, faction);

        try (PreparedStatement statement = database.getConnection().prepareStatement(
                        """
                             INSERT OR REPLACE INTO players
                             (uuid, faction)
                             VALUES (?, ?)
                             """
        )){
            statement.setString(1, uuid.toString());
            statement.setString(2, faction.name());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Faction getFaction(UUID uuid) {
        return factions.get(uuid);
    }

    public boolean hasFaction(UUID uuid) {
        return factions.containsKey(uuid);
    }
}

package org.swa.foxholeCore.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private Connection connection;

    public void connect() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:sqlite:plugins/FoxholeCore/data.db"
            );

            try (Statement statement = connection.createStatement()){
                statement.executeUpdate("""
               CREATE TABLE IF NOT EXISTS players (uuid TEXT PRIMARY KEY, faction TEXT NOT NULL)
               """);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}

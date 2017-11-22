package com.infoPulse.lessons.DaoTools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSql {

    private static ConnectionSql instance = null;

    private String login = "maestro";
    private String password = "maestro";
    private String databaseURL = "jdbc:mysql://localhost/abonentroom?" + "user=" + login + "&password=" + password;

    private static Connection connection;

    private ConnectionSql() {
        try {
            connection = DriverManager.getConnection(databaseURL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionSql getInstance() {
        if (instance == null) {
            instance = new ConnectionSql();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

}

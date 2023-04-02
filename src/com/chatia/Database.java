package com.chatia;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import static java.sql.DriverManager.getConnection;

public abstract class Database {
    private final String hostname;
    private final String username;
    private final String password;
    private final String database;
    private final HashMap<String, String> map;

    public Database(String hostname, String username, String password, HashMap<String, String> map) {
        this.hostname = "jdbc:mysql://" + hostname + ":" + 3306;
        this.username = username;
        this.password = password;
        this.database = "db";
        this.map = map;
    }

    public String query(String sql) throws ChatIARJException, SQLException {
        Connection connection = getConnection(hostname + '/' + database, username, password);
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        connection.endRequest();
        connection.close();
        return "{}";
    }
}
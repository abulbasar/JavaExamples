package com.example.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    private Connection conn = null;
    private String connectionString;

    public void connect() {
        try {
            // create a connection to the database
            conn = DriverManager.getConnection(this.connectionString);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ConnectionProvider(String connectionString){
        this.connectionString = connectionString;
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        if(this.conn == null){
            this.connect();
        }
        return this.conn;
    }
}

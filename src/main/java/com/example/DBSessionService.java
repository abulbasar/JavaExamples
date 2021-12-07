package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBSessionService {
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

    DBSessionService(String connectionString){
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
        return this.conn;
    }
}

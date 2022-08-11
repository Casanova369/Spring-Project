package com.example.demo.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgreSQL {
    private static final String driver   = "org.postgresql.Driver";
    private static final String url      = "jdbc:postgresql://localhost:5432/database";
    private static final String user     = "postgres";
    private static final String password = "1234";

    public Connection getConnection (){
        System.out.println("Connecting with PostgreSQL");
        Connection connection = null;
        try {
            Class.forName(this.driver);
            connection = DriverManager.getConnection(this.url, this.user, this.password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}

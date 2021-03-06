package com.codecool.loginform;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresSqlJDBC {
    private Connection c = null;

    public Connection getConnection(String url, String user, String password) {
        if (c == null) {
            try {
                c = DriverManager.getConnection(url, user, password);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
            System.out.println("Opened database successfully");
        }
        return c;
    }
}

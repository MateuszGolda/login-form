package com.codecool.loginform.user;

public class User {
    private final int id;
    private final String username;
    private final String password;

    User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

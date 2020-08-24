package com.codecool.loginform.user;

public class Query {
    static final String selectByUsername = "SELECT * FROM users WHERE username = ?;";

    static final String selectBySessionId = "SELECT users.id, username, password " +
            "FROM users " +
            "JOIN sessions ON users.id = sessions.user_id " +
            "WHERE sessions.id = ?;";

}

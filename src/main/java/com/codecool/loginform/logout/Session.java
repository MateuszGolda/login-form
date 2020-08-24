package com.codecool.loginform.logout;

import java.sql.Timestamp;

public class Session {
    private final String id;
    private final int userId;
    private final Timestamp creationTime;
    private final Timestamp expirationTime;

    public Session(String id, int userId, Timestamp creationTime, Timestamp expirationTime) {
        this.id = id;
        this.userId = userId;
        this.creationTime = creationTime;
        this.expirationTime = expirationTime;
    }

    String getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    Timestamp getCreationTime() {
        return creationTime;
    }

    Timestamp getExpirationTime() {
        return expirationTime;
    }
}

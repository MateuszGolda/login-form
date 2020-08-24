package com.codecool.loginform.logout;

import java.sql.*;
import java.util.Optional;

public class SessionDao {
    private final Connection c;

    public SessionDao(Connection connection) {
        c = connection;
    }

    public Optional<Session> getById(String id) {
        Optional<Session> session = Optional.empty();
        try (PreparedStatement stmt = c.prepareStatement("SELECT * FROM sessions WHERE id = ?;")
        ) {
            stmt.setString(1, id);
            ResultSet results = stmt.executeQuery();
            session = createSession(results);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return session;
    }

    private Optional<Session> createSession(ResultSet results) throws SQLException {
        if (!results.next()) {
            return Optional.empty();
        }
        String id = results.getString("id");
        int userId = results.getInt("user_id");
        Timestamp creationTime = results.getTimestamp("creation_time");
        Timestamp expirationTime = results.getTimestamp("expiration_time");
        return Optional.of(new Session(id, userId, creationTime, expirationTime));
    }

    public void insert(Session session) {
        try {
            PreparedStatement stmt = c.prepareStatement(
                    "INSERT INTO sessions (id, user_id, creation_time, expiration_time)" +
                            " VALUES (?, ?, ?, ?);");
            stmt.setString(1, session.getId());
            stmt.setInt(2, session.getUserId());
            stmt.setTimestamp(3, session.getCreationTime());
            stmt.setTimestamp(4, session.getExpirationTime());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Session session) {
        try {
            PreparedStatement stmt = c.prepareStatement("DELETE" +
                    " FROM sessions" +
                    " WHERE id = ?;");
            stmt.setString(1, session.getId());
            stmt.executeUpdate();
            // TODO it might be better to return void here
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

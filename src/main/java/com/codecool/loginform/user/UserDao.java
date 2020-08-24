package com.codecool.loginform.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDao {
    private final Connection c;

    public UserDao(Connection connection) {
        c = connection;
    }

    public Optional<User> getByUsername(String username) {
        User user = null;
        try (PreparedStatement stmt = c.prepareStatement(Query.selectByUsername)
        ) {
            stmt.setString(1, username);
            ResultSet results = stmt.executeQuery();
            if (results.next()) {
                user = createUser(results);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(user);
    }

    Optional<User> getBySessionId(String sessionId) {
        User user = null;
        try (PreparedStatement stmt = c.prepareStatement(Query.selectBySessionId)
        ) {
            stmt.setString(1, sessionId);
            ResultSet results = stmt.executeQuery();
            if (results.next()) {
                user = createUser(results);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(user);
    }

    private User createUser(ResultSet results) throws SQLException {
        int id = results.getInt("id");
        String username = results.getString("username");
        String password = results.getString("password");
        return new User(id, username, password);
    }
}

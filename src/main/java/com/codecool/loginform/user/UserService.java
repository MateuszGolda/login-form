package com.codecool.loginform.user;

import java.util.Optional;

public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Optional<User> getByUsername(String username) {
        return userDao.getByUsername(username);
    }

    Optional<String> getUsernameBySessionId(String sessionId) {
        Optional<User> optionalUser = userDao.getBySessionId(sessionId);
        String username = optionalUser.map(User::getUsername).orElse(null);
        return Optional.ofNullable(username);
    }

    public Optional<User> getUserBySessionId(String sessionId) {
        return userDao.getBySessionId(sessionId);
    }
}

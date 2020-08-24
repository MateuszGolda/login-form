package com.codecool.loginform.logout;

public class SessionService {

    private final SessionDao sessionDao;

    public SessionService(SessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }

    void delete(String sessionId) {
        sessionDao.getById(sessionId).ifPresent(sessionDao::delete);
    }

    public void insert(Session session) {
        sessionDao.insert(session);
    }
}

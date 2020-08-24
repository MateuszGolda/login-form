package com.codecool.loginform.login;

import com.codecool.loginform.Controller;
import com.codecool.loginform.cookie.CookieHelper;
import com.codecool.loginform.logout.Session;
import com.codecool.loginform.logout.SessionService;
import com.codecool.loginform.user.User;
import com.codecool.loginform.user.UserService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.net.HttpCookie;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class LoginController extends Controller implements HttpHandler {
    final UserService userService;
    final SessionService sessionService;
    final LoginView view;

    public LoginController(UserService userService, SessionService sessionService, LoginView view) {
        this.userService = userService;
        this.sessionService = sessionService;
        this.view = view;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();

        if (method.equals("GET")) {
            handleGet(exchange);
        } else if (method.equals("POST")) {
            handlePost(exchange);
        }
    }

    private void handleGet(HttpExchange exchange) throws IOException {
        Optional<HttpCookie> optionalCookie = CookieHelper.getSessionIdCookie(exchange);
        if (optionalCookie.isPresent()) {
            redirectTo(exchange, "/user");
            return;
        }
        view.sendLoginPage(exchange);
    }

    private Optional<User> getUserFromCookie(HttpExchange exchange) {
        User user = null;
        Optional<String> optionalSessionId = CookieHelper.getSessionIdFromCookie(exchange);
        if (optionalSessionId.isPresent()) {
            user = userService.getUserBySessionId(optionalSessionId.get()).orElse(null);
        }
        return Optional.ofNullable(user);
    }

    private void handlePost(HttpExchange exchange) throws IOException {
        Optional<User> optionalUser = getUserFromCookie(exchange);
        if (optionalUser.isEmpty()) {
            Map<String, String> inputs = getInputs(exchange);
            String username = inputs.get("username");
            String password = inputs.get("password");

            optionalUser = getMatchingUser(username, password);
            if (optionalUser.isPresent()) {

                String sessionId = getRandomString();
                Session session = new Session(sessionId, optionalUser.get().getId(), Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now().plusMinutes(5)));
                sessionService.insert(session);
                CookieHelper.saveNewCookie(exchange, sessionId);

                redirectTo(exchange, "/user");
                return;
            }
        }
        redirectTo(exchange, "/login");
    }

    private Optional<User> getMatchingUser(String username, String password) {
        Optional<User> user = userService.getByUsername(username);
        boolean passwordIsMatching = false;
        if (user.isPresent()) {
            passwordIsMatching = BCrypt.checkpw(password, user.get().getPassword());
        }
        return passwordIsMatching ? user : Optional.empty();
    }

    private String getRandomString() {
        final int length = 20;
        StringBuilder sb = new StringBuilder(20);

        for (int i = 0; i < length; i++) {
            sb.append(getRandomChar());
        }
        return sb.toString();
    }

    private char getRandomChar() {
        final int start = 65;
        final int end = 91;
        return (char) ThreadLocalRandom.current().nextInt(start, end + 1);
    }
}

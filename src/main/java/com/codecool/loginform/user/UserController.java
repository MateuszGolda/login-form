package com.codecool.loginform.user;

import com.codecool.loginform.Controller;
import com.codecool.loginform.cookie.CookieHelper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.Optional;

public class UserController extends Controller implements HttpHandler {
    private final UserService service;
    private final UserView view;

    public UserController(UserService service, UserView view) {
        this.service = service;
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
        Optional<String> optionalSessionId = CookieHelper.getSessionIdFromCookie(exchange);
        if (optionalSessionId.isEmpty()) {
            redirectToLoginPage(exchange);
            return;
        }
        Optional<String> optionalUsername = service.getUsernameBySessionId(optionalSessionId.get());
        if (optionalUsername.isPresent()) {
            view.sendLoggedInPage(exchange, optionalUsername.get());
        }
    }

    private void handlePost(HttpExchange exchange) throws IOException {
        redirectToLogout(exchange);
    }

    private void redirectToLoginPage(HttpExchange exchange) throws IOException {
        exchange.getResponseHeaders().set("Location", "/login");
        exchange.sendResponseHeaders(302, 0);
    }

    private void redirectToLogout(HttpExchange exchange) throws IOException {
        exchange.getResponseHeaders().set("Location", "/logout");
        exchange.sendResponseHeaders(302, 0);
    }
}

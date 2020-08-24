package com.codecool.loginform.logout;

import com.codecool.loginform.Controller;
import com.codecool.loginform.cookie.CookieHelper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.Optional;

public class LogoutController extends Controller implements HttpHandler {
    private final SessionService service;

    public LogoutController(SessionService service) {
        this.service = service;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();

        if (method.equals("GET")) {
            handleGet(exchange);
        }
    }

    private void handleGet(HttpExchange exchange) throws IOException {
        Optional<String> optionalSessionId = CookieHelper.getSessionIdFromCookie(exchange);
        optionalSessionId.ifPresent(service::delete);
        CookieHelper.deleteCookie(exchange);
        redirectToLoginPage(exchange);
    }

    private void redirectToLoginPage(HttpExchange exchange) throws IOException {
        exchange.getResponseHeaders().set("Location", "/login");
        exchange.sendResponseHeaders(302, 0);
    }
}

package com.codecool.loginform.login;

import com.codecool.loginform.View;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.net.URL;

public class LoginView extends View {
    public void sendLoginPage(HttpExchange exchange) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL fileURL = classLoader.getResource("static/html/login-page.html");
        if (fileURL == null) {
            send404(exchange);
        } else {
            sendFile(exchange, fileURL);
        }
    }
}

package com.codecool.loginform.staticcontent;

import com.codecool.loginform.View;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.URI;
import java.net.URL;

public class StaticController extends View implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        URI uri = httpExchange.getRequestURI();
        String path = "." + uri.getPath();
        ClassLoader classLoader = getClass().getClassLoader();
        URL fileURL = classLoader.getResource(path);
        if (fileURL == null) {
            send404(httpExchange);
        } else {
            sendFile(httpExchange, fileURL);
        }
    }
}

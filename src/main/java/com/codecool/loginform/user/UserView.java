package com.codecool.loginform.user;

import com.sun.net.httpserver.HttpExchange;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;

public class UserView {
    public void sendLoggedInPage(HttpExchange httpExchange, String username) throws IOException {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/logged-in.twig");
        JtwigModel model = JtwigModel.newModel();
        model.with("username", username);

        String response = template.render(model);
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}

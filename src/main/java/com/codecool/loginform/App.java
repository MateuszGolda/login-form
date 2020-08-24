package com.codecool.loginform;

import com.codecool.loginform.login.LoginController;
import com.codecool.loginform.login.LoginView;
import com.codecool.loginform.logout.*;
import com.codecool.loginform.staticcontent.StaticController;
import com.codecool.loginform.user.*;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.Connection;

public class App {
    public static void main(String[] args) throws IOException {
        Connection connection = new PostgresSqlJDBC()
                .getConnection("jdbc:postgresql://localhost:5432/loginform", "loginform", "loginform");
        // There is pg dump file in the resources directory. Each user password is the same as username.

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        var userService = new UserService(new UserDao(connection));
        var sessionService = new SessionService(new SessionDao(connection));

        server.createContext("/login", new LoginController(userService, sessionService, new LoginView()));
        server.createContext("/user", new UserController(userService, new UserView()));
        server.createContext("/logout", new LogoutController(sessionService));
        server.createContext("/static", new StaticController());

        server.setExecutor(null);
        server.start();
    }
}

package com.codecool.loginform.cookie;

import com.sun.net.httpserver.HttpExchange;

import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CookieHelper {
    private static final String SESSION_COOKIE_NAME = "sessionId";

    public static void saveNewCookie(HttpExchange httpExchange, String sessionId) {
        HttpCookie cookie = new HttpCookie(SESSION_COOKIE_NAME, sessionId);
        httpExchange.getResponseHeaders().add("Set-Cookie", cookie.toString());
    }

    public static void deleteCookie(HttpExchange httpExchange) {
        String cookie = httpExchange.getRequestHeaders().getFirst("Cookie") + ";Max-age=0";
        httpExchange.getResponseHeaders().set("Set-Cookie", cookie);
        System.out.println("deleted cookie");
    }

    public static Optional<HttpCookie> getSessionIdCookie(HttpExchange httpExchange) {
        String cookieStr = httpExchange.getRequestHeaders().getFirst("Cookie");
        List<HttpCookie> cookies = parseCookies(cookieStr);
        return findCookieByName(cookies);
    }

    public static Optional<String> getSessionIdFromCookie(HttpExchange httpExchange) {
        Optional<HttpCookie> cookie = getSessionIdCookie(httpExchange);
        String sessionId = null;
        if (cookie.isPresent()) {
            sessionId = cookie.get().getValue().replace("\"", "");
        }
        return Optional.ofNullable(sessionId);
    }

    private static List<HttpCookie> parseCookies(String cookieString) {
        List<HttpCookie> cookies = new ArrayList<>();
        if (cookieString == null || cookieString.isEmpty()) {
            return cookies;
        }
        for (String cookie : cookieString.split(";")) {
            int indexOfEq = cookie.indexOf('=');
            String cookieName = cookie.substring(0, indexOfEq);
            String cookieValue = cookie.substring(indexOfEq + 1);
            cookies.add(new HttpCookie(cookieName, cookieValue));
        }
        return cookies;
    }

    private static Optional<HttpCookie> findCookieByName(List<HttpCookie> cookies) {
        for (HttpCookie cookie : cookies) {
            if (cookie.getName().equals(SESSION_COOKIE_NAME))
                return Optional.of(cookie);
        }
        return Optional.empty();
    }
}

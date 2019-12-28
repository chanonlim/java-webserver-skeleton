package com.sn.WebServer.Handlers;

import com.sn.WebServer.Utils;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SessionHandler implements HttpHandler {
    private Map<String, Map<String, String>> sessionData;
    private SessionRequestHandler handler;
    public SessionHandler(Map<String, Map<String, String>> sessionD, SessionRequestHandler sessionH) {
        this.sessionData = sessionD;
        this.handler = sessionH;
    }
    public void handle(HttpExchange t) throws IOException {
        boolean sessionFound = false;
        Map<String, String> cookies = Utils.parseCookie(t);
        if (cookies.containsKey("session_id") && sessionData.containsKey(cookies.get("session_id"))) {
            sessionFound = true;
        }
        Map<String, String> session; // to make variable accessible outside of if statement
        String sessionID;
        if (sessionFound) {
            sessionID = cookies.get("session_id");
            session = sessionData.get(sessionID);
        } else {
            sessionID = Utils.genKey(64);
            session = new HashMap<String, String>();
            sessionData.put(sessionID, session);
            t.getResponseHeaders().set("Set-Cookie", "session_id=" + sessionID + "; HttpOnly");
        }
        this.handler.handle(t, session);
    }
}
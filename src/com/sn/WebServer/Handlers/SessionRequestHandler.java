package com.sn.WebServer.Handlers;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.Map;

public interface SessionRequestHandler { // To pass a function into an argument
    public void handle(HttpExchange t, Map<String, String> session) throws IOException;
}
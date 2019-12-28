package com.sn.WebServer.Handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.URI;
import java.util.List;

public class MainPage implements HttpHandler {
    private List<String> valids;
    private HttpHandler handler;
    private HttpHandler nHandler;
    public MainPage(List<String> validLinks, HttpHandler successHandler, HttpHandler failHandler) {
        valids = validLinks;
        handler = successHandler;
        nHandler = failHandler;
    }
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        URI uri = exchange.getRequestURI();
        String path = uri.getPath();
        String[] splitPaths = path.split("/");
        if (valids.contains(path) || valids.contains("/" + splitPaths[splitPaths.length-2] + "/*")) {
            handler.handle(exchange);
        } else {
            nHandler.handle(exchange);
        }
    }
}

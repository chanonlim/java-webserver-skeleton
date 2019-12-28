package com.sn.WebServer.Handlers;

import com.sn.WebServer.Utils;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class StaticFile implements HttpHandler {
    private String file;
    public StaticFile(String fileName) {
        file = fileName;
    }
    public void handle(HttpExchange t) throws IOException {
        byte [] response = Utils.readFile(file);
        t.sendResponseHeaders(200, response.length);
        OutputStream os = t.getResponseBody();
        os.write(response);
        os.close();
    }
}
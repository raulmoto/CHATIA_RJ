package com.chatia;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.HashMap;

public class HTMLDebugHandler implements HttpHandler {

    private final HashMap<String, byte[]> map;
    private final String root;

    public HTMLDebugHandler(String directory) {
        this.root = directory;
        map = new HashMap<>();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String request = exchange.getRequestURI().toString();
        if (request.equals("/")) request = "/index.html";
        byte[] response;
        File file = new File(root + request);
        if (file.exists()) response = Files.readAllBytes(file.toPath());
        else response = "<h1>Error 404. Not found</h1>".getBytes();
        exchange.sendResponseHeaders(200, response.length);
        OutputStream os = exchange.getResponseBody();
        os.write(response);
        os.close();
    }
}

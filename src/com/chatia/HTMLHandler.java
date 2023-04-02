package com.chatia;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Objects;

public class HTMLHandler implements HttpHandler {
    private final String ROOT;
    private final HashMap<String, byte[]> map;

    public HTMLHandler(String directory) {
        this.ROOT = directory;
        map = new HashMap<>();
        build();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String request = exchange.getRequestURI().toString();
        byte[] response;

        if (request.equals("/")) request = "/index.html";

        if (map.get(request) != null) {
            response = map.get(request);
        } else response = "<h1>404 Not found</h1>".getBytes();
        exchange.sendResponseHeaders(200, response.length);
        OutputStream os = exchange.getResponseBody();
        os.write(response);
        os.close();
    }

    protected void build() {
        final String separator = File.separator;
        File src = new File(ROOT);
        File[] files = src.listFiles();
        assert files != null;
        for (File f : files) {
            if (f.isDirectory()) {
                loadDirectory(f.getName() + separator, f);
            } else {
                loadFile(f.getName(), f);
            }
        }
    }

    private void loadDirectory(String path, File directory) {
        for (File f : Objects.requireNonNull(directory.listFiles())) {
            if (f.isDirectory()) {
                loadDirectory(path + f.getName() + File.separator, f);
            } else {
                loadFile(path + f.getName(), f);
            }
        }
    }

    private void loadFile(String path, File f) {
        path = path.replace("\\", "/");
        try {
            byte[] content = Files.readAllBytes(f.toPath());
            map.put('/' + path, content);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println(path);
        }
    }
}
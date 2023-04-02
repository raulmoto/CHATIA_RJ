package com.chatia;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedList;

public class APIHandler implements HttpHandler {
    private final ClientListener listener;
    public APIHandler(ClientListener listener) {
        this.listener = listener;
    }

    @Override
    public void handle(HttpExchange exchange) {

        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                final char[] requestUrio = exchange.getRequestURI().toString().toCharArray();
                final String getRaw = exchange.getRequestURI().getQuery();
                LinkedList<String> HEADER = new LinkedList<>();
                int uri_len = requestUrio.length;
                StringBuilder current = new StringBuilder();
                for (int i = 1; i < uri_len; i++) {
                    if (requestUrio[i] == '?') break;
                    if (requestUrio[i] == '/') {
                        try {
                            if (requestUrio[i + 1] == '/') break;
                        } catch (IndexOutOfBoundsException e) {
                            Main.console.warn("Error in " + getClass().getName() + ". Error type: " + e.getClass().getName() + ". Message: " + e.getMessage());
                        }
                        HEADER.add(current.toString());
                        current = new StringBuilder();
                        continue;
                    }
                    current.append(requestUrio[i]);
                }
                HEADER.add(current.toString());
                if (HEADER.getLast().equals("")) HEADER.removeLast();

                final HashMap<String, String> GET = new HashMap<>();
                if (getRaw != null) {
                    String[] gets = getRaw.split("&");
                    for (String s : gets) {
                        String[] arg = s.split("=");
                        if (arg.length == 2) GET.put(arg[0], arg[1]);
                    }
                }
                String resolved_query = listener.service(GET, HEADER);
                try {
                    respond(exchange, resolved_query);
                } catch (RuntimeException e) {
                    Main.console.warn("Error in " + getClass().getName() + ". Error type: " + e.getClass().getName() + ". Message: " + e.getMessage());
                }
            }
        };
        thread.start();
    }

    private void respond(HttpExchange exchange, String resolved_query){
        byte[] bs = resolved_query.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        try{
            exchange.sendResponseHeaders(200, bs.length);
        } catch (IOException e) {
            Main.console.warn("Exception while sending requiest");
            Main.console.warn("Error in " + getClass().getName() + ". Error type: " + e.getClass().getName() + ". Message: " + e.getMessage());
        }
        OutputStream os = exchange.getResponseBody();
        try{
            os.write(bs);
        }catch (IOException e){
            Main.console.warn("Exception while writing output stream");
            Main.console.warn("Error in " + getClass().getName() + ". Error type: " + e.getClass().getName() + ". Message: " + e.getMessage());
        }
        try{
            os.close();
        }catch (IOException e){
            Main.console.warn("Exception while closing outputstream");
            Main.console.warn("Error in " + getClass().getName() + ". Error type: " + e.getClass().getName() + ". Message: " + e.getMessage());
        }
    }
}
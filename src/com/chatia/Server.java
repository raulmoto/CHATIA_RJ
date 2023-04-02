package com.chatia;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    private final int PORT;
    private final boolean debub;

    public Server(int PORT, boolean debug) {
        this.PORT = PORT;
        this.debub = debug;
    }
    public void deploy(ClientListener listener) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
        Main.console.info("Server has started at port " + PORT);
        server.createContext("/api", new APIHandler(listener));
        if(debub){
            server.createContext("/", new HTMLDebugHandler("./html"));
        }else{
            server.createContext("/", new HTMLHandler("./html"));
        }
        server.setExecutor(null);
        server.start();
    }
}
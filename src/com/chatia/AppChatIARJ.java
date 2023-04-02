package com.chatia;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;

public class AppChatIARJ implements ClientListener {
    private final Server server;
    public AppChatIARJ(String hostname, String username, String password, int port, boolean debug) throws SQLException {
        server = new Server(port, debug);
    }
    public void start() throws IOException {
        server.deploy(this);
    }
    @Override
    public String service(HashMap<String, String> get, LinkedList<String> headers) {
        String s = "GET: " + get + " HEADERS: " + headers;
        Main.console.info(s);
        return "{}";
    }
}

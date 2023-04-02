package com.chatia;

import java.io.IOException;
import java.sql.SQLException;
public class Main {

    static Console console;

    public static void main(String[] args) {
        console = new Console();
        console.print("Welcome to Chat IA RJ API");
        console.print("Hostname:(Enter to use localhost)");
        String hostname = console.scan();
        if (hostname.equals("")) {
            hostname = "localhost";
        }
        console.print("Username: (Enter to use 'root')");
        String username = console.scan();
        if (username.equals("")) {
            username = "root";
        }
        console.print("Password: ");
        String password = console.scan();
        console.print("Port: (Enter to use 8080)");
        String portString = console.scan();
        if (portString.equals("")) {
            portString = "8080";
        }

        console.print("Run as developer mode Yes/No (Enter Yes): ");
        boolean debug = true;
        String debugString = console.scan();
        if (debugString.trim().equalsIgnoreCase("n") || debugString.trim().equalsIgnoreCase("no")) {
            debug = false;
        }
        int port;
        try {
            port = Integer.parseInt(portString);
        } catch (NumberFormatException e) {
            Main.console.warn("Error in " + Main.class.getName() + ". Error type: " + e.getClass().getName() + ". Message: " + e.getMessage());
            return;
        }
        try {
            AppChatIARJ app = new AppChatIARJ(hostname, username, password, port, debug);
            app.start();
        } catch (SQLException | IOException e) {
            Main.console.warn("Error in " + Main.class.getName() + ". Error type: " + e.getClass().getName() + ". Message: " + e.getMessage());
        }
    }
}
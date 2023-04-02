package com.chatia;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Scanner;

public class Console {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

    private Logger logger = LogManager.getLogger(Main.class.toString());
    private final Scanner scanner;

    public Console() {
        scanner = new Scanner(System.in);
    }

    public String scan() {
        return scanner.nextLine();
    }

    public void warn(String text) {
        print(text);
        logger.warn(ANSI_YELLOW + text + ANSI_RESET);
    }

    public void fatal(String text) {
        print(text);
        logger.fatal(ANSI_RED + text + ANSI_RESET);
    }

    public void info(String text) {
        print(text);
        logger.info(ANSI_GREEN + text + ANSI_RESET);
    }

    public void trace(String text) {
        print(ANSI_BLUE + text + ANSI_RESET);
        logger.trace(text);
    }

    public void print(String text) {
        System.out.println(text);
    }
}

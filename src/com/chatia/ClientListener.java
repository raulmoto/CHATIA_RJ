package com.chatia;
import java.util.HashMap;
import java.util.LinkedList;
public interface ClientListener {
    String service(HashMap<String, String> get, LinkedList<String> headers);
}
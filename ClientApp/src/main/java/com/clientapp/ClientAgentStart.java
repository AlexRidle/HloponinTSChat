package com.clientapp;

/**
 * Class will start the console client application
 * @author Paul Hloponin
 */
public class ClientAgentStart {
    public static void main(String[] args) {
        new Client("localhost", 2121);
    }
}

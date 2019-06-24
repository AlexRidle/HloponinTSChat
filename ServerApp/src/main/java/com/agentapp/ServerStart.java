package com.agentapp;

/**
 * Class will start the server for connecting clients and agents, both console and web
 * @author Paul Hloponin
 */
public class ServerStart {
    public static void main(String[] args) {
        new Server(2121);//Port should be located in properties file.
    }
}

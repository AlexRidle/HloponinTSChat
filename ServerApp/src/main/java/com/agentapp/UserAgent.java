package com.agentapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;

/**
 * @author Paul Hloponin
 */

//UserClient and UserAgent is 99% similar. Why not to create User class and extend it?

public class UserAgent {
    private Socket socket;
    private String name;
    private BufferedReader in;
    private BufferedWriter out;

    public UserAgent(Socket socket, String name, BufferedReader in, BufferedWriter out) {
        this.socket = socket;
        this.name = name;
        this.in = in;
        this.out = out;
    }

    public Socket getSocket() {
        return socket;
    }

    public String getName() {
        return name;
    }

    public BufferedReader getIn() {
        return in;
    }

    public BufferedWriter getOut() {
        return out;
    }

}

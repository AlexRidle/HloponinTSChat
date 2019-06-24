package com.agentapp;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Paul Hloponin
 */

//UserClient and UserAgent is 99% similar. Why not to create User class and extend it?

public class UserClient {

    private Socket socket;
    private String name;
    private BufferedReader in;
    private BufferedWriter out;
    private Queue<String> memoryMessage;

    public UserClient(Socket socket, String name, BufferedReader in, BufferedWriter out) {
        this.socket = socket;
        this.name = name;
        this.in = in;
        this.out = out;
        memoryMessage = new LinkedList<>();
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


    public Queue<String> getMemoryMessage() {
        return memoryMessage;
    }


    public void addMessage(String message) {
        memoryMessage.add(message);
    }
}

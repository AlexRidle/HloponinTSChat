package com.agentapp;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

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

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BufferedReader getIn() {
        return in;
    }

    public void setIn(BufferedReader in) {
        this.in = in;
    }

    public BufferedWriter getOut() {
        return out;
    }

    public void setOut(BufferedWriter out) {
        this.out = out;
    }

    public Queue<String> getMemoryMessage() {
        return memoryMessage;
    }

    public void setMemoryMessage(Queue<String> memoryMessage) {
        this.memoryMessage = memoryMessage;
    }

    public void addMessage (String message){
        memoryMessage.add(message);
    }
}

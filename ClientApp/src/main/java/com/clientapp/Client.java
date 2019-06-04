package com.clientapp;

import java.io.*;
import java.net.Socket;

public class Client {

    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private final String host;
    private final int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;

        try {
            socket = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            System.out.println("Connect with server failed");
            e.printStackTrace();
            System.exit(0);
        }

        new Thread(new Send(socket, out)).start();  //Поток отсылки сообщения
        new Thread(new Resive(socket, in)).start(); //Поток принятия сообщения
    }

}

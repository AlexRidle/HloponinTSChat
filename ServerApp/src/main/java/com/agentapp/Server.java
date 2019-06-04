package com.agentapp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

public class Server {

    private ServerSocket serverSocket;
    private Service service;
    private int port;

    public Server(int port) {
        this.port = port;
        this.service = new Service();
        try {
            serverSocket = new ServerSocket(port);
            ServerLog.infoLog("Server start, port: " + port);
        } catch (IOException e) {
            ServerLog.errorLog("Server failed on port: " + port);
            e.printStackTrace();
            System.exit(0);
        }
        run();
    }

    public void run() {
        while (true) {
            Socket socket = newConnect();
            if (socket != null) {
                try {
                    UserHandler userHandler = new UserHandler(socket,service);
                    new Thread(userHandler).start();
                } catch (IOException e) {
                    System.out.println(e.getStackTrace() + " <-- Server.run ");
                }
            }
        }
    }

    public Socket newConnect() {
        Socket socket = null;
        try {
            socket = serverSocket.accept();
        } catch (IOException e) {
            ServerLog.errorLog("Connect failed with client");
            System.out.println(e.getStackTrace());
        }
        return socket;
    }
}

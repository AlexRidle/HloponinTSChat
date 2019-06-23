package com.agentapp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * @author Paul Hloponin
 */
public class Server {

    private ServerSocket serverSocket;
    private Service service;
    Logger logger = Logger.getLogger(Server.class.getName());


    public Server(int port) {
        this.service = new Service();
        try {
            serverSocket = new ServerSocket(port);
            logger.info("Server start, port: " + port);
        } catch (IOException e) {
            logger.info("Server failed on port: " + port);
            e.printStackTrace();
            System.exit(0);
        }
        startNewConnect();
    }

    public void startNewConnect() {
        while (true) {
            Socket socket = newConnect();
            if (socket != null) {
                try {
                    UserHandler userHandler = new UserHandler(socket, service);
                    new Thread(userHandler).start();
                } catch (IOException e) {
                    logger.warning(e.getMessage());
                }
            }
        }
    }

    public Socket newConnect() {
        Socket socket = null;
        try {
            socket = serverSocket.accept();
            System.out.println("socket accept");
        } catch (IOException e) {
            logger.info("Connect failed with client");
        }
        return socket;
    }
}

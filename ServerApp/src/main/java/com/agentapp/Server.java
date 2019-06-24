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
            logger.info("Server start, port: " + port);//Wrong form of word "start". "... started on port..."?
        } catch (IOException e) {
            logger.info("Server failed on port: " + port);//Wrong logging. Must be an "Error".
            e.printStackTrace();//Why do we print stack trace but not logging it?
            System.exit(0);
        }
        startNewConnect();
    }

    //Better to create SocketHandler class, that listening new connections and handles them.
    public void startNewConnect() {
        while (true) {
            Socket socket = newConnect();
            if (socket != null) {
                try {
                    UserHandler userHandler = new UserHandler(socket, service);
                    new Thread(userHandler).start();//Use ExecutorService to start new threads.
                } catch (IOException e) {
                    logger.warning(e.getMessage());//Why do we logging a message, not a stack trace?
                }
            }
        }
    }

    public Socket newConnect() {//Maybe createConnection, not newConnect?
        Socket socket = null;
        try {
            socket = serverSocket.accept();
            System.out.println("socket accept");
        } catch (IOException e) {
            logger.info("Connect failed with client");//Why do we not logging stack trace?
        }
        return socket;
    }
}

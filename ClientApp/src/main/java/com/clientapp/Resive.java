package com.clientapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;

public class Resive implements Runnable {

    private Socket socket;
    private BufferedReader in;
    private Logger logger;

    public Resive(Socket socket, BufferedReader in, Logger logger) {
        this.socket = socket;
        this.in = in;
        this.logger = logger;
    }

    //Принимаем сообщение с сервера
    public void run() {
        while (!socket.isClosed()) {
            String inMessage = null;
            try {
                inMessage = in.readLine();
                System.out.println(inMessage);
            } catch (IOException e) {
                logger.warning("Connect failed");
                close();
            }
            if (inMessage == null) {
                logger.warning("Server failed");
                close();
            }
        }
    }

    // Метод закрывает сокет
    public void close() {
        if (!socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException e) {
                logger.warning("Error closing socket");
            }
        }
    }
}

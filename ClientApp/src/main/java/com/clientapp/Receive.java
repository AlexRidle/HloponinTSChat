package com.clientapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * @author Paul Hloponin
 */
public class Receive implements Runnable {//Wrong name of class, must be a noun. Better to name it like "MessageReceiver".

    private Socket socket;
    private BufferedReader in;
    private Logger logger;

    public Receive(Socket socket, BufferedReader in, Logger logger) {
        this.socket = socket;
        this.in = in;
        this.logger = logger;
    }

    //Принимаем сообщение с сервера
    public void run() {
        while (!socket.isClosed()) {
            String inMessage = null;//Suspicious name of variable. Maybe receivedMessage?
            try {
                inMessage = in.readLine();
                System.out.println(inMessage);
            } catch (IOException e) {
                logger.warning("Connect failed");//Why do we not logging stack trace?
                close();
            }
            if (inMessage == null) {
                logger.warning("Server failed");//Why do we not logging stack trace?
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
                logger.warning("Error closing socket");//Why do we not logging stack trace?
            }
        }
    }
}

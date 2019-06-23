package com.clientapp;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * @author Paul Hloponin
 */
public class Client {

    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private final String host;
    private final int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
        Logger logger = Logger.getLogger("ClientApp");

        try {
            socket = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            logger.warning("Connect with server failed");
            System.exit(0);
        }

        new Thread(new Send(socket, out, logger)).start();  //Поток отсылки сообщения
        new Thread(new Receive(socket, in, logger)).start(); //Поток принятия сообщения
    }

}

package com.clientapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class Resive implements Runnable {

    private Socket socket;
    private BufferedReader in;

    public Resive(Socket socket, BufferedReader in) {
        this.socket = socket;
        this.in = in;
    }

    //Принимаем сообщение с сервера

    public void run() {
        while (!socket.isClosed()) {
            String inMessage = null;
            try {
                inMessage = in.readLine();
                System.out.println(inMessage);
            } catch (IOException e) {
                System.out.println("Connect failed");
                close();
            }
            if (inMessage == null) {
                System.out.println("Server failed");
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
                System.out.println("Error closing socket");
                e.printStackTrace();
            }
        }
    }
}

package com.clientapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Send implements Runnable {

    private Socket socket;
    private BufferedWriter out;
    private BufferedReader inConsole;
    private String message;

    public Send(Socket socket, BufferedWriter out) {
        this.socket = socket;
        this.out = out;
        inConsole = new BufferedReader(new InputStreamReader(System.in));
    }

    public void run() {

        while (true) {
            message = null;
            try {
                message = inConsole.readLine();
            } catch (IOException e) {
                System.out.println("Input your message - error");
                e.printStackTrace();
                System.exit(0);
            }
            if (message == null) {
                close();
                break;
            } else if (message.equalsIgnoreCase("/exit")) {
                close();
                break;
            } else {
                send(message, out);
            }
        }
    }

    //Метод отправляет сообщение на сервер введенное с консоли

    public void send(String message, BufferedWriter out) {
        try {
            out.write(message + "\r\n");
            out.flush();
        } catch (IOException e) {
            System.out.println("Error while sending");
            e.printStackTrace();
        }
    }

    // Метод закрывает сокет

    public void close() {
        if (!socket.isClosed()) {
            try {
                socket.close();
                System.exit(0);
            } catch (IOException e) {
                System.out.println("Error closing socket");
                e.printStackTrace();
            }
        }
    }
}

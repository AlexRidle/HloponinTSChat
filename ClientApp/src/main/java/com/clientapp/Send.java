package com.clientapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * @author Paul Hloponin
 */
public class Send implements Runnable {//Wrong name of class, must be a noun. Better to name it like "MessageSender".

    private Socket socket;
    private BufferedWriter out;
    private BufferedReader inConsole;
    private String message;
    private Logger logger;

    public Send(Socket socket, BufferedWriter out, Logger logger) {
        this.socket = socket;
        this.out = out;
        this.logger = logger;
        inConsole = new BufferedReader(new InputStreamReader(System.in));
    }

    public void run() {

        while (true) {
            message = null;
            try {
                message = inConsole.readLine();
            } catch (IOException e) {
                logger.warning("Input your message - error");//Strange message. Also must be shown to user, not logged.
                e.printStackTrace();//Why do we showing stack trace to user? It must be logged.
                System.exit(0);
            }
            if (message == null) {
                close();
                break;
            //Code duplicate. Better to add second statement to first "if".
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
            logger.warning("Error while sending");//Message must be shown to user, not logged.
            e.printStackTrace();//Why do we showing stack trace to user? It must be logged.
        }
    }

    // Метод закрывает сокет
    public void close() {
        if (!socket.isClosed()) {
            try {
                socket.close();
                System.exit(0);
            } catch (IOException e) {
                logger.warning("Error closing socket");//Message must be shown to user, not logged.
                e.printStackTrace();//Why do we showing stack trace to user? It must be logged.
            }
        }
    }
}

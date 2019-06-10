package logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServiceWeb {
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private BufferedReader inConsole;
    private String message;

    //Принимаем сообщение с веба и отправляем на сервер

    public void myResponse(BufferedWriter out) {
        this.out = out;
        inConsole = new BufferedReader(new InputStreamReader(System.in));

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

    //Принимаем сообщение с сервера и отправляем на веб

    public void myRequest(BufferedReader in) {
        this.in = in;


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

    public void send(String message, BufferedWriter out) {
        try {
            out.write(message + "\r\n");
            out.flush();
        } catch (IOException e) {
            System.out.println("Error while sending");
            e.printStackTrace();
        }
    }
}






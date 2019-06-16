package connect;

import endpoints.ChatEndpoint;
import jdk.nashorn.internal.objects.annotations.Setter;
import message.Message;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;


public class Connect implements Runnable {
    private static String host = "localhost";
    private static int port = 2121;
    private static Logger logger;
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private ChatEndpoint chatEndpoint;
    private String name;

    public Connect(ChatEndpoint chatEndpoint, String name) throws IOException {
        this.chatEndpoint = chatEndpoint;
        this.name = name;
        logger.info("connect accept on name : " + name);
        socket = new Socket(host, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));

    }

    @Override
    public void run() {
        Message message;
        String msg;
        while (true) {
            try {
                msg = in.readLine();
                if (msg.equals("/exit")) {
                    close();
                    break;
                }
                message = new Message(msg);
                chatEndpoint.sendEndpoint(message);
            } catch (IOException e) {
                logger.info(e.getMessage());
            }
        }
    }

    public void send(String message) {
        try {
            out.write(message + "\r\n");
            out.flush();
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
    }


    private void close() {
        logger.info("close this : " + name);
        try {
            if (!socket.isClosed()) {
                socket.close();
            }
            if (in == null) {
                in.close();
            }
            if (out == null) {
                out.close();
            }
        } catch (IOException e) {
            logger.info(e.getMessage());

        }
    }
}

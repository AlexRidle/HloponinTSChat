package connect;

import endpoints.ChatEndpoint;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * @author Paul Hloponin
 */
public class Connect extends Thread {
    private static String host = "localhost";
    private static int port = 2121;
    private static Logger logger;
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private ChatEndpoint chatEndpoint;

    public Connect(ChatEndpoint chatEndpoint, Logger logger) throws IOException {
        this.chatEndpoint = chatEndpoint;
        Connect.logger = logger;
        socket = new Socket(host, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
        logger.info("connect accept");

    }

    @Override
    public void run() {
        String message;
        while (true) {
            try {
                message = in.readLine();
                if (message.equals("/exit")) {
                    close();
                    break;
                }
                chatEndpoint.sendToWeb(message);
            } catch (IOException e) {
                logger.info(e.getMessage());
            }
        }
    }

    public void sendToServer(String message) {
        try {
            out.write(message + "\r\n");
            out.flush();
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
    }


    private void close() {
        logger.info("close this : " + chatEndpoint);
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

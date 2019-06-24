package connect;

import endpoints.ChatEndpoint;

import java.io.*;//Do not import all classes.
import java.net.Socket;
import java.util.logging.Logger;

/**
 * @author Paul Hloponin
 */
public class Connect extends Thread {//Suspicious name of class and package. Can be named like ServerService or something like that.
    //Host and port should be located in properties file. Also this variables can be converted to local.
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
            logger.info(e.getMessage());//Why do we not logging stack trace?  Also logging type myst be an error, not info.
        }
    }


    private void close() {//Better to name it like close Socket.
        logger.info("close this : " + chatEndpoint);
        try {
            if (!socket.isClosed()) {
                socket.close();
            }
            if (in == null) {
                in.close();//close method may produce null pointer exception. You need to add second catch block for it.
            }
            if (out == null) {
                out.close();//Same mistake on Line 66.
            }
        } catch (IOException e) {
            logger.info(e.getMessage());//Why do we not logging stack trace?  Also logging type myst be an error, not info.

        }//Formatting (We don't need empty line here.)
    }
}

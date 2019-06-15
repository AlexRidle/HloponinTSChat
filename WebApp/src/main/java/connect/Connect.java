package connect;

import endpoints.ChatEndpoint;
import jdk.nashorn.internal.objects.annotations.Setter;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

public class Connect {
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


}

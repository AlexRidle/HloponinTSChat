package com.agentapp;

import java.io.*;
import java.net.Socket;
import java.util.Map;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * @author Paul Hloponin
 */
public class UserHandler implements Runnable {
    private Socket socket;
    private Service service;
    private BufferedReader in;
    private BufferedWriter out;
    private static Logger logger = Logger.getLogger(UserHandler.class.getName());


    public UserHandler(Socket socket, Service service) throws IOException {
        this.socket = socket;
        this.service = service;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));

    }

    public void run() {
        Map<String, String> map = turnStringToMap(register(in));
        String name;
        if (map.get("type").equalsIgnoreCase(Type.CLIENT.toString())) {
            name = map.get("name");
            service.newClient(this, name, in, out);
            logger.info("New client : " + name);
        } else {
            name = map.get("name");
            service.newAgent(this, name, in, out);
            logger.info("New agent : " + name);
        }

    }

    private String register(BufferedReader in) {
        String input;
        StringBuilder sb = new StringBuilder();
        send("Please write : /register", out);

        try {
            input = in.readLine();
            input.equals("/register");
            while (!input.equals("/register")) {
                send("Try again, write : /register", out);
                input = in.readLine();
            }
            send("Please write your type : you are agent or client", out);
            input = in.readLine();
            while (!input.equalsIgnoreCase("agent") && !input.equalsIgnoreCase("client")) {
                send("Try again, write : you are agent or client", out);
                input = in.readLine();
            }
            sb.append(input + " ");
            send("Please, enter your name...", out);
            input = in.readLine();
            sb.append(input);

        } catch (IOException e) {
            logger.warning("Error registration");
        }
        return sb.toString();
    }

    private HashMap<String, String> turnStringToMap(String message) {
        HashMap<String, String> map = new HashMap<String, String>();
        String[] mass = message.split(" ");
        map.put("type", mass[0]);
        map.put("name", mass[1]);
        return map;
    }


    public Socket getSocket() {
        return socket;
    }

    void send(String message, BufferedWriter out) {
        try {
            out.write(message + "\n");
            out.flush();
        } catch (IOException e) {
            close(socket);
        }
    }

    private synchronized void close(Socket socket) {
        try {
            socket.close();
        } catch (IOException e) {
            logger.warning("Socket close");
        }
    }
}
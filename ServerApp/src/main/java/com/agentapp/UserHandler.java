package com.agentapp;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Logger;

public class UserHandler implements Runnable {
    private Socket socket;
    private Service service;
    private BufferedReader in;
    private BufferedWriter out;
    private static Logger logger = Logger.getLogger(UserHandler.class.getName());


    UserHandler(Socket socket, Service service) throws IOException {
        this.socket = socket;
        this.service = service;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

    }

    public void run() {
        HashMap<String, String> map = turnStringToMap(register(in));
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
        String line;
        StringBuilder sb = new StringBuilder();
        send("Для регистрации введите пожалуйста /register", out);

        try {
            line = in.readLine();
            line.equals("/register");
            while (!line.equals("/register")) {
                send("Попробуйте пожалуйста еще раз /register", out);
                line = in.readLine();
            }
            send("Введите ваш тип : вы agent или client", out);
            line = in.readLine();
            while (!line.equalsIgnoreCase("agent") && !line.equalsIgnoreCase("client")) {
                send("Попробуйте еще раз пожалуйста : вы agent или client?", out);
                line = in.readLine();
            }
            sb.append(line + " ");
            send("Введите ваше имя", out);
            line = in.readLine();
            sb.append(line);

        } catch (IOException e) {
            System.out.println("Error registration");
            e.printStackTrace();
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
            System.out.println("Socket close");
            e.printStackTrace();
        }
    }
}
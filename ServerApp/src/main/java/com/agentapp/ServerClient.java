package com.agentapp;

import java.io.*;

public class ServerClient {

    private UserClient userClient;
    private UserHandler userHandler;
    private Service service;
    private BufferedReader in;
    private BufferedWriter out;
    private boolean exit;
    private ServerAgent serverAgent;


    public ServerClient(UserClient userClient, UserHandler userHandler, Service service) {
        this.userClient = userClient;
        this.userHandler = userHandler;
        this.service = service;
    }

    public void searchAgent() {
        try {
            in = userClient.getIn();
            out = userClient.getOut();
            while (!userClient.getSocket().isClosed()) {
                String message = in.readLine();
                System.out.println(message);
                if (message.equals("/exit")) {
                    ServerLog.infoLog(String.format("Client : \"%s\" exit", userClient.getName()));
                    break;
                } else {
                    userClient.addMessage(message + " -- " + userClient.getName());
                    out.write("Поиск свободных агентов...\n");
                    service.connect(this);
                    talk();
                    System.out.println("prooshlo molodec++");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void talk() {
        try {
            in = userClient.getIn();
            while (!userClient.getSocket().isClosed() && !exit) {
                String message = in.readLine();

                switch (message) {
                    case "/exit": {
                        exit = true;
                        ServerLog.infoLog(userClient.getName() + "exit");
                    }
                    break;
                    case "/leave": {
                        exit = true;
                        ServerLog.infoLog(userClient.getName() + "leave");
                    }
                    break;
                    default: {
                        if (serverAgent != null) {
                            serverAgent.getUserAgent().getOut().write("client " + userClient.getName() + " - " + message);
                        } else userClient.addMessage(message + " -- " + userClient.getName());
                    }
                    break;
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public UserClient getUserClient() {
        return userClient;
    }

    public void setServerAgent(ServerAgent serverAgent) {
        this.serverAgent = serverAgent;
    }
}





















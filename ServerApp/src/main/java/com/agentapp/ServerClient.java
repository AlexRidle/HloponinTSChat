package com.agentapp;

import java.io.*;
import java.util.logging.Logger;

/**
 * @author Paul Hloponin
 */
public class ServerClient {

    private UserClient userClient;
    private UserHandler userHandler;
    private Service service;
    private BufferedReader in;
    private BufferedWriter out;
    private boolean exit;
    private boolean leave;

    private ServerAgent serverAgent;
    private static Logger logger = Logger.getLogger(ServerClient.class.getName());
    public ServerClient(UserClient userClient, UserHandler userHandler, Service service) {
        this.userClient = userClient;
        this.userHandler = userHandler;
        this.service = service;
    }

    public void searchAgent() {
        try {
            in = userClient.getIn();
            out = userClient.getOut();
            while (!userClient.getSocket().isClosed()&& !exit) {
                String message = in.readLine();
                if (message.equals("/exit")) {
                    logger.info(String.format("Client : \"%s\" exit", userClient.getName()));
                    exit=true;
                } else {
                    userClient.addMessage(message + " -- " + userClient.getName());
                    out.write("Поиск свободных агентов...\n");
                    out.flush();
                    service.connect(this);
                    talk();
                    if(leave){
                        leave = false;
                        service.removeAgent(this);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerAgent getServerAgent() {
        return serverAgent;
    }

    public void talk() {
        try {
            in = userClient.getIn();
            while (!userClient.getSocket().isClosed() && !exit && !leave) {
                String message = in.readLine();

                switch (message) {
                    case "/exit": {
                        exit = true;
                        logger.info(userClient.getName() + " exit");
                    }
                    break;
                    case "/leave": {
                        leave = true;
                        logger.info(userClient.getName() + " leave");
                    }
                    break;
                    default: {
                        if (serverAgent != null) {
                            serverAgent.getUserAgent().getOut().write("client " + userClient.getName() + " -> " + message + "\n");
                            serverAgent.getUserAgent().getOut().flush();
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

    public void setLeave(boolean leave) {
        this.leave = leave;
    }
}





















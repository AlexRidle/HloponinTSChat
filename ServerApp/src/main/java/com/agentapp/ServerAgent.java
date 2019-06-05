package com.agentapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.logging.Logger;

public class ServerAgent {

    private UserAgent userAgent;
    private UserHandler userHandler;
    private Service service;
    private ServerClient serverClient;
    private BufferedReader in;
    private BufferedWriter outAgent;
    private BufferedWriter outClient;
    private boolean exit;
    private static Logger logger = Logger.getLogger(ServerAgent.class.getName());

    public ServerAgent(UserAgent userAgent, UserHandler userHandler, Service service) {
        this.userAgent = userAgent;
        this.userHandler = userHandler;
        this.service = service;
    }

    public void talk() {
        try {
            in = userAgent.getIn();
            outAgent = userAgent.getOut();
            outClient = serverClient.getUserClient().getOut();

            outClient.write("К вам подключился агент : " + userAgent.getName()+ "\n");
            outClient.flush();
            outAgent.write(serverClient.getUserClient().getMemoryMessage().toString() + "\n");
            outAgent.flush();

            while (!userAgent.getSocket().isClosed() && !exit) {
                String message = in.readLine();

                switch (message) {
                    case "/exit": {
                        exit = true;
                        logger.info(userAgent.getName() + "exit");
                    }
                    break;
                    case "/leave": {
                        exit = true;
                        logger.info(userAgent.getName() + "leave");
                    }
                    break;
                    default: {

                        outClient.write("agent " + userAgent.getName() + " -> " + message + "\n");
                        outClient.flush();

                    }
                    break;
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public UserAgent getUserAgent() {
        return userAgent;
    }

    public void setServerClient(ServerClient serverClient) {
        this.serverClient = serverClient;
    }


}

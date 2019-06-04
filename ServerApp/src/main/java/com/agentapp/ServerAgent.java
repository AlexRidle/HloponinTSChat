package com.agentapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class ServerAgent {

    private UserAgent userAgent;
    private UserHandler userHandler;
    private Service service;
    private ServerClient serverClient;
    private BufferedReader in;
    private BufferedWriter outAgent;
    private BufferedWriter outClient;
    private boolean exit;

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

            outClient.write("К вам подключился агент : " + userAgent.getName());
            outAgent.write(serverClient.getUserClient().getMemoryMessage().toString());

            while (!userAgent.getSocket().isClosed() && !exit) {
                String message = in.readLine();

                switch (message) {
                    case "/exit": {
                        exit = true;
                        ServerLog.infoLog(userAgent.getName() + "exit");
                    }
                    break;
                    case "/leave": {
                        exit = true;
                        ServerLog.infoLog(userAgent.getName() + "leave");
                    }
                    break;
                    default: {
                        if (serverClient != null) {
                            outClient.write("agent " + userAgent.getName() + " - " + message);
                        }
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

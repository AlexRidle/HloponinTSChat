package com.agentapp;

import java.io.*;//Do not import all classes.
import java.util.logging.Logger;

/**
 * @author Paul Hloponin
 */
public class ServerClient {

    //This class is very similar to ServerAgent.
    //Why not to create a "User" class and not extend it?

    private UserClient userClient;
    private UserHandler userHandler;//This value is never accessed.
    private Service service;
    private BufferedReader in;
    private BufferedWriter out;
    private boolean exit;//Suspicious name of variable.
    private boolean leave;//Suspicious name of variable.

    //Formatting. This empty line is not need here.
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
            while (!userClient.getSocket().isClosed()&& !exit) {//Formatting
                String message = in.readLine();
                if (message.equals("/exit")) {
                    logger.info(String.format("Client : \"%s\" exit", userClient.getName()));//Better to use String.format method. Formatting.
                    exit=true;//Formatting.
                } else {
                    userClient.addMessage(message + " -- " + userClient.getName());
                    out.write("Поиск свободных агентов...\n");
                    out.flush();
                    service.connect(this);
                    talk();
                    if(leave){//Formatting.
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

    //Access can be private.
    //Suspicious name of method. Must be renamed and be a verb.
    public void talk() {
        try {
            in = userClient.getIn();
            while (!userClient.getSocket().isClosed() && !exit && !leave) {
                String message = in.readLine();

                switch (message) {
                    case "/exit": {
                        exit = true;//Better to use marker and break while without a new variable.
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
                    break;//Do we need this break here?
                }

            }

        } catch (IOException e) {
            e.printStackTrace();//Stack trace is not logged.
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





















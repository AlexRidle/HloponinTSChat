package com.agentapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author Paul Hloponin
 */
public class ServerAgent {//Suspicious name of class.

    private UserAgent userAgent;
    private UserHandler userHandler;//This value is never accessed.
    private Service service;
    private ServerClient serverClient;
    private BufferedReader in;
    private BufferedWriter outAgent;
    private BufferedWriter outClient;
    private boolean exit;//Suspicious name of variable.
    private boolean leave;//Suspicious name of variable.
    private static Logger logger = Logger.getLogger(ServerAgent.class.getName());

    public ServerAgent(UserAgent userAgent, UserHandler userHandler, Service service) {
        this.userAgent = userAgent;
        this.userHandler = userHandler;
        this.service = service;
    }

    public ServerClient getServerClient() {
        return serverClient;
    }

    public void setLeave(boolean leave) {
        this.leave = leave;
    }

    public void talk() {//Suspicious name of method. Must be renamed and be a verb.
        try {
            in = userAgent.getIn();
            outAgent = userAgent.getOut();
            outClient = serverClient.getUserClient().getOut();
            leave = false;
            exit = false;

            outClient.write("К вам подключился агент : " + userAgent.getName()+ "\n");//Better to use String.format method. Formatting.
            outClient.flush();
            outAgent.write(serverClient.getUserClient().getMemoryMessage().toString() + "\n");
            outAgent.flush();

            while (!userAgent.getSocket().isClosed() && !exit && !leave) {
                String message = in.readLine();

                switch (message) {
                    case "/exit": {
                        exit = true;//Better to use marker and break while without a new variable.
                        logger.info(userAgent.getName() + " exit");
                    }
                    break;
                    default: {
                        outClient.write("agent " + userAgent.getName() + " -> " + message + "\n");//Better to use String.format method.
                        outClient.flush();
                    }
                    break;//Do we need this break here?
                }

            }
            service.removeClient(this);
            if(exit){//Formatting
                service.terminate();
            }
            if (leave){//Formatting
                service.addAgents(this);
            }

        } catch (IOException e) {
            e.printStackTrace();//Stack trace is not logged.
        }
    }

    public UserAgent getUserAgent() {
        return userAgent;
    }

    public void setServerClient(ServerClient serverClient) {
        this.serverClient = serverClient;
    }


}

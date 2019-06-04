package com.agentapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Service {

    private Queue<ServerAgent> agents;

    public Service() {
        this.agents = new LinkedList<>();
    }

    public synchronized void addAgents(ServerAgent serverAgent) {
        agents.add(serverAgent);
        notifyAll();
    }

    public void newAgent(UserHandler userHandler, String name, BufferedReader in, BufferedWriter out) {
        UserAgent userAgent = new UserAgent(userHandler.getSocket(), name, in, out);
        addAgents(new ServerAgent(userAgent, userHandler, this));
        ServerLog.infoLog("Registration. New agent : " + name);
    }

    public void newClient(UserHandler userHandler, String name, BufferedReader in, BufferedWriter out) {
        UserClient userClient = new UserClient(userHandler.getSocket(), name, in, out);
        ServerClient serverClient = new ServerClient(userClient, userHandler, this);
        serverClient.searchAgent();
        ServerLog.infoLog("Registration. New client : " + name);
    }

    public void connect(ServerClient serverClient) {
        Runnable runnable = () -> {
            ServerAgent serverAgent = getAgent();
            serverClient.setServerAgent(serverAgent);
            serverAgent.setServerClient(serverClient);
            serverAgent.talk();
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private ServerAgent getAgent() {
        if (agents.size() > 0) {
            return agents.poll();
        }return null;
    }
}

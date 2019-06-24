package com.clientapp;

/**
 * Class will start the console client application
 * @author Paul Hloponin
 */
public class ClientAgentStart {//Better to name this class "StartConsoleApp"
    public static void main(String[] args) {
        new Client("localhost", 2121);//Host and port should be located in properties file.
    }
}

package com.dashboard.charts;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
public class BarChartSocket {

    public static int[][] data = { { 10, 20, 30, 40 }, { 15, 25, 35, 45 },{ 44,33,22,11 }, { 99,88,77,66 } , { 32,99,44,22 } };

    @OnMessage
    public void onMessage(String message, Session session) throws IOException, InterruptedException {

        int i = 0;
        try {
            i = Integer.parseInt(message);
            System.out.println("Received input value "+i);
        } catch (NumberFormatException ne) {
            System.out.println("invalid input received");
        }
        System.out.println("Sending data array "+Arrays.toString(data[i]));
        session.getBasicRemote().sendText(Arrays.toString(data[i]));
       
        while (true) {
            i = new Random().nextInt(4);
            System.out.println("Sending data array " + Arrays.toString(data[i]));
            session.getBasicRemote().sendText(Arrays.toString((data[i])));
            
            Thread.sleep(5000);
            
        }
        

    }

    @OnOpen
    public void onOpen() {
        System.out.println("Client connected");
    }

    @OnClose
    public void onClose() {
        System.out.println("Connection closed");
    }
}

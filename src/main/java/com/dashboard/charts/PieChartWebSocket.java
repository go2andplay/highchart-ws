package com.dashboard.charts;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/piechart")
public class PieChartWebSocket {

    public static String[][][] data = { { { "SML", "40" }, { "TML", "20" }, { "FML", "30" }, { "Others", "10" } },
            { { "SML", "50" }, { "TML", "20" }, { "FML", "30" }, { "Others", "0" } },
            { { "SML", "20" }, { "TML", "20" }, { "FML", "30" }, { "Others", "30" } } };

    @OnMessage
    public void onMessage(String message, Session session) throws IOException, InterruptedException {

        int i = 0;
        try {
            i = Integer.parseInt(message);
            System.out.println("Received input value " + i);
        } catch (NumberFormatException ne) {
            System.out.println("invalid input received");
        }
        while (true) {
            i = new Random().nextInt(3);
            System.out.println("Sending data array " + Arrays.deepToString(data[i]));
            session.getBasicRemote().sendText(Arrays.deepToString((data[i])));
            
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

package com.pretech.test.websockets;

import java.io.IOException;
import java.util.Arrays;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/piechart")
public class PieChartWebSocket {

    public static String[][][] data = { { { "Windows", "40" }, { "MacOS", "20" }, { "Linux", "30" }, { "Others", "10" } },
        { { "Windows", "50" }, { "MacOS", "20" }, { "Linux", "30" }, { "Others", "0" } },
            { { "Windows", "20" }, { "MacOS", "20" }, { "Linux", "30" }, { "Others", "30" } } };

    @OnMessage
    public void onMessage(String message, Session session) throws IOException, InterruptedException {

        int i = 0;
        try {
            i = Integer.parseInt(message);
            System.out.println("Received input value " + i);
        } catch (NumberFormatException ne) {
            System.out.println("invalid input received");
        }
        System.out.println("Sending data array " + Arrays.deepToString(data[i]));
        session.getBasicRemote().sendText(Arrays.deepToString((data[i])));

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

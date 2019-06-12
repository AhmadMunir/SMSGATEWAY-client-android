package com.example.smsbaru;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class SmsGatewayClient extends WebSocketClient {
    public SmsGatewayClient(URI serverURI){
        super(serverURI);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        System.out.println("KONEKSI TERSAMBUNG");
    }
    @Override
    public void onMessage(String s) {
        System.out.println("SERVER : " + s);
    }
    @Override
    public void onClose(int i, String s, boolean b) {
        System.out.println("KONEKSI TERPUTUS");
    }
    @Override
    public void onError(Exception e) {
        System.out.println("TERJADI ERROR");
    }


}

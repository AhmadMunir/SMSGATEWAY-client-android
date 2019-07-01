package com.example.smsbaru;

import com.google.gson.JsonObject;

import org.java_websocket.WebSocketListener;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import com.google.gson.Gson;


import java.net.URI;

public class SmsGatewayClient extends WebSocketClient {

    private WebSocketListener listener;

    private String status;

    public SmsGatewayClient(URI serverURI, WebSocketListener listener) {
        super(serverURI);
        this.listener = listener;
    }

    private Gson gson = new Gson();
    public SmsGatewayClient(URI serverURI){
        super(serverURI);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        System.out.println("KONEKSI TERSAMBUNG");
    }
    @Override
    public void onMessage(String message) {
        System.out.println("SERVER : " + message);
        JsonObject json = gson.fromJson(message, JsonObject.class);
        if(json.get("type").getAsString().equals("success")){

            String msg = json.get("message").getAsString();
            System.out.println("SERVER : " + msg);
            setStatus(msg);

        }else if(json.get("type").getAsString().equals("error")){
            String msg = json.get("message").getAsString();
            System.out.println("SERVER : " + msg);
        }else if(json.get("type").getAsString().equals("notification")) {
            String msg = json.get("message").getAsString();

            if(json.get("success").getAsBoolean()){


                System.out.println("Serv : " + msg);
                setStatus("ms");

            }else{
                System.out.println("SERVER : " + msg);
            }

        }else if(json.get("type").getAsString().equals("received")){
            String msg = json.get("message").getAsString();
            String from = json.get("from").getAsString();
            }
        }

    @Override
    public void onClose(int i, String s, boolean b) {
        System.out.println("KONEKSI TERPUTUS");
    }
    @Override
    public void onError(Exception e) {
        System.out.println("TERJADI ERROR");
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

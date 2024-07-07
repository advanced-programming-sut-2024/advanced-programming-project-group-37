package client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import message.server.ServerMessage;
import server.model.toolClasses.Result;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientTPC {
    public String token;

    private Socket socket;
    private DataInputStream receive;
    private DataOutputStream send;

    private String servetIP;
    private int serverPort;

    public Gson gson;

    private String serverMassage;

    public ClientTPC(String servetIP, int serverPort) {
        this.serverPort = serverPort;
        this.servetIP = servetIP;

        GsonBuilder builder = new GsonBuilder();
        this.gson = builder.create();
    }
    public void establishConnection() {
        try {
            socket = new Socket(servetIP, serverPort);

            send = new DataOutputStream(
                    socket.getOutputStream()
            );

            receive = new DataInputStream(
                    socket.getInputStream()
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void endConnection() {
        if (socket == null)
            return;
        try {
            socket.close();
            receive.close();
            send.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendMassage(String massage) {
        try {
            establishConnection();
            send.writeUTF(massage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ServerMessage receiveMassage() {
        try {
            ServerMessage serverMessage = gson.fromJson(receive.readUTF(), ServerMessage.class);
            endConnection();
            return serverMessage;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

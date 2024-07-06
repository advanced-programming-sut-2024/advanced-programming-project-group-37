package client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import message.server.ServerMessage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientTPC {
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
    public Result establishConnection() {
        try {
            socket = new Socket(servetIP, serverPort);

            send = new DataOutputStream(
                    socket.getOutputStream()
            );

            receive = new DataInputStream(
                    socket.getInputStream()
            );

            return new Result(true, null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, null);
        }
    }
    public Result endConnection() {
        if (socket == null) return new Result(true, null);
        try {
            socket.close();
            receive.close();
            send.close();

            return new Result(true, null);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, null);
        }
    }
    public Result sendMassage(String massage) {
        try {
            send.writeUTF(massage);
            return new Result(true, null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, null);
        }
    }
    public ServerMessage receiveMassage() {
        try {
            ServerMessage serverMessage = gson.fromJson(receive.readUTF(), ServerMessage.class);
            return serverMessage;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // getter
    public String getServetIP() {
        return servetIP;
    }
    public int getServerPort() {
        return serverPort;
    }
}

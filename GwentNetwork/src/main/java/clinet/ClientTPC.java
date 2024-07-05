package clinet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import message.Result;

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

    private Gson gson;

    private String serverMassage;

    private ClientTPC(String servetIP, int serverPort) {
        this.serverPort = serverPort;
        this.servetIP = servetIP;

        GsonBuilder builder = new GsonBuilder();
        this.gson = builder.create();
    }
    private Result establishConnection() {
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
    private Result endConnection() {
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
    private Result sendMassage(String massage) {
        try {
            send.writeUTF(massage);
            return new Result(true, null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, null);
        }
    }
    private Result receiveMassage() {
        try {
            return new Result(true, receive.readUTF());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, null);
        }
    }
}

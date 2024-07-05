package server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import message.client.ClientMessage;
import message.server.ServerMessage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerTCP extends Thread{
    private static ServerSocket server;
    private static Gson gson;

    private static int WORKERS;
    private static ArrayList<Socket> connections;

    private DataOutputStream send;
    private DataInputStream receive;


    public ServerTCP() {
        GsonBuilder builder = new GsonBuilder();
        gson = builder.create();
    }
    private static boolean setupServer(int portNumber, int workerNum) {
        try {
            server = new ServerSocket(portNumber);
            connections = new ArrayList <>();
            WORKERS = workerNum;
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public void listen() throws IOException {
        Socket socket;
        while (true) {
            socket = server.accept();
            synchronized (connections) {
                connections.add(socket);
                connections.notify();
            }
        }
    }

    @Override
    public void run() {
        Socket socket;
        while (true) {
            socket = null;
            synchronized (connections) {
                while (connections.isEmpty()) {
                    try {
                        connections.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                socket = connections.get(0);
                connections.remove(0);
            }
            if (socket != null) {
                handleConnection(socket);
            }
        }
    }

    private void handleConnection(Socket socket) {
        try {
            receive = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream())
            );
            send = new DataOutputStream(
                    new BufferedOutputStream(socket.getOutputStream())
            );

            String clientRequest;
            clientRequest = receive.readUTF();
            ClientMessage msg = extractClientMessage(clientRequest);

            /* TODO : اینجا کلاینت مسیج رو داریم. نگا میکنیم ببینیم مربوط به کدوم نوع مسیج هست
             * TODO : با استفاده از instanceOf --> clientMassage instanceOf RegisterMassage
             * TODO : بعد مسیج رو بهش پاس میدی
             * TODO : متد هات رو هم ورودیش رو با انواع مسیج ست کن هر مسیج یک کلاسه که داخلش میتونه هر چیزی داشته باشه که تبدیل به جیسون میشه و بر عکس
             * */

            send.close();
            receive.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private ClientMessage extractClientMessage(String clientStr) {
        try {
            ClientMessage clientMessage = gson.fromJson(clientStr, ClientMessage.class);

            /*
             * TODO : اینجا باید جیسون رو تبدیل کنی به کلاس ها
             * TODO : مثال پایین رو نگاه کن میفهمی یعنی چی باید مطایق مسیج های خودمون کار کنی
             * TODO :  این polymorphism هست سید:))
             * TODO :
             * TODO : switch (clientMessage.getType()) {
             * TODO :     case signupLogin:
             * TODO :         return gsonAgent.fromJson(clientStr, SignupLoginMessage.class);
             * TODO :     case setbio:
             * TODO :         return gsonAgent.fromJson(clientStr, SetBioMessage.class);
             * TODO :     case getbio:
             * TODO :         return gsonAgent.fromJson(clientStr, GetBioMessage.class);
             * TODO :     case logout:
             * TODO :         return gsonAgent.fromJson(clientStr, LogoutMessage.class);
             * TODO :     default:
             * TODO :         return null;
            }

             */
            return  null;
        }
        catch (Exception e) {
            return null;
        }
    }
    private boolean sendMessage(boolean success, String info) {
        ServerMessage failureMessage = new ServerMessage(success, info);
        String failureString = gson.toJson(failureMessage);
        try {
            send.writeUTF(failureString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        try {
            ServerTCP.setupServer(5000, 10);
            for (int i = 0; i < WORKERS; i++) {
                new ServerTCP().start();
            }
            new ServerTCP().listen();
        } catch (Exception e) {
            System.out.println("Server encountered a problem!");
            e.printStackTrace();
        }
    }
}

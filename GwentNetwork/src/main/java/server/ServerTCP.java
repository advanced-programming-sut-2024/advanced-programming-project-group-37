package server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import message.client.*;
import message.client.AnswerQMessage;
import message.client.profileMenu.ChangeNicknameMessage;
import message.client.profileMenu.changeUsernameMessage;
import message.enums.loginMenu.ConfirmQuestions;
import message.server.ServerMessage;
import server.controller.loginController.LoginMenuController;
import server.controller.profileController.ProfileMenuController;
import server.model.User;
import server.model.toolClasses.Result;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;

public class ServerTCP extends Thread {
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
            connections = new ArrayList<>();
            WORKERS = workerNum;
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    private String generateNewToken() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 256; i++)
            sb.append((char) (random.nextInt(128)));
        return sb.toString();
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

            if (msg instanceof RegisterMassage) {
                registerUserCheck((RegisterMassage) msg);
            } else if (msg instanceof PickQuestionMessage) {
                pickQregister((PickQuestionMessage) msg);
            } else if (msg instanceof LoginMessage) {
                loginNetwork((LoginMessage) msg);
            } else if (msg instanceof changeUsernameMessage){
                changeUsernameNetwork((changeUsernameMessage) msg);
            } else if (msg instanceof ChangeNicknameMessage){
                changeNicknameNetwork((ChangeNicknameMessage) msg);
            } else if (msg instanceof AnswerQMessage) {
                answerQNetwork((AnswerQMessage) msg);
            }
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

    private void answerQNetwork(AnswerQMessage msg) {
        Matcher matcher = msg.getMatcher();
        String username = msg.getUsername();
        Result result = LoginMenuController.answerQ(matcher, username);
        sendMessage(new ServerMessage(result));
    }

    private void changeNicknameNetwork(ChangeNicknameMessage msg) {
        String newNickname = msg.getNewNickname();
        String token = msg.getToken();
        Result result = ProfileMenuController.changeNickName(newNickname, token);
        sendMessage(new ServerMessage(result));
    }

    private void changeUsernameNetwork(changeUsernameMessage msg) {
        String token = msg.getToken();
        String newUsername = msg.getNewUsername();
        Result result = ProfileMenuController.changeUserName(newUsername , token);
        sendMessage(new ServerMessage(result));
    }

    private void loginNetwork(LoginMessage msg) {
        String username = msg.getUsername();
        String password = msg.getPassword();
        Result result = LoginMenuController.login(username, password);
        sendMessage(new ServerMessage(result, User.getLoggedInUser().getToken())); // returns result which contains message and check if process was successful or not with current user token
    }

    private void pickQregister(PickQuestionMessage msg) {
        RegisterMassage registerMassage = msg.getRegisterMassage();
        String username = registerMassage.getUsername();
        String password = registerMassage.getPassword();
        String email = registerMassage.getEmail();
        String nickname = registerMassage.getNickname();
        ConfirmQuestions confirmQuestions = msg.getQuestions();
        String answer = msg.getAnswer();
        String token = generateNewToken();
        Result result = LoginMenuController.registerNewUser(confirmQuestions, username, password, nickname,email, answer, token);
        sendMessage(new ServerMessage(result, token));
    }

    private void registerUserCheck(RegisterMassage msg) {
        String username = msg.getUsername();
        String password = msg.getPassword();
        String passwordConfirm = msg.getPasswordConfirm();
        String email = msg.getEmail();
        String nickname = msg.getNickname();
        Result result = LoginMenuController.checkAllErrors(username, password, passwordConfirm, nickname, email);
        sendMessage(new ServerMessage(result));
    }

    private ClientMessage extractClientMessage(String clientStr) {
        try {
            ClientMessage clientMessage = gson.fromJson(clientStr, ClientMessage.class);

            switch (clientMessage.getType()) {
                case MessageType.REGISTER:
                    return gson.fromJson(clientStr, RegisterMassage.class);
                case MessageType.LOGIN:
                    return gson.fromJson(clientStr, LoginMessage.class);
                case MessageType.PICK_QUESTION:
                    return gson.fromJson(clientStr, PickQuestionMessage.class);
                case MessageType.CHANGE_USERNAME:
                    return gson.fromJson(clientStr, changeUsernameMessage.class);
            }
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
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    private boolean sendMessage(ServerMessage message) {
        String failureString = gson.toJson(message);
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

package server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import message.client.*;
import message.client.LoginMenu.*;
import message.client.MainMenu.SignOutMessage;
import message.client.gameLobby.*;
import message.client.profileMenu.*;
import message.enums.PlayerState;
import message.enums.loginMenu.ConfirmQuestions;
import message.server.ServerMessage;
import message.server.ServerType;
import server.controller.GameController.GameLobbyController;
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
            } else if (msg instanceof ChangeUsernameMessage) {
                changeUsernameNetwork((ChangeUsernameMessage) msg);
            } else if (msg instanceof ChangeNicknameMessage) {
                changeNicknameNetwork((ChangeNicknameMessage) msg);
            } else if (msg instanceof AnswerQMessage) {
                answerQNetwork((AnswerQMessage) msg);
            } else if (msg instanceof SetNewPasswordMessage) {
                setNewPassNetwork((SetNewPasswordMessage) msg);
            } else if (msg instanceof ChangePasswordMessage) {
                changePassNetwork((ChangePasswordMessage) msg);
            } else if (msg instanceof ChangeEmailMessage) {
                changeEmailNetwork((ChangeEmailMessage) msg);
            } else if (msg instanceof ForgetPasswordMessage) {
                forgetPassNetwork((ForgetPasswordMessage) msg);
            } else if (msg instanceof SignOutMessage) {
                singOutNetwork((SignOutMessage) msg);
            } else if (msg instanceof GiveFriendMessage) {
                giveFriendNetwork((GiveFriendMessage) msg);
            } else if (msg instanceof AcceptRequest) {
                acceptRequestNetwork((AcceptRequest) msg);
            } else if (msg instanceof SearchMessage) {
                searchMessageNetwork((SearchMessage) msg);
            } else if (msg instanceof GiveMeOnlineFriend) {
                giveMeOnlineNetwork((GiveMeOnlineFriend) msg);
            } else if (msg instanceof ShowPupUpMessage){
                showPopUpNetwork((ShowPupUpMessage) msg);
            } else if (msg instanceof CheckServerMessage) {
                checkServerForMatchReq((CheckServerMessage) msg);
            } else if (msg instanceof RejectRequest) {
                rejectReqNetwork((RejectRequest) msg);
            } else if (msg instanceof EnterGameLobby){
                enterGameLobby((EnterGameLobby) msg);
            } else if (msg instanceof EnterGame) {
                enterGame((EnterGame) msg);
            } else if (msg instanceof ChangeGameMode) {
                changeGameModeNet((ChangeGameMode) msg);
            } else if (msg instanceof BackToMainMenu) {
                backToMainMenu((BackToMainMenu) msg);
            } else if (msg instanceof RandomGameRequest) {
                randomGameReqNetwork((RandomGameRequest) msg);
            }


            send.close();
            receive.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void randomGameReqNetwork(RandomGameRequest msg) {
        String token = msg.getToken();

        GameLobbyController.addToRandReq(token);

        sendMessage(new ServerMessage());
    }


    private void backToMainMenu(BackToMainMenu msg) {
        String token = msg.getToken();
        GameLobbyController.setState(token, PlayerState.OFFLINE);
        sendMessage(new ServerMessage());
    }

    private void changeGameModeNet(ChangeGameMode msg) {
        //TODO
        sendMessage(new ServerMessage());
    }

    private void enterGame(EnterGame msg) {
        String token = msg.getToken();
        GameLobbyController.setState(token, PlayerState.IN_GAME);
        sendMessage(new ServerMessage());
    }

    private void enterGameLobby(EnterGameLobby msg) {
        String token = msg.getToken();
        GameLobbyController.setState(token, PlayerState.ONLINE);
        sendMessage(new ServerMessage());
    }

    private void rejectReqNetwork(RejectRequest msg) {
        String token = msg.getToken();
        String username = msg.getUsername();

        Result result = ProfileMenuController.rejectFriendRequest(token, username);
        //just send a null message
        sendMessage(new ServerMessage());
    }

    private void acceptRequestNetwork(AcceptRequest msg) {
        String token = msg.getToken();
        String username = msg.getUsername();

        Result result = ProfileMenuController.acceptFriendRequest(token , username);
        //just send a null message
        sendMessage(new ServerMessage());

    }
    private void checkServerForMatchReq(CheckServerMessage msg) {
        String token = msg.getToken();
        Result result = GameLobbyController.checkMatchReq(token);

        if (result.isSuccessful()){
            sendMessage(new ServerMessage(ServerType.POP_UP_MATCH_REQ_GAME_LOBBY, result.getMessage()));
            return;
        }

        result = GameLobbyController.checkRandMatch(token);
        if (result.isSuccessful()) {
            sendMessage(new ServerMessage(ServerType.START_RAND_GAME, result.getMessage()));
            return;
        }

        sendMessage(new ServerMessage());
    }

    private void showPopUpNetwork(ShowPupUpMessage msg) {
        String sender = msg.getToken();
        String reciver = msg.getUsername();

        GameLobbyController.sendGameReq(sender , reciver);

        sendMessage(new ServerMessage());//send a null message

    }

    private void giveMeOnlineNetwork(GiveMeOnlineFriend msg) {
        String token = msg.getToken();
        ArrayList<String> onlineFriends = GameLobbyController.getOnlineFriends(token);
        //send array list of online friends
        sendMessage(new ServerMessage(onlineFriends));
    }

    private void searchMessageNetwork(SearchMessage msg) {
        String partOfUsername = msg.getStr();
        ArrayList<String> foundUsers = GameLobbyController.findUsersContainsStr(partOfUsername);
        sendMessage(new ServerMessage(foundUsers));
    }


    private void giveFriendNetwork(GiveFriendMessage msg) {
        String token = msg.getToken();
        ArrayList<String> FriendsName = ProfileMenuController.FriendsName(token);
        ArrayList<ArrayList<String>> friendRequests = ProfileMenuController.friendRequestNames(token);

        ArrayList<String> fromWho = friendRequests.get(0);
        ArrayList<String> date = friendRequests.get(1);
        ArrayList<String> state = friendRequests.get(2);

        sendMessage(new ServerMessage(FriendsName, fromWho, date, state));
    }

    private void singOutNetwork(SignOutMessage msg) {
        String token = msg.getToken();
        Result result = LoginMenuController.signOut(token, generateNewToken());

        sendMessage(new ServerMessage(result));
    }

    private void forgetPassNetwork(ForgetPasswordMessage msg) {
        Matcher matcher = msg.getMatcher();

        Result result = LoginMenuController.forgetPasswordCommand(matcher);
        sendMessage(new ServerMessage(result));
    }

    private void changeEmailNetwork(ChangeEmailMessage msg) {
        String newEmail = msg.getNewEmail();
        String token = msg.getToken();
        Result result = ProfileMenuController.changeEmail(newEmail, token);
        sendMessage(new ServerMessage(result));
    }

    private void changePassNetwork(ChangePasswordMessage msg) {
        String newPass = msg.getNewPassword();
        String oldPass = msg.getOldPassword();
        String token = msg.getToken();
        Result result = ProfileMenuController.changePassword(newPass, oldPass, token);
        sendMessage(new ServerMessage(result));
    }

    private void setNewPassNetwork(SetNewPasswordMessage msg) {
        Matcher matcher = msg.getMatcher();
        String username = msg.getUsername();
        Result result = LoginMenuController.setPassword(matcher, username);
        sendMessage(new ServerMessage(result));
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

    private void changeUsernameNetwork(ChangeUsernameMessage msg) {
        String token = msg.getToken();
        String newUsername = msg.getNewUsername();
        Result result = ProfileMenuController.changeUserName(newUsername, token);
        sendMessage(new ServerMessage(result));
    }

    private void loginNetwork(LoginMessage msg) {
        String username = msg.getUsername();
        String password = msg.getPassword();
        Result result = LoginMenuController.login(username, password);
        User user;
        String token = new String();
        if (!result.isSuccessful()) {
            user = null;
            token = null;
        } else {
            user = User.getLoggedInUser();
            token = user.getToken();
        }
        sendMessage(new ServerMessage(result, token)); // returns result which contains message and check if process was successful or not with current user token
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
        Result result = LoginMenuController.registerNewUser(confirmQuestions, username, password, nickname, email, answer, token);
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
                    return gson.fromJson(clientStr, ChangeUsernameMessage.class);
                case MessageType.ANSWER_Q:
                    return gson.fromJson(clientStr, AnswerQMessage.class);
                case MessageType.SET_NEW_PASSWORD:
                    return gson.fromJson(clientStr, SetNewPasswordMessage.class);
                case MessageType.CHANGE_EMAIL:
                    return gson.fromJson(clientStr, ChangeEmailMessage.class);
                case MessageType.CHANGE_NICKNAME:
                    return gson.fromJson(clientStr, ChangeNicknameMessage.class);
                case MessageType.CHANGE_PASSWORD:
                    return gson.fromJson(clientStr, ChangePasswordMessage.class);
                case MessageType.FORGET_PASSWORD:
                    return gson.fromJson(clientStr, ForgetPasswordMessage.class);
                case MessageType.SIGN_OUT:
                    return gson.fromJson(clientStr, SignOutMessage.class);
                case MessageType.GIVE_FRIEND:
                    return gson.fromJson(clientStr, GiveFriendMessage.class);
                case MessageType.ACCEPT_REQUEST:
                    return gson.fromJson(clientStr, AcceptRequest.class);
                case MessageType.CHECK_SERVER:
                    return gson.fromJson(clientStr, CheckServerMessage.class);
                case MessageType.GEVE_ONLINE_FRIEND:
                    return gson.fromJson(clientStr, GiveMeOnlineFriend.class);
                case MessageType.SEARCH:
                    return gson.fromJson(clientStr, SearchMessage.class);
                case MessageType.PUP_UP:
                    return gson.fromJson(clientStr, ShowPupUpMessage.class);
                case MessageType.REJECT_REQUEST:
                    return gson.fromJson(clientStr, RejectRequest.class);
                case MessageType.ENTER_GAMELOBBY:
                    return gson.fromJson(clientStr, EnterGameLobby.class);
                case MessageType.ENTER_GAME:
                    return gson.fromJson(clientStr, EnterGame.class);
                case MessageType.CHANGE_GAME_MODE:
                    return gson.fromJson(clientStr, ChangeGameMode.class);
                case MessageType.BACK_OFFLINE:
                    return gson.fromJson(clientStr, BackToMainMenu.class);
                case MessageType.RANDOM_GAME_REQUEST:
                    return gson.fromJson(clientStr, RandomGameRequest.class);
                case MessageType.FREAND_GAME_RQUEST:
                    return gson.fromJson(clientStr, RandomGameRequest.class);
            }
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

    private boolean sendMessage(String message) {
        try {
            send.writeUTF(message);
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

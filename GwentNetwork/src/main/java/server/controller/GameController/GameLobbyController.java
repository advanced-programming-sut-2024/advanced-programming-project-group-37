package server.controller.GameController;

import message.enums.PlayerState;
import server.model.User;
import server.model.toolClasses.Result;

import java.util.ArrayList;

public class GameLobbyController {
    private static ArrayList<User> randomReq = new ArrayList<>();

    public static ArrayList<String> findUsersContainsStr(String partOfUsername) {
        ArrayList<String> foundUsers = new ArrayList<>();
        ArrayList<User> allUsers = User.getAllUsers();
        for (User user : allUsers) {
            if (user.getUsername().contains(partOfUsername)) {
                foundUsers.add(user.getUsername());
            }
        }
        return foundUsers;
    }

    public static ArrayList<String> getOnlineFriends(String token) {
        User user = User.getUserByToken(token);
        //check if user is null
        if (user == null) {
            return null;
        }

        ArrayList<String> onlineFriends = new ArrayList<>();

        ArrayList<User> friends = user.getFriends();
        //add online friends
        for (User users : friends) {
            if (users.getState() == PlayerState.ONLINE)
                onlineFriends.add(users.getUsername());
        }

        return onlineFriends;
    }

    public static void sendGameReq(String senderToken, String reciverUsername) {
        User user = User.getUserByUsername(reciverUsername);
        User sender = User.getUserByToken(senderToken);
        System.out.println(sender.getUsername());
        System.out.println(user.getUsername());
        if (user == null) {
            return;
        }
        user.setOpponetRequest(sender);
        user.setHaveRequestForGame(true);
    }

    public static Result checkMatchReq(String token) {
        User user = User.getUserByToken(token);
        //check if user is null
        if (user == null) {
            return new Result(false, "invalid token!");
        }
        if (user.isHaveRequestForGame()) {
            user.setHaveRequestForGame(false);
            User opponent = user.getOpponetRequest();
            user.setOpponetRequest(null);
            return new Result(true, opponent.getUsername());
        } else return new Result(false, "NO!");
    }

    public static void setState(String token, PlayerState playerState) {
        User user = User.getUserByToken(token);
        if (user == null) {
            return;
        }
        user.setState(playerState);
    }

    public static ArrayList<User> getRandomReq() {
        return randomReq;
    }

    public static void setRandomReq(ArrayList<User> randomReq) {
        GameLobbyController.randomReq = randomReq;
    }

    public static void addToRandReq(String token) {
        User user = User.getUserByToken(token);
        //if user is null
        if (user == null) {
            return;
        }
        randomReq.add(user);
    }

    public static Result checkRandMatch(String token) {
        User user = User.getUserByToken(token);

        if (!randomReq.contains(user)){
            return new Result(false, "");
        }

        if (randomReq.size() == 2){
            randomReq.remove(user);
            User user2 = randomReq.get(0);
            PreGameMenuController preGameMenuController = new PreGameMenuController(user, user2);
            return new Result(true,user2.getUsername());
        }
        return new Result(false, "");
    }

    public static void SendBothToGame(String token, String username) {
        User user1 = User.getUserByToken(token);
        User user2 = User.getUserByUsername(username);

        PreGameMenuController newGame = new PreGameMenuController(user1, user2);

        user1.setStartGame(true);
        user2.setStartGame(true);

        user1.setOpponetRequest(user2);
        user2.setOpponetRequest(user1);

    }

    public static void abrotGameReq(String token, String username) {
        User user1 = User.getUserByToken(token);
        User user2 = User.getUserByUsername(username);

        user1.setOpponetRequest(null);
        user1.setHaveRequestForGame(false);

        user2.setOpponetRequest(null);
        user2.setHaveRequestForGame(false);

        user2.setKirShodi(true);

    }

    public static boolean checkFriendMatchAccept(String token) {
        User user = User.getUserByToken(token);

        boolean bool = user.isStartGame();
        user.setStartGame(false);
        return bool;
    }

    public static Boolean checkRejectMatch(String token) {
        User user = User.getUserByToken(token);

        boolean bool = user.isKirShodi();
        user.setKirShodi(false);
        return bool;
    }
}

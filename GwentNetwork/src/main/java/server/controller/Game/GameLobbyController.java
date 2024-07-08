package server.controller.Game;

import server.model.User;

import java.util.ArrayList;

public class GameLobbyController {

    public static ArrayList<String> findUsersContainsStr(String partOfUsername) {
        ArrayList<String> foundUsers = new ArrayList<>();
        ArrayList<User> allUsers = User.getAllUsers();
        for (User user : allUsers) {
            if (user.getUsername().contains(partOfUsername)){
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
        for(User users : friends){
            if (users.isOnline())
                onlineFriends.add(users.getUsername());
        }

        return onlineFriends;
    }

    public static void sendGameReq(String senderToken, String reciverUsername) {
        User user = User.getUserByUsername(reciverUsername);
        User sender = User.getUserByToken(senderToken);
        if (user == null){
            return;
        }
        user.setHaveRequestForGame(true);
        user.setOpponetRequest(sender);
    }
}

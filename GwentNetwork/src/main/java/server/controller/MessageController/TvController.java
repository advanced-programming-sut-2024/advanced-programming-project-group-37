package server.controller.MessageController;

import javafx.scene.image.Image;
import message.client.Game.LastState;
import message.client.Game.SendMessageFromGame;
import message.client.MessageType;
import message.client.gameLobby.*;
import message.server.ServerMessage;
import message.server.ServerType;
import server.model.TvOnlineShow;
import server.model.User;

import java.util.ArrayList;

public class TvController {
    public static ServerMessage updateLastPic(LastState msg) {
        Image image = Imageee.image;
        User user = User.getUserByToken(msg.getToken());

        TvOnlineShow temp = TvOnlineShow.getByUser(user);

        temp.allStates.add(image);
        temp.isUpdate = true;
        return new ServerMessage();
    }

    public static ServerMessage showLiveGame(LiveGame msg) {
        String token = msg.getToken();
        String username = msg.getUsername();
        User user1 = User.getUserByToken(token);
        User user2 = User.getUserByUsername(username);

        TvOnlineShow temp = TvOnlineShow.getByUser(user2);
        user1.tv = temp;

        return new ServerMessage();
    }

    public static ServerMessage getListOfGame(GetListOfGame msg) {
        ArrayList<String> player1 = new ArrayList<>();
        ArrayList<String> player2 = new ArrayList<>();

        for (TvOnlineShow tv : TvOnlineShow.allShows) {
            player1.add(tv.player1.getUsername());
            player2.add(tv.player2.getUsername());
        }

        return new ServerMessage(player1, player2, true);
    }

    public static ServerMessage passLastState(CheckServerMessage msg) {
        User user = User.getUserByToken(msg.getToken());
        TvOnlineShow tvOnlineShow = user.tv;
        if (tvOnlineShow.isUpdate) {
            tvOnlineShow.isUpdate =false;
            Imageee.image = tvOnlineShow.allStates.getLast();
            return new ServerMessage(tvOnlineShow.allStates.getLast(), ServerType.UPDATE_STATE);
        }


        if (user.isHaveNewMessageinOnline){
            user.isHaveNewMessageinOnline = false;
            return new ServerMessage(ServerType.NEW_MESSAGE, user.messageinOnline);
        }

        return new ServerMessage();
    }

    public static ServerMessage sendMessageToPlayers(SendMessageFromTvToPlayers msg) {
        User user = User.getUserByToken(msg.getToken());
        TvOnlineShow tvOnlineShow = user.tv;

        User user1 = tvOnlineShow.player1;
        User user2 = tvOnlineShow.player2;
        user2.haveMessageFromWatcher = true;
        user1.haveMessageFromWatcher = true;
        user1.messageFromWatcher = user.getUsername() + " : " + msg.getM();
        user2.messageFromWatcher = user.getUsername() + " : " + msg.getM();

        return new ServerMessage();
    }

    public static ServerMessage sendMessageToWatchers(SendMessageFromGame msg) {
        String token = msg.getToken();
        User user = User.getUserByToken(token);

        user.isHaveNewMessageinOnline = true;
        user.messageinOnline = msg.getMessage();
        return new ServerMessage();
    }
}

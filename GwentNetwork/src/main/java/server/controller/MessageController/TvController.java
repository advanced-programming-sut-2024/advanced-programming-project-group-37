package server.controller.MessageController;

import javafx.scene.image.Image;
import message.client.Game.LastState;
import message.client.MessageType;
import message.client.gameLobby.CheckServerMessage;
import message.client.gameLobby.GetListOfGame;
import message.client.gameLobby.LiveGame;
import message.server.ServerMessage;
import server.model.TvOnlineShow;
import server.model.User;

import java.util.ArrayList;

public class TvController {
    public static ServerMessage updateLastPic(LastState msg) {
        Image image = msg.getImage();
        User user = User.getUserByToken(msg.getToken());

        TvOnlineShow temp = TvOnlineShow.getByUser(user);

        temp.allStates.add(image);
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

        for(TvOnlineShow tv: TvOnlineShow.allShows){
            player1.add(tv.player1.getUser().getUsername());
            player2.add(tv.player2.getUser().getUsername());
        }

        return new ServerMessage(player1, player2 , true);
    }

    public static ServerMessage passLastState(CheckServerMessage msg) {
        User user = User.getUserByToken(msg.getToken());
        TvOnlineShow tvOnlineShow = user.tv;

        return new ServerMessage(tvOnlineShow.allStates.getLast());

    }
}

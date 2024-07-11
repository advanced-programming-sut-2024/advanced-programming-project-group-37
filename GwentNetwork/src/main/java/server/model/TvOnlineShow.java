package server.model;

import javafx.scene.image.Image;
import server.controller.MessageController.TvController;
import server.model.gameTable.UserInGame;

import java.util.ArrayList;

public class TvOnlineShow {
    public boolean isUpdate = false;
    public static ArrayList<TvOnlineShow> allShows = new ArrayList<>();
    public UserInGame player1;
    public UserInGame player2;
    public Image currentState;
    public ArrayList<Image> allStates = new ArrayList<>();

    public TvOnlineShow(UserInGame player1, UserInGame player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public static TvOnlineShow getByUser(User user){
        for (TvOnlineShow temp : allShows){
            if (temp.player1.getUser() == user || temp.player2.getUser() == user){
                return temp;
            }
        }
        return null;
    }
}

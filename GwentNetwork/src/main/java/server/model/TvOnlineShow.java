package server.model;

import javafx.scene.image.Image;
import server.controller.MessageController.TvController;
import server.model.gameTable.UserInGame;

import java.util.ArrayList;

public class TvOnlineShow {
    public boolean isUpdate = false;
    public static ArrayList<TvOnlineShow> allShows = new ArrayList<>();
    public User player1;
    public User player2;
    public Image currentState;
    public ArrayList<Image> allStates = new ArrayList<>();

    public TvOnlineShow(User player1, User player2) {
        this.player1 = player1;
        this.player2 = player2;
        allShows.add(this);
    }

    public static TvOnlineShow getByUser(User user){
        System.out.println(allShows.size());
        for (TvOnlineShow temp : allShows){
            if (temp.player1 == user || temp.player2 == user){
                return temp;
            }
        }
        return null;
    }
}

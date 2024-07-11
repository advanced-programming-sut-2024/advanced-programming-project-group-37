package server.model;

import javafx.scene.image.Image;
import server.model.gameTable.UserInGame;

import java.util.ArrayList;

public class TvOnlineShow {
    private ArrayList<TvOnlineShow> allShows = new ArrayList<>();
    private UserInGame player1;
    private UserInGame player2;
    private Image currentState;
    private ArrayList<Image> allStates = new ArrayList<>();

    public TvOnlineShow(UserInGame player1, UserInGame player2) {
        this.player1 = player1;
        this.player2 = player2;
    }
}

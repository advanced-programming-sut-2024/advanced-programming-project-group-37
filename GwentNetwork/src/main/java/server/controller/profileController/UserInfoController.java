package server.controller.profileController;

import server.model.GameHistory;
import server.model.User;
import server.model.toolClasses.Result;

import java.util.ArrayList;

public class UserInfoController {
    //show username, nickname, highest score, rank, number of played games, won games, lost games, draw games.
    public static Result showInfo() {return null;}

    public static ArrayList<GameHistory> showGameHistory(int n){
        User user = User.getLoggedInUser();

        //check if the total game that user hase is zero or not
        if (checkUserNumberOfGames())
            return null;

        ArrayList<GameHistory> gameHistories = new ArrayList<>();

        //choose n last games
        for (int i = 0; i < n; i++) {
            gameHistories.add(user.getAllPlayedGamesHistory().get(i));
        }

        return gameHistories;

    }
    //check n validation n>1
    public static boolean checkN(int n){return false;}
    public static boolean checkUserNumberOfGames(){
        return !User.getLoggedInUser().getAllPlayedGamesHistory().isEmpty();
    }

}

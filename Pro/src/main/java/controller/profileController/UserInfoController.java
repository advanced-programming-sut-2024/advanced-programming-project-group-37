package controller.profileController;

import model.GameHistory;
import model.User;
import model.toolClasses.Result;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UserInfoController {
    //show username, nickname, highest score, rank, number of played games, won games, lost games, draw games.
    public static void showInfo() {
    }

    public static ArrayList<GameHistory> showGameHistory(int n) {
        User user = User.getLoggedInUser();
        ArrayList<GameHistory> allGameHistories = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            allGameHistories.add(user.getAllPlayedGamesHistory().get(i));
        }
        return allGameHistories;
    }

    //check n validation n>1
    public static Result checkN(int n) {
        if (n < 0)
            return new Result(false, "enter positive number!");
        if (checkUserNumberOfGames())
            return new Result(false, "no match history!");
        return new Result(true, "ok");
    }

    public static boolean checkUserNumberOfGames() {
        User user = User.getLoggedInUser();
        return user.getAllPlayedGamesHistory().isEmpty();
    }

}

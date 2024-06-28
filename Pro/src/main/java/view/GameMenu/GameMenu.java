package view.GameMenu;

import controller.GameControllers.GameMenuController;
import model.User;
import model.enums.gameMenu.GameMenuCommands;
import model.toolClasses.Result;

import java.math.MathContext;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu {
    public static User firstPlayer;
    public static User secondPlayer;

    public static void run(Scanner scanner) {
        String input = scanner.nextLine();
        GameMenuController newGame = new GameMenuController();
        newGame.setPlayers(firstPlayer, secondPlayer);
        newGame.dealCards();

        Matcher matcher;

        while(!newGame.isOver()){
            if ((matcher = GameMenuCommands.VETO_CARD.getMatcher(input)) != null){
                Result result = newGame.vetoCard(matcher.group("cardnumber"));
            } else if (GameMenuCommands.IN_HAND_DECK.getMatcher(input) != null) {

            }
        }
    }
}

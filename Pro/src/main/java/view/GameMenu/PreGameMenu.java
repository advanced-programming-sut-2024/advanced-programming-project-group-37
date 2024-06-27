package view.GameMenu;

import controller.GameControllers.PreGameMenuController;
import javafx.css.Match;
import model.enums.gameMenu.PreGameCommands;
import model.toolClasses.Result;

import java.util.Scanner;
import java.util.regex.Matcher;

public class PreGameMenu {
    public static void run(Scanner scanner) {
        String input = scanner.nextLine();
        Matcher matcher;
        while (true) {
            if ((matcher = PreGameCommands.SHOW_FACTIONS.getMatcher(input)) != null) {
                Result result = PreGameMenuController.showFactions(matcher.group("n"));
            } else if ((matcher = PreGameCommands.SELECT_FACTION.getMatcher(input)) != null) {
                PreGameMenuController.selectFaction(matcher.group("factionname"), matcher.group("n"));
            } else if ((matcher = PreGameCommands.SHOW_CARDS.getMatcher(input)) != null) {
                Result result = PreGameMenuController.showCards(matcher.group("n"));
            } else if ((matcher = PreGameCommands.SHOW_DECK.getMatcher(input)) != null) {
                Result result = PreGameMenuController.showDeck(matcher.group("n"));
            } else if ((matcher = PreGameCommands.SHOW_INFORMATION_CURRENT_USER.getMatcher(input)) != null) {
                Result result = PreGameMenuController.showInfoCurrentUser(matcher.group("n"));
            } else if ((matcher = PreGameCommands.SHOW_LEADERS.getMatcher(input)) != null) {
                Result result = PreGameMenuController.showLeaders(matcher.group("n"));
            } else if ((matcher = PreGameCommands.SELECT_LEADER.getMatcher(input)) != null) {
                Result result = PreGameMenuController.selectLeader(Integer.parseInt(matcher.group("leadernum")), matcher.group("n"));
            } else if ((matcher = PreGameCommands.ADD_TO_DECK.getMatcher(input)) != null) {
                Result result = PreGameMenuController.addToDeck(matcher.group("cardname"), Integer.parseInt(matcher.group("count"))
                        , matcher.group("n"));
            } else if ((matcher = PreGameCommands.DELETE_FROM_DECK.getMatcher(input)) != null) {
                Result result = PreGameMenuController.deleteFromDeck(matcher.group("cardname"), Integer.parseInt(matcher.group("count")),
                        matcher.group("n"));
            } else if (PreGameCommands.START_GAME.getMatcher(input) != null) {
                GameMenu.run(scanner);
            } else {
                System.out.println("invalid command");
            }
            input = scanner.nextLine();

            //TODO Save Deck by file address and name
            //Todo load deck by file address and name
        }
    }

}

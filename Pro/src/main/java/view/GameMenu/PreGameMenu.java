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
            }
        }
    }

}

package view.GameMenu;

import controller.GameControllers.GameMenuController;
import model.User;
import model.enums.gameMenu.GameMenuCommands;
import model.toolClasses.Result;

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

        while (!newGame.isOver()) {
            if ((matcher = GameMenuCommands.VETO_CARD.getMatcher(input)) != null) {
                Result result = newGame.vetoCard(matcher.group("cardnumber"));
            } else if (GameMenuCommands.IN_HAND_DECK.getMatcher(input) != null) {
                Result result = newGame.inHandDeck(-1);
            } else if ((matcher = GameMenuCommands.IN_HAND_DECK_OPTION.getMatcher(input)) != null) {
                Result result = newGame.inHandDeck(Integer.parseInt(matcher.group("place")));
            } else if ((matcher = GameMenuCommands.REMAINING_CARD.getMatcher(input)) != null) {
                Result result = newGame.remainingCardsToPlay();
            } else if ((matcher = GameMenuCommands.OUT_OF_PLAY_CARD.getMatcher(input)) != null) {
                Result result = newGame.outOfPlayCards();
            } else if ((matcher = GameMenuCommands.CARDS_IN_ROW.getMatcher(input)) != null) {
                Result result = newGame.cardsInRow(Integer.parseInt(matcher.group("rownumber")));
            } else if ((matcher = GameMenuCommands.SPELLS_IN_PLAY.getMatcher(input)) != null) {
                Result result = newGame.spellsInPlay();
            } else if ((matcher = GameMenuCommands.PLACE_CARD.getMatcher(input)) != null) {
                Result result = newGame.placeCard(0, 0);//TODO complete this method
            } else if ((matcher = GameMenuCommands.SHOW_COMMANDER.getMatcher(input)) != null) {
                Result result = newGame.showCommander();
            } else if ((matcher = GameMenuCommands.COMMANDER_POWER_PLAY.getMatcher(input)) != null) {
                Result result = newGame.commanderPowerPLay(); //TODO do this after completing the methods
            } else if ((matcher = GameMenuCommands.SHOW_PLAYERS_LIVE.getMatcher(input)) != null) {
                Result result = newGame.showPlayersLives();
            } else if ((matcher = GameMenuCommands.SHOW_PLAYERS_INFO.getMatcher(input)) != null) {
                Result result = newGame.showPlayersInfo();
            } else if ((matcher = GameMenuCommands.SHOW_NUMBER_OF_CARDS_IN_HAND.getMatcher(input)) != null) {
                Result result = newGame.showNumberOfCardsInHand();
            } else if ((matcher = GameMenuCommands.SHOW_TURN_INFO.getMatcher(input)) != null) {
                Result result = newGame.showTurnInfo();
            } else if ((matcher = GameMenuCommands.SHOW_TOTAL_SCORE.getMatcher(input)) != null) {
                Result result = newGame.showTotalScore();
            }
        }
    }
}

package view.GameMenu;

import controller.GameControllers.PreGameMenuController;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.enums.gameMenu.PreGameCommands;
import model.toolClasses.Result;

import java.util.regex.Matcher;

public class PreGameMenu {
    // graphic terminal
    public AnchorPane terminalPane;
    public TextArea terminalTextArea;
    private boolean isTerminalVisible = false;
    public void showTerminal(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ESCAPE)) {
            terminalPane.setVisible(isTerminalVisible = !isTerminalVisible);
        }
    }
    public void checkCommand(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            String[] inputLines = terminalTextArea.getText().split("\n");
            String inputLine = inputLines[inputLines.length - 1];

            terminalTextArea.setText(terminalTextArea.getText() + "\n" + "-------------------------------------------" +
                    "----------------------------------------" + "\n");

            // some if else
            Matcher matcher;
            if ((matcher = PreGameCommands.SHOW_FACTIONS.getMatcher(inputLine)) != null) {
                Result result = PreGameMenuController.showFactions(matcher.group("n"));
            } else if ((matcher = PreGameCommands.SELECT_FACTION.getMatcher(inputLine)) != null) {
                PreGameMenuController.selectFaction(matcher.group("factionname"), matcher.group("n"));
            } else if ((matcher = PreGameCommands.SHOW_CARDS.getMatcher(inputLine)) != null) {
                Result result = PreGameMenuController.showCards(matcher.group("n"));
            } else if ((matcher = PreGameCommands.SHOW_DECK.getMatcher(inputLine)) != null) {
                Result result = PreGameMenuController.showDeck(matcher.group("n"));
            } else if ((matcher = PreGameCommands.SHOW_INFORMATION_CURRENT_USER.getMatcher(inputLine)) != null) {
                Result result = PreGameMenuController.showInfoCurrentUser(matcher.group("n"));
            } else if ((matcher = PreGameCommands.SHOW_LEADERS.getMatcher(inputLine)) != null) {
                Result result = PreGameMenuController.showLeaders(matcher.group("n"));
            } else if ((matcher = PreGameCommands.SELECT_LEADER.getMatcher(inputLine)) != null) {
                Result result = PreGameMenuController.selectLeader(Integer.parseInt(matcher.group("leadernum")), matcher.group("n"));
            } else if ((matcher = PreGameCommands.ADD_TO_DECK.getMatcher(inputLine)) != null) {
                Result result = PreGameMenuController.addToDeck(matcher.group("cardname"), Integer.parseInt(matcher.group("count"))
                        , matcher.group("n"));
            } else if ((matcher = PreGameCommands.DELETE_FROM_DECK.getMatcher(inputLine)) != null) {
                Result result = PreGameMenuController.deleteFromDeck(matcher.group("cardname"), Integer.parseInt(matcher.group("count")),
                        matcher.group("n"));
            } else if (PreGameCommands.START_GAME.getMatcher(inputLine) != null) {
                Result result = PreGameMenuController.startGame();
                if (result.isSuccessful()); // todo : go to game
            } else {
                terminalTextArea.setText(terminalTextArea.getText() + "invalid command" + "\n");
            }

            terminalTextArea.setText(terminalTextArea.getText() + "-------------------------------------------" +
                    "----------------------------------------" + "\n");
            terminalTextArea.positionCaret(terminalTextArea.getText().length());
        }
    }
}
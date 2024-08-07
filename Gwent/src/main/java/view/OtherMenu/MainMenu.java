package view.OtherMenu;

import controller.mainController.MainMenuController;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.enums.mainMenu.MainMenuCommands;
import view.HeadViewController;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu {
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
                    "-----------------------------------------" + "\n");

            Matcher matcher;
            // some if else
            if ((matcher = MainMenuCommands.logout.getMatcher(inputLine)).find()) {
                signOut();
            }
            else if ((matcher = MainMenuCommands.goToOtherMenu.getMatcher(inputLine)).find()) {
                String menuName = matcher.group(1);

                if (menuName.equals("profile menu")) {
                    goToProfileMenu();
                }
                else if (menuName.equals("pregame menu")) {
                    goToPreGameMenu();
                }
                else terminalTextArea.setText(terminalTextArea.getText() +"Invalid Menu Name" + "\n");
            }
            else terminalTextArea.setText(terminalTextArea.getText() +"Invalid Command" + "\n");

            terminalTextArea.setText(terminalTextArea.getText() + "-------------------------------------------" +
                    "-----------------------------------------" + "\n");
            terminalTextArea.positionCaret(terminalTextArea.getText().length());
        }
    }

    public void goToProfileMenu() {
        HeadViewController.changeScene("profile page");
    }

    public void goToPreGameMenu() {
        HeadViewController.changeScene("pregame page");
    }

    public void signOut() {
        MainMenuController.userLogout();

        HeadViewController.changeScene("login page");
    }

    public void mute() {
        HeadViewController.player.setMute(HeadViewController.isMute = !HeadViewController.isMute);
    }
}
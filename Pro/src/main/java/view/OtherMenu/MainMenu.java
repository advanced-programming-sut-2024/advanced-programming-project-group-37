package view.OtherMenu;

import controller.GameControllers.PreGameMenuController;
import controller.mainController.MainMenuController;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.enums.gameMenu.PreGameCommands;
import model.enums.mainMenu.MainMenuCommands;
import model.toolClasses.Result;
import view.GameMenu.PreGameMenu;
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

            // TODO : some if else for this menu (start menu) see the code
//            terminalTextArea.setText(terminalTextArea.getText() + inputLine + "\n");
//            terminalTextArea.positionCaret(terminalTextArea.getText().length());
        }
    }

    public static void run(Scanner scanner) {
        String str = scanner.nextLine();
        Matcher matcher;
        while (true) {
            if (MainMenuCommands.goToProfileMenu.getMatcher(str) != null) {
                ProfileMenu.run(scanner);
                return;
            } else if (MainMenuCommands.goToPreGameMenu.getMatcher(str) != null) {
                PreGameMenu.run(scanner);
                return;
            } else if (MainMenuCommands.goToPreviousMenu.getMatcher(str) != null) {
                //Todo @Author ILIYA run the login menu
                return;
            } else if ((matcher = PreGameCommands.CREATE_GAME.getMatcher(str)) != null) {
                Result result = PreGameMenuController.createGame(matcher.group("player2username"));
                PreGameMenu.run(scanner);
                return;
            } else {
                System.out.println("invalid command");
                str = scanner.nextLine();
            }
        }
    }

    public void goToProfileMenu() {
        // TODO:
    }

    public void goToPreGameMenu() {
        // TODO:
    }

    public void signOut() {
        MainMenuController.userLogout();

        HeadViewController.changeScene("login page");
    }

    public void mute() {
        HeadViewController.player.setMute(HeadViewController.isMute = !HeadViewController.isMute);
    }
}
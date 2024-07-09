package client.ClientView.OtherMenu;

import client.ClientView.HeadViewController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import message.client.MainMenu.SignOutMessage;
import message.client.gameLobby.EnterGameLobby;
import message.enums.mainMenu.MainMenuCommands;

import java.util.regex.Matcher;
import static client.ClientView.HeadViewController.clientTPC;
import static client.ClientView.HeadViewController.isMute;

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
                    goToGameLobby();
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

    public void goToGameLobby() {
        HeadViewController.changeScene("game lobby");

        clientTPC.sendMassage(clientTPC.gson.toJson(new EnterGameLobby(clientTPC.token)));

        clientTPC.receiveMassage();
    }

    public void signOut() {
        clientTPC.sendMassage(clientTPC.gson.toJson(new SignOutMessage(clientTPC.token)));

        clientTPC.receiveMassage();

        clientTPC.token = null;

        HeadViewController.changeScene("login page");
    }

    public void mute(ActionEvent actionEvent) {
        HeadViewController.player.setMute(HeadViewController.isMute = !HeadViewController.isMute);

        Button button = (Button) actionEvent.getSource();

        if (isMute) button.setText("UNMUTE");
        else button.setText("MUTE");
    }
}
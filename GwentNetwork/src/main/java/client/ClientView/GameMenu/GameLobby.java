package client.ClientView.GameMenu;


import client.ClientView.HeadViewController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import message.client.gameLobby.CheckServerMessage;
import message.server.ServerMessage;

import java.util.regex.Matcher;
import static client.ClientView.HeadViewController.clientTPC;

public class GameLobby {

    // terminal for game lobby
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
            if (false) System.out.println();
            else terminalTextArea.setText(terminalTextArea.getText() +"Invalid Command" + "\n");

            terminalTextArea.setText(terminalTextArea.getText() + "-------------------------------------------" +
                    "-----------------------------------------" + "\n");
            terminalTextArea.positionCaret(terminalTextArea.getText().length());
        }
    }
    public void mute() {
        HeadViewController.player.setMute(HeadViewController.isMute = !HeadViewController.isMute);
    }

    // check server
    private static boolean isCalled = false;
    public void checkServer() {
        // this method check if Server have massage
        if (!isCalled){
            isCalled = true;

            Timeline timeline = new Timeline();

            timeline.setCycleCount(Animation.INDEFINITE);

            KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> {
                clientTPC.sendMassage(clientTPC.gson.toJson(new CheckServerMessage(clientTPC.token)));

                ServerMessage message = clientTPC.receiveMassage();

                // todo
            });

            timeline.getKeyFrames().add(keyFrame);

            timeline.play();
        }
    }

    // graphic part
    public Button closeFriendButton;
    public AnchorPane onlineFriendPane;
    public Button openFriendButton;

    public void showOnlineFriend() {
    }

    public void closeOnlineFriend() {
    }
}

package client.ClientView.GameMenu;

import client.ClientView.HeadViewController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import message.client.MessageType;
import message.client.gameLobby.CheckServerMessage;
import message.client.gameLobby.GetListOfGame;
import message.client.gameLobby.LiveGame;
import message.client.gameLobby.SendMessageFromTvToPlayers;
import message.server.ServerMessage;
import message.server.ServerType;

import java.util.regex.Matcher;

import static client.ClientView.HeadViewController.clientTPC;

public class TV {
    // terminal for game lobby
    public AnchorPane terminalPane;
    public TextArea terminalTextArea;
    public TextField terminalTextField;
    private boolean isTerminalVisible = false;
    public void showTerminal(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ESCAPE)) {
            terminalPane.setVisible(isTerminalVisible = !isTerminalVisible);
        }
    }
    public void checkCommand(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            String m = terminalTextField.getText();

            terminalTextArea.setText(terminalTextArea.getText() + "\n" + "-------------------------------------------" +
                    "-----------------------------------------" + "\n");

            terminalTextArea.setText(terminalTextArea.getText() + "me: " + m + "\n");

            terminalTextArea.setText(terminalTextArea.getText() + "-------------------------------------------" +
                    "-----------------------------------------" + "\n");
            terminalTextArea.positionCaret(terminalTextArea.getText().length());

            clientTPC.sendMassage(clientTPC.gson.toJson(new SendMessageFromTvToPlayers(username)));
            clientTPC.receiveMassage();
        }
    }
    private void updateTextArea(String message) {
        terminalTextArea.setText(terminalTextArea.getText() + "\n" + "-------------------------------------------" +
                "-----------------------------------------" + "\n");

        terminalTextArea.setText(terminalTextArea.getText() + "player: \n" + message + "\n");

        terminalTextArea.setText(terminalTextArea.getText() + "-------------------------------------------" +
                "-----------------------------------------" + "\n");
        terminalTextArea.positionCaret(terminalTextArea.getText().length());
    }

    // check server
    private static Timeline timeline;
    private static boolean isCalled = false;
    public void checkServer() {
        // this method check if Server have massage
        if (!isCalled){
            isCalled = true;

            timeline = new Timeline();

            timeline.setCycleCount(Animation.INDEFINITE);

            KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> {
                clientTPC.sendMassage(clientTPC.gson.toJson(new CheckServerMessage(clientTPC.token, MessageType.CHECK_SERVER4)));

                ServerMessage message = clientTPC.receiveMassage();

//                if () {} // todo : for live watch
//                if () {
//                  updateTextArea(message.getInfo());
//                } // todo : for get message
            });

            timeline.getKeyFrames().add(keyFrame);

            timeline.play();
        }
    }

    // code
    public AnchorPane listPane;
    public ScrollPane listScrollPane;
    public ImageView liveImage;
    public AnchorPane livePane;

    private String username;

    public void showListOfGames() {
        listPane.setVisible(!listPane.isVisible());

        if (listPane.isVisible()) {
            GridPane gridPane = new GridPane();
            gridPane.setHgap(10);
            gridPane.setVgap(40);

            listScrollPane.setFitToWidth(true);
            listScrollPane.setFitToHeight(true);

            clientTPC.sendMassage(clientTPC.gson.toJson(new GetListOfGame(clientTPC.token)));
            ServerMessage message = clientTPC.receiveMassage();

            for (int i = 0; i < message.getFriends().size(); i++) {
                Button button = new Button("watch");
                button.setStyle("-fx-background-color: #000a; -fx-text-fill: white");
                int ii = i;
                button.setOnAction(event -> {
                    listPane.setVisible(false);
                    watchThisGame(message.getFriends().get(0));
                });

                gridPane.add(button, 0, i);

                Label label = new Label(message.getFriends().get(i));
                label.setStyle("-fx-text-fill: white;");
                gridPane.add(label, 1, i);

                Label label1 = new Label("VS");
                label.setStyle("-fx-text-fill: white; -fx-font-size: 22px");
                gridPane.add(label1, 2, i);

                Label label2 = new Label(message.getFromWho().get(i));
                label.setStyle("-fx-text-fill: white;");
                gridPane.add(label2, 3, i);
            }
        }
    }
    private void watchThisGame(String username) {
        this.username = username;

        clientTPC.sendMassage(clientTPC.gson.toJson(new LiveGame(clientTPC.token, username)));
        clientTPC.receiveMassage();
    }

    public void backToGameLobby() {
        HeadViewController.changeScene("game lobby");
    }

    public void backFromLive() {
    }
}

package client.ClientView.GameMenu;

import client.ClientView.HeadViewController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import message.client.gameLobby.*;
import message.server.ServerMessage;
import message.server.ServerType;

import java.util.ArrayList;
import java.util.regex.Matcher;
import static client.ClientView.HeadViewController.clientTPC;
import static client.ClientView.HeadViewController.isMute;

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
    public void mute(ActionEvent actionEvent) {
        HeadViewController.player.setMute(HeadViewController.isMute = !HeadViewController.isMute);

        Button button = (Button) actionEvent.getSource();

        if (isMute) button.setText("UNMUTE");
        else button.setText("MUTE");
    }
    public void back() {
        HeadViewController.changeScene("main page");
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

                if (message.getType() == ServerType.POP_UP_MATCH_REQ_GAME_LOBBY) {
                    popUpPane.setVisible(true);

                    username.setText(message.getOpponent());

                    Media media = new Media(getClass().getResource("/VIDEOS/hand.mp4").toExternalForm());
                    MediaPlayer player = new MediaPlayer(media);
                    player.setCycleCount(1);
                    mediaView.setMediaPlayer(player);
                    player.play();
                } else if (message.getType() == ServerType.POP_UP_MATCH_REQ_GAME_LOBBY) { // todo : تغیر تایپ
                    counterButton.setVisible(false);
                }
            });

            timeline.getKeyFrames().add(keyFrame);

            timeline.play();
        }
    }

    // graphic part
    public Button closeFriendButton;
    public AnchorPane onlineFriendPane;
    public Button openFriendButton;
    public ScrollPane onlineFriendScroll;
    public Button counterButton;
    public ImageView typePicture;
    public HBox typeHBox;
    public AnchorPane popUpPane;
    public MediaView mediaView;
    public Label username;

    public void showOnlineFriend() {
        openFriendButton.setVisible(false);

        onlineFriendPane.setVisible(true);

        closeFriendButton.setVisible(true);

        clientTPC.sendMassage(clientTPC.gson.toJson(new GiveMeOnlineFriend(clientTPC.token)));

        ServerMessage message = clientTPC.receiveMassage();

        ArrayList<String> onlineFriends = message.getFriends();

        GridPane gridPane = new GridPane();

        onlineFriendScroll.setFitToHeight(true);
        onlineFriendScroll.setFitToWidth(true);

        onlineFriendScroll.setContent(gridPane);

        for (int i = 0; i < onlineFriends.size(); i++) {
            Button button = new Button("invite");

            gridPane.add(button, 0, i);

            int ii = i;
            button.setOnAction(event -> inviteFriend(onlineFriends.get(ii)));

            gridPane.add(new Label(onlineFriends.get(i)), 1, i);
        }
    }
    private void inviteFriend(String username) {
        counterButton.setVisible(true);

        Timeline timeline = new Timeline();

        for (int i = 1; i <= 7; i++) {
            int ii = i;
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(ii), event -> {
                counterButton.setVisible(true);
                counterButton.setText(ii + "s passed since invite");
            });
            timeline.getKeyFrames().add(keyFrame);
        }

        timeline.play();

        if (!showPupUp(username)) {
            openFriendButton.setVisible(true);

            onlineFriendPane.setVisible(false);

            closeFriendButton.setVisible(false);
            timeline.stop();
            counterButton.setVisible(false);
        }
        else {
            openFriendButton.setVisible(false);

            onlineFriendPane.setVisible(true);

            closeFriendButton.setVisible(true);

            timeline.stop();
            counterButton.setVisible(false);

            clientTPC.sendMassage(clientTPC.gson.toJson(new FriendGameRequest(clientTPC.token, username)));
        }
    }
    private boolean showPupUp(String username) {
        clientTPC.sendMassage(clientTPC.gson.toJson(new ShowPupUpMessage(clientTPC.token, username)));
        return clientTPC.receiveMassage().isSuccess();
    }
    public void closeOnlineFriend() {
        openFriendButton.setVisible(true);

        onlineFriendPane.setVisible(false);

        closeFriendButton.setVisible(false);
    }

    public void showTypeHBox() {
        typeHBox.setVisible(true);
    }
    public void changeGameType(MouseEvent mouseEvent) {
        typeHBox.setVisible(false);

        Image image = ((ImageView) mouseEvent.getSource()).getImage();

        typePicture.setImage(image);

        clientTPC.sendMassage(clientTPC.gson.toJson(
            new ChangeGameMode(image.equals(new Image(
                    getClass().getResource("/asset/img/icons/L1.jpeg").toExternalForm())))
        ));

        clientTPC.receiveMassage();
    }
    public void maximize(MouseEvent mouseEvent) {
        ImageView image = (ImageView) mouseEvent.getSource();
        image.setScaleX(1.5);
        image.setScaleY(1.5);
    }
    public void minimize(MouseEvent mouseEvent) {
        ImageView image = (ImageView) mouseEvent.getSource();
        image.setScaleX(1);
        image.setScaleY(1);
    }

    public void startRandomGame() {
        counterButton.setVisible(true);
        Timeline timeline = new Timeline();

        int i = 1;
        while (true) {
            int ii = i++;
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(ii), event -> {
                counterButton.setVisible(true);
                if (ii%3 == 0) counterButton.setText(ii + "s passed. please wait o..");
                if (ii%3 == 1) counterButton.setText(ii + "s passed. please wait ..o");
                if (ii%3 == 2) counterButton.setText(ii + "s passed. please wait .o.");
            });
            timeline.getKeyFrames().add(keyFrame);

            if (ii == 60) break;
        }

        timeline.play();

        timeline.setOnFinished(actionEvent -> {
            counterButton.setVisible(false);
        });

        startGame();
    }
    private void startGame() {
        clientTPC.sendMassage(clientTPC.gson.toJson(new RandomGameRequest(clientTPC.token)));

        clientTPC.receiveMassage().isSuccess();
    }

    // these methods are for pop up
    public void accept() {
        clientTPC.sendMassage(clientTPC.gson.toJson(new AcceptFriendRequest(clientTPC.token, username.getText())));

        clientTPC.receiveMassage();
    }
    public void reject() {
        clientTPC.sendMassage(clientTPC.gson.toJson(new RejectFriendRequest(clientTPC.token, username.getText())));

        clientTPC.receiveMassage();
    }
}
package client.ClientView;

import client.ClientTPC;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import java.net.URL;
import java.util.HashMap;

/**
 * @author iliya
 * this class doesn't have any conection with backent and, It easyly add to phase3 of project from phase2
 *
 * changes: no change
 */

public class HeadViewController extends Application {
    // network
    public static ClientTPC clientTPC;

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

            if (inputLine.equals("menu enter login menu")) {
                goToLoginPage();
            }
            else if (inputLine.startsWith("menu enter")) {
                terminalTextArea.setText(terminalTextArea.getText() + "Invalid Menu Name" + "\n");
            }
            else terminalTextArea.setText(terminalTextArea.getText() + "Invalid Command" + "\n");


            terminalTextArea.setText(terminalTextArea.getText() + "-------------------------------------------" +
                    "----------------------------------------" + "\n");
            terminalTextArea.positionCaret(terminalTextArea.getText().length());
        }
    }


    // some variable for start page
    public static Stage stage;
    public static HashMap<String, Scene> scenes = new HashMap<>();
    public MediaView mediaView;

    public static MediaPlayer player;

    public static boolean isMute = false;
    // these methods are for start page

    @Override
    public void start(Stage stage) throws Exception {
        HeadViewController.stage = stage;

        stage.setScene(scenes.get("start page"));

        stage.show();
    }

    @FXML
    public void initialize() {
        Media media = new Media(getClass().getResource("/VIDEOS/1.mp4").toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

        player = mediaPlayer;
        mediaView.setMediaPlayer(mediaPlayer);
    }

    public static void main(String[] args) {
        // network
        clientTPC = new ClientTPC("localhost", 5000);

        try {
            addAllScene();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        launch(args);
    }

    private static void addAllScene() throws Exception{
        // add start scene
        addAScene("start page", HeadViewController.class.getResource("/FXML/Start.fxml"));

        // add login scene
        addAScene("login page", HeadViewController.class.getResource("/FXML/LoginPage.fxml"));

        // add register Page
        addAScene("register page", HeadViewController.class.getResource("/FXML/RegisterPage.fxml"));

        // add main page
        addAScene("main page", HeadViewController.class.getResource("/FXML/MainPage.fxml"));

        // add profile page
        addAScene("profile page", HeadViewController.class.getResource("/FXML/ProfilePage.fxml"));

//        // add pregame page
//        addAScene("pregame page", HeadViewController.class.getResource("/FXML/PregamePage.fxml"));
//
//        // add game page
//        addAScene("game page", HeadViewController.class.getResource("/FXML/GamePage.fxml"));
    }
    private static void addAScene(String pageName, URL url) throws Exception {
        Pane pane = FXMLLoader.load(url);
        Scene scene = new Scene(pane);
        scenes.put(pageName, scene);
    }

    public void goToLoginPage() {
        changeScene("login page");
    }

    public void mute() {
        player.setMute(isMute = !isMute);
    }

    // this method change scene and set special media for each of them
    public static void changeScene(String scene) {
        player.stop();

        // change scene
        HeadViewController.stage.setScene(scenes.get(scene));

        // set media
        if (!scene.equals("pregame page") && !scene.equals("game page")) {
            Media media = new Media(HeadViewController.class.getResource("/VIDEOS/2.mp4").toExternalForm());
            player = new MediaPlayer(media);
            MediaView mediaView1 = ((MediaView) ((AnchorPane) scenes.get(scene).getRoot()).getChildren().get(0));
            mediaView1.setMediaPlayer(player);
            player.setCycleCount(MediaPlayer.INDEFINITE);
            player.play();
            player.setMute(isMute);
        }
    }
}

package view;

import controller.loginController.LoginMenuController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import model.enums.loginMenu.LoginMenuCommands;
import model.toolClasses.Result;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;


public class HeadViewController extends Application {
//    // declare currentMenuName to use this in terminal to know that we are in witch menu
//    public static String currentMenuName = "start page";
//    public static void setCurrentMenuName(String currentMenuName) {
//        HeadViewController.currentMenuName = currentMenuName;
//    }
//    // terminal thread method
//    private void terminalThread() {
//        Thread terminal = new Thread( () -> {
//            // declare a scanner for input of terminal
//            Scanner scanner = new Scanner(System.in);
//
//            // a true while for scan input in terminal
//            while (true) {
//
//                String inputLine = scanner.nextLine();
//
//                // public command
//
//
//                // each menu spacial command
//                switch (currentMenuName) {
//                    case "start page" :
//
//                        break;
//                    case "login page" :
//
//                        break;
//                    case "register page" :
//
//                        break;
//                    case "main page" :
//
//                        break;
//                }
//            }
//
//        });
//        // set name of the thread --> terminal thread
//        terminal.setName("terminalThread");
//        // set daemon --> true
//        terminal.setDaemon(true);
//        terminal.start();
//    }

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

            // TODO : some if else for this menu (start menu) see the code
//            if ()
//            } else if ((matcher = LoginMenuCommands))
//            terminalTextArea.setText(terminalTextArea.getText() + inputLine + "\n");
//            terminalTextArea.positionCaret(terminalTextArea.getText().length());
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

        stage.setScene(scenes.get("startPage"));

        stage.show();

        // now we call terminal thread
//        terminalThread();
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
        try {
            addAllScene();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        launch(args);
    }

    private static void addAllScene() throws Exception{
        // add start scene
        Pane pane = FXMLLoader.load(HeadViewController.class.getResource("/FXML/Start.fxml"));
        Scene scene = new Scene(pane);
        scenes.put("startPage", scene);

        // add login scene
        Pane pane2 = FXMLLoader.load(HeadViewController.class.getResource("/FXML/LoginPage.fxml"));
        Scene scene2 = new Scene(pane2);
        scenes.put("login page", scene2);

        // add register Page
        Pane pane3 = FXMLLoader.load(HeadViewController.class.getResource("/FXML/RegisterPage.fxml"));
        Scene scene3 = new Scene(pane3);
        scenes.put("register page", scene3);

        // add main page
        Pane pane4 = FXMLLoader.load(HeadViewController.class.getResource("/FXML/MainPage.fxml"));
        Scene scene4 = new Scene(pane4);
        scenes.put("main page", scene4);
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

        if (scene.equals("login page") || scene.equals("register page")) {
            // set media
            Media media = new Media(HeadViewController.class.getResource("/VIDEOS/2.mp4").toExternalForm());
            player = new MediaPlayer(media);
            MediaView mediaView1 = ((MediaView) ((AnchorPane) scenes.get(scene).getRoot()).getChildren().get(0));
            mediaView1.setMediaPlayer(player);
            player.setCycleCount(MediaPlayer.INDEFINITE);
            player.play();
        }
        else if (scene.equals("main page")) {
            // set media for main page
            Media media = new Media(HeadViewController.class.getResource("/VIDEOS/2.mp4").toExternalForm());
            player = new MediaPlayer(media);
            MediaView mediaView1 = ((MediaView) ((AnchorPane) scenes.get(scene).getRoot()).getChildren().get(0));
            mediaView1.setMediaPlayer(player);
            player.setCycleCount(MediaPlayer.INDEFINITE);
            player.play();
        }
    }
}
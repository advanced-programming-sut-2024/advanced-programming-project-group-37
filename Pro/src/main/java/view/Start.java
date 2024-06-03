package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.OtherMenu.LoginMenu;

import java.util.HashMap;

public class Start extends Application {
    public static Stage stage;
    public static HashMap<String, Scene> scenes = new HashMap<>();

    @Override
    public void start(Stage stage) throws Exception {
        Start.stage = stage;

        stage.setScene(scenes.get("startPage"));

        stage.show();
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
        Pane pane = FXMLLoader.load(Start.class.getResource("/FXML/Start.fxml"));
        Scene scene = new Scene(pane);
        scenes.put("startPage", scene);

        // add login scene
        Pane pane2 = FXMLLoader.load(Start.class.getResource("/FXML/LoginPage.fxml"));
        Scene scene2 = new Scene(pane2);
        scenes.put("LoginPage", scene2);
    }

    public void goToLoginPage() {
        LoginMenu.loadThisPage();
    }
}
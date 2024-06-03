package view.OtherMenu;

import controller.loginController.LoginMenuController;
import javafx.application.Application;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import view.Start;

import java.util.Scanner;

public class LoginMenu {
    public TextField usernameTextField;
    public TextArea ErrorTextArea;

    public static void run(Scanner scanner){
        LoginMenuController.login("", "");
    }

    public static void loadThisPage() {
        Start.stage.setScene(Start.scenes.get("LoginPage"));
    }
}
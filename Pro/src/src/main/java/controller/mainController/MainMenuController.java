package controller.mainController;

import model.User;

import java.util.Scanner;

public class MainMenuController {

    //user Logout
    public static void userLogout(){
        User.setLoggedInUser(null);
    }
}
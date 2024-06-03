package controller.mainController;

import model.User;

import java.util.Scanner;

public class MainMenuController {

    //user Logout
    public static void userLogout(){
        User.setLoggedInUser(null);
    }

    //go to profile menu
    public static void goToProfileMenu(Scanner scanner){}
    //go to pregame menu
    public static void goToPreGameMenu(Scanner scanner){}
}

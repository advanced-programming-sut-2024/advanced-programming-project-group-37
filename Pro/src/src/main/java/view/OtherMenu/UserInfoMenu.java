package view.OtherMenu;

import controller.profileController.UserInfoController;
import model.enums.profileMenu.UserInfoMenuCommands;
import model.toolClasses.Result;

import java.util.Scanner;
import java.util.regex.Matcher;

public class UserInfoMenu {
    public static void run(Scanner scanner){
        Result result = UserInfoController.showInfo();
        String input = scanner.nextLine();
        Matcher mathcer;
        while (true) {
            if ((mathcer = UserInfoMenuCommands.gameHistory.getMatcher(input)) != null) {
                Result result = UserInfoController.showGameHistory(Integer.parseInt(mathcer.group("n")));
            } else if (UserInfoMenuCommands.goToPreviousMenu.getMatcher(input) != null) {
                MainMenu.run(scanner);
                return;
            } else {
                System.out.println("invalid command");
            }
            input = scanner.nextLine();
        }
    }

}

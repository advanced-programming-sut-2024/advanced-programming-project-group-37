package view.OtherMenu;

import controller.profileController.ProfileMenuController;
import model.enums.profileMenu.ProfileMenuCommands;
import model.enums.profileMenu.UserInfoMenuCommands;
import model.toolClasses.Result;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileMenu {
    public static void run(Scanner scanner) {
        String input = scanner.nextLine();
        Matcher matcher;
        while (true) {
            //call every command and do what is needed
            if ((matcher = ProfileMenuCommands.changeUsername.getMatcher(input)) != null) {
                Result result = ProfileMenuController.changeUserName(matcher.group("username"));
            } else if ((matcher = ProfileMenuCommands.changeNickname.getMatcher(input)) != null) {
                Result result = ProfileMenuController.changeNickName(matcher.group("nickname"));
            } else if ((matcher = ProfileMenuCommands.changeEmail.getMatcher(input)) != null) {
                Result result = ProfileMenuController.changeEmail(matcher.group("email"));
            } else if ((matcher = ProfileMenuCommands.changePassword.getMatcher(input)) != null) {
                Result result = ProfileMenuController.changePassword(matcher.group("newpassword"),
                        matcher.group("oldpassword"));
            } else if (ProfileMenuCommands.goToPreviousMenu.getMatcher(input) != null) {
                //todo run the main menu
                MainMenu.run(scanner);
                return;
            } else if (ProfileMenuCommands.enterUserInfoMenu.getMatcher(input) != null) {
                //todo run the user info menu
                UserInfoMenu.run(scanner);
                return;
            } else {
                System.out.println("invalid command");
            }
            input = scanner.nextLine();
        }
    }
}

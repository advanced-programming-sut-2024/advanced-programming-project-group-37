package controller.profileController;

import controller.loginController.LoginMenuController;
import model.User;
import model.enums.loginMenu.RegEx;
import model.toolClasses.Result;

import java.util.ArrayList;
import java.util.Scanner;

//extending from LoginMenuController because of some methods
public class ProfileMenuController extends LoginMenuController {

    //change user username
    public static Result changeUserName(String newUserName) {
        //first check if the username is the same as the previous username
        if (User.getLoggedInUser().getUsername().equals(newUserName))
            return new Result(false, "New username is the same as the previous one.");

        //check if username regex is valid and matches
        if (!isUsernameValid(newUserName))
            return new Result(false, "Invalid username!");

        //change the username
        User user = User.getLoggedInUser();
        user.setUsername(newUserName);
        User.setLoggedInUser(user);
        return new Result(true, "Username changed successfully.");
    }

    //change nickname
    public static Result changeNickName(String newNickname) {
        User user = User.getLoggedInUser();

        //first check if the nickname is as the same as the previous nickname or not
        if (user.getNickname().equals(newNickname))
            return new Result(false, "New nickname is the same as the previous one.");

        user.setNickname(newNickname);
        User.setLoggedInUser(user);
        return new Result(true, "Nickname changed successfully to " + newNickname);
    }

    //change email
    public static Result changeEmail(String newEmail) {
        User user = User.getLoggedInUser();

        //first check if the Email is as the same as the previous Email or not
        if (user.getEmail().equals(newEmail))
            return new Result(false, "New Email is as the same as the previous one.");

        //check email validation
        if (isEmailValid(newEmail))
            return new Result(false, "Invalid Email Format!");

        //change the email
        user.setEmail(newEmail);
        User.setLoggedInUser(user);
        return new Result(true, "Email change successfully to " + newEmail);
    }

    //change Password
    public static Result changePassword(String newPassword, String oldPassword) {
        User user = User.getLoggedInUser();

        //check if new password and the old one is the same or not
        if (newPassword.equals(oldPassword))
            return new Result(false, "Enter different password than older one!");

        //check if oldPassword matches with real one
        if (!user.getPassword().equals(oldPassword))
            return new Result(false, "Enter your older password correctly!");

        //check password validation
        if (!isPasswordValid(newPassword))
            return new Result(false, "Invalid password!");

        //check if password is strong enough or not
        if (!isPasswordStrong(newPassword))
            return new Result(false, "Weak Password!");

        //change password
        user.setPassword(newPassword);
        User.setLoggedInUser(user);
        return new Result(true, "Password changed successfully!");
    }

    //check if 2 strings are the same

    //go to user info menu and show user's information
    public static void goToUserInfoMenu(Scanner scanner) {
    }
}

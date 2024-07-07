package server.controller.profileController;

import server.controller.loginController.LoginMenuController;
import server.model.FriendRequest;
import server.model.User;
import server.model.toolClasses.Result;

import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.ArrayList;

//extending from LoginMenuController because of some methods
public class ProfileMenuController extends LoginMenuController {

    //change user username
    public static Result changeUserName(String newUserName, String token) {
        //first check if the username is the same as the previous username
        if (User.getLoggedInUser().getUsername().equals(newUserName))
            return new Result(false, "New username is the same as the previous one.");

        //check if username regex is valid and matches
        if (!isUsernameValid(newUserName))
            return new Result(false, "Invalid username!");

        //change the username
        User user = User.getUserByToken(token);
        if (user == null) {
            return new Result(false, "invalid token!");
        }
        user.setUsername(newUserName);
        User.setLoggedInUser(user);
        return new Result(true, "Username changed successfully.");
    }

    //change nickname
    public static Result changeNickName(String newNickname, String token) {
        User user = User.getUserByToken(token);
        //check if token is invalid
        if (user == null)
            return new Result(false, "invalid token");
        //first check if the nickname is as the same as the previous nickname or not
        if (user.getNickname().equals(newNickname))
            return new Result(false, "New nickname is the same as the previous one.");

        user.setNickname(newNickname);
        User.setLoggedInUser(user);
        return new Result(true, "Nickname changed successfully to " + newNickname);
    }

    //change email
    public static Result changeEmail(String newEmail, String token) {
        User user = User.getUserByToken(token);
        //check if token was invalid
        if (user == null) {
            return new Result(false, "Invalid token!");
        }
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
    public static Result changePassword(String newPassword, String oldPassword, String token) {
        User user = User.getUserByToken(token);
        //check if token is valid
        if (user == null) {
            return new Result(false, "invalid token!");
        }

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

    public static ArrayList<String> FriendsName(String token) {
        User user = User.getUserByToken(token);
        if (user == null) {
            return null;
        }
        ArrayList<String> friendsName = new ArrayList<>();
        for (User friend : user.getFriends()) {
            friendsName.add(friend.getUsername());
        }
        return friendsName;
    }

    public static ArrayList<ArrayList<String>> friendRequestNames(String token) {
        User user = User.getUserByToken(token);
        if (user == null) {
            return null;
        }
        ArrayList<String> fromWho = new ArrayList<>();
        ArrayList<String> date = new ArrayList<>();
        ArrayList<String> state = new ArrayList<>();

        ArrayList<ArrayList<String>> totalInfo = new ArrayList<>();
        for (FriendRequest friendRequest : user.getFriendRequests()) {
            if (!friendRequest.getState().equals("waiting")) continue;
            User fromUser = friendRequest.getFromUser();
            fromWho.add(friendRequest.getFromUser().getUsername());
            date.add(friendRequest.getDate().toString());
            if (fromUser.isOnline())
                state.add("online");
            else state.add("offline");
        }
        totalInfo.add(fromWho);
        totalInfo.add(date);
        totalInfo.add(state);
        return totalInfo;
    }


    public static Result acceptFriendRequest(String token, String username) {
        User firstUser = User.getUserByToken(token);
        User secondUser = User.getUserByUsername(username);
        //add second user to first user friends
        ArrayList<User> firstUserFriends = firstUser.getFriends();
        firstUserFriends.add(secondUser);
        //add first user to second user friends
        ArrayList<User> secondUserFriends = secondUser.getFriends();
        secondUserFriends.add(firstUser);

        return null; // todo
    }

    //check if 2 strings are the same
}

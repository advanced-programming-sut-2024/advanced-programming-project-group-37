package controller.loginController;

import model.User;
import model.enums.loginMenu.LoginMenuCommands;
import model.enums.loginMenu.RegEx;
import model.toolClasses.Result;
import model.enums.loginMenu.ConfirmQuestions;

import java.lang.reflect.Array;
import java.security.interfaces.RSAKey;
import java.util.ArrayList;
import java.util.Random;

public class LoginMenuController{

    // register
    public static Result checkAllErrors(String username, String password, String passwordConfirm, String nickname, String email) {
        //first check if the username has used before or not
        if(isUsernameAlreadyUsed(username))
            return new Result(false, "Username already used!");

        //check if the username format is valid
        if (!isUsernameValid(username))
            return new Result(false, "Invalid username format!");

        //check if email format is valid or not
        if (!isEmailValid(email))
            return new Result(false, "Invalid email format!");

        //check if password is valid or not
        if (!isPasswordValid(password))
            return new Result(false, "Invalid password format!");

        //check password strength
        if (!isPasswordStrong(password))
            return new Result(false, "Weak password!");

        //check password match with password confirm
        if (!isPasswordConfirmed(password, passwordConfirm))
            return new Result(false, "Those passwords didn't match!");

        return new Result(true, "No Error found!");
    }
    public static Result registerNewUser(ConfirmQuestions confirmQuestions, String username, String password, String nickname, String email, String answer) {
        User user = new User(confirmQuestions, username, password, nickname, email, answer);
        return new Result(true, "User created successfully");
    }
    protected static boolean isUsernameAlreadyUsed(String username) {
        //get all users
        ArrayList<User> allUsers = User.getAllUsers();

        //check if the username found in all users or not
        for (int i = 0; i < allUsers.size(); i++)
            if (allUsers.get(i).getUsername().equals(username))
                return true;
        return false;
    }
    protected static boolean isUsernameValid(String username) {
        return username.matches(RegEx.VALID_USERNAME.getRegex());
    }
    protected static boolean isEmailValid(String email) {
        return email.matches(RegEx.validEmail.getRegex());
    }
    protected static boolean isPasswordValid(String password) {
        return password.matches(RegEx.validPassword.getRegex());
    }
    protected static boolean isPasswordStrong(String password) {
        return password.matches(RegEx.strongPassword.getRegex());
    }
    private static boolean isPasswordConfirmed(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
    private static String randomPasswordGenerator() {
        //for choosing a random small letter we add 'a' a random number between 0 and 26
        char firstSmallLetter = 'a';
        int numberOfLetters = 26;

        //for choosing a random capital letter we add 'A' a random number between 0 and 26
        char firstCapitalLetter = 'A';

        //choosing random special character
        char[] specialChars = {'!', '@', '#', '$', '%', '^', '&', '*'};
        int numberOfSpecialChars = 8;

        //creating an arraylist of password chars
        ArrayList<Character> password = new ArrayList<>();
        // a random object for creating random numbers
        Random random = new Random();

        //choosing random small letters
        for (int i = 0; i < 3; i++) {
            char randomSmallChar = (char) (firstSmallLetter + random.nextInt(0 , numberOfLetters));
            password.add(randomSmallChar);
        }

        //choosing random capital letters
        for (int i = 0; i < 3; i++) {
            char randomCapitalChar = (char) (firstCapitalLetter + random.nextInt(0 , numberOfLetters));
            password.add(randomCapitalChar);
        }

        //choosing random special chars
        for (int i = 0; i < 3; i++) {
            char randomSpecialChar = (char) specialChars[random.nextInt(0 , numberOfSpecialChars - 1)];
            password.add(randomSpecialChar);
        }

        //choosing random number
        for (int i = 0; i < 3; i++) {
            //48 is 0 in ASCII
            char randomSmallChar = (char) (48 + random.nextInt(0 , 9));
            password.add(randomSmallChar);
        }

        StringBuilder validPassword = new StringBuilder();

        //building password by adding to string builder
        for (int i = 0; i < 12; i++) {
            addingCharToPassword(validPassword, password);
        }
        return validPassword.toString();
    }
    private static void addingCharToPassword(StringBuilder validPassword, ArrayList<Character> password) {
        Random random = new Random();
        //choose random place for putting in string builder
        int randomPlace = random.nextInt(0, password.size());
        validPassword.append(password.get(randomPlace));
        //removing the selected char
        password.remove(randomPlace);
    }



    // login
    public static Result login(String username, String password) {
        //check if username is valid and available
        if (!isUsernameAlreadyUsed(username))
            return new Result(false, "Username not found!");

        //check if username and password matches
        User user = User.getUserByUsername(username);

        if (!user.getPassword().equals(password))
            return new Result(false, "Password doesn't match!");
        //login successful
        User.setLoggedInUser(user);
        return new Result(true, "Login successfully.");
    }
    // use the  <isUsernameAlreadyUsed> for check existence of username

    // forget password
    private static Result forgetPassword(String username, String answer) {  // todo : اول چک کن ببین یوزر وجود داره و اینکه کاری کن بتونم بزنم اول باید برات بفرستم  که چک
        //get user by username
        User user = User.getUserByUsername(username);

        //check if answer matches
        if (answer.equals(user.getAnswer()))
            return new Result(true, "answer matches!");

        return new Result(false, "answer doesn't match.");

    }
    // check existence of username by <isUsernameAlreadyUsed>
    private static boolean isAnswerCorrect(String username, String answer) {return true;}
}
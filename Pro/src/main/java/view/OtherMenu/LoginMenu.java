package view.OtherMenu;

import controller.loginController.LoginMenuController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import model.User;
import model.enums.loginMenu.ConfirmQuestions;
import model.enums.loginMenu.LoginMenuCommands;
import model.toolClasses.Result;
import view.HeadViewController;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
/** @author Foad
 * methods:
 *      private ArrayList<String> savedData = new ArrayList<>();
 *      private String usernameForForget;
 *      public void checkCommand(KeyEvent keyEvent)
 *      private Result setPassword(Matcher matcher)
 *      private Result answerQ(Matcher matcher)
 *      private Result forgetPasswordCommand(Matcher matcher)
 *
 *
 * */
public class LoginMenu {
    public AnchorPane terminalPane;
    public TextArea terminalTextArea;
    private boolean isTerminalVisible = false;

    public void showTerminal(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ESCAPE)) {
            terminalPane.setVisible(isTerminalVisible = !isTerminalVisible);
        }
    }

    private ArrayList<String> savedData = new ArrayList<>();
    private String usernameForForget;

    public void checkCommand(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            String[] inputLines = terminalTextArea.getText().split("\n");
            String inputLine = inputLines[inputLines.length - 1];

            // TODO : some if else for this menu (start menu) see the code44
            Matcher matcher;
            if ((matcher = LoginMenuCommands.register.getMatcher(inputLine)) != null) {
                Result message = registerCommand(matcher);
                //chop the message returned and chop the question for the user
                //in the next step the user must choose one of these questions for next command
                terminalTextArea.setText(terminalTextArea.getText() + message + "Pick your confirm question from below options\n" +
                        ConfirmQuestions.q1.getQuestion() + "\n" +
                        ConfirmQuestions.q2.getQuestion() + "\n" +
                        ConfirmQuestions.q3.getQuestion() + "\n" +
                        ConfirmQuestions.q4.getQuestion() + "\n" +
                        ConfirmQuestions.q5.getQuestion() + "\n");
                terminalTextArea.positionCaret(terminalTextArea.getText().length());
            } else if ((matcher = LoginMenuCommands.login.getMatcher(inputLine)) != null) {
                Result message = loginCommand(matcher);
                //chop the message returned in terminal
                terminalTextArea.setText(terminalTextArea.getText() + message + "\n");
                terminalTextArea.positionCaret(terminalTextArea.getText().length());
            } else if ((matcher = LoginMenuCommands.pickQuestion.getMatcher(inputLine)) != null) {
                Result message = pickQuestion(matcher);
                //chop the message returned in terminal
                terminalTextArea.setText(terminalTextArea.getText() + message + "\n");
                terminalTextArea.positionCaret(terminalTextArea.getText().length());
            } else if ((matcher = LoginMenuCommands.forgetPassword.getMatcher(inputLine)) != null) {
                Result message = forgetPasswordCommand(matcher);
                //chop the message returned in terminal
                terminalTextArea.setText(terminalTextArea.getText() + message + "\n");
                terminalTextArea.positionCaret(terminalTextArea.getText().length());
            } else if ((matcher = LoginMenuCommands.answerQ.getMatcher(inputLine)) != null){
                Result message = answerQ(matcher);
                terminalTextArea.setText(terminalTextArea.getText() + message + "\n");
                terminalTextArea.positionCaret(terminalTextArea.getText().length());
            } else if ((matcher = LoginMenuCommands.setPassword.getMatcher(inputLine)) != null) {
                Result message = setPassword(matcher);
                //chop the message returned
                terminalTextArea.setText(terminalTextArea.getText() + message + "\n");
                terminalTextArea.positionCaret(terminalTextArea.getText().length());
            } else if ((matcher = LoginMenuCommands.enterMainMenu.getMatcher(inputLine)) != null){
                MainMenu.run(new Scanner(System.in));
            } else {
                terminalTextArea.setText(terminalTextArea.getText() + "Invalid Command" + "\n");
                terminalTextArea.positionCaret(terminalTextArea.getText().length());
            }
        }
    }
    private Result setPassword(Matcher matcher) {
        User user = User.getUserByUsername(usernameForForget);
        user.setPassword(matcher.group("password"));
        return new Result(true, "Password changed successfully!");
    }

    private Result answerQ(Matcher matcher) {
        String answer = matcher.group("answer");
        User user = User.getUserByUsername(usernameForForget);
        if (!user.getAnswer().equals(answer)){
            return new Result(false, "Wrong answer!");
        }
        return new Result(false, "now change your password!");
    }

    private Result forgetPasswordCommand(Matcher matcher) {
        usernameForForget = matcher.group("username");
        User user;
        if ((user = User.getUserByUsername(usernameForForget)) == null) return new Result(false, "Username not found!");
        return new Result(true, "answer your saved question\n" +
                user.getConfirmQuestions().getQuestion() + "\n");
    }

    private Result loginCommand(Matcher matcher) {
        String username = matcher.group("username");
        String password = matcher.group("password");

        return LoginMenuController.login(username, password);
    }

    private Result pickQuestion(Matcher matcher) {
        String questionNum = matcher.group("questionnum");
        String answer = matcher.group("answer");
        String answerConfirm = matcher.group("answerconfirm");
        ConfirmQuestions confirmQuestions = null;

        //set confirmQuestion
        switch (Integer.parseInt(questionNum)) {
            case 1:
                confirmQuestions = ConfirmQuestions.q1;
                break;
            case 2:
                confirmQuestions = ConfirmQuestions.q2;
                break;
            case 3:
                confirmQuestions = ConfirmQuestions.q3;
                break;
            case 4:
                confirmQuestions = ConfirmQuestions.q4;
                break;
            case 5:
                confirmQuestions = ConfirmQuestions.q5;
                break;
        }
        if (!answerConfirm.equals(answer)) {
            return new Result(false, "confirm questions Doesn't match.");
        }
        // add new user
        Result result = LoginMenuController.registerNewUser(confirmQuestions, savedData.get(0), savedData.get(1),
                savedData.get(2), savedData.get(3), answer);
        return result;
    }

    private Result registerCommand(Matcher matcher) {
        // getting some detail to seperated strings to register
        String username = matcher.group("username");
        String password = matcher.group("password");
        String passwordConfirm = matcher.group("passwordconfirm");
        String nickname = matcher.group("nickname");
        String email = matcher.group("email");
        //save data for next command
        savedData.add(username);
        savedData.add(password);
        savedData.add(nickname);
        savedData.add(email);
        //return result
        Result result = LoginMenuController.checkAllErrors(username, password, passwordConfirm, nickname, email);
        return result;
    }

    // declare some fields for fxml file
    public TextField usernameTextField;
    public PasswordField passwordField;
    public TextField nicknameTextField;
    public AnchorPane passwordPane;
    public TextField emailTextField;
    public AnchorPane questionPane;
    public Label errorLabel;

    public void goToRegisterPage() {
        HeadViewController.changeScene("register page");
    }

    public void backToLoginPage() {
        HeadViewController.changeScene("login page");
    }

    // login
    public void login() {
        // get result
        Result result = LoginMenuController.login(usernameTextField.getText(), passwordField.getText());

        // check error
        if (!result.isSuccessful()) {
            errorLabel.setText(result.getMessage());

            // use Timeline to delete massage after 2 seconds
            Timeline timeline = new Timeline();
            timeline.setCycleCount(1);
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(2), actionEvent -> errorLabel.setText(""));
            timeline.getKeyFrames().add(keyFrame);
            timeline.play();
            return;
        }

        // change scene to mane scene
        HeadViewController.changeScene("main page");


    }

    public void forgetPassword() {
        // todo:
    }

    // register method
    public void register() {
        // declare a var to get access of user some variable in lambda
        var ref = new Object() {
            Result result;
            String password, confirmPassword;
        };
        String username, email, nickname;

        // get username, nickname ,and email
        username = usernameTextField.getText();
        nickname = nicknameTextField.getText();
        email = emailTextField.getText();

        // show password pane
        passwordPane.setVisible(true);


        ((Button) passwordPane.getChildren().get(4)).setOnMouseClicked(event -> {
            // set visible false for passwordPane
            passwordPane.setVisible(false);

            // get password ,and confirm password
            ref.password = ((TextField) passwordPane.getChildren().get(0)).getText();
            ref.confirmPassword = ((TextField) passwordPane.getChildren().get(1)).getText();

            ref.result = LoginMenuController.checkAllErrors
                    (username, ref.password, ref.confirmPassword, nickname, email);


            if (!ref.result.isSuccessful()) {
                // show error in a label with color RED
                errorLabel.setText(ref.result.getMessage());

                // use Timeline to delete massage after 2 seconds
                Timeline timeline = new Timeline();
                timeline.setCycleCount(1);
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(2), actionEvent -> errorLabel.setText(""));
                timeline.getKeyFrames().add(keyFrame);
                timeline.play();

                // clear textFields and passwordFields
                ((TextField) passwordPane.getChildren().get(0)).setText("");
                ((TextField) passwordPane.getChildren().get(1)).setText("");
                usernameTextField.setText("");
                nicknameTextField.setText("");
                emailTextField.setText("");

                return;
            }

            // show question page
            questionPane.setVisible(true);

            var ref2 = new Object() {
                ConfirmQuestions confirmQuestions;
                String answer;
            };

            // for all buttons that I declare in fxml we must set text -> <question> and setOnMouthClick for all
            for (int i = 1; i <= 5; i++) {
                // get button no.i
                Button button = ((Button) questionPane.getChildren().get(i));
                // set text
                button.setText(ConfirmQuestions.valueOf("q" + i).getQuestion());

                // declare x to use that in lambda
                int x = i;
                button.setOnMouseClicked(event2 -> {
                    // get ConfirmQuestion
                    ref2.confirmQuestions = ConfirmQuestions.valueOf("q" + x);
                    AnchorPane pane = (AnchorPane) questionPane.getChildren().get(6);
                    // show textArea
                    pane.setVisible(true);
                    TextArea textArea = (TextArea) pane.getChildren().get(0);
                    // set prompt text of textArea --> question
                    textArea.setPromptText(ConfirmQuestions.valueOf("q" + x).getQuestion());
                    pane.getChildren().get(1).setOnMouseClicked(event3 -> {
                        // set questionPane invisible
                        questionPane.setVisible(false);
                        ref2.answer = textArea.getText();

                        // clear textFields and passwordFields
                        ((TextField) passwordPane.getChildren().get(0)).setText("");
                        ((TextField) passwordPane.getChildren().get(1)).setText("");
                        usernameTextField.setText("");
                        nicknameTextField.setText("");
                        emailTextField.setText("");
                        textArea.setText("");
                    });
                });
            }

            // now we sure that register can successful, so we call method registerNewUser in LoginMEnuController
            LoginMenuController.registerNewUser
                    (ref2.confirmQuestions, username, ref.password, nickname, email, ref2.answer);
        });
    }

    public void mute() {
        HeadViewController.player.setMute(HeadViewController.isMute = !HeadViewController.isMute);
    }

}
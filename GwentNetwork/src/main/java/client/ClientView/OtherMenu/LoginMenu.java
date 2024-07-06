package client.ClientView.OtherMenu;


import client.ClientView.HeadViewController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import message.client.LoginMenu.*;
import message.enums.loginMenu.ConfirmQuestions;
import message.enums.loginMenu.LoginMenuCommands;
import message.server.ServerMessage;

import java.util.regex.Matcher;

import static client.ClientView.HeadViewController.clientTPC;

/**
 * @author iliya
 *
 * this class connect to logic in server
 * <p>
 *
 * changes:
 * remove some methods and change place to loginMenuController
 *
 * and change all of calls methods in other classes and replace with clientTCP.send and .receive
 */
public class LoginMenu {
    // terminal part
    public AnchorPane terminalPane;
    public TextArea terminalTextArea;
    private boolean isTerminalVisible = false;
    private String usernameForForget;

    private RegisterMassage saveRegister;

    public void showTerminal(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ESCAPE)) {
            terminalPane.setVisible(isTerminalVisible = !isTerminalVisible);
        }
    }

    public void checkCommand(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            String[] inputLines = terminalTextArea.getText().split("\n");
            String inputLine = inputLines[inputLines.length - 1];

            terminalTextArea.setText(terminalTextArea.getText() + "\n" + "-------------------------------------------" +
                    "-----------------------------------------" + "\n");

            Matcher matcher;
            if ((matcher = LoginMenuCommands.register.getMatcher(inputLine)) != null) {
                ServerMessage message = registerCommand(matcher);
                //chop the message returned and chop the question for the user
                //in the next step the user must choose one of these questions for next command
                terminalTextArea.setText(terminalTextArea.getText() + message.getInfo() + "Pick your confirm question from below options\n" +
                        ConfirmQuestions.q1.getQuestion() + "\n" +
                        ConfirmQuestions.q2.getQuestion() + "\n" +
                        ConfirmQuestions.q3.getQuestion() + "\n" +
                        ConfirmQuestions.q4.getQuestion() + "\n" +
                        ConfirmQuestions.q5.getQuestion() + "\n");
            }
            else if ((matcher = LoginMenuCommands.login.getMatcher(inputLine)) != null) {
                ServerMessage message = loginCommand(matcher);

                clientTPC.token = message.getToken();
                //chop the message returned in terminal
                terminalTextArea.setText(terminalTextArea.getText() + message + "\n");
            }
            else if ((matcher = LoginMenuCommands.pickQuestion.getMatcher(inputLine)) != null) {
                ServerMessage message = pickQuestion(matcher);
                //chop the message returned in terminal
                terminalTextArea.setText(terminalTextArea.getText() + message.getInfo() + "\n");
            }
            else if ((matcher = LoginMenuCommands.forgetPassword.getMatcher(inputLine)) != null) {
                clientTPC.sendMassage(clientTPC.gson.toJson(new ForgetPasswordMessage(matcher)));
                ServerMessage message = clientTPC.receiveMassage();

                usernameForForget = matcher.group("username");
                //chop the message returned in terminal
                terminalTextArea.setText(terminalTextArea.getText() + message.getInfo() + "\n");
            }
            else if ((matcher = LoginMenuCommands.answerQ.getMatcher(inputLine)) != null) {
                clientTPC.sendMassage(clientTPC.gson.toJson(new AnswerQMessage(matcher, usernameForForget)));

                ServerMessage message = clientTPC.receiveMassage();

                terminalTextArea.setText(terminalTextArea.getText() + message.getInfo() + "\n");
            }
            else if ((matcher = LoginMenuCommands.setPassword.getMatcher(inputLine)) != null) {
                clientTPC.sendMassage(clientTPC.gson.toJson(new SetNewPasswordMessage(matcher, usernameForForget)));

                ServerMessage message = clientTPC.receiveMassage();

                //chop the message returned
                terminalTextArea.setText(terminalTextArea.getText() + message.getInfo() + "\n");
            } else {
                terminalTextArea.setText(terminalTextArea.getText() + "Invalid Command" + "\n");
            }

            terminalTextArea.setText(terminalTextArea.getText() + "-------------------------------------------" +
                    "-----------------------------------------" + "\n");
            terminalTextArea.positionCaret(terminalTextArea.getText().length());
        }
    }
    private ServerMessage loginCommand(Matcher matcher) {
        String username = matcher.group("username");
        String password = matcher.group("password");

        clientTPC.sendMassage(clientTPC.gson.toJson(new LoginMessage(username, password)));

        return clientTPC.receiveMassage();
    }

    private ServerMessage pickQuestion(Matcher matcher) {
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
            return new ServerMessage(false, "confirm questions Doesn't match.");
        }

        // add new user
        clientTPC.sendMassage(clientTPC.gson.toJson(new PickQuestionMessage(confirmQuestions, saveRegister, answer)));

        return clientTPC.receiveMassage();
    }

    private ServerMessage registerCommand(Matcher matcher) {
        // getting some detail to seperated strings to register
        String username = matcher.group("username");
        String password = matcher.group("password");
        String passwordConfirm = matcher.group("passwordconfirm");
        String nickname = matcher.group("nickname");
        String email = matcher.group("email");

        //save data for next command TODO ILIYA: password confirm
        RegisterMassage registerMassage = new RegisterMassage(username, password, passwordConfirm, nickname, email);

        // call backend and wait for response
        clientTPC.sendMassage(
                clientTPC.gson.toJson(registerMassage)
        );

        return clientTPC.receiveMassage();
    }


//     graphic part
//     declare some fields for fxml file
    public TextField usernameTextField;
    public PasswordField passwordField;
    public TextField nicknameTextField;
    public AnchorPane passwordPane;
    public TextField emailTextField;
    public AnchorPane questionPane;
    public Label errorLabel;
    public AnchorPane forgetPane;

    public void goToRegisterPage() {
        HeadViewController.changeScene("register page");
    }

    public void backToLoginPage() {
        HeadViewController.changeScene("login page");
    }

    // login
    public void login() {
        // get result
        clientTPC.sendMassage(
                clientTPC.gson.toJson(new LoginMessage(usernameTextField.getText(), passwordField.getText()))
        );

        ServerMessage message = clientTPC.receiveMassage();

        // set token
        clientTPC.token = message.getToken();

        // check error
        if (!message.isSuccess()) {
            errorLabel.setText(message.getInfo());

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
        forgetPane.setVisible(true);

        Button button = (Button) forgetPane.getChildren().get(0);
        TextField textField = (TextField) forgetPane.getChildren().get(1);

        button.setOnAction(event -> {
            clientTPC.sendMassage(clientTPC.gson.toJson(new ForgetPasswordMessage(
                    LoginMenuCommands.forgetPassword.getMatcher("forget password -u " + textField.getText()))));

            ServerMessage message = clientTPC.receiveMassage();

            if (message.isSuccess()) {
                forgetPane.getChildren().get(3).setVisible(true);
                forgetPane.getChildren().get(4).setVisible(true);

                button.setOnAction(event1 -> {
                    clientTPC.sendMassage(clientTPC.gson.toJson(new AnswerQMessage(
                        LoginMenuCommands.answerQ.getMatcher("answer -a " +
                        ((TextField) forgetPane.getChildren().get(3)).getText()), textField.getText())));

                    ServerMessage message1 = clientTPC.receiveMassage();

                    if (message1.isSuccess()) {
                        PasswordField passwordField;

                        passwordField = (PasswordField) forgetPane.getChildren().get(5);

                        passwordField.setVisible(true);

                        button.setOnAction(event2 -> {
                            clientTPC.sendMassage(clientTPC.gson.toJson(new SetNewPasswordMessage(
                                    LoginMenuCommands.setPassword.getMatcher("set password -p " +
                                    passwordField.getText()), textField.getText())));

                            ServerMessage message2 = clientTPC.receiveMassage();

                            if (message2.isSuccess()) forgetPane.setVisible(false);
                        });
                    }
                });
            }
        });
    }

    // register method
    public void register() {
        // declare a var to get access of user some variable in lambda
        var ref = new Object() {
            ServerMessage message;
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

            RegisterMassage registerMassage;
            clientTPC.sendMassage(clientTPC.gson.toJson(
                   registerMassage = new RegisterMassage(username, ref.password, ref.confirmPassword, nickname, email)));

            ref.message = clientTPC.receiveMassage();

            if (!ref.message.isSuccess()) {
                // show error in a label with color RED
                errorLabel.setText(ref.message.getInfo());

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
            clientTPC.sendMassage(clientTPC.gson.toJson(new PickQuestionMessage(
                    ref2.confirmQuestions, registerMassage, ref2.answer)));

            clientTPC.receiveMassage();
        });
    }

    public void mute() {
        HeadViewController.player.setMute(HeadViewController.isMute = !HeadViewController.isMute);
    }

}
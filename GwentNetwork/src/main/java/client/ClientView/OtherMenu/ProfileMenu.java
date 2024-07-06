package client.ClientView.OtherMenu;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import message.enums.profileMenu.ProfileMenuCommands;

import java.util.regex.Matcher;

public class ProfileMenu {

    public AnchorPane terminalPane;
    public TextArea terminalTextArea;
    private boolean isTerminalVisible = false;
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
            // some if else
            if ((matcher = ProfileMenuCommands.changeUsername.getMatcher(inputLine)).find()) {
                String newUsername = matcher.group("username");

                Result result = ProfileMenuController.changeUserName(newUsername);

                terminalTextArea.setText(terminalTextArea.getText() + result.getMessage() + "\n");
            }
            else if ((matcher = ProfileMenuCommands.changeNickname.getMatcher(inputLine)).find()) {
                String newNickname = matcher.group("nickname");

                Result result = ProfileMenuController.changeUserName(newNickname);

                terminalTextArea.setText(terminalTextArea.getText() + result.getMessage() + "\n");
            }
            else if ((matcher = ProfileMenuCommands.changeEmail.getMatcher(inputLine)).find()) {
                String newEmail = matcher.group("email");

                Result result = ProfileMenuController.changeUserName(newEmail);

                terminalTextArea.setText(terminalTextArea.getText() + result.getMessage() + "\n");
            }
            else if ((matcher = ProfileMenuCommands.changePassword.getMatcher(inputLine)).find()) {
                String newPassword = matcher.group("newPassword");
                String oldPassword = matcher.group("oldPassword");

                Result result = ProfileMenuController.changePassword(newPassword, oldPassword);

                terminalTextArea.setText(terminalTextArea.getText() + result.getMessage() + "\n");
            }
            else if ((matcher = ProfileMenuCommands.enterOtherMenu.getMatcher(inputLine)).find()) {
                if (matcher.group().equals("user info")) {
                    showUserInfo();
                } else {
                    terminalTextArea.setText(terminalTextArea.getText() + "Invalid Menu Name" + "\n");
                }
            }
            else terminalTextArea.setText(terminalTextArea.getText() + "Invalid Command" + "\n");


            terminalTextArea.setText(terminalTextArea.getText() + "-------------------------------------------" +
                    "-----------------------------------------" + "\n");
            terminalTextArea.positionCaret(terminalTextArea.getText().length());
        }
    }


    // graphic part
    public AnchorPane buttonPane;
    public AnchorPane editPane;
    public void editProfile() {
        // set editPane visible and set button pane invisible
        editPane.setVisible(true);
        buttonPane.setVisible(false);

        // get buttons --> change username, password, and e.g.
        Button changeUsername = (Button) editPane.getChildren().get(2);
        Button changeNickname = (Button) editPane.getChildren().get(3);
        Button changeEmail = (Button) editPane.getChildren().get(4);
        Button changePassword = (Button) editPane.getChildren().get(5);

        // set on action for each of the button
        // --> change username
        changeUsername.setOnAction( actionEvent -> {
            setActionForOKButton("CHANGE USERNAME", "new username");
        });

        // --> change nickName
        changeNickname.setOnAction(actionEvent -> {
            setActionForOKButton("CHANGE NICKNAME", "new nickname");
        });

        // --> change email
        changeEmail.setOnAction(actionEvent -> {
            setActionForOKButton("CHANGE email", "new email");
        });
        changePassword.setOnAction(actionEvent -> {
            // get pane
            AnchorPane pane = (AnchorPane) editPane.getChildren().get(1);
            // set it visible
            pane.setVisible(true);

            // find obj on it
            TextField newPassword = (TextField) pane.getChildren().get(1);
            TextField confirmPassword = (TextField) pane.getChildren().get(2);
            Button ok = (Button) pane.getChildren().get(3);
            Label errorLabel = (Label) pane.getChildren().get(4);

            ok.setOnAction(event -> {
                String newPassWord = newPassword.getText();
                String confirmPassWord = confirmPassword.getText();

                Result result = ProfileMenuController.changePassword(newPassWord, confirmPassWord);

                if (result.isSuccessful()) {
                    newPassword.setText("");
                    confirmPassword.setText("");
                    pane.setVisible(false);
                } else {
                    errorLabel.setText(result.getMessage());

                    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event1 -> errorLabel.setText("")));
                    timeline.setCycleCount(1);
                    timeline.play();
                }
            });

        });

    }

    private void setActionForOKButton(String labelTXT, String textFieldPRMT) {
        AnchorPane pane = (AnchorPane) editPane.getChildren().get(0);
        TextField textField = (TextField) pane.getChildren().get(1);
        Button ok = (Button) pane.getChildren().get(2);
        Label errorLabel = (Label) pane.getChildren().get(3);

        // set anchorPane visible
        pane.setVisible(true);

        // set label text and textField prompt text
        ((Label) pane.getChildren().get(0)).setText(labelTXT);
        textField.setPromptText(textFieldPRMT);

        ok.setOnAction( event -> {
            String str = textField.getText();

            // find result
            Result result;

            if (labelTXT.equals("CHANGE USERNAME"))  result = ProfileMenuController.changeUserName(str);
            if (labelTXT.equals("CHANGE NICKNAME")) result = ProfileMenuController.changeNickName(str);
            else result = ProfileMenuController.changeEmail(str);

            if (result.isSuccessful()) {
                textField.setText("");
                pane.setVisible(false);
            } else {
                errorLabel.setText(result.getMessage());

                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event1 -> errorLabel.setText("")));
                timeline.setCycleCount(1);
                timeline.play();
            }
        });
    }

    public void showUserInfo() {
        // todo: IF
    }

    public void mute() {
        HeadViewController.player.setMute(HeadViewController.isMute = !HeadViewController.isMute);
    }
    public void back() {
        // back to main page
        HeadViewController.changeScene("main page");
    }
}
package client.ClientView.OtherMenu;

import client.ClientView.HeadViewController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import message.client.profileMenu.*;
import message.enums.profileMenu.ProfileMenuCommands;
import message.server.ServerMessage;
import message.server.UpdateFriendRequestMessage;

import java.util.ArrayList;
import java.util.regex.Matcher;

import static client.ClientView.HeadViewController.clientTPC;

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

                clientTPC.sendMassage(clientTPC.gson.toJson(new ChangeUsernameMessage(newUsername, clientTPC.token)));

                ServerMessage message = clientTPC.receiveMassage();

                terminalTextArea.setText(terminalTextArea.getText() + message.getInfo() + "\n");
            }
            else if ((matcher = ProfileMenuCommands.changeNickname.getMatcher(inputLine)).find()) {
                String newNickname = matcher.group("nickname");

                clientTPC.sendMassage(clientTPC.gson.toJson(new ChangeNicknameMessage(newNickname, clientTPC.token)));

                ServerMessage message = clientTPC.receiveMassage();

                terminalTextArea.setText(terminalTextArea.getText() + message.getInfo() + "\n");
            }
            else if ((matcher = ProfileMenuCommands.changeEmail.getMatcher(inputLine)).find()) {
                String newEmail = matcher.group("email");

                clientTPC.sendMassage(clientTPC.gson.toJson(new ChangeEmailMessage(newEmail, clientTPC.token)));

                ServerMessage message = clientTPC.receiveMassage();

                terminalTextArea.setText(terminalTextArea.getText() + message.getInfo() + "\n");
            }
            else if ((matcher = ProfileMenuCommands.changePassword.getMatcher(inputLine)).find()) {
                String newPassword = matcher.group("newPassword");
                String oldPassword = matcher.group("oldPassword");

                clientTPC.sendMassage(
                        clientTPC.gson.toJson(new ChangePasswordMessage(newPassword, oldPassword, clientTPC.token))
                );

                ServerMessage message = clientTPC.receiveMassage();

                terminalTextArea.setText(terminalTextArea.getText() + message.getInfo() + "\n");
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
    public AnchorPane friendPane;
    public ScrollPane friendScrollPane;
    public ScrollPane friendRequests;
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

                clientTPC.sendMassage(
                        clientTPC.gson.toJson(new ChangePasswordMessage(newPassWord, confirmPassWord, clientTPC.token))
                );

                ServerMessage message = clientTPC.receiveMassage();

                if (message.isSuccess()) {
                    newPassword.setText("");
                    confirmPassword.setText("");
                    pane.setVisible(false);
                } else {
                    errorLabel.setText(message.getInfo());

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
            ServerMessage message;

            if (labelTXT.equals("CHANGE USERNAME"))  {
                clientTPC.sendMassage(clientTPC.gson.toJson(new ChangeUsernameMessage(str, clientTPC.token)));

                message = clientTPC.receiveMassage();
            }
            if (labelTXT.equals("CHANGE NICKNAME")) {
                clientTPC.sendMassage(clientTPC.gson.toJson(new ChangeNicknameMessage(str, clientTPC.token)));

                message = clientTPC.receiveMassage();
            }
            else {
                clientTPC.sendMassage(clientTPC.gson.toJson(new ChangeEmailMessage(str, clientTPC.token)));

                message = clientTPC.receiveMassage();
            }

            if (message.isSuccess()) {
                textField.setText("");
                pane.setVisible(false);
            } else {
                errorLabel.setText(message.getInfo());

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

    public void showFriendRequest() {
        friendPane.setVisible(true);

        updateFriend();
    }

    private void updateFriend() {
        clientTPC.sendMassage(clientTPC.gson.toJson(new GiveFriendMessage(clientTPC.token)));
        System.out.println(clientTPC.receiveMassage().getClass());
        UpdateFriendRequestMessage message = (UpdateFriendRequestMessage) clientTPC.receiveMassage();

        ArrayList<String> friends = message.getFriends();
        ArrayList<String> requests = message.getFromWho();

        updateScroll(friends, friendScrollPane);
        updateScroll(requests, friendRequests);
    }

    private void updateScroll(ArrayList<String> list, ScrollPane pane) {
        GridPane gridPane = new GridPane();

        pane.setContent(gridPane);

        for (int i = 0; i < list.size(); i++) {
            Circle node = new Circle();
            node.setFill(new ImagePattern(
                    new Image(getClass().getResource("/asset/img/icons/profile.png").toExternalForm())));

            gridPane.add(node, 0, i);
            gridPane.add(new Label(list.get(i)), 1, i);

            Button button = new Button("Accept");

            gridPane.add(button, 2, i);
        }
    }
    public void backFromFriendPage() {
        friendPane.setVisible(false);
    }
}
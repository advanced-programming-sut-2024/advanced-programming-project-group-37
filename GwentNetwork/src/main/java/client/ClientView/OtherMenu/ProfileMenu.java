package client.ClientView.OtherMenu;

import client.ClientView.HeadViewController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import message.client.profileMenu.*;
import message.enums.profileMenu.ProfileMenuCommands;
import message.server.ServerMessage;

import java.util.ArrayList;
import java.util.regex.Matcher;

import static client.ClientView.HeadViewController.clientTPC;
import static client.ClientView.HeadViewController.isMute;

public class ProfileMenu {

    public TextArea terminalTextArea;
    public AnchorPane terminalPane;
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
    public AnchorPane requestPane;
    public ScrollPane searchScrollPane;
    public TextField searchTextField;
    public AnchorPane searchWindow;
    public AnchorPane searchPane;
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

    public void mute(ActionEvent actionEvent) {
        HeadViewController.player.setMute(HeadViewController.isMute = !HeadViewController.isMute);

        Button button = (Button) actionEvent.getSource();

        if (isMute) button.setText("UNMUTE");
        else button.setText("MUTE");
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

        ServerMessage message = clientTPC.receiveMassage();

        ArrayList<String> friends = message.getFriends();
        ArrayList<String> requests = message.getFromWho();

        updateScrollForFriend(friends, message.getState(), friendScrollPane);
        updateScrollForRequests(requests, message.getDate(), friendRequests);
    }
    private void updateScrollForFriend(ArrayList<String> users, ArrayList<String> state, ScrollPane pane) {
        GridPane gridPane = new GridPane();

        pane.setContent(gridPane);
        gridPane.setHgap(170);
        gridPane.setVgap(20);

        pane.setFitToWidth(true);
        pane.setFitToHeight(true);

        for (int i = 0; i < users.size(); i++) {
            Button button = new Button(state.get(i));
            if (state.get(i).equals("online")) button.setStyle("-fx-background-color: #4fb90e");
            else button.setStyle("-fx-background-color: gray");

            gridPane.add(button, 0, i);

            gridPane.add(new Label(users.get(i)), 1, i);

            Circle node = new Circle(15);
            node.setFill(new ImagePattern(
                    new Image(getClass().getResource("/asset/img/icons/profile.png").toExternalForm())));

            gridPane.add(node, 2, i);
        }
    }
    private void updateScrollForRequests(ArrayList<String> users, ArrayList<String> dates, ScrollPane pane) {
        GridPane gridPane = new GridPane();

        pane.setContent(gridPane);
        gridPane.setHgap(50);
        gridPane.setVgap(20);

        pane.setFitToWidth(true);
        pane.setFitToHeight(true);

        for (int i = 0; i < users.size(); i++) {

            Button button = new Button("Accept");

            gridPane.add(button, 0, i);

            int finalI = i;
            button.setOnAction(event -> acceptRequest(users.get(finalI)));

            Button button2 = new Button("Reject");

            gridPane.add(button, 1, i);

            button2.setOnAction(event -> rejectRequest(users.get(finalI)));

            gridPane.add(new Label(dates.get(i)), 2, i);

            gridPane.add(new Label(users.get(i)), 3, i);

            Circle node = new Circle(15);
            node.setFill(new ImagePattern(
                    new Image(getClass().getResource("/asset/img/icons/profile.png").toExternalForm())));

            gridPane.add(node, 3, i);
        }
    }
    private void updateScrollPaneForSearch(ArrayList<String> users, ScrollPane pane) {
        GridPane gridPane = new GridPane();

        pane.setContent(gridPane);
        gridPane.setHgap(170);
        gridPane.setVgap(20);

        pane.setFitToWidth(true);
        pane.setFitToHeight(true);

        for (int i = 0; i < users.size(); i++) {

            Button button = new Button("request");

            gridPane.add(button, 0, i);

            int finalI = i;
            button.setOnAction(event -> addFriend(users.get(finalI)));
            button.setStyle("-fx-background-color: white");

            gridPane.add(new Label(users.get(i)), 1, i);

            Circle node = new Circle(15);
            node.setFill(new ImagePattern(
                    new Image(getClass().getResource("/asset/img/icons/profile.png").toExternalForm())));

            gridPane.add(node, 2, i);
        }


    }

    private void addFriend(String username) {
        clientTPC.sendMassage(clientTPC.gson.toJson(new SendRequest(clientTPC.token, username)));

        clientTPC.receiveMassage();
    }

    private void rejectRequest(String username) {
        clientTPC.sendMassage(clientTPC.gson.toJson(new AcceptRequest(clientTPC.token, username)));

        clientTPC.receiveMassage();
        updateFriend();
    }
    private void acceptRequest(String username) {
        clientTPC.sendMassage(clientTPC.gson.toJson(new AcceptRequest(clientTPC.token, username)));

        clientTPC.receiveMassage();
        updateFriend();
    }
    public void backFromFriendPage() {
        friendPane.setVisible(false);
    }

    public void showRequest() {
        requestPane.setVisible(true);
    }

    public void showSearch() {
        searchPane.setVisible(true);
        searchWindow.setVisible(true);
    }

    public void search() {
        searchWindow.setVisible(false);

        String username = searchTextField.getText();

        clientTPC.sendMassage(clientTPC.gson.toJson(new SearchMessage(username)));

        ServerMessage message = clientTPC.receiveMassage();

        ArrayList<String> users = message.getFriends();

        for (String user : users) {
            System.out.println(user);
        }

        updateScrollPaneForSearch(users, searchScrollPane);
    }

    public void closeSearchPane() {
        searchPane.setVisible(false);
    }
}
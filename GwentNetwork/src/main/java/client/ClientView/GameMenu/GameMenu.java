package client.ClientView.GameMenu;
import client.ClientView.HeadViewController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import message.client.Game.*;
import message.client.MessageType;
import message.client.gameLobby.CheckServerMessage;
import message.client.gameLobby.SendMessageFromTvToPlayers;
import message.enums.card.Card;
import message.enums.card.CardType;
import message.enums.card.Leaders;
import message.enums.gameMenu.Shields;
import message.server.ServerMessage;
import message.server.ServerType;
import server.model.toolClasses.Pair;
import server.model.toolClasses.Result;

import java.time.LocalTime;
import java.util.ArrayList;

import static client.ClientView.HeadViewController.clientTPC;

public class GameMenu {
    // check server
    private static Timeline timeline;
    public AnchorPane terminalPane;
    public TextArea terminalTextArea;
    public TextField terminalTextField;
    private boolean isTerminalVisible = false;
    public void showTerminal() {
        terminalPane.setVisible(isTerminalVisible = !isTerminalVisible);
    }
    public void checkCommand(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            String m = terminalTextField.getText();

            terminalTextArea.setText(terminalTextArea.getText() + "\n" + "-------------------------------------------" +
                    "-----------------------------------------" + "\n");

            terminalTextArea.setText(terminalTextArea.getText() + "me: " + m + "\n");

            terminalTextArea.setText(terminalTextArea.getText() + "-------------------------------------------" +
                    "-----------------------------------------" + "\n");
            terminalTextArea.positionCaret(terminalTextArea.getText().length());

            clientTPC.sendMassage(clientTPC.gson.toJson(new SendMessageFromGame(clientTPC.token, m)));
            clientTPC.receiveMassage();
        }
    }
    private void updateTextArea(String message) {
        terminalTextArea.setText(terminalTextArea.getText() + "\n" + "-------------------------------------------" +
                "-----------------------------------------" + "\n");

        terminalTextArea.setText(terminalTextArea.getText() + message + "\n");

        terminalTextArea.setText(terminalTextArea.getText() + "-------------------------------------------" +
                "-----------------------------------------" + "\n");
        terminalTextArea.positionCaret(terminalTextArea.getText().length());
    }

    // check server
    public void checkServer() {
        timeline = new Timeline();

        timeline.setCycleCount(Animation.INDEFINITE);

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> {
            clientTPC.sendMassage(clientTPC.gson.toJson(new CheckServerMessage(clientTPC.token, MessageType.CHECK_SERVER3)));

            ServerMessage message = clientTPC.receiveMassage();

            if (message.getType() == ServerType.NEW_ROUND) {
                result.setVisible(true);
                if (message.getOpponent() == null) {
                    ((Label) this.result.getChildren().get(2)).setText("DRAW");
                }
                else {
                    if (message.isSuccess()) {
                        ((Label) this.result.getChildren().get(2)).setText("you WON this round");
                        setShield(true);
                    } else {
                        ((Label) this.result.getChildren().get(2)).setText("you lose this round");
                        setShield(false);
                    }
                }
                new Timeline(new KeyFrame(Duration.seconds(2), actionEvent -> result.setVisible(false))).play();
            }

            if (message.getType() == ServerType.END_GAME) {
                updateEndPage(message.isSuccess());
            }

            if (message.getType() == ServerType.YOUR_TURN) {
                updateTable();

                isMyTurn = true;

                showAnimation();
            }

            if (message.getType() == ServerType.NEW_MESSAGE) {
                receiveMessage(message.getOpponent(), message.getTime());
            }

            if (message.getType() == ServerType.REACTION) {
                showEnemyReaction(message.getOpponent());
            }

            if (message.getType() == ServerType.NEW_MESSAGE_FROM_WATCHER) {
                updateTextArea(message.getOpponent());
            }
        });

        timeline.getKeyFrames().add(keyFrame);

        timeline.play();
    }



    public HBox player2siege;
    public HBox player2rangedCombat;
    public HBox player2closeCombat;
    public HBox player1siege;
    public HBox player1rangedCombat;
    public HBox player1closeCombat;
    public HBox hand;
    public HBox weather;
    public ImageView player2leader;
    public ImageView player1leader;
    public Label player2deckNum;
    public Label player1deckNum;
    public ImageView player2dead;
    public ImageView player1dead;
    public ImageView player2siegeSpecial;
    public ImageView player2rangedSpecial;
    public ImageView player2closeSpecial;
    public ImageView player1closeSpecial;
    public ImageView player1rangedSpecial;
    public ImageView player1siegeSpecial;
    public Label player2sumSiege;
    public Label player2sumRanged;
    public Label player2sumClose;
    public Label player1sumClose;
    public Label player1sumRanged;
    public Label player1sumSiege;
    public Label player2sum;
    public Label player1sum;
    public ImageView player2shield;
    public ImageView player1shield;
    public AnchorPane selectPane;
    public AnchorPane turnPane;
    public AnchorPane passPane;
    public AnchorPane result;
    public AnchorPane winnerPane;
    public ScrollPane chatScrollPane;
    private GridPane gridPane;
    public TextField messageTextField;
    private int lineOfChat = 0;
    private boolean isMyTurn;
    public final int pictureWidth = 90;

    private static boolean isCalled = false;
    // this method call ar first and initialize some of field

    public void firstOption() {
        if (!isCalled) {
            checkServer();
            isCalled = true;

            // set content of chat
            gridPane = new GridPane();
            chatScrollPane.setContent(gridPane);
            gridPane.setHgap(10);
            gridPane.setVgap(20);

            ColumnConstraints column1 = new ColumnConstraints();
            column1.setPercentWidth(100);
            ColumnConstraints column2 = new ColumnConstraints();
            column2.setPercentWidth(100);
            ColumnConstraints column3 = new ColumnConstraints();
            column3.setPercentWidth(50);

            gridPane.getColumnConstraints().addAll(column1, column2, column3);

            chatScrollPane.setFitToWidth(true);
            chatScrollPane.setFitToHeight(true);

            // set whose turn
            clientTPC.sendMassage(clientTPC.gson.toJson(new WhoseTrun(clientTPC.token)));

            ServerMessage m = clientTPC.receiveMassage();

            if (m.isSuccess()) {
                isMyTurn = true;
            } else {
                isMyTurn = false;

                turnPane.getChildren().getFirst().setRotate(-90);
            }

            // set picture are leaders
            clientTPC.sendMassage(clientTPC.gson.toJson(new GiveMeLeader(clientTPC.token)));

            ServerMessage message = clientTPC.receiveMassage();

            Leaders me = message.yourLeader;
            Leaders opponent = message.opponnetLeader;

            player1leader.setImage(me.getImage());
            player2leader.setImage(opponent.getImage());
            player1shield.setImage(Shields.valueOf(me.getFaction().name()).getImage1());
            player2shield.setImage(Shields.valueOf(opponent.getFaction().name()).getImage1());

            // other setting
            hand.setSpacing(10);

            showVetoCards();

            HeadViewController.takeShot();
        }
    }
// show vetoCards

    private void showVetoCards() {
        selectPane.setVisible(true);

        ImageView image1 = (ImageView) selectPane.getChildren().get(0);
        ImageView image2 = (ImageView) selectPane.getChildren().get(1);
        ImageView image3 = (ImageView) selectPane.getChildren().get(2);
        ImageView image4 = (ImageView) selectPane.getChildren().get(3);
        ImageView image5 = (ImageView) selectPane.getChildren().get(4);

        clientTPC.sendMassage(clientTPC.gson.toJson(new GetHand(clientTPC.token)));

        ServerMessage message = clientTPC.receiveMassage();

        ArrayList<Card> Hand = message.getMyInHand();

        image1.setImage(Hand.get(0).getImage());
        image2.setImage(Hand.get(1).getImage());
        image3.setImage(Hand.get(2).getImage());
        image4.setImage(Hand.get(3).getImage());
        image5.setImage(Hand.get(4).getImage());

        image1.setOnMouseClicked(mouseEvent -> changePlaceOfImage(-2, Hand, image1, image2, image3, image4, image5));
        image2.setOnMouseClicked(mouseEvent -> changePlaceOfImage(-1, Hand, image1, image2, image3, image4, image5));
        image4.setOnMouseClicked(mouseEvent -> changePlaceOfImage(1, Hand, image1, image2, image3, image4, image5));
        image5.setOnMouseClicked(mouseEvent -> changePlaceOfImage(2, Hand, image1, image2, image3, image4, image5));

        image3.setOnMouseClicked(mouseEvent -> selectVetoCard(mouseEvent, Hand));

        updateTable();
    }
    private void changePlaceOfImage(int mode, ArrayList<Card> Hand, ImageView... images) {
        int centerIndex = Hand.indexOf(Card.getCardByImage(images[2].getImage()));

        for (int i = 0; i < 5; i++) {
            if (centerIndex + mode + (i - 2) < 0 || centerIndex + mode + (i - 2) >= Hand.size()) {
                images[i].setImage(null);
                continue;
            }

            images[i].setImage(Hand.get(centerIndex + mode + (i - 2)).getImage());
        }
    }
    private void selectVetoCard(MouseEvent mouseEvent, ArrayList<Card> hand) {
        Image image = ((ImageView) mouseEvent.getSource()).getImage();

        Card card = Card.getCardByImage(image);

        clientTPC.sendMassage(clientTPC.gson.toJson(new SelectVetoCard(clientTPC.token, hand.indexOf(card) + 1 + "")));

        ServerMessage message = clientTPC.receiveMassage();

        int numOfVeto = message.getNumberOfVeto();

        if (numOfVeto == 1) {
            showVetoCards();
        } else {
            selectPane.setVisible(false);
            changeTurn();
            updateTable();
        }
    }
// update table

    private void updateTable() {
        // remove all --> hand, rows, weather
        removeAllLines();

        // update hand
        clientTPC.sendMassage(clientTPC.gson.toJson(new GetHand(clientTPC.token)));
        ServerMessage message = clientTPC.receiveMassage();

        for (Card card : message.getMyInHand()) {
            locateCard(card, hand);
        }

        // update spells row
        for (Card card : message.getSpells()) {
            locateCard(card, weather);
        }

        // update rows
        updateRows(message.getMyCards(), message.getMyDead(), 1);
        updateRows(message.getOpponentCards(), message.getOpponentDead(), 2);

        // update sum of card
        player1sum.setText(String.valueOf(message.getMyScores().get(3)));
        player1sumSiege.setText(String.valueOf(message.getMyScores().get(0)));
        player1sumRanged.setText(String.valueOf(message.getMyScores().get(1)));
        player1sumClose.setText(String.valueOf(message.getMyScores().get(2)));
        player2sum.setText(String.valueOf(message.getOpponentScores().get(3)));
        player2sumSiege.setText(String.valueOf(message.getOpponentScores().get(0)));
        player2sumRanged.setText(String.valueOf(message.getOpponentScores().get(1)));
        player2sumClose.setText(String.valueOf(message.getOpponentScores().get(2)));

        // update num of card in deck
        player1deckNum.setText(String.valueOf(message.getMyDeck().size()));
        player2deckNum.setText(String.valueOf(message.getOpponentDeck().size()));

        HeadViewController.takeShot();
    }
    // this method is for remove card from all line
    private void removeAllLines() {
        hand.getChildren().clear();
        player1siege.getChildren().clear();
        player1closeCombat.getChildren().clear();
        player1rangedCombat.getChildren().clear();
        player2siege.getChildren().clear();
        player2closeCombat.getChildren().clear();
        player2rangedCombat.getChildren().clear();
        weather.getChildren().clear();
    }
    // these methods are for set rows
    private void updateRows(Pair<Card, ArrayList<Card>>[] rows, ArrayList<Card> dead, int mode) {
        ImageView siege, ranged, close;
        HBox siegeH, rangedH, closeH;
        if (mode == 1) {
            siege = player1siegeSpecial; ranged = player1rangedSpecial; close = player1closeSpecial;
            siegeH = player1siege; rangedH = player1rangedCombat; closeH = player1closeCombat;
        } else {
            siege = player2siegeSpecial; ranged = player2rangedSpecial; close = player2closeSpecial;
            siegeH = player2siege; rangedH = player2rangedCombat; closeH = player2closeCombat;
        }
        // for siege
        if (rows.length > 2){
            if (rows[0].getFirst() != null) siege.setImage(rows[0].getFirst().getImage());
            for (Card card : rows[0].getSecond()) {
                locateCard(card, siegeH);
            }
            // for ranged combat
            if (rows[1].getFirst() != null) ranged.setImage(rows[1].getFirst().getImage());
            for (Card card : rows[1].getSecond()) {
                locateCard(card, rangedH);
            }
            // for close combat
            if (rows[2].getFirst() != null) close.setImage(rows[2].getFirst().getImage());
            for (Card card : rows[2].getSecond()) {
                locateCard(card, closeH);
            }
        }

        // update dead card
        if (!dead.isEmpty()) {
            if (mode == 1) player1dead.setImage(dead.getLast().getImage());
            else  player2dead.setImage(dead.getLast().getImage());
        }

    }
    private void locateCard(Card card, HBox hBox) {
        ImageView image = new ImageView(card.getImage());

        image.setFitHeight(pictureWidth);
        image.setPreserveRatio(true);

        hBox.getChildren().add(image);

        // set on mouth click for each card of hand
        if (hBox.equals(hand)) {
            setHover(image);
            image.setOnMouseClicked(mouseEvent -> {
                image.setOnMouseExited(null);
            });
        }
    }
    // these methods are for effect of card --> x1.5
    private void setHover(ImageView imageView) {
        imageView.setOnMouseEntered(this::maximize);
        imageView.setOnMouseExited(this::minimize);
    }
    private void maximize(MouseEvent mouseEvent) {
        ImageView image = (ImageView) mouseEvent.getSource();
        image.setScaleX(1.5);
        image.setScaleY(1.5);

        CardType type = Card.getCardByImage(((ImageView) mouseEvent.getSource()).getImage()).getType();
        Color color = Color.web("00F7FF4C");
        if (isMyTurn) {
            if (type.equals(CardType.CLOSE_COMBAT)) {
                player1closeCombat.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
                player1closeCombat.setOnMouseClicked(event -> playCard(Card.getCardByImage(image.getImage()), player1closeCombat, 3));
            } else if (type.equals(CardType.RANGED_COMBAT)) {
                player1rangedCombat.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
                player1rangedCombat.setOnMouseClicked(event -> playCard(Card.getCardByImage(image.getImage()), player1rangedCombat, 2));
            } else if (type.equals(CardType.SIEGE)) {
                player1siege.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
                player1siege.setOnMouseClicked(event -> playCard(Card.getCardByImage(image.getImage()), player1siege, 1));
            } else if (type.equals(CardType.WEATHER)) {
                weather.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
                weather.setOnMouseClicked(event -> playCard(Card.getCardByImage(image.getImage()), weather, 4));
            } else if (type.equals(CardType.AGILE)) {
                player1closeCombat.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
                player1rangedCombat.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
                player1rangedCombat.setOnMouseClicked(event -> playCard(Card.getCardByImage(image.getImage()), player1rangedCombat, 2));
                player1closeCombat.setOnMouseClicked(event -> playCard(Card.getCardByImage(image.getImage()), player1closeCombat, 3));
            }
        }


        HeadViewController.takeShot();
    } // todo.for.debug
    private void minimize(MouseEvent mouseEvent) {
        ImageView image = (ImageView) mouseEvent.getSource();
        image.setScaleX(1);
        image.setScaleY(1);

        CardType type = Card.getCardByImage(((ImageView) mouseEvent.getSource()).getImage()).getType();
        if (type.equals(CardType.CLOSE_COMBAT)) {
            player1closeCombat.setBackground(null);
            player1closeCombat.setOnMouseClicked(null);
        } else if (type.equals(CardType.RANGED_COMBAT)) {
            player1rangedCombat.setBackground(null);
            player1rangedCombat.setOnMouseClicked(null);
        } else if (type.equals(CardType.SIEGE)) {
            player1siege.setBackground(null);
            player1siege.setOnMouseClicked(null);
        } else if (type.equals(CardType.WEATHER)) {
            weather.setBackground(null);
            weather.setOnMouseClicked(null);
        } else if (type.equals(CardType.AGILE)) {
            player1closeCombat.setBackground(null);
            player1rangedCombat.setBackground(null);
            player1rangedCombat.setOnMouseClicked(null);
            player1closeCombat.setOnMouseClicked(null);
        }

        HeadViewController.takeShot();
    } // todo.for.debug
// play card method

    private void playCard(Card card, HBox hBox, int row) {
        // add card to imageview
        hBox.setBackground(null);
        hBox.setOnMouseClicked(null);

        // call backend
        clientTPC.sendMassage(clientTPC.gson.toJson(new PlayCard(clientTPC.token, card, row)));
        clientTPC.receiveMassage();
        changeTurn();

        // update table
        updateTable();
    }
// change turn

    private void changeTurn() {
        clientTPC.sendMassage(clientTPC.gson.toJson(new ChangeTurn(clientTPC.token)));
        clientTPC.receiveMassage();

        showAnimation();
    }
    private void showAnimation() {
        // now show some graphic
        turnPane.setVisible(true);

        RotateTransition transition = new RotateTransition();
        transition.setDuration(Duration.seconds(1));
        transition.setByAngle(180);
        transition.setNode(turnPane.getChildren().get(0));
        transition.play();
        new Timeline(new KeyFrame(Duration.seconds(2), event -> {
            turnPane.setVisible(false);
        })).play();
    }

// pass turn
    public void passTurn() {
        passPane.setVisible(true);
        ((Label) passPane.getChildren().get(2)).setText("you pass turn");

        new Timeline(new KeyFrame(Duration.seconds(2), event -> {
            passPane.setVisible(false);

            clientTPC.sendMassage(clientTPC.gson.toJson(new PassTurn(clientTPC.token)));

            clientTPC.receiveMassage();
            changeTurn();
            updateTable();
        })).play();

        HeadViewController.takeShot();
    }
    private void updateEndPage(boolean win) {
        winnerPane.setVisible(true);

        Label massage =(Label) winnerPane.getChildren().get(0);

        ImageView imageView = (ImageView) winnerPane.getChildren().get(4);
        if (win) {
            massage.setText("you won the game");

            imageView.setImage(new Image(getClass().getResource("/asset/img/icons/end_win.png").toExternalForm()));
        }
        else {
            massage.setText("you lose the game");
            imageView.setImage(new Image(getClass().getResource("/asset/img/icons/end_lose.png").toExternalForm()));
        }

        // know set on action for buttons
        Button rematch = (Button) winnerPane.getChildren().get(1);
        Button customize = (Button) winnerPane.getChildren().get(2);

        rematch.setOnAction(event -> {
            winnerPane.setVisible(false);
                // todo
            firstOption();
        });

        customize.setOnAction(event -> {
            // todo
        });
    }
    private void setShield(boolean win) {
        clientTPC.sendMassage(clientTPC.gson.toJson(new GiveMeLeader(clientTPC.token)));
        ServerMessage message = clientTPC.receiveMassage();

        if (win) {
            player2shield.setImage(Shields.valueOf(message.opponnetLeader.getFaction().name()).getImage2());
        }
        else {
            player1shield.setImage(Shields.valueOf(message.yourLeader.getFaction().name()).getImage2());
        }
    }
    public void showChat(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE)) {
            chatScrollPane.setVisible(!chatScrollPane.isVisible());
            messageTextField.setVisible(!messageTextField.isVisible());
        }
    }

    public void sendMessage(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            String message = messageTextField.getText();
            messageTextField.setText("");

            LocalTime time = LocalTime.now();
            int hour = time.getHour(); int minute = time.getMinute();
            String localTime = hour + ":" + String.format("%02d", minute);
            gridPane.add(new Label(localTime), 2, lineOfChat);

            Button button = new Button(message);
            button.setStyle("-fx-background-color: #5500ff; -fx-text-fill: #fff");
            button.setMaxWidth(200);
            gridPane.add(button, 0, lineOfChat++);

            clientTPC.sendMassage(clientTPC.gson.toJson(new Chat(clientTPC.token, message, localTime)));
            clientTPC.receiveMassage();
        }
    }
    private void receiveMessage(String message, String time) {
        gridPane.add(new Label(time), 2, lineOfChat);

        Button button = new Button(message);
        button.setStyle("-fx-background-color: gray; -fx-text-fill: #fff");
        gridPane.add(button, 1, lineOfChat++);
    }
    public AnchorPane reactionPane;
    public TextField reactionTextField;
    public ImageView reactionImagePlayer1;
    public Label reactionLabelPlayer1;
    public ImageView reactionImagePlayer2;
    public Label reactionLabelPlayer2;
    private void showEnemyReaction(String message) {
        reactionImagePlayer2.setVisible(true);
        reactionLabelPlayer2.setVisible(true);
        reactionLabelPlayer2.setText(message);

        new Timeline(new KeyFrame(Duration.seconds(7), event -> {
            reactionLabelPlayer2.setVisible(false);
            reactionImagePlayer2.setVisible(false);
        })).play();
    }
    public void showReaction(ActionEvent actionEvent) {
        reactionPane.setVisible(false);

        String message = ((Button) actionEvent.getSource()).getText();

        sendReaction(message);
    }
    public void showReactionText(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER){
            reactionPane.setVisible(false);

            String message = ((TextField) keyEvent.getSource()).getText();

            sendReaction(message);
        }
    }
    private void sendReaction(String message) {
        reactionImagePlayer1.setVisible(true);
        reactionLabelPlayer1.setVisible(true);
        reactionLabelPlayer1.setText(message);

        new Timeline(new KeyFrame(Duration.seconds(7), event -> {
            reactionLabelPlayer1.setVisible(false);
            reactionImagePlayer1.setVisible(false);
        })).play();

        clientTPC.sendMassage(clientTPC.gson.toJson(new Reaction(clientTPC.token, message)));
        clientTPC.receiveMassage();
    }

    public void showReactionPane() {
        reactionPane.setVisible(!reactionPane.isVisible());
    }
}
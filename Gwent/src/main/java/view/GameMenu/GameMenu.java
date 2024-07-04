package view.GameMenu;

import controller.GameControllers.GameMenuController;
import controller.GameControllers.PreGameMenuController;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.User;
import model.enums.card.Card;
import model.enums.card.CardType;
import model.enums.gameMenu.Factions;
import model.enums.gameMenu.GameMenuCommands;
import model.enums.gameMenu.Shields;
import model.gameTable.UserInGame;
import model.toolClasses.Pair;
import model.toolClasses.Result;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu {
    public static User firstPlayer;
    public static User secondPlayer;

//    public static void run(Scanner scanner) {
//        String input = scanner.nextLine();
//        GameMenuController newGame = new GameMenuController();
//        newGame.setPlayers(firstPlayer, secondPlayer);
//        newGame.dealCards();
//
//        Matcher matcher;
//
//        while (!newGame.isOver()) {
//            if ((matcher = GameMenuCommands.VETO_CARD.getMatcher(input)) != null) {
//                Result result = newGame.vetoCard(matcher.group("cardnumber"));
//            } else if (GameMenuCommands.IN_HAND_DECK.getMatcher(input) != null) {
//                Result result = newGame.inHandDeck(-1);
//            } else if ((matcher = GameMenuCommands.IN_HAND_DECK_OPTION.getMatcher(input)) != null) {
//                Result result = newGame.inHandDeck(Integer.parseInt(matcher.group("place")));
//            } else if ((matcher = GameMenuCommands.REMAINING_CARD.getMatcher(input)) != null) {
//                Result result = newGame.remainingCardsToPlay();
//            } else if ((matcher = GameMenuCommands.OUT_OF_PLAY_CARD.getMatcher(input)) != null) {
//                Result result = newGame.outOfPlayCards();
//            } else if ((matcher = GameMenuCommands.CARDS_IN_ROW.getMatcher(input)) != null) {
//                Result result = newGame.cardsInRow(Integer.parseInt(matcher.group("rownumber")));
//            } else if ((matcher = GameMenuCommands.SPELLS_IN_PLAY.getMatcher(input)) != null) {
//                Result result = newGame.spellsInPlay();
//            } else if ((matcher = GameMenuCommands.PLACE_CARD.getMatcher(input)) != null) {
//                Result result = newGame.placeCard(0, 0);//TODO complete this method
//            } else if ((matcher = GameMenuCommands.SHOW_COMMANDER.getMatcher(input)) != null) {
//                Result result = newGame.showCommander();
//            } else if ((matcher = GameMenuCommands.COMMANDER_POWER_PLAY.getMatcher(input)) != null) {
//                Result result = newGame.commanderPowerPLay(); //TODO do this after completing the methods
//            } else if ((matcher = GameMenuCommands.SHOW_PLAYERS_LIVE.getMatcher(input)) != null) {
//                Result result = newGame.showPlayersLives();
//            } else if ((matcher = GameMenuCommands.SHOW_PLAYERS_INFO.getMatcher(input)) != null) {
//                Result result = newGame.showPlayersInfo();
//            } else if ((matcher = GameMenuCommands.SHOW_NUMBER_OF_CARDS_IN_HAND.getMatcher(input)) != null) {
//                Result result = newGame.showNumberOfCardsInHand();
//            } else if ((matcher = GameMenuCommands.SHOW_TURN_INFO.getMatcher(input)) != null) {
//                Result result = newGame.showTurnInfo();
//            } else if ((matcher = GameMenuCommands.SHOW_TOTAL_SCORE.getMatcher(input)) != null) {
//                Result result = newGame.showTotalScore();
//            }
//        }
//    }

    // graphic part
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


    public GameMenuController game;
    public UserInGame player1;
    public UserInGame player2;
    public final int pictureWidth = 90;

// this method call ar first and initialize some of field
    public void firstOption() {
        if (game == null){
            GameMenuController game = new GameMenuController();

            game.setPlayers(PreGameMenuController.currentUser, PreGameMenuController.opponentUser);
            game.dealCards();
            this.game = game;
            // initialize player1 and 2
            player1 = game.getPlayer1();
            player2 = game.getPlayer2();
            // set picture are leaders
            player1leader.setImage(player1.getGameTable().getLeader().getImage());
            player2leader.setImage(player2.getGameTable().getLeader().getImage());
            player1shield.setImage(Shields.valueOf(player1.getGameTable().getLeader().getFaction().name()).getImage1());
            player2shield.setImage(Shields.valueOf(player2.getGameTable().getLeader().getFaction().name()).getImage1());

            // other setting
            hand.setSpacing(10);

            showVetoCards();
        }
    }

// show vetoCards
    private void showVetoCards() {
        ImageView image1 = (ImageView) selectPane.getChildren().get(0);
        ImageView image2 = (ImageView) selectPane.getChildren().get(1);
        ImageView image3 = (ImageView) selectPane.getChildren().get(2);
        ImageView image4 = (ImageView) selectPane.getChildren().get(3);
        ImageView image5 = (ImageView) selectPane.getChildren().get(4);



        updateTable();
    }

// update table
    private void updateTable() {
        // update hand
        UserInGame userTurn = game.getUserTurn();

        for (Card card : userTurn.getGameTable().getInHandsCards()) {
            locateCard(card, hand);
        }

        // update rows
        updateRows(player1.getGameTable().getCardsOfRow(), 1);
        updateRows(player2.getGameTable().getCardsOfRow(), 2);

        // update sum of card
        player1sum.setText(String.valueOf(game.calculateTotalScore(player1)));
        player1sumSiege.setText(String.valueOf(game.calculateScoreInRow(player1, 1)));
        player1sumRanged.setText(String.valueOf(game.calculateScoreInRow(player1, 2)));
        player1sumClose.setText(String.valueOf(game.calculateScoreInRow(player1, 3)));
        player2sum.setText(String.valueOf(game.calculateTotalScore(player2)));
        player2sumSiege.setText(String.valueOf(game.calculateScoreInRow(player2, 1)));
        player2sumRanged.setText(String.valueOf(game.calculateScoreInRow(player2, 2)));
        player2sumClose.setText(String.valueOf(game.calculateScoreInRow(player2, 3)));

        // update num of card in deck
        player1deckNum.setText(String.valueOf(player1.getGameTable().getDeckCards().size()));
        player2deckNum.setText(String.valueOf(player2.getGameTable().getDeckCards().size()));
    }
    // these methods are for set rows
    private void updateRows(Pair<Card, ArrayList<Card>>[] rows, int mode) {
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
        if (!player1.getGameTable().getDeadCards().isEmpty())
            player1dead.setImage(player1.getGameTable().getDeadCards().getLast().getImage());
        if (!player2.getGameTable().getDeadCards().isEmpty())
            player2dead.setImage(player2.getGameTable().getDeadCards().getLast().getImage());
    }
    private void locateCard(Card card, HBox hBox) {
        ImageView image = new ImageView(card.getImage());

        image.setFitHeight(pictureWidth);
        image.setPreserveRatio(true);
        setHover(image);

        hBox.getChildren().add(image);
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

        if (game.getUserTurn().equals(player1)) {
            if (type.equals(CardType.CLOSE_COMBAT))
                player1closeCombat.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
            else if (type.equals(CardType.RANGED_COMBAT))
                player1rangedCombat.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
            else if (type.equals(CardType.SIEGE))
                player1siege.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
            else if (type.equals(CardType.WEATHER))
                weather.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
            else if (type.equals(CardType.AGILE)) {
                player1closeCombat.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                player1rangedCombat.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        } else {
            if (type.equals(CardType.CLOSE_COMBAT))
                player2closeCombat.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
            else if (type.equals(CardType.RANGED_COMBAT))
                player2rangedCombat.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
            else if (type.equals(CardType.SIEGE))
                player2siege.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
            else if (type.equals(CardType.WEATHER))
                weather.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
            else if (type.equals(CardType.AGILE)) {
                player2closeCombat.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                player2rangedCombat.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
    }
    private void minimize(MouseEvent mouseEvent) {
        ImageView image = (ImageView) mouseEvent.getSource();
        image.setScaleX(1);
        image.setScaleY(1);

        CardType type = Card.getCardByImage(((ImageView) mouseEvent.getSource()).getImage()).getType();

        if (game.getUserTurn().equals(player1)) {
            if (type.equals(CardType.CLOSE_COMBAT))
                player1closeCombat.setBackground(null);
            else if (type.equals(CardType.RANGED_COMBAT))
                player1rangedCombat.setBackground(null);
            else if (type.equals(CardType.SIEGE))
                player1siege.setBackground(null);
            else if (type.equals(CardType.WEATHER))
                weather.setBackground(null);
            else if (type.equals(CardType.AGILE)) {
                player1closeCombat.setBackground(null);
                player1rangedCombat.setBackground(null);
            }
        } else {
            if (type.equals(CardType.CLOSE_COMBAT))
                player2closeCombat.setBackground(null);
            else if (type.equals(CardType.RANGED_COMBAT))
                player2rangedCombat.setBackground(null);
            else if (type.equals(CardType.SIEGE))
                player2siege.setBackground(null);
            else if (type.equals(CardType.WEATHER))
                weather.setBackground(null);
            else if (type.equals(CardType.AGILE)) {
                player2closeCombat.setBackground(null);
                player2rangedCombat.setBackground(null);
            }
        }
    }
}
package view.GameMenu;

import controller.GameControllers.GameMenuController;
import controller.GameControllers.PreGameMenuController;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.User;
import model.enums.card.Card;
import model.enums.card.CardType;
import model.enums.gameMenu.Shields;
import model.gameTable.UserInGame;
import model.toolClasses.Pair;
import model.toolClasses.Result;
import java.util.ArrayList;

public class GameMenu {

// terminal part
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

            showVetoCards(player1);
        }
    }

// show vetoCards
    private void showVetoCards(UserInGame player) {
        selectPane.setVisible(true);

        ImageView image1 = (ImageView) selectPane.getChildren().get(0);
        ImageView image2 = (ImageView) selectPane.getChildren().get(1);
        ImageView image3 = (ImageView) selectPane.getChildren().get(2);
        ImageView image4 = (ImageView) selectPane.getChildren().get(3);
        ImageView image5 = (ImageView) selectPane.getChildren().get(4);

        ArrayList<Card> Hand = player.getGameTable().getInHandsCards();
        image1.setImage(Hand.get(0).getImage());
        image2.setImage(Hand.get(1).getImage());
        image3.setImage(Hand.get(2).getImage());
        image4.setImage(Hand.get(3).getImage());
        image5.setImage(Hand.get(4).getImage());

        image1.setOnMouseClicked(mouseEvent -> changePlaceOfImage(-2, Hand, image1, image2, image3, image4, image5));
        image2.setOnMouseClicked(mouseEvent -> changePlaceOfImage(-1, Hand, image1, image2, image3, image4, image5));
        image4.setOnMouseClicked(mouseEvent -> changePlaceOfImage(1, Hand, image1, image2, image3, image4, image5));
        image5.setOnMouseClicked(mouseEvent -> changePlaceOfImage(2, Hand, image1, image2, image3, image4, image5));

        image3.setOnMouseClicked(mouseEvent -> selectVetoCard(mouseEvent, Hand, player));

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
    private void selectVetoCard(MouseEvent mouseEvent, ArrayList<Card> hand, UserInGame player) {
        Image image = ((ImageView) mouseEvent.getSource()).getImage();

        Card card = Card.getCardByImage(image);

        Result result = game.vetoCard(String.valueOf(hand.indexOf(card) + 1));

        String[] temp = result.getMessage().split(" ");
        int numOfVeto = Integer.parseInt(temp[temp.length - 1]);

        if (numOfVeto == 1) {
            showVetoCards(player);
        } else if (player.equals(player1)) {
            game.changeTurn(); // todo : a graphic method for change turn
            showVetoCards(player2);
        } else {
            selectPane.setVisible(false);
            game.changeTurn(); // todo : a graphic method for change turn
            updateTable();
        }
    }

// update table
    private void updateTable() {
        // remove all --> hand, rows, weather
        removeAllLines();

        // update hand
        UserInGame userTurn = game.getUserTurn();

        for (Card card : userTurn.getGameTable().getInHandsCards()) {
            locateCard(card, hand);
        }

        // update spells row
        for (Card card : game.getSpells()) {
            locateCard(card, weather);
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

        // set on mouth click for each card of hand
        if (hBox.equals(hand)) {
            image.setOnMouseClicked(mouseEvent -> playCard(card));
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
        if (game.getUserTurn().equals(player1)) {
            if (type.equals(CardType.CLOSE_COMBAT))
                player1closeCombat.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
            else if (type.equals(CardType.RANGED_COMBAT))
                player1rangedCombat.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
            else if (type.equals(CardType.SIEGE))
                player1siege.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
            else if (type.equals(CardType.WEATHER))
                weather.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
            else if (type.equals(CardType.AGILE)) {
                player1closeCombat.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
                player1rangedCombat.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        } else {
            if (type.equals(CardType.CLOSE_COMBAT))
                player2closeCombat.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
            else if (type.equals(CardType.RANGED_COMBAT))
                player2rangedCombat.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
            else if (type.equals(CardType.SIEGE))
                player2siege.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
            else if (type.equals(CardType.WEATHER))
                weather.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
            else if (type.equals(CardType.AGILE)) {
                player2closeCombat.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
                player2rangedCombat.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
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

    // play card methods
    private void playCard(Card card) {

    }
}
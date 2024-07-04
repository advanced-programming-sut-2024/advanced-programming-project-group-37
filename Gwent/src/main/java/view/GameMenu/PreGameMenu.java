package view.GameMenu;

import controller.GameControllers.PreGameMenuController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.Duration;
import model.User;
import model.enums.card.Card;
import model.enums.card.CardType;
import model.enums.card.Leaders;
import model.enums.gameMenu.Factions;
import model.enums.gameMenu.PreGameCommands;
import model.enums.gameMenu.Shields;
import model.toolClasses.Pair;
import model.toolClasses.Result;
import view.HeadViewController;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class PreGameMenu {
    // graphic terminal
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
                    "----------------------------------------" + "\n");

            // some if else
            Matcher matcher;
            if ((matcher = PreGameCommands.SHOW_FACTIONS.getMatcher(inputLine)) != null) {
                Result result = PreGameMenuController.showFactions(matcher.group("n"));
            } else if ((matcher = PreGameCommands.SELECT_FACTION.getMatcher(inputLine)) != null) {
                PreGameMenuController.selectFaction(matcher.group("factionname"), matcher.group("n"));
            } else if ((matcher = PreGameCommands.SHOW_CARDS.getMatcher(inputLine)) != null) {
                Result result = PreGameMenuController.showCards(matcher.group("n"));
            } else if ((matcher = PreGameCommands.SHOW_DECK.getMatcher(inputLine)) != null) {
                Result result = PreGameMenuController.showDeck(matcher.group("n"));
            } else if ((matcher = PreGameCommands.SHOW_INFORMATION_CURRENT_USER.getMatcher(inputLine)) != null) {
                Result result = PreGameMenuController.showInfoCurrentUser(matcher.group("n"));
            } else if ((matcher = PreGameCommands.SHOW_LEADERS.getMatcher(inputLine)) != null) {
                Result result = PreGameMenuController.showLeaders(matcher.group("n"));
            } else if ((matcher = PreGameCommands.SELECT_LEADER.getMatcher(inputLine)) != null) {
                Result result = PreGameMenuController.selectLeader(matcher.group("leadernum"), matcher.group("n"));
            } else if ((matcher = PreGameCommands.ADD_TO_DECK.getMatcher(inputLine)) != null) {
                Result result = PreGameMenuController.addToDeck(matcher.group("cardname"), Integer.parseInt(matcher.group("count"))
                        , matcher.group("n"));
            } else if ((matcher = PreGameCommands.DELETE_FROM_DECK.getMatcher(inputLine)) != null) {
                Result result = PreGameMenuController.deleteFromDeck(matcher.group("cardname"), Integer.parseInt(matcher.group("count")),
                        matcher.group("n"));
            } else if (PreGameCommands.START_GAME.getMatcher(inputLine) != null) {
                Result result = PreGameMenuController.startGame();
                if (result.isSuccessful()); // todo : go to game
            } else {
                terminalTextArea.setText(terminalTextArea.getText() + "invalid command" + "\n");
            }

            terminalTextArea.setText(terminalTextArea.getText() + "-------------------------------------------" +
                    "----------------------------------------" + "\n");
            terminalTextArea.positionCaret(terminalTextArea.getText().length());
        }
    }


    // graphic part
    private static int playerNum = 1;
    public HBox factionHBox;
    public HBox factionShowHBox;
    public ImageView leaderImageView;
    public HBox leadersList;
    public ScrollPane CollectionScroll;
    public ScrollPane deckScroll;
    public GridPane labelsGridPane;
    public GridPane DeckGridPane;
    public GridPane CardCollectionGridPane;
    public Label DeckError;
    public AnchorPane selectOpponentPane;
    // change faction methods

    public void showFaction() {
        factionHBox.setVisible(true);
    }
    public void changeFaction(MouseEvent mouseEvent) {
        // set faction HBox
        factionHBox.setVisible(false);

        // find image
        ImageView image = (ImageView) mouseEvent.getSource();

        // declare a string for faction name and Image for ImageView
        String newName = "";
        Image newImage = null;

        // I use location to differ card --> with method <getX()>
        int x = (int) image.getX();

        if (x == 1) {
            newName = "Skellige";
            newImage = Shields.SKELLIGE.getImage1();
        } else if (x == 2){
            newName = "Monster";
            newImage = Shields.MONSTER.getImage1();
        } else if (x == 3) {
            newName = "Empire NilfGaarden";
            newImage = Shields.EMPIRE_NILFGARDEN.getImage1();
        } else if (x == 4) {
            newName = "Realm Northern";
            newImage = Shields.REALMS_NORTHERN.getImage1();
        } else if (x == 5) {
            newName = "Scoiateal";
            newImage = Shields.SCOIATEAL.getImage1();
        }

        // change faction in backend
        PreGameMenuController.selectFaction(newName, String.valueOf(playerNum));

        // set new image
        ImageView imageView = (ImageView) factionShowHBox.getChildren().get(0);
        imageView.setImage(newImage);

        // set new label
        Label label = (Label) factionShowHBox.getChildren().get(1);
        label.setText(newName);

        updateDeck();
        showLeaders();
    }
    // these two methods are for hover effect
    public void maximize(MouseEvent mouseEvent) {
        ImageView image = (ImageView) mouseEvent.getSource();
        image.setScaleX(1.5);
        image.setScaleY(1.5);
    }
    public void minimize(MouseEvent mouseEvent) {
        ImageView image = (ImageView) mouseEvent.getSource();
        image.setScaleX(1);
        image.setScaleY(1);
    }


    // change leader methods  --> I use maximize and minimize for effect
    public void showLeaders() {
        // remove all child of HBox
        leadersList.getChildren().clear();

        // set leaderLeast visible
        leadersList.setVisible(true);
        // set leaderImageView invisible
        leaderImageView.setVisible(false);

        // find faction and get leaders of faction
        Factions faction;
        if (playerNum == 1) faction = User.getLoggedInUser().getUserPreGameInfo().getFaction();
        else faction = PreGameMenuController.opponentUser.getUserPreGameInfo().getFaction();
        ArrayList<Leaders> leaders = Leaders.getLeadersByFaction(faction);

        forBreak:
        for (Leaders leader : leaders) {
            // find image
            ImageView imageView = new ImageView(leader.getImage());
            imageView.setFitWidth(130);
            imageView.setPreserveRatio(true);
            // add it to HBox
            leadersList.getChildren().add(imageView);

            leadersList.setSpacing(30);

            imageView.setOnMouseEntered(this::maximize);
            imageView.setOnMouseExited(this::minimize);
            imageView.setOnMouseClicked(event -> {
                //set leaders least invisible
                leadersList.setVisible(false);

                changeLeaders(leader, imageView);
            });
        }
    }
    private void changeLeaders(Leaders leader, ImageView image) {
        PreGameMenuController.selectLeader(leader.name(), String.valueOf(playerNum));

        // set image visible and set it's image
        leaderImageView.setVisible(true);
        leaderImageView.setImage(leader.getImage());
    }

    // upload and download Deck
    public void uploadDeck() {
        // todo : foad : it needs backend :(
    }
    public void DownloadDeck() {
        // todo : foad : it needs backend :(
    }

    // update Deck and Card
    private void updateDeck() {
        // find arraylist of cardCollection and cardInDeck
        User user;
        if (playerNum == 1) user = User.getLoggedInUser();
        else user = PreGameMenuController.opponentUser;
        ArrayList<Pair<Card, Integer>> cardCollection = user.getUserPreGameInfo().getCardCollection();
        ArrayList<Pair<Card, Integer>> deck = user.getUserPreGameInfo().getCardsInDeck();

        int cardSize = cardCollection.size(), deckSize = deck.size();

        // add card to collectionGridPane
        CardCollectionGridPane = new GridPane();
        CardCollectionGridPane.setVgap(10);
        CardCollectionGridPane.setHgap(5);

        Break:
        for (int j = 0; true; j++) {
            for (int i = 0; i < 3; i++) {
                CollectionScroll.setContent(CardCollectionGridPane);
                if (j*3 + i == cardSize) break Break;

                ImageView imageView = new ImageView(cardCollection.get(j*3+i).getFirst().getImage());
                imageView.setFitWidth(90);
                imageView.setPreserveRatio(true);

                CardCollectionGridPane.add(imageView, i, j);

                // setOnMouthClick for imageView
                imageView.setOnMouseClicked(event -> addToDeck(imageView));
            }
        }

        // add card to deckGridPane
        DeckGridPane = new GridPane();
        DeckGridPane.setVgap(10);
        DeckGridPane.setHgap(5);

        Break1:
        for (int j = 0; true; j++) {
            for (int i = 0; i < 3; i++) {
                deckScroll.setContent(DeckGridPane);
                if (j*3 + i == deckSize) break Break1;

                ImageView imageView = new ImageView(deck.get(j*3 + i).getFirst().getImage());
                imageView.setFitWidth(90);
                imageView.setPreserveRatio(true);

                DeckGridPane.add(imageView, i, j);

                // set on mouthClick for each of image
                imageView.setOnMouseClicked(event -> removeFromDeck(imageView));

            }

        }
    }
    private void addToDeck(ImageView imageView) {
        Image image = imageView.getImage();

        Card card = Card.getCardByImage(image);

        PreGameMenuController.addToDeck(card.getName(), 1, String.valueOf(playerNum));

        updateDeck();

        // set labels
        Label totalCards = (Label) labelsGridPane.getChildren().get(10);
        Label unitCards = (Label) labelsGridPane.getChildren().get(11);
        Label specialCards = (Label) labelsGridPane.getChildren().get(12);
        Label strength = (Label) labelsGridPane.getChildren().get(13);
        Label heroCards = (Label) labelsGridPane.getChildren().get(14);

        CardType type = card.getType();

        totalCards.setText(String.valueOf(Integer.parseInt(totalCards.getText()) + 1));
        if(type == CardType.CLOSE_COMBAT ||
            type == CardType.RANGED_COMBAT ||
            type == CardType.SIEGE ||
            type == CardType.AGILE) {
            unitCards.setText(String.valueOf(Integer.parseInt(unitCards.getText()) + 1));
            strength.setText(String.valueOf(Integer.parseInt(strength.getText()) + card.getPower()));
        } else {
            specialCards.setText(String.valueOf(Integer.parseInt(specialCards.getText()) + 1));
        }
        if (card.getIsHero()) {
            heroCards.setText(String.valueOf(Integer.parseInt(heroCards.getText()) + 1));
        }
    }

    private void removeFromDeck(ImageView imageView) {
        Image image = imageView.getImage();

        Card card = Card.getCardByImage(image);

        PreGameMenuController.deleteFromDeck(card.getName(), 1, String.valueOf(playerNum));

        updateDeck();

        // set labels
        Label totalCards = (Label) labelsGridPane.getChildren().get(10);
        Label unitCards = (Label) labelsGridPane.getChildren().get(11);
        Label specialCards = (Label) labelsGridPane.getChildren().get(12);
        Label strength = (Label) labelsGridPane.getChildren().get(13);
        Label heroCards = (Label) labelsGridPane.getChildren().get(14);

        CardType type = card.getType();

        totalCards.setText(String.valueOf(Integer.parseInt(totalCards.getText()) - 1));
        if(type == CardType.CLOSE_COMBAT ||
                type == CardType.RANGED_COMBAT ||
                type == CardType.SIEGE ||
            type == CardType.AGILE) {
            unitCards.setText(String.valueOf(Integer.parseInt(unitCards.getText()) - 1));
            strength.setText(String.valueOf(Integer.parseInt(strength.getText()) - card.getPower()));
        } else {
            specialCards.setText(String.valueOf(Integer.parseInt(specialCards.getText()) - 1));
        }
        if (card.getIsHero()) {
            heroCards.setText(String.valueOf(Integer.parseInt(heroCards.getText()) - 1));
        }
    }

    public void setOpponent() {
        TextField textField = (TextField) selectOpponentPane.getChildren().get(2);
        Label error = (Label) selectOpponentPane.getChildren().get(0);

        String username = textField.getText();

        Result result = PreGameMenuController.createGame(username);

        if (result.isSuccessful()) {
            showFaction();
            selectOpponentPane.setVisible(false);
        } else {
            textField.setText("");
            error.setText(result.getMessage());

            new Timeline(new KeyFrame(Duration.seconds(2), event -> error.setText(""))).play();
        }


    }

    public void startGame(ActionEvent event) {
        Button button = (Button) event.getSource();

        if (button.getText().equals("READY")) {
            if (!PreGameMenuController.checkDeckIsOk(PreGameMenuController.currentUser)) {
                DeckError.setText("you don't have enough unit card in your deck!");
                new Timeline(new KeyFrame(Duration.seconds(3), event1 -> DeckError.setText(""))).play();
                return;
            }
            // set labels
            ((Label) labelsGridPane.getChildren().get(10)).setText("0");
            ((Label) labelsGridPane.getChildren().get(11)).setText("0");
            ((Label) labelsGridPane.getChildren().get(12)).setText("0");
            ((Label) labelsGridPane.getChildren().get(13)).setText("0");
            ((Label) labelsGridPane.getChildren().get(14)).setText("0");

            button.setText("Start Game");
            playerNum = playerNum + 1;
            showFaction();
        } else {
            if (!PreGameMenuController.checkDeckIsOk(PreGameMenuController.opponentUser)) {
                DeckError.setText("you don't have enough unit card in your deck!");
                new Timeline(new KeyFrame(Duration.seconds(3), event1 -> DeckError.setText(""))).play();
                return;
            }

            HeadViewController.changeScene("game page");

        }
    }
}
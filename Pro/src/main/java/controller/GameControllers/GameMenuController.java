package controller.GameControllers;

import model.User;
import model.enums.card.Card;
import model.gameTable.GameTable;
import model.gameTable.UserInGame;
import model.toolClasses.Pair;
import model.toolClasses.Result;

import java.util.*;

public class GameMenuController {

    private UserInGame player1, player2;
    private UserInGame userTurn = player1;

    public void setPlayers(User user1, User user2) {
        player1 = new UserInGame(user1);
        player2 = new UserInGame(user2);
    }

    public static void changeTurn() {
    }

    //
    public Result vetoCard(String cardNumber) {
        //check if player has vetoed before ot not
        if (userTurn.getNumberOfVeto() == 2)
            return new Result(false, "you can't veto card");

        int n = Integer.getInteger(cardNumber);
        GameTable gameTable = userTurn.getGameTable();

        ArrayList<Card> cardsInDeck = gameTable.getDeckCards();
        ArrayList<Card> cardsInHand = gameTable.getInHandsCards();
        //n is 1 based but arraylist is 0 based
        Card cardToRemove = cardsInHand.get(n - 1);

        cardsInHand.remove(n - 1);

        //replace with another one
        Random random = new Random();
        int randomNum = random.nextInt(cardsInDeck.size());

        Card cardToAdd = cardsInDeck.get(randomNum);

        // add to hand and remove from deck
        cardsInHand.add(cardToAdd);
        cardsInDeck.add(cardToRemove);
        cardsInHand.remove(cardToAdd);

        //set everything
        gameTable.setDeckCards(cardsInDeck);
        gameTable.setInHandsCards(cardsInHand);

        userTurn.setGameTable(gameTable);

        //increase number of veto times
        userTurn.setNumberOfVeto(userTurn.getNumberOfVeto() + 1);
        return new Result(true, "card replaced successfully");
    }

    //creating random cards in hand from deck
    public static ArrayList<Card> createRandomCardsInHand(ArrayList<Card> cards) {
        ArrayList<Card> handCards = new ArrayList<Card>();

        //set upperBound and LowerBound
        int lowerBound = 0;
        int upperBound = cards.size();

        //new Random Object
        Random random = new Random();

        // Create a set to store unique random numbers
        Set<Integer> uniqueNumbers = new HashSet<>();

        // Generate random numbers until we have 10 unique numbers
        while (uniqueNumbers.size() < 10) {
            int randomNumber = random.nextInt(upperBound - lowerBound) + lowerBound;
            uniqueNumbers.add(randomNumber);
        }

        //create a hand
        for (Integer integer : uniqueNumbers) handCards.add(cards.get(integer));


        //remove from deck
        // Convert the Set to a List
        List<Integer> list = new ArrayList<>(uniqueNumbers);

        // Sort the List
        Collections.sort(list);

        for (int i = list.size() - 1; i > 0; i++) {
            cards.remove(list.get(i));
        }

        return handCards;
    }

    // showing hand
    public static Result inHandDeck(int place) {
        return null;
    }

    //remaining cards to play
    public static Result remainingCardsToPlay() {
        return null;
    }

    //show out of play cards
    public static Result outOfPlayCards() {
        return null;
    }

    //show cards in one row
    public static Result cardsInRow() {
        return null;
    }

    //spells in play
    public static Result spellsInRow(int rowNumber) {
        return null;
    }

    public static Result placeCard(int cardNumber, int rowNumber) {
        return null;
    }

    public static Result showCommander() {
        return null;
    }

    public static Result commanderPowerPLay() {
        return null;
    }

    public static Result showPlayersLives() {
        return null;
    }

    public static Result showNumberOfCardsInHand() {
        return null;
    }

    public static Result showTurnInfo() {
        return null;
    }

    public static Result showTotalScore() {
        return null;
    }

    public static Result showTotalScoreOfRow(int rowNumber) {
        return null;
    }

    public static Result passRound() {
        return null;
    }

    public boolean isOver() {
        return true;
    }

    public void dealCards() {
        //for first user
        ArrayList<Card> cardsInDeck1 = deckToArrayList(player1);
        GameTable gameTable1 = player1.getGameTable();
        gameTable1.setInHandsCards(createRandomCardsInHand(cardsInDeck1));
        gameTable1.setDeckCards(cardsInDeck1);
        player1.setGameTable(gameTable1);


        //for second player
        ArrayList<Card> cardsInDeck2 = deckToArrayList(player2);
        GameTable gameTable2 = player1.getGameTable();
        gameTable2.setInHandsCards(createRandomCardsInHand(cardsInDeck2));
        gameTable2.setDeckCards(cardsInDeck2);
        player2.setGameTable(gameTable2);
    }

    private ArrayList<Card> deckToArrayList(UserInGame player1) {
        //make an arrayList of cards in deck
        ArrayList<Card> cards = new ArrayList<>();
        for (Pair<Card, Integer> pair : player1.getUser().getUserPreGameInfo().getCardsInDeck()) {
            for (int j = 0; j < pair.getSecond(); j++) {
                cards.add(pair.getFirst());
            }
        }
        return cards;
    }
}

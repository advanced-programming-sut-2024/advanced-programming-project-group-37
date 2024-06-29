package controller.GameControllers;

import model.User;
import model.enums.card.Card;
import model.enums.card.Leaders;
import model.gameTable.GameTable;
import model.gameTable.UserInGame;
import model.toolClasses.Pair;
import model.toolClasses.Result;

import java.util.*;

public class GameMenuController {
    private int roundNumber = 1;

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

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
    public Result inHandDeck(int place) {
        //check if it has an option or not
        StringBuilder toPrint = new StringBuilder();

        if (place == -1) {
            //no having option
            toPrint.append("cards in hand:\n");
            int n = 1;
            for (Card card : userTurn.getGameTable().getInHandsCards())
                toPrint.append(n).append("- ").append(card.name()).append("\n");
        } else {
            Card card = userTurn.getGameTable().getInHandsCards().get(place - 1);
            toPrint.append(place).append("- ").append(card.name()).append("\n");
        }
        return new Result(true, toPrint.toString());
    }

    //remaining cards to play
    public Result remainingCardsToPlay() {
        StringBuilder toPrint = new StringBuilder();
        int n = 1;
        for (Card card : userTurn.getGameTable().getDeckCards()) {
            toPrint.append(n).append("- ");
            toPrint.append(card.name()).append("\n");
            n++;
        }
        return new Result(true, toPrint.toString());
    }

    //show out of play cards
    public Result outOfPlayCards() {
        StringBuilder toPrint = new StringBuilder();
        int n = 1;
        for (Card card : userTurn.getGameTable().getDeadCards()) {
            toPrint.append(n).append("- ");
            toPrint.append(card.name()).append("\n");
            n++;
        }
        return new Result(true, toPrint.toString());
    }

    //show cards in one row
    public Result cardsInRow(int rowNumber) {
        StringBuilder toPrint = new StringBuilder();
        if (rowNumber != 1 && rowNumber != 2 && rowNumber != 3)
            return new Result(false, "invalid row number");

        //check if there is a horn
        GameTable gameTable = userTurn.getGameTable();
        Card horn = gameTable.getCardsOfRow().get(rowNumber - 1).getFirst();
        if (horn == null) toPrint.append("no horn in this row | ");
        else toPrint.append(horn.name()).append("|");

        //append all cards
        ArrayList<Card> cardsInRow = gameTable.getCardsOfRow().get(rowNumber - 1).getSecond();
        for (Card card : cardsInRow) {
            toPrint.append(card.name()).append("| ");
        }
        return new Result(true, toPrint.toString());
    }

    //spells in play
    public Result spellsInPlay() {
        StringBuilder toPrint = new StringBuilder();
        ArrayList<Card> spellCards = userTurn.getGameTable().getSpells();
        if (spellCards.isEmpty()) toPrint.append("No spell card");
        else {
            toPrint.append("| ");
            for (Card card : spellCards)
                toPrint.append(card.name()).append(" | ");
        }
        return new Result(true, toPrint.toString());
    }
    public Result placeCard(int cardNumber, int rowNumber) {
        return null;
    }

    public Result showCommander() {
        Leaders leaders = userTurn.getGameTable().getLeader();
        return new Result(true, leaders.name());
    }

    public Result commanderPowerPLay() {
        return null;
    }
    public Result showPlayersInfo(){
        StringBuilder info = new StringBuilder();
        info.append("player: ").append(player1.getUser().getUsername()).append(" Faction:")
                .append(player1.getUser().getUserPreGameInfo().getFaction()).append("\n");
        info.append("player: ").append(player2.getUser().getUsername()).append(" Faction:")
                .append(player2.getUser().getUserPreGameInfo().getFaction()).append("\n");
    }
    public Result showPlayersLives() {
        StringBuilder toPrint = new StringBuilder();
        toPrint.append(player1.getUser().getUsername()).append(" Hp :").append(player1.getGameTable().getHP()).append("\n");
        toPrint.append(player2.getUser().getUsername()).append(" Hp :").append(player2.getGameTable().getHP()).append("\n");
        return new Result(true, toPrint.toString());
    }

    public Result showNumberOfCardsInHand() {
        StringBuilder toPrint = new StringBuilder();
        toPrint.append("first player: ").append(player1.getUser().getUsername()).append(" number of cards in hand: ")
                .append(player1.getGameTable().getInHandsCards().size());
        toPrint.append("second player: ").append(player2.getUser().getUsername()).append(" number of cards in hand: ")
                .append(player2.getGameTable().getInHandsCards().size());
        return new Result(true, toPrint.toString());
    }

    public Result showTurnInfo() {
        return new Result(true, userTurn.getUser().getUsername() + "'s Turn");
    }

    public Result showTotalScore() {
        int firstPlayerTotalScore = calculateTotalScore(player1);
        int secondPlayerTotalScore = calculateTotalScore(player2);
    }
    public int calculateTotalScore(UserInGame player){
        int sum = 0;
        calculateScoreInRow(player, 1);
    }

    private void calculateScoreInRow(UserInGame player1, int i) {
    }

    public Result showTotalScoreOfRow(int rowNumber) {
        return null;
    }

    public Result passRound() {
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

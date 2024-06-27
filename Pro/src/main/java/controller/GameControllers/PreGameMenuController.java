package controller.GameControllers;

import model.User;
import model.UserPreGameInfo;
import model.enums.card.Card;
import model.enums.card.Leaders;
import model.enums.gameMenu.Factions;
import model.toolClasses.Pair;
import model.toolClasses.Result;

import java.util.ArrayList;

public class PreGameMenuController {
    public static User currentUser = User.getLoggedInUser();
    public static User opponentUser;

    // create a game
    public static Result createGame(String player2Username) {
        //check if player2Username is valid or not
        User secondUser = User.getLoggedInUser();
        if (secondUser == null)
            return new Result(false, "Second player username is invalid!");

        //check if current user wants to play with himself or not
        if (secondUser.getUsername().equals(currentUser.getUsername()))
            return new Result(false, "You can't play with yourself!");

        //run the game menu in view
        PreGameMenuController.opponentUser = secondUser;
        return new Result(true, "Game started");
    }

    private static boolean isUsernameValid(String username) {
        return true;
    }

    // show factions
    // it returns the faction that the user selected before
    public static Result showFactions(String playerNum) {
        User user;
        if (playerNum.equals("1"))
            user = User.getLoggedInUser();
        else user = opponentUser;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Monster\nEmpire Nilfgaardian\nRealms Northern\nScoiatael\nSkellige\n");
        stringBuilder.append("selected Faction: " + user.getUserPreGameInfo().getFaction().name + "\n");
        return new Result(true, stringBuilder.toString());
    }

    // select faction
    public static Result selectFaction(String factionName, String playerNum) {
        Factions factions = Factions.getFaction(factionName);
        if (factions == null)
            return new Result(false, "No faction found with this name!");


        //changing the faction
        if (playerNum.equals("1")) {
            User user = User.getLoggedInUser();
            UserPreGameInfo userPreGameInfo = user.getUserPreGameInfo();
            userPreGameInfo.setFaction(factions);
            user.setUserPreGameInfo(userPreGameInfo);
            User.setLoggedInUser(user);
        } else if (playerNum.equals("2")) {
            UserPreGameInfo userPreGameInfo = opponentUser.getUserPreGameInfo();
            userPreGameInfo.setFaction(factions);
            opponentUser.setUserPreGameInfo(userPreGameInfo);
        }

        return new Result(true, "Faction changed successfully!");
    }

    // show Cards
    public static Result showCards(String playerNum) {
        User user;
        if (playerNum.equals("1")) user = currentUser;
        else user = opponentUser;

        Factions factions = user.getUserPreGameInfo().getFaction();
        ArrayList<Pair<Card, Integer>> factionCards = factions.getDeepCopyOfArraylist();
        // printing all cards
        StringBuilder toPrint = new StringBuilder();
        for (int i = 0; i < factionCards.size(); i++) {
            Pair<Card, Integer> cardIntegerPair = factionCards.get(i);
            Card card = cardIntegerPair.getFirst();
            int numberOfCard = cardIntegerPair.getSecond();
            //Printing Number
            toPrint.append(card.getName()).append(":\n");

            //TYPE
            toPrint.append("Type").append(card.getType().name()).append("\n");

            //IS HERO OR NOT
            toPrint.append("Is Hero:").append(card.getIsHero()).append("\n");

            //PLACE IN BOARD
            toPrint.append("Place in board:").append(i + 1).append("\n");

            //ABILITY
            toPrint.append("Ability: ");
            String ability = (card.getAbility() == null) ? "no ability" : card.getAbility().name();
            toPrint.append(ability).append("\n");

            //NUMBER OF CARD IN DECK
            int numberOfThisCardInDeck = searchForCardInDeck(card, user);
            toPrint.append("Number Of Cards in Deck: ").append(numberOfCard).append("\n");

            //NUMBER OF TOTAL CARDS
            toPrint.append("Number Of Cards to Pick: ").append(card.getNumberOfCardInGame() - numberOfCard).append("\n");

            //CARD POWER
            toPrint.append("Card Power: ").append(card.getPower()).append("\n");
        }
        return new Result(true, toPrint.toString());
    }

    private static int searchForCardInDeck(Card card, User user) {
        int n = 0;
        UserPreGameInfo userPreGameInfo = user.getUserPreGameInfo();
        ArrayList<Pair<Card, Integer>> deck = userPreGameInfo.getCardsInDeck();
        for (Pair<Card, Integer> cardInDeck : deck) {
            if (cardInDeck.getFirst().equals(card)) {
                n = cardInDeck.getSecond();
                break;
            }
        }
        return n;
    }


    // show information current user
    public static Result showInfoCurrentUser(String playerNum) {
        //set the user we want to print its information
        User user;
        if (playerNum.equals("1")) user = currentUser;
        else user = opponentUser;

        StringBuilder information = new StringBuilder();

        //NAME
        information.append("Username: ").append(user.getUsername()).append("\n");
        //FACTION NAME
        information.append("Faction: ").append(user.getUserPreGameInfo().getFaction().name).append("\n");
        //NUMBER OF CARDS IN DECK
        int numberOfCardsInDeck = calculateNumberOfCardsInDeck(user);
        information.append("Number of cards in deck:").append(numberOfCardsInDeck).append("\n");


        //TODO Soldier cards
        //TODO Special cards

        //HERO CARDS
        int heroCards = calculateNumberOfHerosInDeck(user);
        information.append("Number of Hero Cards in deck: ").append(heroCards).append("\n");

        //Power of cards
        int powerSum = calculatePowerOfAllCardsInDeck(user);
        information.append("Power Of Cards in Deck: ").append(powerSum).append("\n");
        return new Result(true, information.toString());
    }

    private static int calculatePowerOfAllCardsInDeck(User user) {
        int powerSum = 0;
        for (Pair<Card, Integer> pair : user.getUserPreGameInfo().getCardsInDeck()) {
            powerSum += pair.getSecond() * pair.getFirst().getPower();
        }
        return powerSum;
    }

    private static int calculateNumberOfHerosInDeck(User user) {
        int n = 0;
        for (Pair<Card, Integer> pair : user.getUserPreGameInfo().getCardsInDeck()) {
            if (pair.getFirst().getIsHero()) n += pair.getSecond();
        }

    }

    private static int calculateNumberOfCardsInDeck(User user) {
        int n = 0;
        for (Pair<Card, Integer> pair : user.getUserPreGameInfo().getCardsInDeck()) {
            n += pair.getSecond();
        }
        return n;
    }

    // save deck
    public static Result saveDeckWithName(String address) {
        return null;
    }

    public static Result saveDeckWithAddress(String address) {
        return null;
    }

    private static boolean isAddressAlreadyUsed(String address) {
        return true;
    }

    private static boolean isSavedSuccessfully(String address) {
        return true;
    }

    // load deck
    public static Result loadDeckWithAddress(String address) {
        return null;
    }

    public static Result loadDeckWithName(String address) {
        return null;
    }

    private static boolean isAddressValid(String address) {
        return true;
    }

    private static boolean checkValidationOfFileContext(String fileAddress) {
        return true;
    }

    // leader commands
    public static Result showLeaders(String playerNum) {
        //Set user
        User user;
        if (playerNum.equals("1")) user = currentUser;
        else user = opponentUser;

        StringBuilder toPrint = new StringBuilder();
        int n = 1;

        for (Leaders leaders : Leaders.values()) {
            if (leaders.getFaction().equals(user.getUserPreGameInfo().getFaction())) {
                toPrint.append(n).append("- ").append(leaders.name());
                if (user.getUserPreGameInfo().getLeader().equals(leaders))
                    toPrint.append(" : Selected Leader\n");
                else toPrint.append("\n");
            }
        }

        return new Result(true, toPrint.toString());
    }

    public static Result selectLeader(int leaderNumber, String playerNum) {
        User user;
        if (playerNum.equals("1")) user = currentUser;
        else user = opponentUser;

        ArrayList<Leaders> leaders = new ArrayList<>();
        for (Leaders leader : Leaders.values()) {
            if (leader.getFaction().equals(user.getUserPreGameInfo().getFaction())) {
                leaders.add(leader);
            }
        }

        //check if number is valid
        if (leaderNumber < 0 || leaderNumber > leaders.size() + 1)
            return new Result(false, "invalid Leader Number");


        UserPreGameInfo userPreGameInfo = user.getUserPreGameInfo();
        // leaderNumber - 1 because the leaderNumber that passed to this function is 1 based
        userPreGameInfo.setLeader(leaders.get(leaderNumber - 1));
        user.setUserPreGameInfo(userPreGameInfo);

        return new Result(true, "Leader " + user.getUserPreGameInfo().getLeader().name() +
                " set for player " + user.getUsername());
    }

    // add to deck
    public static Result addToDeck(String cardName, int count) {
        return null;
    }

    private static boolean isCountValid(int count) {
        return true;
    }

    private static boolean isCardNameValid(String cardName) {
        return true;
    }

    private static boolean checkNumberOfCardInDeck() {
        return true;
    } // TODO: ???

    private static boolean checkSpecialCardInDeck() {
        return true;
    }

    // delete card from deck
    public static Result deleteFromDeck(String cardName, int count) {
        return null;
    }

    // change turn
    public static Result changeTurn() {
        return null;
    }

    private static boolean checkDeckIsOk() {
        return true;
    }


    // start game
    public static Result startGame() {
        return null;
    }

    public static Result showDeck(String playerNum) {
        User user;
        if (playerNum.equals("1")) user = currentUser;
        else user = opponentUser;

        ArrayList<Pair<Card, Integer>> deckCards = user.getUserPreGameInfo().getCardsInDeck();
        StringBuilder cards = new StringBuilder();

        for (Pair<Card, Integer> cardIntegerPair : deckCards) {
            cards.append(cardIntegerPair.getFirst().name()).append(" ").append(cardIntegerPair.getSecond()).append("\n");
        }

        return new Result(true, cards.toString());

    }

}

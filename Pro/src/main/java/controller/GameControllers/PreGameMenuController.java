package controller.GameControllers;

import model.User;
import model.UserPreGameInfo;
import model.enums.card.Card;
import model.enums.gameMenu.Factions;
import model.toolClasses.Pair;
import model.toolClasses.Result;
import view.GameMenu.PreGameMenu;

import java.util.ArrayList;
import java.util.Scanner;

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
            toPrint.append(card)
        }
        return null; // todo
    }


    // show information current user
    public static Result showInfoCurrentUser() {
        return null;
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
    public static Result showLeaders() {
        return null;
    }

    public static Result selectLeader(int leaderNumber) {
        return null;
    }

    private static boolean isLeaderNumValid(int leaderNumber) {
        return true;
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

}

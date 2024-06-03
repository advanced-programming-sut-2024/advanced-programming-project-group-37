package controller.GameControllers;

import model.User;
import model.toolClasses.Result;

public class PreGameMenuController {
    public static User currentUser = User.getLoggedInUser();
    // create a game
    public static Result createGame(String player2Username) {
        return new Result(true, "");
    }
    private static boolean isUsernameValid(String username) {
        return true;
    }

    // show factions
    public static Result showFactions() {
        return new Result(true, "");
    }

    // select faction
    public static Result selectFaction(String factionName) {
        return null;
    }

    // show Cards
    public static Result showCards() {
        return null;
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
    private static boolean checkValidationOfFileContext(String fileAddress){
        return true;
    }

    // leader commands
    public static Result showLeaders(){
        return null;
    }
    public static Result selectLeader(int leaderNumber){
        return null;
    }
    private static boolean isLeaderNumValid(int leaderNumber) {
        return true;
    }

    // add to deck
    public static Result addToDeck(String cardName, int count){
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
    public static Result deleteFromDeck(String cardName, int count){
        return null;
    }

    // change turn
    public static Result changeTurn(){
        return null;
    }
    private static boolean checkDeckIsOk() {
        return true;
    }


    // start game
    public static Result startGame(){
        return null;
    }

}

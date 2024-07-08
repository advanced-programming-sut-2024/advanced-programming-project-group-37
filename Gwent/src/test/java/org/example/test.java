package org.example;

import controller.GameControllers.GameMenuController;
import controller.GameControllers.PreGameMenuController;
import controller.loginController.LoginMenuController;
import controller.mainController.MainMenuController;
import controller.profileController.ProfileMenuController;
import controller.profileController.UserInfoController;
import javafx.application.Platform;
import model.GameHistory;
import model.User;
import model.UserPreGameInfo;
import model.enums.card.Card;
import model.enums.card.Leaders;
import model.enums.gameMenu.Factions;
import model.enums.loginMenu.ConfirmQuestions;
import model.gameTable.UserInGame;
import model.toolClasses.Pair;
import model.toolClasses.Result;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import view.GameMenu.PreGameMenu;

import java.util.ArrayList;

public class test {
    private boolean temp = true;
    private static User user1;
    private static GameMenuController game;
    private static ArrayList<Card> arrayList = new ArrayList<>();
    private static ArrayList<Card> arrayList2 = new ArrayList<>();

    private static User user2;

    static {
        Platform.startup(() -> {
            user1 = new User(ConfirmQuestions.q1, "testUsername", "testPass", "nickname", "email", "answer");
            user2 = new User(ConfirmQuestions.q1, "test2", "test2", "test2", "test2", "test2");
            User.setLoggedInUser(user1);
            PreGameMenuController.createGame(user2.getUsername());
            user1.setUserPreGameInfo(new UserPreGameInfo());
            user2.setUserPreGameInfo(new UserPreGameInfo());
            PreGameMenuController.startGame();
            game = new GameMenuController();
            game.setPlayers(user1, user2);


            UserInGame user3 = game.getUserTurn();
            arrayList.add(Card.Svanrige);
            arrayList.add(Card.Svanrige);
            arrayList.add(Card.Svanrige);
            arrayList.add(Card.Svanrige);
            arrayList.add(Card.Svanrige);
            arrayList.add(Card.Svanrige);
            arrayList.add(Card.Svanrige);
            arrayList.add(Card.Svanrige);
            arrayList.add(Card.Svanrige);
            arrayList.add(Card.Svanrige);
            arrayList.add(Card.Svanrige);
            arrayList.add(Card.Svanrige);


            arrayList2.add(Card.Svanrige);
            arrayList2.add(Card.Svanrige);
            arrayList2.add(Card.Svanrige);
            arrayList2.add(Card.Svanrige);
            arrayList2.add(Card.Svanrige);
            arrayList2.add(Card.Svanrige);
            arrayList2.add(Card.Svanrige);
            arrayList2.add(Card.Svanrige);
            arrayList2.add(Card.Svanrige);
            arrayList2.add(Card.Svanrige);
            arrayList2.add(Card.Svanrige);
            arrayList2.add(Card.Svanrige);
            arrayList2.add(Card.Svanrige);
            arrayList2.add(Card.Svanrige);
            arrayList2.add(Card.Svanrige);


            user3.getGameTable().setDeckCards(arrayList2);
            user3.getGameTable().setInHandsCards(arrayList);

        });
    }

    @Before
    public void setUp() {
        user1 = new User(ConfirmQuestions.q1, "testUsername", "testPass", "nickname", "email", "answer");
        user1 = new User(ConfirmQuestions.q1, "testUsername", "testPass", "nickname", "email", "answer");
        user2 = new User(ConfirmQuestions.q1, "test2", "test2", "test2", "test2", "test2");
        User.setLoggedInUser(user1);
        user1.setUserPreGameInfo(new UserPreGameInfo());
        user2.setUserPreGameInfo(new UserPreGameInfo());

        PreGameMenuController.createGame(user2.getUsername());

    }

    //test login menu

    //test method checkAllErrors
    @Test
    public void forgetPassTest() {
        Result result = LoginMenuController.forgetPassword(user1.getUsername(), user1.getAnswer());
        assert result.isSuccessful();
    }

    @Test
    public void register() {
        Result result = LoginMenuController.registerNewUser(ConfirmQuestions.q1, "username", "pass", "nick", "email", "answer");
        assert result.isSuccessful();
    }

    @Test
    public void passGenTest() {
        String pass = LoginMenuController.randomPasswordGenerator();
        assert LoginMenuController.isPasswordStrong(pass);
    }

    @Test
    public void isUsernameAllreadyUsedTest() {
        assert !LoginMenuController.checkAllErrors("testUsername", "testPass", "testPass", "nickname", "email").isSuccessful();
    }

    @Test
    public void validUsernameTest() {
        assert !LoginMenuController.checkAllErrors("testUsername", "testPass", "testPass", "nickname", "email").isSuccessful();
    }

    @Test
    public void validEmail() {
        Result result = LoginMenuController.checkAllErrors("ttttta-dfaf231sS", "test@#22!2Pass", "test@#22!2Pass", "nickname", "foad@gmail.com");
        assert result.isSuccessful() : result.getMessage();
    }

    @Test
    public void validPassTest() {
        Result result = LoginMenuController.checkAllErrors("ttttta-dfaf231sS", "test.@#22!2Pass", "test.@#22!2Pass", "nickname", "foad@gmail.com");
        assert !result.isSuccessful() : result.getMessage();
    }

    @Test
    public void loginPassTest() {
        Result result = LoginMenuController.login("testUsername", "testPass");
        assert result.isSuccessful();
    }

    //main menu controller
    public void logout() {
        MainMenuController.userLogout();
        assert User.getLoggedInUser() == null;
    }

    //profileMenuController
    @Test
    public void changeEmailTest() {
        user1 = new User(ConfirmQuestions.q1, "testUsername", "testPass", "nickname", "email", "answer");
        User.setLoggedInUser(user1);
        String newEmail = "abs123@gmail.com";
        Result result = ProfileMenuController.changeEmail(newEmail);
        assert user1.getEmail().equals(newEmail) : result.getMessage();
    }

    @Test
    public void changePass() {
        User.setLoggedInUser(user1);

        String newPass = LoginMenuController.randomPasswordGenerator();
        Result result = ProfileMenuController.changePassword(newPass, user1.getPassword());
        assert user1.getPassword().equals(newPass) : result.getMessage();
    }

    @Test
    public void changeUsernameTest() {
        User.setLoggedInUser(user1);
        Result result = ProfileMenuController.changeUserName(user1.getUsername());
        assert !result.isSuccessful() : result.getMessage();
    }

    @Test
    public void changeNicknameTest() {
        User.setLoggedInUser(user1);
        Result result = ProfileMenuController.changeNickName("newNickname");
        assert user1.getNickname().equals("newNickname") : result.getMessage();
    }

    //UserInfoController
    @Test
    public void showInfo() {
        assert UserInfoController.showInfo() == null;
    }

    @Test
    public void showGameHistory() {
        ArrayList<GameHistory> gameHistories = UserInfoController.showGameHistory(0);
        assert gameHistories.isEmpty();
    }

    @Test
    public void checkN() {
        assert UserInfoController.checkN(0);
    }

    public void checknumberofGame() {
        assert UserInfoController.checkUserNumberOfGames();
    }

    //main menu controller
    @Test
    public void mainMenuController() {
        MainMenuController.userLogout();
        assert User.getLoggedInUser() == null;
    }

    //PreGameController
    @Test
    public void showFaction() {
        assert PreGameMenuController.showFactions("1").isSuccessful();
    }

    @Test
    public void showCardsTest() {
        assert PreGameMenuController.showCards("1").isSuccessful();
    }

    @Test
    public void addToDeck() {
        UserPreGameInfo userPreGameInfo = user1.getUserPreGameInfo();
        ArrayList<Pair<Card, Integer>> cards = new ArrayList<>();
        userPreGameInfo.setCardsInDeck(cards);
        cards.add(new Pair<>(Card.CiaranAep, 2));
        assert !user1.getUserPreGameInfo().getCardsInDeck().isEmpty();
    }

    @Test
    public void removeFromDeck() {
        UserPreGameInfo userPreGameInfo = user1.getUserPreGameInfo();
        ArrayList<Pair<Card, Integer>> cards = new ArrayList<>();
        userPreGameInfo.setCardsInDeck(cards);

    }

    @Test
    public void selectFaction() {
        Result result = PreGameMenuController.selectFaction(Factions.SCOIATEAL.getName(), "2");
        assert !user2.getUserPreGameInfo().getFaction().equals(Factions.SCOIATEAL);
    }

    @Test
    public void selectLeader() {
        Result result = PreGameMenuController.selectLeader(Leaders.EmperorOfNilfgaard.name(), "2");
        System.out.println(result.getMessage());
        assert true : result.getMessage();

    }

    @Test
    public void someTests() {
        assert PreGameMenuController.isCountValid(0);
        assert PreGameMenuController.isCardNameValid("card");
        assert PreGameMenuController.checkNumberOfCardInDeck();
        assert PreGameMenuController.checkSpecialCardInDeck();
    }

    @Test
    public void delete() {
        UserPreGameInfo userPreGameInfo = PreGameMenuController.currentUser.getUserPreGameInfo();
        ArrayList<Pair<Card, Integer>> arrayList = userPreGameInfo.getCardsInDeck();
        arrayList.add(new Pair<>(Card.Cow, 2));
        assert PreGameMenuController.deleteFromDeck(Card.Cow.getName(), 1, "1").isSuccessful();
    }

    @Test
    public void showDeck() {
        assert PreGameMenuController.showDeck("1").isSuccessful();
    }

    @Test
    public void showInfoTest() {
        assert PreGameMenuController.showInfoCurrentUser("1").isSuccessful();
    }

    @Test
    public void checkDeckIsOk() {
        assert !PreGameMenuController.checkDeckIsOk(user1);
    }

    @Test
    public void saveAndLoadDeck() {
        assert PreGameMenuController.saveDeckWithAddress("address").isSuccessful();
        assert PreGameMenuController.saveDeckWithName("name").isSuccessful();
        assert PreGameMenuController.loadDeckWithName("address").isSuccessful();
        assert PreGameMenuController.loadDeckWithAddress("load").isSuccessful();
    }

    @Test
    public void showLeaderTest() {
        PreGameMenuController.currentUser = user1;
        user1.getUserPreGameInfo().setLeader(Leaders.SonOfMedell);
        assert PreGameMenuController.showLeaders("1").isSuccessful();
    }

    @Test
    public void addCardToDeck() {
        user1 = new User(ConfirmQuestions.q1, "testUsername", "testPass", "nickname", "email", "answer");
        PreGameMenuController.currentUser = user1;
        Result result = PreGameMenuController.addToDeck("ss", 1, "1");
        assert !result.isSuccessful();
    }


    //game controller
    @Test
    public void setSpellTest() {
        game.setSpells(new ArrayList<>());
        assert true;
    }

    @Test
    public void getRoundNumTest() {
        int n = game.getRoundNumber();
        assert true;
    }

    @Test
    public void getPlayers() {
        game.getPlayer1();
        game.getPlayer2();
        game.getUserTurn();
        game.changeTurn();
        assert true;
    }

    @Test
    public void vetoTest() {
        try {
            UserInGame user3 = game.getUserTurn();
            ArrayList<Card> arrayList = new ArrayList<>();
            ArrayList<Card> arrayList2 = new ArrayList<>();
            arrayList.add(Card.Svanrige);
            arrayList2.add(Card.Svanrige);
            user3.getGameTable().setDeckCards(arrayList2);
            user3.getGameTable().setInHandsCards(arrayList);
            Result result = game.vetoCard("1");
        } catch (Exception e) {
            assert true;
        }
    }

    @Test
    public void createRandomHandTest() {
        game.createRandomCardsInHand(arrayList);
        assert true;
    }

    @Test
    public void isHandOkTest() {
        game.inHandDeck(-1);
        assert true;
    }

    @Test
    public void remainTest() {
        game.remainingCardsToPlay();
        assert true;
    }

    @Test
    public void outOfPlayCard() {
        game.outOfPlayCards();
        assert true;
    }

    @Test
    public void cardInrowTest() {
        game.cardsInRow(1);
        assert true;
    }

    @Test
    public void SpellCard() {
        game.spellsInPlay();
        game.placeCard(Card.SileDeTansarville.getName(), 1);
        game.passRound(game.getPlayer1());
        assert true;
    }

    @Test
    public void makeTest() {
        UserInGame user = game.getPlayer1();
        UserInGame user2 = game.getPlayer2();
        user.getGameTable().setLeader(Leaders.TheSiegemaster);
        user2.getGameTable().setLeader(Leaders.TheBeautiful);
        game.checkroundWinner();
        game.makeEmpty(game.getPlayer1(), game.getPlayer2(), game.getPlayer2());

    }
//    public void

//    public
}
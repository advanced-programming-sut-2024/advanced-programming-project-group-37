package org.example;

import controller.GameControllers.PreGameMenuController;
import controller.loginController.LoginMenuController;
import controller.profileController.ProfileMenuController;
import javafx.application.Platform;
import model.User;
import model.UserPreGameInfo;
import model.enums.card.Card;
import model.enums.gameMenu.Factions;
import model.enums.loginMenu.ConfirmQuestions;
import model.toolClasses.Pair;
import model.toolClasses.Result;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class test {
    private boolean temp = true;
    private static User user1;
    private static User user2;

    static {
        Platform.startup(() -> {
            user1 = new User(ConfirmQuestions.q1, "testUsername", "testPass", "nickname", "email", "answer");
            user2 = new User(ConfirmQuestions.q1, "test2", "test2", "test2", "test2", "test2");
            User.setLoggedInUser(user1);
            PreGameMenuController.createGame(user2.getUsername());
            user1.setUserPreGameInfo(new UserPreGameInfo());
            UserPreGameInfo userPreGameInfo = user1.getUserPreGameInfo();
            ArrayList<Pair<Card, Integer>> cards = new ArrayList<>();
            userPreGameInfo.setCardsInDeck(cards);
        });
    }

    @Before
    public void setUp() {

        user1 = new User(ConfirmQuestions.q1, "testUsername", "testPass", "nickname", "email", "answer");
    }

    //test login menu

    //test method checkAllErrors
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

    //profileMenuController
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
    public void selectFaction(){
        PreGameMenuController.selectFaction(Factions.SCOIATEAL.getName(), "1")
    }
}
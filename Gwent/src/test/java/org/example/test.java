package org.example;

import controller.loginController.LoginMenuController;
import controller.profileController.ProfileMenuController;
import controller.profileController.UserInfoController;
import javafx.application.Platform;
import model.User;
import model.UserPreGameInfo;
import model.enums.card.Card;
import model.enums.loginMenu.ConfirmQuestions;
import model.toolClasses.Pair;
import model.toolClasses.Result;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class test {
    private boolean temp = true;
    private static User user;

    static {
        Platform.startup(() -> {
            user = new User(ConfirmQuestions.q1, "testUsername", "testPass", "nickname", "email", "answer");
            user.setUserPreGameInfo(new UserPreGameInfo());
            UserPreGameInfo userPreGameInfo = user.getUserPreGameInfo();
            ArrayList<Pair<Card,Integer>> cards = new ArrayList<>();
            userPreGameInfo.setCardsInDeck(cards);
        });
    }
    @Before
    public void setUp() {

            user = new User(ConfirmQuestions.q1, "testUsername", "testPass", "nickname", "email", "answer");
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
    public void validEmail(){
        Result result = LoginMenuController.checkAllErrors("ttttta-dfaf231sS", "test@#22!2Pass", "test@#22!2Pass", "nickname", "foad@gmail.com");
        assert result.isSuccessful() : result.getMessage();
    }
    @Test
    public void validPassTest(){
        Result result = LoginMenuController.checkAllErrors("ttttta-dfaf231sS", "test.@#22!2Pass", "test.@#22!2Pass", "nickname", "foad@gmail.com");
        assert !result.isSuccessful() : result.getMessage();
    }
    @Test
    public void loginPassTest(){
        Result result = LoginMenuController.login("testUsername", "testPass");
        assert result.isSuccessful();
    }
    //profileMenuController
    @Test
    public void changeUsernameTest(){
        User.setLoggedInUser(user);
        Result result = ProfileMenuController.changeUserName(user.getUsername());
        assert !result.isSuccessful() : result.getMessage();
    }
    @Test
    public void changeNicknameTest(){
        User.setLoggedInUser(user);
        Result result = ProfileMenuController.changeNickName("newNickname");
        assert user.getNickname().equals("newNickname") :result.getMessage();
    }


}
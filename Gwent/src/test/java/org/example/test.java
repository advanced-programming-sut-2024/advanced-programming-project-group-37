package org.example;

import controller.loginController.LoginMenuController;
import javafx.application.Platform;
import model.User;
import model.enums.loginMenu.ConfirmQuestions;
import org.junit.Before;
import org.junit.Test;

public class test {
    private boolean temp = true;

    @Before
    public void setUp() {

        if (temp) {
            Platform.startup(() -> {
                User user = new User(ConfirmQuestions.q1, "testUsername", "testPass", "nickname", "email", "answer");
                temp = false;
            });
        } else {
            User user = new User(ConfirmQuestions.q1, "testUsername", "testPass", "nickname", "email", "answer");
        }
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
}
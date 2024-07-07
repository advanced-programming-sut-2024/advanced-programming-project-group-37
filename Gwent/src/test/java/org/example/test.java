package org.example;

import model.User;
import model.enums.loginMenu.ConfirmQuestions;
import org.junit.Before;

class test{
    @Before
    public void setUp() {
        User user = new User(ConfirmQuestions.q1, "testUsername", "testPass", "nickname", "email" , "answer");
    }
}
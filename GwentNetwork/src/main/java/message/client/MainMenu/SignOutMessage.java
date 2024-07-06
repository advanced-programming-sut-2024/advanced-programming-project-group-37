package message.client.MainMenu;

import message.client.ClientMessage;

/**
 * @author iliya
 *
 * this class is for log out in main menu
 *
 * you can find user by token and if it's equal with loggedin user signout
 * */
public class SignOutMessage extends ClientMessage {
    public SignOutMessage(String token) {
        this.token = token;
    }
}

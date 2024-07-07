package message.client.profileMenu;

import message.client.ClientMessage;

/**
 * @author  iliya
 *
 * you have to geve me ArrayList<String> friend, Arraylist<String> friendRequest
 */
public class GiveFriendMessage extends ClientMessage {
    public GiveFriendMessage(String token) {
        this.token = token;
    }
}
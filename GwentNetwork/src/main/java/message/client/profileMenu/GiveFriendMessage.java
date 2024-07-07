package message.client.profileMenu;

import message.client.ClientMessage;
import message.client.MessageType;

/**
 * @author  iliya
 *
 * you have to geve me ArrayList<String> friend, Arraylist<String> friendRequest
 */
public class GiveFriendMessage extends ClientMessage {
    public GiveFriendMessage(String token) {
        this.type = MessageType.GIVE_FRIEND;
        this.token = token;
    }
}
package message.client.profileMenu;

import message.client.ClientMessage;
import message.client.MessageType;

/**
 * @author iliya
 *
 * for this message you have to add user that I pass username
 * to friend and, remove him/her from requests
 */
public class AcceptRequest extends ClientMessage {
    String username;

    public AcceptRequest(String token, String username) {
        this.token = token;
        this.type = MessageType.ACCEPT_REQUEST;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

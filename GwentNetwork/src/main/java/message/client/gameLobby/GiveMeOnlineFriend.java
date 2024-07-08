package message.client.gameLobby;

import message.client.ClientMessage;
import message.client.MessageType;

/**
 * @author iliya
 * give me online friend in <friends> arralist
 */
public class GiveMeOnlineFriend extends ClientMessage {
    public GiveMeOnlineFriend(String token) {
        this.token = token;
        this.type = MessageType.GEVE_ONLINE_FRIEND;
    }
}

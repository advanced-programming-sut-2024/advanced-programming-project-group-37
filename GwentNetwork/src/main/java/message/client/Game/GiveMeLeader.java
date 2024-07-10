package message.client.Game;

import message.client.ClientMessage;
import message.client.MessageType;

/**
 * @author iliya
 *
 * give me leader with token --> object of leader
 */
public class GiveMeLeader extends ClientMessage {

    public GiveMeLeader(String token) {
        this.token = token;
        this.type = MessageType.GET_LEADER;
    }
}

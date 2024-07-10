package message.client.Game;

import message.client.ClientMessage;
import message.client.MessageType;

/**
 * give me hand of this player
 */
public class GetHand extends ClientMessage {

    public GetHand(String token) {
        this.token = token;
        this.type = MessageType.GET_HAND;
    }
}

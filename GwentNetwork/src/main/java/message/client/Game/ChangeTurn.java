package message.client.Game;

import message.client.ClientMessage;
import message.client.MessageType;

public class ChangeTurn extends ClientMessage {
    public ChangeTurn(String token) {
        this.token = token;
        this.type = MessageType.CHANGE_TURN;
    }
}

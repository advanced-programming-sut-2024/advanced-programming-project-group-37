package message.client.Game;

import message.client.ClientMessage;
import message.client.MessageType;

public class WhoseTrun extends ClientMessage {
    public WhoseTrun(String token) {
        this.type = MessageType.TURN_CHECKING;
        this.token = token;
    }
}

package message.client.Game;

import message.client.ClientMessage;
import message.client.MessageType;

public class Reaction extends ClientMessage {

    private String message;
    public Reaction(String token, String message) {
        this.type = MessageType.REACTION;
        this.token = token;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

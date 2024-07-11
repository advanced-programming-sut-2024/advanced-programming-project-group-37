package message.client.Game;

import message.client.ClientMessage;
import message.client.MessageType;

public class SendMessageFromGame extends ClientMessage {

    private String message;
    public SendMessageFromGame(String token, String message) {
        this.token = token;
        this.message = message;
        this.type = MessageType.SEND_MESSAGE_FROM_GAME;
    }

    public String getMessage() {
        return message;
    }
}

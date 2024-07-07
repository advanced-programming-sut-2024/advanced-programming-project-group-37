package message.client.gameLobby;

import message.client.ClientMessage;
import message.client.MessageType;

/**
 * @author iliya
 *
 * this method is message for check server that have any message
 */
public class CheckServerMessage extends ClientMessage {

    public CheckServerMessage(String token) {
        this.token = token;
        this.type = MessageType.CHECK_SERVER;
    }
}

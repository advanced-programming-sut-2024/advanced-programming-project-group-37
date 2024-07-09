package message.client.pregame;

import message.client.ClientMessage;
import message.client.MessageType;

/**
 * @author iliya
 *
 * if ture --> success = true else success = false
 */
public class CheckDeckIsOk extends ClientMessage {
    public CheckDeckIsOk(String token) {
        this.token = token;
        this.type = MessageType.IS_DECK_OK;
    }
}

package message.client.pregame;

import message.client.ClientMessage;

public class getFactionMessage extends ClientMessage {

    public getFactionMessage(String token) {
        this.token = token;
    }
}

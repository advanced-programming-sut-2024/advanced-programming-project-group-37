package message.client.gameLobby;

import message.client.ClientMessage;
import message.client.MessageType;

public class EnterGame extends ClientMessage {
    public EnterGame (String token) {
        this.token = token;
        this.type = MessageType.ENTER_GAME;
    }
}

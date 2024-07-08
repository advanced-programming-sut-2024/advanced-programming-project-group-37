package message.client.gameLobby;

import message.client.ClientMessage;
import message.client.MessageType;

public class EnterGameLobby extends ClientMessage {
    public EnterGameLobby(String token) {
        this.token = token;
        this.type = MessageType.ENTER_GAMELOBBY;
    }
}

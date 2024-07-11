package message.client.gameLobby;

import message.client.ClientMessage;
import message.client.MessageType;

public class LiveGame extends ClientMessage {

    private String username;
    public LiveGame(String token, String username) {
        this.token = token;
        this.username = username;
        this.type = MessageType.LIVE_GAME;
    }

    public String getUsername() {
        return username;
    }
}

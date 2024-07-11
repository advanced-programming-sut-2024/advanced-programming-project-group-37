package message.client.gameLobby;

import message.client.ClientMessage;

public class SendMessageFromTvToPlayers extends ClientMessage {

    private String username;

    public SendMessageFromTvToPlayers(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

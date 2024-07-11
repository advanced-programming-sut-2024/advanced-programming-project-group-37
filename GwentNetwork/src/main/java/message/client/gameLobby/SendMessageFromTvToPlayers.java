package message.client.gameLobby;

import message.client.ClientMessage;
import message.client.MessageType;

public class SendMessageFromTvToPlayers extends ClientMessage {

    private String m;

    public SendMessageFromTvToPlayers(String token, String m) {
        this.m = m;
        this.token = token;
        this.type = MessageType.SEND_MESSAGE_FROM_TV;
    }

    public String getM() {
        return m;
    }
}

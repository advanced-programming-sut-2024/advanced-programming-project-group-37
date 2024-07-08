package message.client.gameLobby;

import message.client.ClientMessage;
import message.client.MessageType;

public class AcceptFriendRequest extends ClientMessage {

    private final String username;

    public AcceptFriendRequest(String token, String username) {
        this.username = username;
        this.token = token;
        this.type = MessageType.ACCEPT_FRIEND_REQUEST;
    }

    public String getUsername() {
        return username;
    }
}

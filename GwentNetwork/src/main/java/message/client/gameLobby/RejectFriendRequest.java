package message.client.gameLobby;

import message.client.ClientMessage;
import message.client.MessageType;

public class RejectFriendRequest extends ClientMessage {
    private final String username;

    public RejectFriendRequest(String token, String username) {
        this.username = username;
        this.token = token;
        this.type = MessageType.REJECT_FRIEND_REQUEST;
    }

    public String getUsername() {
        return username;
    }
}

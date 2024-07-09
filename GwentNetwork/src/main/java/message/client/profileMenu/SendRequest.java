package message.client.profileMenu;

import message.client.ClientMessage;
import message.client.MessageType;

public class SendRequest extends ClientMessage {

    private String username;

    public SendRequest(String token, String username) {
        this.type = MessageType.SEND_FRIEND_REQUEST;
        this.username = username;
        this.token = token;
    }
    public String getUsername() {
        return username;
    }
}

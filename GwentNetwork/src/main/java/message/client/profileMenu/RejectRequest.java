package message.client.profileMenu;

import message.client.ClientMessage;
import message.client.MessageType;

public class RejectRequest extends ClientMessage {

    String username;

    public RejectRequest(String token, String username) {
        this.token = token;
        this.username = username;
        this.type = MessageType.REJECT_REQUEST;
    }

    public String getUsername() {
        return username;
    }
}

package message.client.profileMenu;

import message.client.ClientMessage;

public class RejectRequest extends ClientMessage {

    String username;

    public RejectRequest(String token, String username) {
        this.token = token;
        this.username = username;
    }
}

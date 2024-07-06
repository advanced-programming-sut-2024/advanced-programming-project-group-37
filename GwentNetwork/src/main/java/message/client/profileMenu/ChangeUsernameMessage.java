package message.client.profileMenu;

import message.client.ClientMessage;
import message.client.MessageType;

public class ChangeUsernameMessage extends ClientMessage {
    String newUsername;

    public ChangeUsernameMessage(String newUsername, String token) {
        this.token = token;
        this.type = MessageType.CHANGE_USERNAME;
        this.newUsername = newUsername;
    }

    public String getNewUsername() {
        return newUsername;
    }
}

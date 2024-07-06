package message.client.profileMenu;

import message.client.ClientMessage;
import message.client.MessageType;

public class ChangeEmailMessage extends ClientMessage {
    String newEmail;

    public ChangeEmailMessage(String newEmail, String token) {
        this.newEmail = newEmail;
        this.token = token;
        this.type = MessageType.CHANGE_EMAIL;
    }

    public String getNewEmail() {
        return newEmail;
    }
}

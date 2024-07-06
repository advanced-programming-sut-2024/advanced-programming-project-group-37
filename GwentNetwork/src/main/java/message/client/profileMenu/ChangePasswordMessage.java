package message.client.profileMenu;

import message.client.ClientMessage;
import message.client.MessageType;

public class ChangePasswordMessage extends ClientMessage {
    String newPassword;
    String oldPassword;

    public ChangePasswordMessage(String newPassword, String oldPassword, String token) {
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
        this.token = token;
        this.type = MessageType.CHANGE_PASSWORD;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }
}

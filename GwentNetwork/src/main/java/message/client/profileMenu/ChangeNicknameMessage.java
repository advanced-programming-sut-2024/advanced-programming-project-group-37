package message.client.profileMenu;

import message.client.ClientMessage;
import message.client.MessageType;

public class ChangeNicknameMessage extends ClientMessage {
    String newNickname;

    public ChangeNicknameMessage(String newNickname, String token) {
        this.token = token;
        this.type = MessageType.CHANGE_USERNAME;
        this.newNickname = newNickname;
    }

    public String getNewUsername() {
        return newNickname;
    }
}

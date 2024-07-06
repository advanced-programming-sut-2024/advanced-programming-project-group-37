package message.client.profileMenu;

import message.client.ClientMessage;
import message.client.MessageType;

public class ChangeNicknameMessage extends ClientMessage {
    String newNickname;

    public ChangeNicknameMessage(String newNickname, String token) {
        this.token = token;
        this.type = MessageType.CHANGE_NICKNAME;
        this.newNickname = newNickname;
    }

    public String getNewNickname() {
        return newNickname;
    }
}
